import * as _ from 'lodash'
import * as moment from 'moment'
interface JQuery {
	tabs: (...args) => any
	datagrid: (...args) => any
	tree: (...args) => any
}
declare const $: any

/**
 * docsite 的新闻选择器
 * todo
 */
export class FileSelector {
	private dialog: any
	constructor() {

	}
	init() {
		const dialogContent = $('<ul/>')
		this.dialog = $('<div/>').dialog({
			title: '新建信息',
			width: 600,
			height: 400,
			closed: false,
			modal: true,
			buttons: [{
				iconCls: 'icon-save',
				text: '保存',
				handler: () => {

				}
			}, {
				iconCls: 'icon-cancel',
				text: '取消',
				handler: () => {

				}
			}]
		})
		this.dialog.append(dialogContent)
		dialogContent.tree()
	}
	setSelected(data = null) {

	}
}