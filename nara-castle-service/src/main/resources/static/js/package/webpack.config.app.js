/**
 * Created by hkkang on 2016-06-01.
 */
var path = require('path');
var webpack = require('webpack');


module.exports = {
    //
    entry: {
        app: './app.js'
    },
    resolve: {
        moduleDirectories: ['node_modules'],
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
        'jquery': 'var externalLib.jQuery',
        'react': 'var externalLib.React',
        'react-dom': 'var externalLib.ReactDOM',
        'react-router': 'var externalLib.ReactRouter',
        'react-bootstrap': 'var externalLib.ReactBootstrap',
        'nara': 'var naraLib.Nara',
        'nara-react': 'var naraLib.NaraReact'
    }
};