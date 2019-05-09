'use strict';

require('../scss/main.scss');

import globalMixins from './mixins/global.js';

window._ = require('lodash');

window.axios = require('axios');
window.axios.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
window.axios.defaults.headers.common['Content-Type'] = 'application/json';

Vue.component('example-component', require('./components/ExampleComponent.vue').default);

Vue.component('cash-registers-index', require('./components/cash-registers/Index.vue').default);
Vue.component('pos-system', require('./components/POS.vue').default);

Vue.component('inventories-index', require('./components/inventories/Index.vue').default);
Vue.component('inventories-edit', require('./components/inventories/Edit.vue').default);

Vue.mixin({ mixins: [globalMixins] });

const app = new Vue({
    el: '#app',
});
