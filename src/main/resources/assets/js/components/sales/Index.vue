<template>
    <div class="container">
        <h2 class="mb-4">Sales</h2>
        <div class="row">
            <div v-for="sale in dataSales" class="col-6 col-md-4">
                <div class="card mb-4 shadow-sm">
                    <div class="card-body text-center">
                        <h3><i class="fas fa-sales text-muted"></i></h3>
                        <h5>{{ sale.sale_total }}</h5>
                        <a :href="'/sales'</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        props: ['data-sales'],
        mounted() {

        }
    }
</script>