<template>
    <div class="container py-5">
        <h2 class="mb-4">Sales</h2>
        <div class="row">
            <div v-for="sale in dataSales" :key="sale.id" class="col-6 col-md-4">
                <div class="card shadow-sm mb-4">
                    <div class="card-body">
                        <div class="mb-3">
                            <h5 class="mb-0">Sale #{{ sale.id | toSaleIdFormat }}</h5>
                            <p class="small text-muted">{{ sale.cashRegister.name }}</p>
                        </div>  
                        <div class="mb-3">
                            <h4 class="mb-2">{{ sale.saleTotal | centsToDollars}}</h4>
                            <p class="small"><i>Sale completed by {{ sale.user.firstName + ' ' + sale.user.lastName }}</i></p>
                        </div>
                        <a :href="'/sales/' + sale.id" class="btn btn-dark btn-block">Details</a>
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

        },
    }
</script>