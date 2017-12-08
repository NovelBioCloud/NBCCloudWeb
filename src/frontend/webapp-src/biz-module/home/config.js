module.exports = {
    clean: ['webapp/biz-module/home'],
    watchSrc: [
        'webapp-src/biz-module/home/**',
    ],
    copy: [
        {
            source: 'webapp-src/biz-module/home/html/**/*',
            target: 'webapp/biz-module/home/html/'
        },
        {
            source: 'webapp-src/biz-module/home/image/**/*',
            target: 'webapp/biz-module/home/image/'
        }
    ],
    less: [
        {
            source: 'webapp-src/biz-module/home/less/page/index.less',
            target: 'webapp/biz-module/home/css/'
        },
        {
            source: 'webapp-src/biz-module/home/less/page/about.less',
            target: 'webapp/biz-module/home/css/'
        },
        {
            source: 'webapp-src/biz-module/home/less/page/news.less',
            target: 'webapp/biz-module/home/css/'
        },
        {
            source: 'webapp-src/biz-module/home/less/page/newsInfo.less',
            target: 'webapp/biz-module/home/css/'
        },
        {
            source: 'webapp-src/biz-module/home/less/page/product.less',
            target: 'webapp/biz-module/home/css/'
        },
        {
            source: 'webapp-src/biz-module/home/less/page/productList.less',
            target: 'webapp/biz-module/home/css/'
        },
        {
            source: 'webapp-src/biz-module/home/less/page/admin.less',
            target: 'webapp/biz-module/home/css/'
        },
        {
            source: 'webapp-src/biz-module/home/less/page/videoPlayer.less',
            target: 'webapp/biz-module/home/css/'
        }
    ],
    es6: [
    ],
    ts: [
        {
            source: ['webapp-src/biz-module/home/ts/page/index.ts'],
            target: 'webapp/biz-module/home/js',
            name: 'index.js'
        },
        {
            source: ['webapp-src/biz-module/home/ts/page/about.ts'],
            target: 'webapp/biz-module/home/js',
            name: 'about.js'
        },
        {
            source: ['webapp-src/biz-module/home/ts/page/news.ts'],
            target: 'webapp/biz-module/home/js',
            name: 'news.js'
        },
        {
            source: ['webapp-src/biz-module/home/ts/page/newsInfo.ts'],
            target: 'webapp/biz-module/home/js',
            name: 'newsInfo.js'
        },
        {
            source: ['webapp-src/biz-module/home/ts/page/product.ts'],
            target: 'webapp/biz-module/home/js',
            name: 'product.js'
        },
        {
            source: ['webapp-src/biz-module/home/ts/page/productList.ts'],
            target: 'webapp/biz-module/home/js',
            name: 'productList.js'
        },
        {
            source: ['webapp-src/biz-module/home/ts/page/admin.ts'],
            target: 'webapp/biz-module/home/js',
            name: 'admin.js'
        },
        {
            source: ['webapp-src/biz-module/home/ts/page/videoPlayer.ts'],
            target: 'webapp/biz-module/home/js',
            name: 'videoPlayer.js'
        }
    ]
}
