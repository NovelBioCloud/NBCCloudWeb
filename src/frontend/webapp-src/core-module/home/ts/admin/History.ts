import * as _ from 'lodash'
import * as moment from 'moment'
import { PathUtil } from '../common/PathUtil'
declare const $: any
interface JQuery {
	tabs: (...args) => any
	datagrid: (...args) => any
}
export class History {
	element: JQuery
	container: JQuery
	id: string = '公司发展'
	constructor() {

	}
	init({ container }) {
		this.container = container
		this.element = $('<table></table>').appendTo(container)
		this.element.datagrid({
			fit: true,
			striped: true,
			idField: 'id',
			singleSelect: true,
			loadMsg: '数据加载中...',
			emptyMsg: '没有数据',
			toolbar: [{
				text: '新建',
				iconCls: 'icon-add',
				handler: () => this.openCreateDialog()
			}, {
				text: '修改',
				iconCls: 'icon-edit',
				handler: () => this.openEditDialog()
			}, {
				text: '删除',
				iconCls: 'icon-remove',
				handler: () => this.openRemoveDialog()
			}],
			columns: [[
				{ field: 'checkbox', checkbox: true, title: '', width: 50, align: 'center' },
				{
					title: '日期',
					field: 'time',
					width: 100,
					formatter: function (value, row, index) {
						return moment(value).format('YYYY-MM-DD')
					}
				},
				{ title: '内容', field: 'content', width: 300 },
			]]
		})
		this.loadData()
		this.resize()
		$(window).resize(_.throttle(() => this.resize(), 100))
	}
	resize() {
		this.element.datagrid('resize')
	}
	openCreateDialog() {

		const dialogContentTemplate = this.getDialogContentTemplate({})
		const dialogContent = $(dialogContentTemplate)
		const dialog = $('<div/>').dialog({
			title: '新建信息',
			width: 600,
			height: 400,
			closed: false,
			modal: true,
			buttons: [{
				iconCls: 'icon-save',
				text: '保存',
				handler: () => {
					let form = dialogContent.find('form')
					if (form.form('validate')) {
						this.create(form.serializeArray()).then(() => {
							this.refresh()
							dialog.dialog('close').dialog('destroy').remove()
						}, (msg) => this.message(`保存失败${msg}!`))
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
		dialog.dialog('body').append(dialogContent)
		$.parser.parse(dialogContent)
		dialog.dialog('open').dialog('center')
	}
	openRemoveDialog() {
		const data = this.element.datagrid('getSelected')
		if (data) {
			$.messager.confirm('确认信息', '确认要删除数据吗？', (result) => {
				if (!result) { return }
				this.remove(data.id).then(() => {
					this.refresh()
				}, (msg) => {
					this.message(`删除失败:${msg}!`)
				})
			})
		}
	}

	remove(id) {
		return $.post(PathUtil.resolve('admin/history/remove'), { id })
	}
	getDialogContentTemplate(data): string {
		const {
			id = '',
			time = '',
			content = '',
		} = data
		return `<div>
					<style type="text/css">
						.history-dialog-content {
							padding : 30px 50px 30px 90px;
						}
						.history-dialog-content td {
							padding : 5px 8px;
						}
						.history-dialog-content input, .history-dialog-content select {
							width : 300px;
						}
						.history-dialog-content textarea {
							width : 300px;
							height : 180px;
						}
					</style>
					<div class='history-dialog-content'>
						<form>
							<input type='hidden' name='id' value ='${id}'/>
							<table>
								<tr>
									<td>发布日期:</td>
									<td><input class="easyui-datebox" type="text" name="time"
									data-options="required:true,validType:'datebox'" value="${time}"></input></td>
								</tr>
								<tr>
									<td>岗位描述:</td>
									<td>
										<textarea class="easyui-textarea" type="text" name="content"
										data-options="required:true">${content}</textarea>
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
			`
	}
	openEditDialog() {
		const data = this.element.datagrid('getSelected')
		if (!data) {
			return
		}
		const dialogContentTemplate = this.getDialogContentTemplate(data)

		const dialogContent = $(dialogContentTemplate)
		const dialog = $('<div/>').dialog({
			title: '修改信息',
			width: 600,
			height: 400,
			closed: false,
			modal: true,
			buttons: [{
				iconCls: 'icon-save',
				text: '保存',
				handler: () => {
					let form = dialogContent.find('form')
					if (form.form('validate')) {
						this.update(form.serializeArray()).then(() => {
							this.refresh()
							dialog.dialog('close').dialog('destroy').remove()
						}, () => this.message('保存失败'))
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
		dialog.dialog('body').append(dialogContent)
		$.parser.parse(dialogContent)
		dialog.dialog('open').dialog('center')
	}
	create(postData) {
		return $.post(PathUtil.resolve('admin/history/create'), postData).then(data => data.result)
	}
	update(postData) {
		return $.post(PathUtil.resolve('admin/history/update'), postData).then(data => data.result)
	}
	private _loadData() {
		return $.post(PathUtil.resolve('admin/history/getList')).then(data => data.result)

	}
	loadData() {
		this._loadData().then((data) => {
			this.element.datagrid({ data: data })
		}, (message = '未知原因') => {
			this.message(`获取数据失败:${message}`)
		})
	}
	refresh() {
		this.loadData()
	}
	message(message: string = '') {
		$.messager.show({
			title: '消息提示',
			msg: `${message}`,
			icon: 'error',
			fn: () => { }
		})
	}
}