let proxyObj = {}

proxyObj['/'] = {
    //websocket
    ws: false,
    //后端地址
    target: 'http://localhost:8888',
    //是否跨域
    changeOrigin: true,
    /*
    '^/'是个正则表达式，匹配路径
    ''修改成的地址
     */
    pathRewrite: {
        '^/': ''
    }
}
module.exports = {
    devServer: {
        //vue项目的运行地址及端口号
        host: 'localhost',
        port: 8080,
        proxy: proxyObj
    },
}
