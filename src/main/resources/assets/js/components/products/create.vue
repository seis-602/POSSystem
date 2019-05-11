<template>
    <div class="container py-5">
        <h3 class="mb-3">Add New Product</h3>
        <form @submit.prevent="onSubmit">
            <div class="form-group">
                <label for="inputName">Name</label>
                <input type="text" id="inputName" class="form-control" name="name" v-model="name" required>
            </div>
            <div class="form-group">
                <label for="inputCategory">Category</label>
                <input type="text" id="inputCategory" class="form-control" name="category" v-model="category" required>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="inputSupplier">Supplier</label>
                        <input type="text" id="inputSupplier" class="form-control" name="supplier" v-model="supplier" required>
                    </div>
                </div>
                <div class="col-6 col-md-3">
                    <div class="form-group">
                        <label for="inputPrice">Unit Price (in cents)</label>
                        <input type="number" id="inputPrice" class="form-control" name="price" v-model="price" required>
                    </div>
                </div>
                <div class="col-6 col-md-3">
                    <div class="form-group">
                        <label for="inputRemaining">Initial Inventory</label>
                        <input type="number" id="inputRemaining" class="form-control" name="remaining" v-model="remaining" required>
                    </div>
                </div>
            </div>
            <button class="btn btn-primary" type="submit"><i class="fas fa-check pr-1"></i> Submit</button>
        </form>

        <div v-if="processing" class="overlay">
            <div>
                <i class="fas fa-circle-notch fa-spin"></i>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    props: [],
    data: () => ({
        processing: false,
        name: '',
        category: '',
        supplier: '',
        price: '',
        remaining: ''
    }),
    computed: {
        formData() {
            return {
                name: this.name,
                category: this.category,
                supplier: this.supplier,
                price: this.price,
                remaining: this.remaining,
            }
        }
    },
    methods: {
        onSubmit() {
            this.processing = true;

            setTimeout(() => {
                axios.post('/products', this.formData)
                    .then((response) => {
                        this.processing = false;
                        Swal.fire({
                            type: 'success',
                            title: 'Added!',
                            text: 'This new product has been added to your inventory.'
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