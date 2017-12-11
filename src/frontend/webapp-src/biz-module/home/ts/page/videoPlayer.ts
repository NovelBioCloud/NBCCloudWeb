const flvjs = require('flv.js')
import * as queryString from 'query-string'
export class VideoModule {
	template = `<div class='fn-video-module' style='text-align:center;'>
		<h1 class='fn-video-title' style='margin:50px auto 30px auto;'></h1>
		<div class='fn-video-player-container' style='padding:30px 0px 60px;margin:0 100px 80px 100px;background:hsl(210, 10%, 30%);'>
			<video class='fn-video-player' controls preload='auto' style='width:800px;height:500px;margin:auto;'/>
		</div>
	</div>`
	container: JQuery
	element: JQuery
	init({ container, id }: { container: JQuery, id: string }) {
		this.element = $(this.template).appendTo(container)
		const videoElement = this.element.find('.fn-video-player').get(0)
		if (flvjs.isSupported()) {
			const flvPlayer = flvjs.createPlayer({
				type: 'mp4',
				url: `cloudFile/getFileStream?id=${id}`
			})
			flvPlayer.attachMediaElement(videoElement)
			flvPlayer.load()
			flvPlayer.play()
		}
		$.post('cloudFile/getVideo', { id: id }).then(data => {
			if (data.state) {
				const rawName = data.result.name as string
				const name = rawName.substring(0, rawName.lastIndexOf('.'))
				this.element.find('.fn-video-title').html(name)
			}
		})
	}
}

$(() => {
	const param = queryString.parse(location.search)
	const id = param.id
	const container = $('.fn-flv-container')
	new VideoModule().init({
		container: container,
		id: id as string
	})
})
