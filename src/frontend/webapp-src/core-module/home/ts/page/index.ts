import * as polyfill from '../common/polyfill'

import { BackgroundSlider } from '../index/BackgroundSlider'
import * as $ from 'jquery'
import * as _ from 'lodash'

$(() => {
    new BackgroundSlider()
    $('.fn-slider-animate').each((index, element) => {
        const slider = new Slider()
        slider.init({
            container: $(element),
        })
    })
})
class Slider {

    container: JQuery
    defaultContainer: JQuery
    activeContainer: JQuery
    init({ container }) {
        this.container = container
        this.defaultContainer = this.container.find('.fn-slider-animate-default')
        this.activeContainer = this.container.find('.fn-slider-animate-active')
        this.defaultContainer.css({
            'z-index': 0,
            'position': 'absolute',
            width: '100%'
        })
        this.activeContainer.css({
            'z-index': 1,
            'position': 'absolute',
            'transition': 'all 0.5s ease',
            'top': 430,
            width: '100%'
        })
        const height = this.container.height()
        this.container.hover(() => this.activeContainer.css('top', 0),
            () => this.activeContainer.css('top', 430))
    }

}