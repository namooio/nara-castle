/**
 * Created by hkkang on 2016-06-01.
 */
var path = require('path');
var webpack = require('webpack');


module.exports = {
    //
    entry: {
        //external: ['jquery', 'react', 'react-dom', 'react-router'],
        app: './app.js'
    },
    resolve: {
        moduleDirectories: ['node_moduels'],
        extensions: ['', '.js', '.jsx'],
        alias:  {
            'app' : path.join(__dirname, '../')
        }
    },
    output: {
        filename: '../[name]-bundle.js'
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
            },
            {
                test: /\.jsx$/,
                loader: 'babel',
                query: {
                    presets: ['babel-preset-react-es2015'],
                    compact: false
                }
            }
        ]
    },
    externals : {
        'jquery': 'lib.jQuery',
        'react': 'lib.React',
        'react-dom': 'lib.ReactDOM',
        'react-router': 'lib.ReactRouter'
    }

};