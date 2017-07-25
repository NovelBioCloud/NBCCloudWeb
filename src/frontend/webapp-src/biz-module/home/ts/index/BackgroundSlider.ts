import { Slider } from './Slider'
import * as $ from 'jquery'
import * as _ from 'lodash'
export class BackgroundSlider extends Slider {
	constructor() {
		super({
			index: 0,
			size: 3,
			enable: true,
			duration: 10000
		})
		this.onChange((index) => {
			this.change(index)
		})
		const items = $('.fn-backgroundSlider-control-item')
		items.each((index, elem) => {
			$(elem).hover(() => {
				this.dispose()
				if (this.index !== index) {
					this.go(index)
				}
			}, () => {
				this.startTimer()
			})
		})
		this.change(0)
	}
	change(index) {
		const backgrounds = $('.fn-nb-head-bg')
		const items = $('.fn-backgroundSlider-control-item')
		_.times(this.size, (current) => {
			const currentBackground = backgrounds.eq(current)
			const currentItem = items.eq(current)
			if (current === index) {
				currentBackground.animate({ opacity: 1 }, 600)
				currentItem.addClass('active')
			} else {
				currentBackground.animate({ opacity: 0 }, 600)
				currentItem.removeClass('active')
			}
		})
	}
}