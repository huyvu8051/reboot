const { createProxyMiddleware } = require('http-proxy-middleware')

module.exports = function(app) {
    app.use(
        '/api',
        createProxyMiddleware({
            target: 'http://localhost:8080',
            changeOrigin: true,
        })
    )
    app.use(
        '/socket.io',
        createProxyMiddleware({
            target: 'ws://localhost:8080',
            changeOrigin: true,
            ws: true
        })
    )
    app.use(
        '/ws',
        createProxyMiddleware({
            target: 'ws://localhost:8080',
            changeOrigin: true,
            ws:true
        })
    )
}