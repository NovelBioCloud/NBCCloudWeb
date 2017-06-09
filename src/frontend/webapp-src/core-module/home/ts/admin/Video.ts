import * as _ from 'lodash'
import * as moment from 'moment'
import axios from 'axios'
import { ImageSelector } from '../imageSelector/ImageSelector'
import { VideoSelector } from '../videoSelector/VideoSelector'
declare const $: any
export class Video {
    element: JQuery
    container: JQuery
    id: string = '视频管理'
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
                    title: '发布日期',
                    field: 'publishDate',
                    width: 100,
                    formatter: function (value, row, index) {
                        return moment(value).format('YYYY-MM-DD')
                    }
                },
                { title: '标题', field: 'title', width: 300 },
                { title: '图片链接', field: 'image', width: 300 },
                { title: '视频链接', field: 'link', width: 300 },
                { title: '简介', field: 'description', width: 500 },
            ]]
        })
        window['formatter'] = function (row) {
            let s = `<div>
                <div style="font-weight:bold">${row.name}</div>
                <div>
                    <img style="max-width:50px;max-height:50px;" src="${row.inputStream}"></img>
                </div>
            </div>`
            return s
        }
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
        this.bindEvent(dialogContent)
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
                url: 'admin/video/remove',
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
            title = '',
            image = '',
            link = '',
            publishDate = '',
            description = '',
        } = data

        return `<div>
                    <style type="text/css">
                        .video-dialog-content {
                            padding : 30px 50px 30px 90px;
                        }
                        .video-dialog-content td {
                            padding : 5px 8px;
                        }
                        .video-dialog-content input, .video-dialog-content select {
                            width : 300px;
                        }
                        .video-dialog-content textarea {
                            width : 300px;
                            height : 180px;
                        }
                    </style>
                    <div class='video-dialog-content'>
                        <form>
                            <input type='hidden' name='id' value ='${id}'/>
                            <table>
                                <tr>
                                    <td>视频链接:</td>
                                    <td><input class="easyui-validatebox fn-link" type="text" name="link"
                                        data-options="required:true" value="${link}"></input>
                                        <button class='fn-add-video' type='button'>选择</button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>标题:</td>
                                    <td><input class="easyui-validatebox" type="text" name="title"
                                    data-options="required:true," value="${title}"></input></td>
                                </tr>
                                <tr>
                                    <td>发布日期:</td>
                                    <td><input class="easyui-datebox" type="text" name="publishDate"
                                    data-options="required:true,validType:'datebox'" value="${publishDate}"></input></td>
                                </tr>
                                <tr>
                                    <td>图片链接:</td>
                                    <td>
                                        <input class="easyui-validatebox" type="text"  name='image' value='${image}'/>
                                        <br/>
                                        <img class='fn-image-img' style='max-width:300px;max-height:200px;' src='${image}'></img>
                                        <br/>
                                        <button class='fn-add-image' type='button'>选择</button>
                                        <button class='fn-refresh-image' type='button'>刷新</button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>描述:</td>
                                    <td>
                                        <textarea class="easyui-textarea" name="description"
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
        this.bindEvent(dialogContent)
        dialog.dialog('open').dialog('center')
    }
    private bindEvent(dialogContent) {
        let selectImage = (data) => {
            if (data) {
                let value = `cloudFile/getInputStream?id=` + data.id
                dialogContent.find('[name="image"]').val(value)
                dialogContent.find('.fn-image-img').prop('src', value)
            }
        }
        let selectVideo = (data) => {
            if (data) {
                let value = `cloudFile/getInputStream?id=` + data.id
                dialogContent.find('[name="link"]').val(value)
            }
        }
        dialogContent.find('.fn-image-img').click(() => {
            new ImageSelector().init({
                isSingleSelect: true,
                onSelect: selectImage
            })
        })
        dialogContent.find('.fn-add-image').click(() => {
            new ImageSelector().init({
                isSingleSelect: true,
                onSelect: selectImage
            })
        })
        dialogContent.find('.fn-refresh-image').click(() => {
            let value = dialogContent.find('[name="image"]').val()
            dialogContent.find('.fn-image-img').prop('src', value)
        })
        dialogContent.find('.fn-add-video').click(() => {
            new VideoSelector().init({
                isSingleSelect: true,
                onSelect: selectVideo
            })
        })
    }
    create(postData) {
        return new Promise((resolve, reject) => {
            $.ajax({
                url: 'admin/video/create',
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
                url: 'admin/video/update',
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
                url: 'admin/video/getList',
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