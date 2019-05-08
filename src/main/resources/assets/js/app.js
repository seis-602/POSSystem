'use strict';

require('../scss/main.scss');

window._ = require('lodash');

window.axios = require('axios');
window.axios.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
window.axios.defaults.headers.common['Content-Type'] = 'application/json';

Vue.component('example-component', require('./components/ExampleComponent.vue').default);

Vue.component('pos-system', require('./components/POS.vue').default);

const app = new Vue({
    el: '#app',
});
