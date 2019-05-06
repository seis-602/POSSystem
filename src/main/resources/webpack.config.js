'use strict';

var path = require('path');
var webpack = require('webpack');
var ExtractTextPlugin = require("extract-text-webpack-plugin");
var VueLoaderPlugin = require('vue-loader/lib/plugin')


module.exports = {
	entry: './assets/js/app.js',

	output: {
		path: path.resolve(__dirname, 'static'),
		filename: 'js/app.js'
	},

	watch: true,

	plugins: [
		new ExtractTextPlugin('css/main.css'),
		new VueLoaderPlugin()
	],

	module: {
		rules: [
			{
				test: /\.vue$/,
				loader: 'vue-loader'
			},
			{
				test: /\.js$/,
				exclude: /node_modules/,
				use: [
					{
                        loader: 'babel-loader',
                        options: {
                            presets: ['es2015']
                        }
                    }
				]
			},
			{
				test: /\.scss$/,
				use: ExtractTextPlugin.extract({
					fallback: 'style-loader',
					use: ['css-loader', 'sass-loader']
				})
			}
		]
	},
};