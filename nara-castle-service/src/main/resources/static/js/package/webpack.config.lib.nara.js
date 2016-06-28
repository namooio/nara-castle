/**
 * Created by hkkang on 2016-06-01.
 */
var path = require('path');
var webpack = require('webpack');


module.exports = {
    //
    entry: {
        'nara-lib': './nara-lib.js'
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
        library: 'naraLib'
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
        'jquery': 'externalLib.jQuery',
        'react': 'externalLib.React',
        'react-dom': 'externalLib.ReactDOM',
        'react-router': 'externalLib.ReactRouter',
        'react-bootstrap': 'externalLib.ReactBootstrap',
        'nara': 'naraLib.Nara',
        'nara-role-book': 'naraLib.NaraRoleBook'
    }
};