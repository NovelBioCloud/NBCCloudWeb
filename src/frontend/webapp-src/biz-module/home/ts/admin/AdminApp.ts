declare const $: any
interface JQuery {
	tabs: (...args) => any
	select: (...args) => void
	datagrid: (...args) => any
	find: (...args) => any
}
import * as _ from 'lodash'
import { foo } from './easyui-ext'
import * as moment from 'moment'
import { Recruitment } from './Recruitment'
import { History } from './History'
import { News } from './News'
import { Video } from './Video'
import { Information } from './Information'
import { CloudFile } from './CloudFile'
declare const contextPath: string
export class AdminApp {
	template: string = `

		
		<div class='class-info-manager'>
			<div class='info-manager-title'>
				<div class='title-logo'></div>
				<div class='title-text'>
					<div class='text-inner'>
						信息管理
					</div>
				</div>
				<div class='logout-button-warp'>
					<button class='fn-logout-button logout-button'>退出登录</button>
				</div>
			</div>
			<div class='fn-info-manager-content info-manager-content'>
				<div class='fn-info-manage-tabs info-manage-tabs' title="信息管理"/>
			</div>
		</div>
	
		`
	container: JQuery
	element: JQuery
	infoManageTabs: JQuery
	history: History
	recruitment: Recruitment
	news: News
	video: Video
	information: Information
	cloudFile: CloudFile
	private panel
	constructor() {
		this.container = $(document.body)
		this.element = $(this.template).appendTo(this.container)
		this.infoManageTabs = this.element.find('.fn-info-manage-tabs')
		this.infoManageTabs.tabs({
			fit: true,
			onSelect: function (title) { }
		})
		this.recruitment = this.createRecruitment()
		this.history = this.createHistory()
		this.news = this.createNews()
		this.video = this.createVideo()
		this.information = this.createInformation()
		this.cloudFile = this.createCloudFile()
		this.logout()
		setTimeout(() => this.resize(), 100)
		$(window).resize(() => {
			this.resize()
		})
	}
	private resize() {
		this.infoManageTabs.tabs('resize')
	}
	createHistory(): History {
		let history = new History()
		this.infoManageTabs.tabs('add', {
			title: history.id,
			content: '',
			closable: false,
			selected: false,
		})
		const historyContainer = this.infoManageTabs.tabs('getTab', history.id)
		history.init({
			container: historyContainer
		})
		return history
	}
	createNews(): News {
		let news = new News()
		this.infoManageTabs.tabs('add', {
			title: news.id,
			content: '',
			closable: false,
			selected: false,
		})
		const newsContainer = this.infoManageTabs.tabs('getTab', news.id)
		news.init({
			container: newsContainer
		})
		return news
	}
	createVideo(): Video {
		let video = new Video()
		this.infoManageTabs.tabs('add', {
			title: video.id,
			content: '',
			closable: false,
			selected: false,
		})
		const videoContainer = this.infoManageTabs.tabs('getTab', video.id)
		video.init({
			container: videoContainer
		})
		return video
	}
	createRecruitment(): Recruitment {
		let recruitment = new Recruitment()
		this.infoManageTabs.tabs('add', {
			title: recruitment.id,
			content: '',
			closable: false,
		})
		const recruitmentContainer = this.infoManageTabs.tabs('getTab', recruitment.id)
		recruitment.init({
			container: recruitmentContainer
		})
		return recruitment
	}
	createInformation(): Information {
		let information = new Information()
		this.infoManageTabs.tabs('add', {
			title: information.id,
			content: '',
			closable: false,
			selected: false,
		})
		const informationContainer = this.infoManageTabs.tabs('getTab', information.id)
		information.init({
			container: informationContainer
		})
		return information
	}

	createCloudFile(): CloudFile {
		let cloudFile = new CloudFile()
		this.infoManageTabs.tabs('add', {
			title: cloudFile.id,
			content: '',
			closable: false,
			selected: false,
		})
		const cloudFileContainer = this.infoManageTabs.tabs('getTab', cloudFile.id)
		cloudFile.init({
			container: cloudFileContainer
		})
		return cloudFile
	}
	private logout() {
		this.element.find('.fn-logout-button').click(e => {
			$.post('admin/logout').then(() => {
				window.location.href = contextPath
			})
		})
	}
}

