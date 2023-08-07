const path = require('path');

module.exports = {
    entry: './src/main/js',
    devtool: 'source-map',
    mode: 'development',
    output: {
        path: path.resolve(__dirname),
        filename: './src/main/resources/static/built/bundle.js'
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
    watch: true
};
