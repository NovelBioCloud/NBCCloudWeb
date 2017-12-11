import * as _ from 'lodash'
interface JQueryExt extends JQuery {
	tabs: (...args) => any
	datagrid: (...args) => any
	dialog: (...args) => any
}
declare const $: JQueryStatic
class VideoList {
	template: string = `<div>
        <div>
            <video autoplay="autoplay" style='height:200px;margin:30px auto;display:block;' class='fn-player' controls="controls"></video>
        </div>
        <div>
            <ul class='fn-videos-container'></ul>
        </div>
    </div>`
	videoTemplate: string = `<li style='margin:15px;display:inline-block;'></li>`
	element: JQuery
	container: JQuery
	videosContainer: JQuery
	videos: Video[] = []
	isSingleSelect: boolean = false
	player
	init({ container, isSingleSelect }) {
		this.container = container
		this.element = $(this.template).appendTo(this.container)
		this.videosContainer = this.element.find('.fn-videos-container')
		this.player = this.element.find('.fn-player')
		this.isSingleSelect = isSingleSelect
	}
	getSelectData() {
		return _(this.videos).filter(it => it.isSelect()).map(it => it.getData()).value()
	}
	addVideo(data) {
		const videoContainer = $(this.videoTemplate).appendTo(this.videosContainer)
		const video = new Video({
			container: videoContainer,
			data: data,
			onClick: (data) => {
				if (this.isSingleSelect) {
					this.setSingleSelect(data)
				} else {
					this.setSelect(data)
				}
			},
			onPlay: (data) => {
				this.player.prop('src', '')
				if (data) {
					this.player.prop('src', `home/video/player?id=${data.id}`)
				}
			}
		})
		this.videos.push(video)
	}
	setSelect(data) {
		_(this.videos).forEach(it => {
			if (it.getData() === data) {
				it.setSelect(true)
			}
		})
	}
	setSingleSelect(data) {
		_(this.videos).forEach(it => {
			if (it.getData() === data) {
				it.setSelect(!it.isSelect())
			} else {
				it.setSelect(false)
			}
		})
	}
}

class Video {
	template: string = `<div class='class-video'>
        <style>
            .class-video{
                width:200px;
            }
            .class-video .title{
                padding:5px;
                display:inline-block;
                width:180px;
                overflow:hidden;
                margin:5px auto;
            }
            .active-video{
                outline:1px solid #4cae4c;
            }
        </style>
        <div class='fn-title title'></div>
    </div>`
	container: JQuery
	element: JQuery
	title: JQuery
	data
	onClick
	onPlay
	constructor({ container, data, onClick, onPlay }) {
		this.container = container
		this.data = data
		this.onClick = onClick
		this.onPlay = onPlay
		this.render()
	}
	private render() {
		this.element = $(this.template).appendTo(this.container)
		this.title = this.element.find('.fn-title').html(this.data.name)
		this.title.click(() => {
			this.onClick(this.data)
		})
		this.title.dblclick(() => {
			this.onPlay(this.data)
		})
	}
	getData() {
		return this.data
	}
	isSelect() {
		return this.title.hasClass('active-video')
	}
	setSelect(flag) {
		if (flag) {
			this.title.addClass('active-video')
		} else {
			this.title.removeClass('active-video')
		}
	}
}
export class VideoSelector {

	init({ isSingleSelect, onSelect }) {
		const videoList = new VideoList()
		const div: any = $('<div/>')
		const dialog = div.dialog({
			title: '图片选择',
			width: 800,
			height: 500,
			closed: false,
			modal: true,
			buttons: [{
				iconCls: 'icon-save',
				text: '保存',
				handler: () => {
					const video = videoList.getSelectData().find(it => true) || null
					if (video) {
						onSelect(video)
						dialog.dialog('close').dialog('destroy').remove()
					}
				}
			}, {
				iconCls: 'icon-cancel',
				text: '取消',
				handler: () => {
					dialog.dialog('close').dialog('destroy').remove()
				}
			}]
		})
		videoList.init({
			container: dialog.dialog('body'),
			isSingleSelect: isSingleSelect
		})
		dialog.dialog('open').dialog('center')
		this.loadData().then(data => {
			if (data.state) {
				const videoDatas: any[] = data.result
				_(videoDatas).forEach(it => videoList.addVideo(it))
			}
		})
	}
	loadData() {
		return $.post('cloudFile/getVideoList')
	}
}