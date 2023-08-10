const path = require('path');
const CopyPlugin = require('copy-webpack-plugin');

module.exports = {
    entry: './src/main/js',
    devtool: 'source-map',
    mode: 'development',
    output: {
        path: path.resolve(__dirname),
        filename: './src/main/resources/static/built/bundle.js'
    },
    devServer: {
        static: {
            directory: path.join(__dirname, './src/main/resources/static/built')
        },
        hot: true
    },
    resolve: {
        extensions: ['.ts', ".js"],
    },
    module: {
        rules: [
            {
                test: /\.js$/,
                exclude: /node_modules/,
                use: {
                    loader: 'babel-loader',
                    options: {
                        presets: ["@babel/preset-env", "@babel/preset-react"]
                    }
                }
            },
            {
                test: /\.(sass|css)$/,
                use: ['style-loader', 'css-loader']
            }
        ]
    },
    plugins: [
        new CopyPlugin({
            patterns: [
                {
                    from: './src/main/resources/templates',
                    to: './src/main/resources/static/built'
                }
            ]
        })
    ],
    watch: true,
    watchOptions:  {
        poll: true,
        ignored: /node_modules/
    }
};
