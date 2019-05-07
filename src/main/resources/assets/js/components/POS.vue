<template>
    <div class="d-flex">
        <div class="pos-sidebar bg-light p-4 border-right">

            <table class="table mb-0">
                <thead>
                    <tr>
                        <th scope="col">Product</th>
                        <th scope="col">Price</th>
                        <th scope="col">Qty</th>
                        <th scope="col">Subtotal</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="item in cartItems" :key="item.id">
                        <th scope="row">{{ item.name }}</th>
                        <td class="text-right">{{ item.price | centsToDollars }}</td>
                        <td>
                            {{ item.count }} <a href="#" @click="removeFromCart(item)"><i class="fas fa-minus"></i></a>
                        </td>
                        <td class="text-right">{{ getSubtotal(item) | centsToDollars }}</td>
                    </tr>
                </tbody>
            </table>

            <div class="clearfix p-2">
                <div class="float-left">
                    <h5>Total</h5>
                </div>
                <div class="float-right">
                    <h5>{{ getTotal() | centsToDollars }}</h5>
                </div>
            </div>
            <a href="#" @click.prevent="checkout" class="btn btn-block btn-dark">
                Checkout
            </a>
            <a href="#" @click.prevent="cancelTransaction" class="btn btn-block btn-danger">
                Cancel Transaction
            </a>
        </div>
        <div class="pos-items-container p-4">
            <div class="container-fluid">
                <div class="form-label-group">
                    <input type="text" id="inputSearch" class="form-control" placeholder="Search" v-model="searchQuery">
                    <label for="inputSearch">Search</label>
                </div>
                <div class="row">
                    <div v-for="product in filteredProducts" class="col-lg-6 col-xl-4" :key="product.id">
                        <div class="card mb-4 shadow-sm">
                            <div class="card-body">
                                <img class="w-100 rounded mb-4" src="http://via.placeholder.com/640x360" alt="">
                                <h5 v-if="product.remaining == 0" class="mb-0"><strike class="text-muted">{{ product.name }}</strike></h5>
                                <h5 v-else class="mb-0">{{ product.name }}</h5>
                                <p class="small text-muted mb-1">{{ product.category }}</p>
                                <div class="clearfix mb-3">
                                    <div class="float-left">
                                        <span v-if="product.remaining == 0" class="badge badge-pill badge-danger">Out Of Stock</span>
                                        <span v-else class="badge badge-pill badge-success">In Stock ({{ product.remaining }} left)</span>
                                    </div>
                                    <div class="float-right">
                                        {{ product.price | centsToDollars}}
                                    </div>
                                </div>
                                <a v-if="product.remaining == 0" href="#" class="btn btn-dark btn-block disabled" disabled>
                                    Add to Cart
                                </a>
                                <a v-else href="#" @click.prevent="addToCart(product)" class="btn btn-dark btn-block">
                                    Add to Cart
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
        
</template>

<script>
    export default {
        props: ['data-products'],
        data: () => ({
            searchQuery: '',
            products: [],
            cartItems: [],
        }),
        computed: {
            filteredProducts() {
                const query = this.searchQuery.toLowerCase();
                let products = this.products;

                if(query === '') {
                    return products;
                }

                products = _.filter(products, (product) => { 
                    return _.includes(product.name.toLowerCase(), query) || _.includes(product.category.toLowerCase(), query);
                });

                return products;
            }
        },
        methods: {
            clone(obj) {
                return JSON.parse(JSON.stringify(obj));
            },
            addToCart(product) {
                let item = _.find(this.cartItems, item => item.id === product.id);
                let inventoryProduct = _.find(this.products, prod => prod.id === product.id);
                inventoryProduct.remaining--;

                if(item) {
                    item.count++;
                } else {
                    product.count = 1;
                    this.cartItems.push(product);
                }
                
                // neeed to force update vue component to register change
                this.$forceUpdate();
            },
            removeFromCart(item) {
                let cartItem = _.find(this.cartItems, it => it.id === item.id);
                let product = _.find(this.products, product => product.id === item.id);
                product.remaining++;

                if(cartItem) {
                    if(cartItem.count === 1) {
                        this.cartItems = _.remove(this.cartItems, it => {
                            return it.id != cartItem.id;
                        });
                    } else {
                        cartItem.count--;
                    }
                }
                
                // neeed to force update vue component to register change
                this.$forceUpdate();
            },
            getSubtotal(item) {
                return item.price * item.count;
            },
            getTotal() {
                let total = 0;
                
                _.forEach(this.cartItems, (item) => {
                    total += this.getSubtotal(item);
                });

                return total;
            },
            cancelTransaction() {
                this.cartItems = [];
                this.products = JSON.parse(JSON.stringify(this.dataProducts));
            },
            checkout() {
                // axios post to end point
            }
        },
        mounted() {
            this.products = this.clone(this.dataProducts);
        },
        filters: {
            centsToDollars(cents) {
                const dollars = cents / 100;
                return dollars.toLocaleString("en-US", {style:"currency", currency:"USD"});
            }
        }
    }
</script>