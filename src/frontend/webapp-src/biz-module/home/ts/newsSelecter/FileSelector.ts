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
	private selected: any
	constructor() {
	}

	init(options: { onClick: (url: string) => void }) {
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
					const selected = dialogContent.tree('getSelected')
					if (selected) {
						options.onClick(`https://docsite.novelbrain.com/docsite/filePage?path=` + selected.path)
						this.dialog.dialog('close').dialog('destroy').remove()
					}
				}
			}, {
				iconCls: 'icon-cancel',
				text: '取消',
				handler: () => {
					this.dialog.dialog('close').dialog('destroy').remove()
				}
			}]
		})
		this.dialog.append(dialogContent)
		dialogContent.tree({
			url: 'https://docsite.novelbrain.com/docsite/category/get?id=04_news',
			loadFilter: (data) => {
				data.resources && _.isArray(data.resources) && data.resources.forEach(it => it.text = it.name)
				return data.resources
			}, formatter: (node) => {
				return node.text
			}
		})
	}
}