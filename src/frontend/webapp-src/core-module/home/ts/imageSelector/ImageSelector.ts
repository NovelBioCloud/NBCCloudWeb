import * as $ from 'jquery'

class ImageList {
    template: string = `<div>
        <div>
            <ul class='fn-images-container'></ul>
        </div>
    </div>`
    imageTemplate: string = `<li style='margin:15px;display:inline-block;'></li>`
    element: JQuery
    container: JQuery
    imagesContainer: JQuery
    images: Image[] = []
    isSingleSelect: boolean = false
    init({ container, isSingleSelect }) {
        this.container = container
        this.element = $(this.template).appendTo(this.container)
        this.imagesContainer = this.element.find('.fn-images-container')
        this.isSingleSelect = isSingleSelect
    }
    getSelectData() {
        return this.images.filter(it => it.isSelect()).map(it => it.getData())
    }
    addImage(data) {
        const imageContainer = $(this.imageTemplate).appendTo(this.imagesContainer)
        const image = new Image({
            container: imageContainer,
            data: data,
            onClick: (data) => {
                if (this.isSingleSelect) {
                    this.setSingleSelect(data)
                } else {
                    this.setSelect(data)
                }
            }
        })
        this.images.push(image)
    }
    setSelect(data) {
        this.images.forEach(it => {
            if (it.getData() === data) {
                it.setSelect(true)
            }
        })
    }
    setSingleSelect(data) {
        this.images.forEach(it => {
            if (it.getData() === data) {
                it.setSelect(!it.isSelect())
            } else {
                it.setSelect(false)
            }
        })
    }
}

class Image {
    template: string = `<div class='class-image'>
        <style>
            .class-image{
                width:200px;
                height:200px;
            }
            .class-image .img{
                width:200px;
                height:130px;
            }
            .class-image .title{
                padding:5px;
                overflow:hidden;
            }
            .active-image{
                outline:1px solid #4cae4c;
            }
        </style>
        <div class='fn-img img'></div>
        <div class='fn-title title'></div>
    </div>`
    container: JQuery
    element: JQuery
    img: JQuery
    data
    onClick
    constructor({ container, data, onClick }) {
        this.container = container
        this.data = data
        this.onClick = onClick
        this.render()
    }
    private render() {
        this.element = $(this.template).appendTo(this.container)
        this.img = this.element.find('.fn-img').css({
            'background-image': `url(cloudFile/getInputStream?id=${this.data.id})`,
            'background-size': `contain`,
            'background-repeat': `no-repeat`,
            'background-position': 'center'
        })
        this.element.find('.fn-title').html(this.data.name)
        this.element.click(() => {
            this.onClick(this.data)
        })
    }
    getData() {
        return this.data
    }
    isSelect() {
        return this.element.hasClass('active-image')
    }
    setSelect(flag) {
        if (flag) {
            this.element.addClass('active-image')
        } else {
            this.element.removeClass('active-image')
        }
    }
}
export class ImageSelector {

    init({ isSingleSelect, onSelect }) {
        const imageList = new ImageList()
        const dialog = $('<div/>').dialog({
            title: '图片选择',
            width: 800,
            height: 500,
            closed: false,
            modal: true,
            buttons: [{
                iconCls: 'icon-save',
                text: '保存',
                handler: () => {
                    const image = imageList.getSelectData().find(it => true) || null
                    if (image) {
                        onSelect(image)
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
        imageList.init({
            container: dialog.dialog('body'),
            isSingleSelect: isSingleSelect
        })
        dialog.dialog('open').dialog('center')
        this.loadData().then(data => {
            if (data.state) {
                const imageDatas: any[] = data.result
                imageDatas.forEach(it => imageList.addImage(it))
            }
        })
    }
    loadData() {
        return $.post('cloudFile/getImageList')
    }
}