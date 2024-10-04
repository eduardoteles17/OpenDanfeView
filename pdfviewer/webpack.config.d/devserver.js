if (config.devServer) {
    config.devServer.hot = true;
    config.devServer.open = false;
    config.devServer.historyApiFallback = true;
    config.devtool = 'eval-cheap-source-map';
}
