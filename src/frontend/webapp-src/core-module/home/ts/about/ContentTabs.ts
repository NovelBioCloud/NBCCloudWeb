import { Subject } from 'rxjs'
import * as _ from 'lodash'
import { HistoryPanel } from './HistoryPanel'
import { RecruitmentTable } from './RecruitmentTable'

interface ContentTabsSupportOptions {
    index: number
    size: number
}
class ContentTabsSupport {
    index: number
    size: number
    private onChangeSubject = new Subject()
    constructor(options: ContentTabsSupportOptions) {
        this.index = options.index
        this.size = options.size
    }
    onChange(onChange: (index) => void) {
        this.onChangeSubject.subscribe(onChange)
    }

    go(newIndex: number) {
        this.index = newIndex % this.size
        this.onChangeSubject.next(this.index)
    }
}
/**
 * 主面板类，该类展示about页面的全部内容
 * 
 * @export
 * @class ContentTabs
 * @extends {ContentTabsSupport}
 */
export class ContentTabs extends ContentTabsSupport {
    private titles: JQuery
    private contents: JQuery
    public static partNames = ['introduction', 'team', 'culture', 'history', 'join', 'contact']
    constructor(partName: string = 'introduction') {
        super({
            index: 0,
            size: 6
        })
        const element = $('.fn-container-tabs')
        this.titles = $('.fn-about-part-title')
        this.contents = $('.fn-about-part-content')
        this.titles.each((index, element) => {
            $(element).click(() => {
                if (this.index !== index) {
                    this.go(index)
                }
            })
        })
        this.onChange(this.change.bind(this))
        let index = ContentTabs.partNames.findIndex((value) => partName === value)
        if (index < 0) {
            index = 0
        }
        this.change(index)


        const historyPanel = new HistoryPanel({
            container: element.find('.fn-historyPanel-container')
        })
        const recruitmentTable = new RecruitmentTable({
            container: element.find('.fn-recruitmentTable-container')
        })
    }
    private change(index) {
        location.hash = ContentTabs.partNames[index]
        _.times(this.size, (current) => {
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