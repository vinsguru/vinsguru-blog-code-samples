const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const { CleanWebpackPlugin } = require('clean-webpack-plugin');

module.exports = {
    mode: 'development',
    devServer: {
        port: 8081,
        contentBase: './dist',
     },
     entry: './src/index.js',
     output: {
         filename: 'bundle.js',
         path: path.resolve(__dirname, 'dist'),
         publicPath: '/'
     },
     plugins: [
        new HtmlWebpackPlugin({
            inlineSource: '.(js|css)$',
            template: __dirname + `/src/index.html`,
            filename: __dirname + `/dist/index.html`,
            inject: 'head',
          })
     ]
}