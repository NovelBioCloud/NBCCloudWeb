import * as polyfill from '../common/polyfill'

import { ContentTabs } from '../about/ContentTabs'
import * as $ from 'jquery'
import * as qs from 'querystring'
import axios from 'axios'
$(() => {
    const query = qs.parse(location.search.replace('?', ''))
    const info = new NewsInfo()
    info.init({ container: $('.fn-content') })
    info.show(query.id)
})
class NewsInfo {

    container: JQuery
    init({ container }) {
        this.container = container
    }
    show(id: string) {
        if (!id) {
            this.showMessage()
        } else {
            this.loadData(id).then((data) => {
                if (data.state) {
                    this.showSrc(data.result.link)
                } else {
                    this.showMessage(data.message || '文件不存在。。。')
                }
            }).catch(() => {
                this.showMessage('数据加载错误。。。')
            })
        }
    }
    showSrc(link) {
        this.container.html(`<iframe src='${link}' style='border:0px;width:100%;'></iframe>`)
    }
    showMessage(message: string = '') {
        this.container.html(message)
    }
    loadData(id) {
        this.showMessage('数据加载中')
        return axios.post('home/news/get', qs.stringify({ id: id })).then((resp) => {
            return resp.data
        })
    }

}