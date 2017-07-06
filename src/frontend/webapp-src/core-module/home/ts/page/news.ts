import * as polyfill from '../common/polyfill'
import * as moment from 'moment'
import * as _ from 'lodash'
declare const $

$(() => {
	const newsContent = $('.fn-news-content')
	new NewsApp().init({ container: newsContent })
})
class NewsApp {
	container: JQuery
	init({ container }) {
		const studyContainer = $(`<div></div>`).appendTo(container)
		const newsContainer = $(`<div></div>`).appendTo(container)
		new StudyModule().init({
			container: studyContainer
		})
		new NewsModule().init({
			container: newsContainer
		})
	}
}
class StudyModule {
	template: string = `<div id='study' class='study-list'>
        <div class='study-title'>
            <h1>学习资料</h1>
        </div>
        <div class='study-media'>
            <div class='fn-first-item-container study-media-player'>
            </div>
            <div class='study-media-list'>
                <div class='fn-items-container study-media-items'>
                </div>
                <div class='study-media-pagination'>
                </div>
            </div>
        </div>
    </div>`
	firstItemTemplate: string = `<div class='study-media-item first-item'>
        <img class='fn-video-image video-image'/>
        <div class='fn-video-play video-play'></div>
        <div class='fn-video-title video-title'></div>
    </div>`
	itemTemplate: string = `<div class='study-media-item'>
        <img class='fn-image'/>
    </div>`
	element: JQuery
	init({ container }) {
		this.element = $(this.template).appendTo(container)
		this.loadData().then(data => {
			if (data.state) {
				const result: any[] = data.result
				_(result).forEach((item, index) => {
					if (index === 0) {
						this.createFirstItem(item)
					}
					this.createItem(item)
				})
			}
		})
	}
	loadData() {
		return $.post('home/video/getList')
	}
	createFirstItem(data) {
		this.element.find('.fn-first-item-container').empty()
		const item = $(this.firstItemTemplate).appendTo(this.element.find('.fn-first-item-container'))
		item.find('.fn-video-title').html(data.title)
		item.find('.fn-video-play').click(() => {
			window.open(data.link)
		})
		item.find('.fn-video-image').prop('src', data.image)
	}
	createItem(data) {
		const item = $(this.itemTemplate).appendTo(this.element.find('.fn-items-container'))
		item.find('.fn-image').prop('src', data.image).click(() => {
			this.createFirstItem(data)
		})
		item.find('.fn-title').html(data.title).click(() => {
			this.createFirstItem(data)
		})
		item.find('.fn-publishDate').html(moment(data.publishDate).format('YYYY-MM-DD'))
		item.find('.fn-description').html(data.description)
	}
}

class NewsModule {
	template: string = `<div id='message' class='message-list'>
        <div class='message-title'>
            <h1>行业动态</h1>
        </div>
        <div class='fn-message-items'></div>
    </div>`
	itemTemplate: string = `<div class='message-item'>
        <div class='message-item-image'>
            <img class='fn-image' style='cursor:pointer;'/>
        </div>
        <div class='message-item-text'>
            <div class='fn-title message-item-title' style='cursor:pointer;'></div>
            <div class='fn-publishDate message-item-time'></div>
            <div class='fn-description message-item-content'>
            </div>
        </div>
    </div>`
	element: JQuery
	init({ container }) {
		this.element = $(this.template).appendTo(container)
		this.loadData().then((data) => {
			if (data.state) {
				_(data.result).forEach(it => {
					this.createItem(it)
				})
			}
		})
	}
	createItem(data) {
		const item = $(this.itemTemplate).appendTo(this.element.find('.fn-message-items'))
		item.find('.fn-image').prop('src', data.image).click(() => {
			window.open('home/newsInfo?id=' + data.id)
		})
		item.find('.fn-title').html(data.title).click(() => {
			window.open('home/newsInfo?id=' + data.id)
		})
		item.find('.fn-publishDate').html(moment(data.publishDate).format('YYYY-MM-DD'))
		item.find('.fn-description').html(data.description)

	}
	loadData() {
		return $.post('home/news/getList')
	}
}