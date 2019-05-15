<template>
    <div class="container py-5">
        
        <div class="clearfix mb-4">
            <h1 class="float-left">Sale #{{ sale.id | toSaleIdFormat }}</h1>
            <a href="/sales" class="float-right btn btn-primary"><i class="fas fa-arrow-left pr-1"></i> Back</a>
        </div>

        <p class="text-muted mb-1">{{ sale.cashRegister.name }}</p>
        <p class="small text-muted"><i>Sale completed by {{ sale.user.firstName + ' ' + sale.user.lastName }}</i></p>
        <table class="table mt-4">
            <thead>
                <tr>
                    <th scope="col">Product</th>
                    <th scope="col">Price <span class="text-muted">(Unit)</span></th>
                    <th scope="col">Quantity</th>
                    <th scope="col">Subtotal</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="product in productsGroup">
                    <td>{{ product[0].name }}</td>
                    <td>{{ product[0].price | centsToDollars }}</td>
                    <td>{{ product.length }}</td>
                    <td>{{ getProductSubtotal(product) | centsToDollars }}</td>
                </tr>
                <tr>
                    <th colspan="3">TOTAL</th>
                    <th>{{ dataSale.saleTotal | centsToDollars }}</th>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
    export default {
        props: ['data-sale'],
        computed: {
            sale() {
                return this.dataSale;
            },
            products() {
                return JSON.parse(this.dataSale.products);
            },
            productsGroup() {
                return _.groupBy(this.products, 'id');
            }
        },
        methods: {
            getProductSubtotal(productGroup) {
                return productGroup[0].price * productGroup.length;
            },
        }
    }
</script>
