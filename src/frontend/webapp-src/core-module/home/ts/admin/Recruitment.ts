import * as _ from 'lodash'
import * as moment from 'moment'
declare const $: any
export class Recruitment {
    element: JQuery
    container: JQuery
    id: string = '招聘信息'
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
                { title: '岗位名称', field: 'name', width: 100 },
                { title: '岗位类型', field: 'type', width: 100 },
                { title: '招聘人数', field: 'number', width: 100 },
                { title: '工作地址', field: 'workLocation', width: 100 },
                {
                    title: '发布日期',
                    field: 'publishTime',
                    width: 100,
                    formatter: function (value, row, index) {
                        return moment(value).format('YYYY-MM-DD')
                    }
                },
                { title: '链接地址', field: 'link', width: 150 },
                { title: '岗位描述', field: 'description', width: 300 },
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
        return new Promise((resolve, reject) => {
            $.ajax({
                url: 'admin/recruitment/remove',
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
            name = '',
            type = '',
            number = '',
            publishTime = '',
            description = '',
            workLocation = '',
            link = '',
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
                                    <td>岗位名称:</td>
                                    <td><input class="easyui-textbox" type="text" name="name"
                                    data-options="required:true" value="${name}"></input></td>
                                </tr>
                                <tr>
                                    <td>岗位类型:</td>
                                    <td><input class="easyui-textbox" type="text" name="type"
                                    data-options="required:true" value="${type}"></input></td>
                                </tr>
                                <tr>
                                    <td>招聘人数:</td>
                                    <td><input class="easyui-textbox" type="text" name="number"
                                    data-options="required:true,validType:'integer'" value="${number}"></input></td>
                                </tr>
                                <tr>
                                    <td>发布日期:</td>
                                    <td><input class="easyui-datebox" type="text" name="publishTime"
                                    data-options="required:true,validType:'datebox'" value="${publishTime}"></input></td>
                                </tr>
                                <tr>
                                    <td>链接地址:</td>
                                    <td><input class="easyui-textbox" type="text" name="link"
                                    data-options="validType:'url'" value="${link}"></input></td>
                                </tr>
                                <tr>
                                    <td>工作地址:</td>
                                    <td><input class="easyui-textbox" type="text" name="workLocation"
                                    data-options="required:true" value="${workLocation}"></input></td>
                                </tr>
                                <tr>
                                    <td>岗位描述:</td>
                                    <td>
                                        <textarea class="easyui-textarea" type="text" name="description"
                                        data-options="required:true">${description}</textarea>
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
        return new Promise((resolve, reject) => {
            $.ajax({
                url: 'admin/recruitment/create',
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
                url: 'admin/recruitment/update',
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
                url: 'admin/recruitment/getList',
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