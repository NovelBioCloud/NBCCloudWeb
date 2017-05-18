declare const $
import * as _ from 'lodash'
import { foo } from './easyui-ext'
import * as moment from 'moment'
import { Recruitment } from './Recruitment'
import { History } from './History'
import { News } from './News'
import { Video } from './Video'
import { Information } from './Information'
export class AdminApp {
    template: string = `
    	<div title="信息管理" style="margin:auto;padding:5px 5px 5px 10px;">
        </div>`
    container: JQuery
    element: JQuery
    history: History
    recruitment: Recruitment
    news: News
    video: Video
    information: Information
    private panel
    constructor() {
        this.container = $(document.body)
        this.element = $(this.template).appendTo(this.container)
        this.element.tabs({
            fit: true,
            onSelect: function (title) { }
        })
        this.recruitment = this.createRecruitment()
        this.history = this.createHistory()
        this.news = this.createNews()
        this.video = this.createVideo()
        this.information = this.createInformation()
        setTimeout(() => this.resize(), 100)
        $(window).resize(() => {
            this.resize()
        })
    }
    private resize() {
        this.element.tabs('resize')
    }
    createHistory(): History {
        let history = new History()
        this.element.tabs('add', {
            title: history.id,
            content: '',
            closable: false,
        })
        const historyContainer = this.element.tabs('getTab', history.id)
        history.init({
            container: historyContainer
        })
        return history
    }
    createNews(): News {
        let news = new News()
        this.element.tabs('add', {
            title: news.id,
            content: '',
            closable: false,
        })
        const newsContainer = this.element.tabs('getTab', news.id)
        news.init({
            container: newsContainer
        })
        return news
    }
    createVideo(): Video {
        let video = new Video()
        this.element.tabs('add', {
            title: video.id,
            content: '',
            closable: false,
        })
        const videoContainer = this.element.tabs('getTab', video.id)
        video.init({
            container: videoContainer
        })
        return video
    }
    createRecruitment(): Recruitment {
        let recruitment = new Recruitment()
        this.element.tabs('add', {
            title: recruitment.id,
            content: '',
            closable: false,
        })
        const recruitmentContainer = this.element.tabs('getTab', recruitment.id)
        recruitment.init({
            container: recruitmentContainer
        })
        return recruitment
    }
    createInformation(): Information {
        let information = new Information()
        this.element.tabs('add', {
            title: information.id,
            content: '',
            closable: false,
        })
        const informationContainer = this.element.tabs('getTab', information.id)
        information.init({
            container: informationContainer
        })
        return information
    }
}

