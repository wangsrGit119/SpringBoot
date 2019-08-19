

module.exports = {
    publicPath: process.env.NODE_ENV === 'production'
        ? '/wangsr/'
        : '/',
    outputDir:'dist',
    indexPath:'index.html',
    assetsDir: 'static',
    devServer: {
        host: '0.0.0.0',
        port: 3000,
        proxy: {
            '/api': {
                target: 'http://115.159.127.105:8089/',
                changeOrigin: true,
                ws:true,
                pathRewrite: {
                    '^/api': '/'
                }
            }
        }
    },
}