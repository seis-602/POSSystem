<template>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">Example Component</div>

                    <div class="card-body">
                        I'm an example component.
                        <ul>
                            <li v-for="product in products">
                                <b>{{product.name}}</b>
                                <small>{{product.category}}</small>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        props: ['products'],
        mounted() {
            alert('Component mounted.')
            console.log('Component mounted.')
        }
    }
</script>