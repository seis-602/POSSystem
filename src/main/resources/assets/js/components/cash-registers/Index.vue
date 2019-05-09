<template>
    <div class="container">
        <div class="row">
            <div v-for="register in dataCashRegisters" class="col-6 col-md-4">
                <div class="card mb-4 shadow-sm">
                    <div class="card-body text-center">
                        <h3><i class="fas fa-cash-register text-muted"></i></h3>
                        <h5>{{ register.name }}</h5>
                        <p class="mb-4">
                            <small class="text-muted text-uppercase font-weight-bold">Balance</small> <br>
                            {{ register.balance | centsToDollars }}
                        </p>
                        <a :href="'/cash-registers/' + register.id + '/point-of-sale'" class="btn btn-primary btn-block">Open Register</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        props: ['data-cash-registers'],
        mounted() {

        }
    }
</script>
