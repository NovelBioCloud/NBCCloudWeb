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
    private sliderContainer: JQuery
    private slider: Slider
    // public static partNames = ['introduction', 'team', 'culture', 'history', 'join', 'contact']
    public static partNames = ['introduction', 'culture', 'history', 'join', 'contact']
    constructor(partName: string = 'introduction') {
        const element = $('.fn-container-tabs')
        this.titles = $('.fn-about-part-title')
        this.contents = $('.fn-about-part-content')
        this.sliderContainer = $('.fn-slider-container')
        this.slider = new Slider()
        this.slider.init({
            container: this.sliderContainer,
            length: ContentTabs.partNames.length
        })

        window.addEventListener('hashchange', this.onHashChange.bind(this))
        this.titles.each((index, element) => {
            const partName = ContentTabs.partNames[index]
            $(element).attr('id', partName)
            $(element).click(() => {
                this.showPart(partName)
            }).hover(() => {
                this.showPart(partName)
                // this.slider.slideTo(index)
            }, () => {
                this.slider.slideToCurrent()
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
                const currentContent = this.contents.eq(current).addClass('active')
                currentContent.stop(true)
                currentContent.animate({ opacity: 0 }, 0)
                currentContent.animate({ opacity: 1 }, 'slow')
            } else {
                this.titles.eq(current).removeClass('active')
                this.contents.eq(current).removeClass('active')
            }
        })
        this.slider.setCurrent(index)
        this.slider.slideToCurrent()

    }
}

class Slider {
    private container: JQuery
    private element: JQuery
    private template: string = `<div class='class-slider'>
        <style>
            .class-slider{
            }
            .class-slider .slider-part{
                background: #1E88F5;
                box-shadow: 0 1px 15px 0 rgba(30, 136, 245, 0.3);
                color: #fff;
                width: 120px;
                height: 8px;
                margin: 4px auto;
                border-top-left-radius: 4px;
                border-top-right-radius: 4px;
                transition:left 0.5s ease;
            }
        </style>
        <div class='fn-slider slider-part'></div>
    </div>`
    private sliderContainer: JQuery
    private slider: JQuery
    private length: number
    private index: number
    init({ container, length }) {
        this.sliderContainer = $(this.template).appendTo(container)
        this.slider = this.sliderContainer.find('.fn-slider')
        this.sliderContainer.css('position', 'relative')
        this.slider.css('position', 'absolute')
        this.length = length
    }
    slideTo(index) {
        const width = this.sliderContainer.width()
        const sliderWidth = this.slider.width()
        const partWidth = width / this.length
        const leftOrigin = partWidth * index
        const leftMargin = (partWidth - sliderWidth) / 2
        const left = leftOrigin + leftMargin
        this.slider.css('left', left)
    }
    setCurrent(index) {
        this.index = index
    }
    slideToCurrent() {
        this.slideTo(this.index)
    }
}