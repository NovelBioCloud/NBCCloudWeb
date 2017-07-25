import * as polyfill from '../common/polyfill'
import { ContentTabs } from '../about/ContentTabs'
import * as qs from 'querystring'
declare const $: JQueryStatic
$(() => {
	const query = qs.parse(location.search.replace('?', ''))
	const info = new NewsInfo()
	info.init({ container: $('.fn-content') })
	info.show(query.id)
})
class NewsInfo {

	container: JQuery
	timer: any
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
			}).fail(() => {
				this.showMessage('数据加载错误。。。')
			})
		}
	}
	showSrc(link) {
		this.timer && window.clearInterval(this.timer)
		this.container.empty()
		const encodeLink = encodeURI(link)
		const $iframe = $(`<iframe src='${encodeLink}' style='border:0px;width:100%;min-height: 1000px;' scrolling="no"></iframe>`)
		this.container.append($iframe)
		// 定义一个函数，定时调用并刷新iframe高度
		function reinitIframe() {
			const iframe = $iframe.get(0) as HTMLIFrameElement
			try {
				let bHeight = iframe.contentWindow.document.body.scrollHeight
				let dHeight = iframe.contentWindow.document.documentElement.scrollHeight
				let height = Math.max(bHeight, dHeight)
				iframe.height = height + ''
			} catch (ex) {
				console.log(ex)
			}
		}
		this.timer = window.setInterval(reinitIframe, 500)
	}
	showMessage(message: string = '') {
		this.container.html(message)
	}
	loadData(id) {
		this.showMessage('数据加载中')
		return $.post('home/news/get', { id: id })
	}

}