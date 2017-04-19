declare const $
import * as _ from 'lodash'
import { foo } from './easyui-ext'
import * as moment from 'moment'
import { Recruitment } from './Recruitment'
import { History } from './History'
export class AdminApp {
    template: string = `
    	<div title="信息管理" style="margin:auto;padding:5px 5px 5px 10px;">
        </div>`
    container: JQuery
    element: JQuery
    history: History
    recruitment: Recruitment
    private panel
    constructor() {
        this.container = $(document.body)
        this.element = $(this.template).appendTo(this.container)
        this.element.tabs({
            onSelect: function (title) {
            }
        })
        this.recruitment = this.createRecruitment()
        this.history = this.createHistory()
        this.element.tabs('select', this.recruitment.id)
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
}

