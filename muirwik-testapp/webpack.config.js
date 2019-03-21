'use strict';

const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const webpack = require('webpack');

module.exports = {
};

const config = {
    mode: 'development',
    entry: [
        require.resolve('react-dev-utils/webpackHotDevClient'),
        './build/js/app.js',
    ],
    output: {
        path: path.resolve(__dirname, './build/dist'),
        filename: 'bundle.js',

        // Webpack uses `publicPath` to determine where the app is being served from.
        // In development, we always serve from the root. This makes config easier.
        // publicPath: '/',
        // devtoolModuleFilenameTemplate: info =>
        //    path.resolve(info.absoluteResourcePath),
        //     path.relative(path.resolve(__dirname, 'src'), info.absoluteResourcePath),
    },
    devtool: 'cheap-module-source-map',
    // devtool: 'cheap-module-inline-source-map',
    devServer: {
        contentBase: './build/dist',
        compress: true,
        // port: 9000,
        // contentBase: paths.appPublic,
        // By default files from `contentBase` will not trigger a page reload.
        watchContentBase: true,
        // Enable hot reloading server. It will provide /sockjs-node/ endpoint
        // for the WebpackDevServer client so it can learn when the files were
        // updated. The WebpackDevServer client is included as an entry point
        // in the Webpack development configuration. Note that only changes
        // to CSS are currently hot reloaded. JS changes will refresh the browser.
        hot: true,
    },
    resolve: {
        modules: [
            //"js",
            "build/js",
            //"resources",
            "node_modules",
            "../muirwik-components/build/js"
        ],
        alias: {
            // We alias things for two reasons, one is to allow sub-projects (e.g. components) to access the same
            // copy of these modules (otherwise webpack pulls in two duplicate versions, and things don't work
            // like they should). The other reason is that the @jetbrains/kotlin-xyz modules seems to refer to kotlin-xyz
            // rather than @jetbrains/kotlin-xyz (for example) and don't get imported or referenced properly... not
            // sure if it is the right way to go, but this fixes the problem.
            'kotlin': path.resolve(path.join(__dirname, 'node_modules', 'kotlin')),
            'kotlin-react': path.resolve(path.join(__dirname, 'node_modules', '@jetbrains/kotlin-react')),
            'kotlin-react-dom': path.resolve(path.join(__dirname, 'node_modules', '@jetbrains/kotlin-react-dom')),
            'kotlin-extensions': path.resolve(path.join(__dirname, 'node_modules', '@jetbrains/kotlin-extensions')),
            'kotlin-css-js': path.resolve(path.join(__dirname, 'node_modules', '@jetbrains/kotlin-css-js')),
            'kotlin-styled': path.resolve(path.join(__dirname, 'node_modules', '@jetbrains/kotlin-styled')),
            'kotlinx-html-js': path.resolve(path.join(__dirname, 'node_modules', 'kotlinx-html-js')),
            '@material-ui': path.resolve(path.join(__dirname, 'node_modules', '@material-ui')),
        }
    },
    plugins: [
        // Creates the html page for us...
        // new HtmlWebpackPlugin(),
        new webpack.HotModuleReplacementPlugin()
    ],
    module: {
        strictExportPresence: true,
        rules: [
            // { test: /\.vue$/, loader: 'vue-loader' },
            {
                test: /\.css$/,
                use: [ 'style-loader', 'css-loader' ]
            },

            {
                test: /\.js$/,
              /*  include: path.resolve(__dirname, './build/js'), */
                loader: require.resolve('source-map-loader'),
                enforce: 'pre',
            },

            /* At the moment, it does not seem useful to have the react-hot-reloader working for kotlin js...
               maybe I have done it wrong, but the state never seems to be remembered... maybe you have to use
               something like Redux??. Note that we still use webpack's HMR because it does more nicely reload the
               app... but the state seems to get replaced each time...

               Might come back to it later, so leaving it here for now...
            {
                test: /\.(js|jsx)$/,
                include: path.resolve(__dirname, 'src'),
                // loader: require.resolve('babel-loader'),
                loader: 'babel-loader',
                options: {
                    // This is a feature of `babel-loader` for Webpack (not Babel itself).
                    // It enables caching results in ./node_modules/.cache/babel-loader/
                    // directory for faster rebuilds.
                    cacheDirectory: true,
                    plugins: ['react-hot-loader/babel'],
                },
            }
            */
        ]
    },
};

module.exports = config;
