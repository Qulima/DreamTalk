var path = require('path')

module.exports = {
  name: "dev.config",
  entry: './frontend/index.js',
  watchOptions: {
    ignored: /node_modules/
  },
  watch: true,
  output: {
    filename: 'bundle.js',
    path: path.resolve(__dirname, 'dist'),
  },
  mode: 'development',
  devtool: 'source-map',
  devServer: {
    contentBase: './dist',
    watchContentBase: true,

    compress: true,
    port: 8000,
    allowedHosts: [
      'localhost:8080'
    ]
  },
  resolve: {
    extensions: ['.js', '.jsx', '.css', '.html', '.png']
  },
  module: {
    rules: [
      {
        test: path.join(__dirname, '.'),
        exclude: /(node_modules)/,
        use: [{
          loader: 'babel-loader',
          options: {
            presets: ['@babel/preset-env', '@babel/preset-react']
          }
        }]
      },
      {
        test: /\.css$/,
        use: ['style-loader', 'css-loader']
      },
      {
        test: /\.(png|jpg|svg|gif)$/,
        use: ['file-loader']
      },
      {
        test: /\.js$/,
        exclude: /node_modules/,
        use: [{
          loader: 'babel-loader',
          options: {
            presets: [
              '@babel/preset-env',
              '@babel/preset-react'
            ]
          }
        }]
      },
      {
        test: /\.jsx$/,
        exclude: /node_modules/,
        use: [{
          loader: 'babel-loader',
          options: {
            presets: [
              '@babel/preset-env',
              '@babel/preset-react'
            ]
          }
        }]
      },
    ]
  }
}