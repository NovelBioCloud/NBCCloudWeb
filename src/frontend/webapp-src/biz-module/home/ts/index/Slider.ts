import * as _ from 'lodash'
import { Subject } from 'rxjs'

interface ISliderOptions {
	index: number
	size: number
	duration: number
	enable: boolean
}
export class Slider {
	private timer: any
	private onChangeSubject = new Subject()
	index: number = 0
	size: number = 5
	duration: number = 10000
	enable: boolean
	constructor(options: ISliderOptions) {
		this.index = options.index || 0
		this.size = options.size || 5
		this.duration = options.duration || 10000
		this.enable = !!options.enable
		this.startTimer()
	}
	startTimer() {
		const timeAction = () => {
			if (this.enable) {
				this.step(1)
			}
		}
		this.timer = setInterval(timeAction, this.duration)
	}
	go(newIndex: number) {
		this.index = newIndex % this.size
		this.onChangeSubject.next(this.index)
	}
	step(stepSize: number) {
		this.go(this.index + stepSize)
	}
	onChange(onChange: (index: number) => void) {
		this.onChangeSubject.subscribe(onChange)
	}
	dispose() {
		clearInterval(this.timer)
	}
}

