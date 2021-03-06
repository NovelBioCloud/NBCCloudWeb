import * as polyfill from '../common/polyfill'
import { BackgroundSlider } from '../index/BackgroundSlider'
import * as _ from 'lodash'
import * as moment from 'moment'
declare const $: JQueryStatic

$(() => {
	new BackgroundSlider()
	new NewsContent()
	$('.fn-slider-animate').each((index, element) => {
		const slider = new HoverChanger()
		const container = $(element)
		slider.init({
			container: container,
			defaultElement: container.find('.fn-slider-animate-default'),
			hoverElement: container.find('.fn-slider-animate-active')
		})
		slider.start()
	})
})
class NewsContent {
	template = `<div class='part-content-main'>
        <div class='part-detail'>
            <div class='detail-left'>
                <div class='detail-left-content-container'>
                    <div class='left-title'><%=newsList[0].title%></div>
                    <a class='left-button' href='<%=newsList[0].link%>' target='_blank'>
                        详细信息</a>
                </div>
            </div>
            <div class='detail-right'>
                <div class='detail-right-content-container'>
                    <div class='detail-content1'>
                        <div class='spliter-container'>
                            <div class='spliter'></div>
                        </div>
                        <div class='detail-content-container'>
                            <div class='content-title'><%=newsList[1].publishDate%></div>
                            <div class='content-main'>
                                <a href='<%=newsList[1].link%>' target='_blank'>
                                    <%=newsList[1].title%></a>
                            </div>
                        </div>
                    </div>
                    <div class='detail-content2'>
                        <div class='spliter-container'>
                            <div class='spliter'></div>
                        </div>
                        <div class='detail-content-container'>
                            <div class='content-title'><%=newsList[2].publishDate%></div>
                            <div class='content-main'>
                                <a href='<%=newsList[2].link%>' target='_blank'>
                                    <%=newsList[2].title%></a>
                            </div>
                        </div>
                    </div>
                    <div class='detail-content3'>
                        <div class='spliter-container'>
                            <div class='spliter'></div>
                        </div>
                        <div class='detail-content-container'>
                            <div class='content-title'><%=newsList[3].publishDate%></div>
                            <div class='content-main'>
                                <a href='<%=newsList[3].link%>' target='_blank'><%=newsList[3].title%></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>`
	container: JQuery
	element: JQuery
	constructor() {
		this.container = $('.fn-news-container')
		this.render()
		this.loadData().then((data) => {
			if (data.state) {
				this.render(data.result)
			}
		})
	}
	render(newsList = []) {
		const defaultNewsList = [
			new NewsInfo('', '', '', ''),
			new NewsInfo('', '', '', ''),
			new NewsInfo('', '', '', ''),
			new NewsInfo('', '', '', '')
		]
		let tempList = _(newsList).map(it => {
			let publishDate = ''
			if (it.publishDate) {
				publishDate = moment(it.publishDate).format('YYYY-MM-DD')
			}
			if (it.link) {
				it.link = `home/newsInfo?id=${it.id}`
			}
			return new NewsInfo(it.title || '', publishDate, it.link || '', it.description || '')
		}).value()
		tempList = [...tempList, ...defaultNewsList]
		this.element && this.element.remove()
		this.element = $(_.template(this.template)({ newsList: tempList })).appendTo(this.container)
	}
	loadData() {
		return $.get('home/news/getLastList?size=4')
	}
}
class NewsInfo {
	constructor(public title, public publishDate, public link, public description) { }
}
class HoverChanger {

	container: JQuery
	defaultContainer: JQuery
	activeContainer: JQuery
	init({ container, defaultElement, hoverElement }) {
		this.container = container
		this.defaultContainer = defaultElement
		this.activeContainer = hoverElement
		this.defaultContainer.css({
			'z-index': 0,
			'position': 'absolute',
			'width': '100%'
		})
		this.activeContainer.css({
			'z-index': 1,
			'position': 'absolute',
			'top': 0,
			'display': 'none',
			'width': '100%'
		})
	}

	public start() {
		this.container.hover(() => {
			this.activeContainer.stop().slideDown()
			// this.activeContainer.removeClass('hidden animated flipInY')
			// 	.addClass('animated flipInY').one('animationend', () => {
			// 		this.activeContainer.removeClass('animated flipInY')
			// 	})
		}, () => {
			this.activeContainer.stop().slideUp()
			// this.activeContainer.removeClass('hidden animated flipOutY')
			// 	.addClass('animated flipOutY').one('animationend', () => {
			// 		this.activeContainer.removeClass('animated flipOutY')
			// 		this.activeContainer.addClass('hidden')
			// 	})
		})
	}
	public stop() {
		this.container.off('hover')
	}
}