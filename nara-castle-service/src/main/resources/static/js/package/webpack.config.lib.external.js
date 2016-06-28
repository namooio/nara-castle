/**
 * Created by hkkang on 2016-06-01.
 */
var path = require('path');
var webpack = require('webpack');


module.exports = {
    //
    entry: {
        'external-lib': './external-lib.js'
    },
    resolve: {
        moduleDirectories: ['node_modules'],
        extensions: ['', '.js', '.jsx'],
        alias:  {
            'app' : path.join(__dirname, '../')
        }
    },
    output: {
        filename: '../[name]-bundle.js',
        libraryTarget: 'var',
        library: 'externalLib'
    },
    module: {
        loaders: [
            {
                test: /\.js$/,
                loader: 'babel',
                query: {
                    presets: ['babel-preset-es2015'],
                    compact: false
                }
            }
        ]
    }
};