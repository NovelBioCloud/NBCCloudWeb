import { Subject } from 'rxjs'
import * as _ from 'lodash'
import { HistoryPanel } from './HistoryPanel'
import { RecruitmentTable } from './RecruitmentTable'


/**
 * 该类展示about页面的全部内容
 * 
 * @export
 * @class ContentTabs
 * @extends {ContentTabsSupport}
 */
export class ContentTabs {
    private titles: JQuery
    private contents: JQuery
    public static partNames = ['introduction', 'team', 'culture', 'history', 'join', 'contact']
    constructor(partName: string = 'introduction') {
        const element = $('.fn-container-tabs')
        this.titles = $('.fn-about-part-title')
        this.contents = $('.fn-about-part-content')
        window.addEventListener('hashchange', this.onHashChange.bind(this))
        this.titles.each((index, element) => {
            $(element).attr('id', ContentTabs.partNames[index])
            $(element).click(() => {
                this.showPart(ContentTabs.partNames[index])
            })
        })
        this.showPart(partName)
        const historyPanel = new HistoryPanel({
            container: element.find('.fn-historyPanel-container')
        })
        const recruitmentTable = new RecruitmentTable({
            container: element.find('.fn-recruitmentTable-container')
        })
    }
    private onHashChange(e: HashChangeEvent) {
        e.preventDefault()
        e.stopImmediatePropagation()
        const target = e.newURL.substring(e.newURL.indexOf('#') + 1)
        this.showPart(target)
    }
    private showPart(partName) {
        let index = ContentTabs.partNames.indexOf(partName)
        index = index >= 0 ? index : 0
        _.times(ContentTabs.partNames.length, (current) => {
            if (current === index) {
                this.titles.eq(current).addClass('active')
                this.contents.eq(current).addClass('active')
            } else {
                this.titles.eq(current).removeClass('active')
                this.contents.eq(current).removeClass('active')
            }
        })

    }
}