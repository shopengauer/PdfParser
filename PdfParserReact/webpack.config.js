'use strict';

const path = require('path');
const webpack = require('webpack');


const config = {
    entry: path.resolve(__dirname, 'src/index.js'),
    output: {
        path: path.resolve(__dirname, 'dist'),
        filename: 'bundle.js'
    },
    module: {
        rules: [
            {
                test: '/\.css$',
                use: [
                    {loader: 'css-loader'},
                    {loader: 'style-loader'}
                ]
            },
            {
                test: /js$/ ,
                use: 'babel-loader'


            }
        ]
    }

};

module.exports = config;