export default {
    methods: {
        clone(obj) {
            return JSON.parse(JSON.stringify(obj));
        },
    },
	filters: {
		capitalize (value) {
            if (!value) return '';
           value = value.toString();
            return value.charAt(0).toUpperCase() + value.slice(1);
        },
        uppercase (value) {
            if (!value) return '';
           return value.toString().toUpperCase();
        },
        centsToDollars(cents) {
            const dollars = cents / 100;
            return dollars.toLocaleString("en-US", {style:"currency", currency:"USD"});
        }
	},
}