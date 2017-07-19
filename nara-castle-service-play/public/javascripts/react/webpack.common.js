
var webpack = require('webpack');
var CopyWebpackPlugin = require('copy-webpack-plugin');
var HtmlWebpackPlugin = require('html-webpack-plugin');
var ExtractTextPlugin = require('extract-text-webpack-plugin');
var path = require('path');

module.exports = {

  entry: {
    'vendor': './app/Vendor.js',
    'app': './app/App.js'
  },

  resolve: {
    modules: [path.resolve(__dirname, "app"), 'node_modules'],
    descriptionFiles: ["package.json"],
    extensions: ['.js', '.jsx']
  },

  module: {
    loaders: [{
      test: /jquery\.flot\.resize\.js$/,
      loader: 'imports-loader?this=>window'
    }, {
      test: /\.js/,
      loader: 'imports-loader?define=>false'
    }, {
      test: /\.js?$/,
      exclude: /(node_modules|bower_components)/,
      loaders: ['react-hot-loader']
    }, {
      test: /\.js?$/,
      exclude: /(node_modules|bower_components)/,
      loader: 'babel-loader',
      query: {
        presets: ['es2015', 'stage-2', 'react'],
        compact: false
      }
    }, {
      test: /\.css$/,
      exclude: path.join(process.cwd(), '/app'),
      loader: ExtractTextPlugin.extract({
        fallback: 'style-loader',
        use: 'css-loader?sourceMap'
      })
    }, {
      test: /\.css$/,
      include: path.join(process.cwd(), '/app'),
      loader: 'raw-loader'
    }, {
      test: /\.scss$/,
      loader: 'style-loader!css-loader!sass-loader?outputStyle=expanded'
    }, {
      test: /\.woff|\.woff2|\.svg|.eot|\.ttf/,
      loader: "file-loader?publicPath=./&name=fonts/[name].[ext]"
    }, {
      test: /\.(png|jpg|gif)$/,
      loader: 'url-loader?limit=10000&publicPath=resources/&name=img/app/[name].[ext]'
    }]
  },

  resolveLoader: {
    alias: {
      'rtlcss-loader': path.join(__dirname, 'rtlcss-loader.js')
    }
  },

  plugins: [
    new webpack.optimize.CommonsChunkPlugin({
      name: 'vendor',
      filename: 'vendor.[hash].js'
    }),
    new HtmlWebpackPlugin({
      template: 'app/index.html',
      baseUrl: '/',
      xhtml: true
    }),
    new CopyWebpackPlugin([{
      from: 'resources/img',
      to: 'img',
      context: path.join(__dirname, 'app')
    }, {
      from: 'resources/server',
      to: 'server',
      context: path.join(__dirname, 'app')
    }, {
      from: 'resources/fonts',
      to: 'fonts',
      context: path.join(__dirname, 'app')
    }]),
    new webpack.ProvidePlugin({
      $: 'jquery',
      jQuery: 'jquery',
      'window.jQuery': 'jquery'
    }),
    new webpack.ContextReplacementPlugin(/\.\/locale$/, 'empty-module', false, /js$/),
    new webpack.DefinePlugin({
      WP_BASE_HREF: '/'
    })
  ],

  externals: {
    'jquery': 'var NaraVendor.JQuery',
    'react': 'var NaraVendor.React',
    'react-dom': 'var NaraVendor.ReactDOM',
    'react-autobind': 'var NaraVendor.ReactAutobind',
    'react-router': 'var NaraVendor.ReactRouter',
    'redux': 'var NaraVendor.Redux',
    'redux-thunk': 'var NaraVendor.ReduxThunk',
    'react-redux': 'var NaraVendor.ReactRedux',
    'react-router-redux': 'var NaraVendor.ReactRouterRedux',
    'react-bootstrap': 'var NaraVendor.ReactBootstrap',
    'react-router-bootstrap': 'var NaraVendor.ReactRouterBootstrap',
    'whatwg-fetch': 'var NaraVendor.WhatwgFetch',

    'nara-core': 'var nara.core',
    'nara-react': 'var nara.react',
  },
};
