import * as _ from 'lodash'
import * as moment from 'moment'
import axios from 'axios'
declare const $: any
export class Information {
    element: JQuery
    container: JQuery
    id: string = '基本信息'
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
                    title: 'key',
                    field: 'key',
                    width: 100,
                },
                { title: '名称', field: 'name', width: 300 },
                { title: '值', field: 'value', width: 300 },
            ]]
        })
        this.loadData()
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
        return new Promise((resolve, reject) => {
            $.ajax({
                url: 'admin/information/remove',
                method: 'post',
                data: { id: id },
                success: (data) => {
                    if (data.state) {
                        resolve()
                    } else {
                        reject()
                    }
                },
                error: reject
            })
        })
    }
    getDialogContentTemplate(data): string {
        const {
            id = '',
            key = '',
            name = '',
            value = ''
        } = data
        return `<div>
                    <style type="text/css">
                        .information-dialog-content {
                            padding : 30px 50px 30px 80px;
                        }
                        .information-dialog-content td {
                            padding : 5px 8px;
                        }
                        .information-dialog-content input, .information-dialog-content select {
                            width : 300px;
                        }
                        .information-dialog-content textarea {
                            width : 300px;
                            height : 180px;
                        }
                    </style>
                    <div class='information-dialog-content'>
                        <form>
                            <input type='hidden' name='id' value ='${id}'/>
                            <table>
                                <tr>
                                    <td>key:</td>
                                    <td><input class="easyui-textbox fn-key" type="text" name="key"
                                    data-options="required:true" value="${key}"></input>
                                    </td>
                                </tr>
                                <tr>
                                    <td>名称:</td>
                                    <td><input class="easyui-textbox" type="text" name="name"
                                    data-options="required:true,validType:'datebox'" value="${name}"></input></td>
                                </tr>
                                <tr>
                                    <td>值:</td>
                                    <td><input class="easyui-textbox" type="text" name="value"
                                    data-options="required:true,validType:'datebox'" value="${value}"></input></td>
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
        return new Promise((resolve, reject) => {
            $.ajax({
                url: 'admin/information/create',
                data: postData,
                method: 'post',
                success: (data) => {
                    if (data.state) {
                        resolve(data.result)
                    } else {
                        reject(data.message)
                    }
                },
                error: reject
            })
        })
    }
    update(postData) {
        return new Promise((resolve, reject) => {
            $.ajax({
                url: 'admin/information/update',
                data: postData,
                method: 'post',
                success: (data) => {
                    if (data.state) {
                        resolve(data.result)
                    } else {
                        reject(data.message)
                    }
                },
                error: reject
            })
        })
    }
    private _loadData(): Promise<any> {
        return new Promise((resolve, reject) => {
            $.ajax({
                url: 'admin/information/getList',
                method: 'post',
                success: (data) => {
                    if (data.state) {
                        resolve(data.result)
                    } else {
                        reject(data.message)
                    }
                },
                error: reject
            })
        })

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