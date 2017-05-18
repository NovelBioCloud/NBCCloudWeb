import * as polyfill from '../common/polyfill'
import * as $ from 'jquery'
import axios from 'axios'
import * as moment from 'moment'

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
            <div class='study-media-player'>
                <img class='fn-image1'/>
            </div>
            <div class='study-media-list'>
                <div class='study-media-items'>
                    <div class='study-media-item'>
                        <img class='fn-image2'/>
                    </div>
                    <div class='study-media-item'>
                        <img class='fn-image3'/>
                    </div>
                    <div class='study-media-item'>
                        <img class='fn-image4'/>
                    </div>
                </div>
                <div class='study-media-pagination'>
                </div>
            </div>
        </div>
    </div>`
    init({ container }) {
        $(this.template).appendTo(container)
        this.loadData().then(data => {
            if (data.state) {

            }
        })
    }
    loadData() {
        return axios.post('home/video/getList').then(resp => resp.data)
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
            <img class='fn-image'/>
        </div>
        <div class='message-item-text'>
            <div class='fn-title message-item-title'></div>
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
                data.result.forEach(it => {
                    this.createItem(it)
                })
            }
        })
    }
    createItem(data) {
        const item = $(this.itemTemplate).appendTo(this.element.find('.fn-message-items'))
        item.find('.fn-image').prop('src', data.image).click(() => {
            console.log(data)
        })
        item.find('.fn-title').html(data.title).click(() => {
            console.log(data)
        })
        item.find('.fn-publishDate').html(moment(data.publishDate).format('YYYY-MM-DD'))
        item.find('.fn-description').html(data.description)

    }
    loadData() {
        return axios.post('home/news/getList').then(resp => {
            return resp.data
        })
    }
}