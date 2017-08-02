
var webpack = require('webpack');
var webpackMerge = require('webpack-merge');
var ExtractTextPlugin = require('extract-text-webpack-plugin');
var commonConfig = require('./webpack.common.js');
var path = require('path');

module.exports = webpackMerge(commonConfig, {
    devtool: '#cheap-module-eval-source-map',

    output: {
        path: path.join(process.cwd(), '/dist'),
        filename: '[name][hash].js'
    },

    plugins: [
        new webpack.HotModuleReplacementPlugin(),
        new ExtractTextPlugin('[name][hash].css')
    ],

    devServer: {
      historyApiFallback: true,
      stats: 'minimal',
      inline: true,
      hot: true,
      proxy: {
        '/castle-api': {
          target: 'http://localhost:9000',
          secure: false
        },
        '/resources': {
          target: 'http://localhost:9000',
          secure: false
        }
      }
    }
});
