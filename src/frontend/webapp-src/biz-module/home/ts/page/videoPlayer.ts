const flvjs = require('flv.js')
import * as queryString from 'query-string'

export class VideoPlayer {
	public init({ url, videoElement }: { url: string, videoElement: HTMLElement }) {
		if (flvjs.isSupported()) {
			const flvPlayer = flvjs.createPlayer({
				type: 'mp4',
				url: url
			})
			flvPlayer.attachMediaElement(videoElement)
			flvPlayer.load()
			flvPlayer.play()
		}
	}
}

$(() => {
	const param = queryString.parse(location.search)
	const id = param.id
	const container = $('.fn-flv-container').get(0)
	container.style.textAlign = 'center'
	const video = document.createElement('video')
	video.style.width = '900px'
	video.style.margin = '100px'
	video.controls = true
	container.appendChild(video)
	new VideoPlayer().init({
		url: `cloudFile/getFileStream?id=${id}`,
		videoElement: video
	})
})
