import * as _ from 'lodash'
import * as moment from 'moment'
declare const $: any
export class CloudFile {
    element: JQuery
    container: JQuery
    id: string = '文件管理'
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
                text: '下载',
                iconCls: 'icon-download',
                handler: () => this.download()
            }, {
                text: '删除',
                iconCls: 'icon-remove',
                handler: () => this.openRemoveDialog()
            }],
            columns: [[
                { field: 'checkbox', checkbox: true, title: '', width: 50, align: 'center' },
                { title: '文件名', field: 'name', width: 300 },
                { title: '文件路径', field: 'path', width: 300 },
                { title: 'link', field: 'link', width: 300 },
                { title: '备注', field: 'description', width: 300 },
            ]]
        })
        this.loadData()
        this.resize()
        $(window).resize(_.throttle(() => this.resize(), 100))
    }
    resize() {
        this.element.datagrid('resize')
    }
    download() {
        const data = this.element.datagrid('getSelected')
        window.open(`cloudFile/download?id=${data.id}`)
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
                        let fileInput = form.find('[name=uploadFile]').get(0)
                        let file = fileInput.files[0]
                        let formData = new FormData()
                        formData.append('uploadFile', file, file.name)
                        formData.append('path', form.find('[name=path]').val())
                        formData.append('description', form.find('[name=description]').val())
                        this.create(formData).then(() => {
                            this.refresh()
                            dialog.dialog('close').dialog('destroy').remove()
                        }, msg => this.message(`保存失败${msg}!`))
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
                url: 'admin/cloudFile/remove',
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
            path = '',
            description = ''
        } = data
        return `<div>
                    <style type="text/css">
                        .cloudFile-dialog-content {
                            padding : 30px 50px 30px 90px;
                        }
                        .cloudFile-dialog-content td {
                            padding : 5px 8px;
                        }
                        .cloudFile-dialog-content input, .cloudFile-dialog-content select {
                            width : 300px;
                        }
                        .cloudFile-dialog-content textarea {
                            width : 300px;
                            height : 180px;
                        }
                    </style>
                    <div class='cloudFile-dialog-content'>
                        <form enctype='multipart/form-data'>
                            <table>
                                <tr>
                                    <td>上传文件:</td>
                                    <td><input class="easyui-filebox" type="text" name="uploadFile"
                                        data-options="required:true"></input>
                                    </td>
                                </tr>
                                <tr>
                                    <td>路径:</td>
                                    <td><input class="easyui-textbox" type="text" name="path"
                                        data-options="" value="${path}"></input></td>
                                </tr>
                                <tr>
                                    <td>备注:</td>
                                    <td><textarea class="easyui-textarea" type="text" name="description"
                                        data-options="required:true">${description}</textarea>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>
                </div>
            `
    }
    create(postData) {
        return new Promise((resolve, reject) => {
            $.ajax({
                url: 'admin/cloudFile/create',
                data: postData,
                method: 'post',
                cache: false,
                processData: false,
                contentType: false,
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
                url: 'admin/cloudFile/update',
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
                url: 'admin/cloudFile/getList',
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