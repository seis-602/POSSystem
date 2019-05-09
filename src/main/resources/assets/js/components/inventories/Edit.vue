<template>
    <div v-if="product" class="container py-5">
        
        <div v-if="processing" class="overlay">
            <div>
                <i class="fas fa-circle-notch fa-spin"></i>
            </div>
        </div>

        <div class="mb-4">
            <h1>{{ product.name }}</h1>
            <h4 class="text-muted">{{ product.category }}</h4>
            <p v-if="product.remaining === 0" class="text-danger">Out of Stock</p>
            <p v-else :class="{'text-danger': product.remaining < 5}">{{product.remaining}} item<span v-if="product.remaining > 1">s</span> left</p>
        </div>
        <div class="row">
            <div class="col-md-5">
                <div class="form-group">
                    <label for="amountNeededInput">Amount Needed</label>
                    <input id="amountNeededInput" type="number" class="form-control" v-model="requestAmount">
                </div>
                <a href="#" @click.prevent="requestInventory" class="btn btn-primary btn-block">Request</a>
            </div>
            <div v-if="product.requestedAmount" class="col-md-5">
                <div class="form-group">
                    <label>{{ product.requestedAmount }} Previously Requested</label>
                    <input type="number" class="form-control" v-model="receivedAmount">
                </div>
                <a href="#" @click.prevent="requestInventory" class="btn btn-success btn-block">Received</a>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        props: ['data-product'],
        data: () => ({
            product: null,
            requestAmount: "",
            receivedAmount: 0,
            processing: false,
        }),
        computed: {
            formData() {
                return {
                    request_amount: this.requestAmount, 
                }
            }
        },
        methods: {
            requestInventory() {
                this.processing = true;
                let formData = {
                    request_amount: this.requestAmount, 
                }
                // post axios
                // product.requestedAmount =+ request.request_amount
            },
            requestInventory() {
                this.processing = true;
                let formData = {
                    received_amount: this.receivedAmount, 
                }
                // post axios
                // product.requestedAmount =- request.received_amount
            }
        },
        mounted() {
            this.product = this.clone(this.dataProduct).value;
        },
        watch: {
            product(value) {
                if (value) {
                    this.receivedAmount = value.requestedAmount;
                }
            },
            receivedAmount(value) {
                if (this.product && value > this.product.requestedAmount) {
                    this.receivedAmount = this.product.requestedAmount;
                }
            }
        }
    }
</script>