<template>
    <div class="container py-5">
        <div class="row">
            <div v-for="product in dataProducts" class="col-6 col-md-4 col-lg-3">
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
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        props: ['data-products'],
        mounted() {

        }
    }
</script>