<template>
    <div class="container py-5">
        <div v-if="processing" class="overlay">
            <div>
                <i class="fas fa-circle-notch fa-spin"></i>
            </div>
        </div>
        <div class="clearfix mb-4">
            <h2 class="float-left">Available Products</h2>
            <a href="/products/create" class="float-right btn btn-primary"><i class="fas fa-plus pr-1"></i> Add Product</a>
        </div>
        <div class="row">
            <div v-for="product in dataProducts" :key="product.id" class="col-6 col-md-4">
                <div class="card shadow-sm mb-4">
                    <div class="card-body">
                        <img src="http://via.placeholder.com/640x360" alt="" class="w-100 rounded mb-4">
                        <h5 class="mb-0">{{ product.name }}</h5>
                        <p class="small text-muted mb-1">{{ product.category }}</p>
                        <div class="mb-3">
                            <p v-if="product.remaining == 0" class="badge badge-pill badge-danger mb-0">Out of Stock</p>
                            <p v-else-if="product.remaining <= 5" class="badge badge-pill badge-danger mb-0">{{ product.remaining }} items left</p>
                            <p v-else class="badge badge-pill badge-success mb-0">{{ product.remaining }} items left</p>
                        </div>
                        <a :href="'/inventories/' + product.id" class="btn btn-dark btn-block">Update Inventory</a>
                        <a href="#" @click.prevent="deleteProduct(product)" class="btn btn-outline-danger btn-block">Delete</a>
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
            processing: false,
        }),
        methods: {
            deleteProduct(product) {
                this.processing = true;
                setTimeout(() => {
                    axios.delete('/products/' + product.id)
                        .then((response) => {
                            this.processing = false;
                            Swal.fire({
                                type: 'success',
                                title: 'Deleted!',
                                text: 'Product has been removed from your inventory.'
                            })
                            setTimeout(() => { window.location.href = "/inventories"; }, 1500);
                        })
                        .catch((errors) => {
                            this.processing = false;
                            Swal.fire({
                                type: 'error',
                                title: 'Oops...',
                                text: 'Something went wrong!',
                            });
                        })
                }, 1000);
            }
        }
    }
</script>