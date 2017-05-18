module.exports = {
    clean: ['webapp/core-module/home'],
    watchSrc: [
        'webapp-src/core-module/home/**',
    ],
    copy: [
        {
            source: 'webapp-src/core-module/home/html/**',
            target: 'webapp/core-module/home/html/'
        },
        {
            source: 'webapp-src/core-module/home/image/**',
            target: 'webapp/core-module/home/image/'
        }
    ],
    less: [
        {
            source: 'webapp-src/core-module/home/less/page/index.less',
            target: 'webapp/core-module/home/css/'
        },
        {
            source: 'webapp-src/core-module/home/less/page/about.less',
            target: 'webapp/core-module/home/css/'
        },
        {
            source: 'webapp-src/core-module/home/less/page/news.less',
            target: 'webapp/core-module/home/css/'
        },
        {
            source: 'webapp-src/core-module/home/less/page/newsInfo.less',
            target: 'webapp/core-module/home/css/'
        },
        {
            source: 'webapp-src/core-module/home/less/page/product.less',
            target: 'webapp/core-module/home/css/'
        },
        {
            source: 'webapp-src/core-module/home/less/page/productList.less',
            target: 'webapp/core-module/home/css/'
        },
        {
            source: 'webapp-src/core-module/home/less/page/admin.less',
            target: 'webapp/core-module/home/css/'
        }
    ],
    es6: [
    ],
    ts: [
        {
            source: ['webapp-src/core-module/home/ts/page/index.ts'],
            target: 'webapp/core-module/home/js',
            name: 'index.js'
        },
        {
            source: ['webapp-src/core-module/home/ts/page/about.ts'],
            target: 'webapp/core-module/home/js',
            name: 'about.js'
        },
        {
            source: ['webapp-src/core-module/home/ts/page/news.ts'],
            target: 'webapp/core-module/home/js',
            name: 'news.js'
        },
        {
            source: ['webapp-src/core-module/home/ts/page/newsInfo.ts'],
            target: 'webapp/core-module/home/js',
            name: 'newsInfo.js'
        },
        {
            source: ['webapp-src/core-module/home/ts/page/product.ts'],
            target: 'webapp/core-module/home/js',
            name: 'product.js'
        },
        {
            source: ['webapp-src/core-module/home/ts/page/productList.ts'],
            target: 'webapp/core-module/home/js',
            name: 'productList.js'
        },
        {
            source: ['webapp-src/core-module/home/ts/page/admin.ts'],
            target: 'webapp/core-module/home/js',
            name: 'admin.js'
        },
    ]
}
