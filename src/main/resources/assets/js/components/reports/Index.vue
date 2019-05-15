<template>
    <div class="container py-5">
        <h2 class="mb-4">Reports</h2>
        <div class="row">
            <div class="col-6 col-xl-4">
                <div class="card shadow-sm mb-4">
                    <div class="card-body">
                        <h5 class="mb-3">Inventory Report</h5>
                        <p class="small text-muted">
                            Lorem ipsum dolor sit amet consectetur adipisicing elit. Tempora repellat mollitia, saepe earum est repudiandae tenetur.
                        </p>
                        <a v-if="inventoryReportFileName == ''" href="#" class="btn btn-primary btn-block" @click.prevent="generateInventoryReport()">
                            <span v-if="generating == 1"><i class="fas fa-spinner fa-pulse mr-1"></i> Generating</span>
                            <span v-else>Generate</span>
                        </a>
                        <a v-else :href="'/documents/' + inventoryReportFileName" target="_blank" class="btn btn-primary btn-block">
                            <i class="fas fa-download pr-1"></i> Download
                        </a>
                    </div>
                </div>
            </div>
            <div class="col-6 col-xl-4">
                <div class="card shadow-sm mb-4">
                    <div class="card-body">
                        <h5 class="mb-3">Shift Report</h5>
                        <p class="small text-muted">
                            Lorem ipsum dolor sit amet consectetur adipisicing elit. Tempora repellat mollitia, saepe earum est repudiandae tenetur.
                        </p>
                        <a v-if="shiftReportFileName == ''" href="#" class="btn btn-primary btn-block" @click.prevent="generateShiftsReport()">
                            <span v-if="generating == 2"><i class="fas fa-spinner fa-pulse mr-1"></i> Generating</span>
                            <span v-else>Generate</span>
                        </a>
                        <a v-else :href="'/documents/' + shiftReportFileName" target="_blank" class="btn btn-primary btn-block">
                            <i class="fas fa-download pr-1"></i> Download
                        </a>
                    </div>
                </div>
            </div>
            <div class="col-6 col-xl-4">
                <div class="card shadow-sm mb-4">
                    <div class="card-body">
                        <h5 class="mb-3">Sale Report</h5>
                        <p class="small text-muted">
                            Lorem ipsum dolor sit amet consectetur adipisicing elit. Tempora repellat mollitia, saepe earum est repudiandae tenetur.
                        </p>
                        <a v-if="saleReportFileName == ''" href="#" class="btn btn-primary btn-block" @click.prevent="generateSalesReport()">
                            <span v-if="generating == 3"><i class="fas fa-spinner fa-pulse mr-1"></i> Generating</span>
                            <span v-else>Generate</span>
                        </a>
                        <a v-else :href="'/documents/' + saleReportFileName" target="_blank" class="btn btn-primary btn-block">
                            <i class="fas fa-download pr-1"></i> Download
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        props: ['data-sales'],
        data: () => ({
            generating: false,
            inventoryReportFileName: '',
            shiftReportFileName: '',
            saleReportFileName: '',
        }),
        mounted() {
            
        },
        methods: {
            generateInventoryReport() {
                this.generating = 1;
                axios.get('/api/reports/inventory')
                    .then((response) => {
                        this.generating = false;
                        this.inventoryReportFileName = response.data;
                        Swal.fire({
                            type: 'success',
                            title: 'Generated!',
                            text: 'The inventory report has been generated successfully.'
                        })
                    }).catch((errors) => {
                        this.generating = false;
                        Swal.fire({
                            type: 'error',
                            title: 'Oops...',
                            text: 'Something went wrong!',
                        });
                    })
            },
            generateShiftsReport() {
                this.generating = 2;
                axios.get('/api/reports/shifts')
                    .then((response) => {
                        this.generating = false;
                        this.shiftReportFileName = response.data;
                        Swal.fire({
                            type: 'success',
                            title: 'Generated!',
                            text: 'The shift report has been generated successfully.'
                        })
                    }).catch((errors) => {
                        this.generating = false;
                        Swal.fire({
                            type: 'error',
                            title: 'Oops...',
                            text: 'Something went wrong!',
                        });
                    })
            },
            generateSalesReport() {
                this.generating = 3;
                axios.get('/api/reports/sales')
                    .then((response) => {
                        this.generating = false;
                        this.saleReportFileName = response.data;
                        Swal.fire({
                            type: 'success',
                            title: 'Generated!',
                            text: 'The sale report has been generated successfully.'
                        })
                    }).catch((errors) => {
                        this.generating = false;
                        Swal.fire({
                            type: 'error',
                            title: 'Oops...',
                            text: 'Something went wrong!',
                        });
                    })
            }
        }
    }
</script>