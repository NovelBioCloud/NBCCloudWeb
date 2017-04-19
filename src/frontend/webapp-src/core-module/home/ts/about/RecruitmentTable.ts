

class Recruitment {
    id: string
    name: string
    type: string
    number: number
    description: string
    publishTime: string
    link: string
    workLocation: string
}

/**
 * 招聘信息
 * 
 * @export
 * @class RecruitmentTable
 */
export class RecruitmentTable {

    container: JQuery
    table: JQuery
    template = `<div class='recruitment-table'>
            <div class='recruitment-table-title'>
                <div class='recruitment-table-title-column'>职位名称</div>
                <div class='recruitment-table-title-column'>职位类别</div>
                <div class='recruitment-table-title-column'>招聘人数</div>
                <div class='recruitment-table-title-column'>工作地点</div>
                <div class='recruitment-table-title-column'>发布时间</div>
            </div>  
        </div>`

    constructor({ container }) {
        this.container = container
        this.table = $(this.template).appendTo(this.container)
        this.loadData()
    }
    private createTd(data: Recruitment, odd: boolean) {
        const flag = odd ? 'odd' : 'even'
        return $(`<div class='recruitment-table-row ${flag}'>
            <div class='recruitment-table-row-column'>` +
            (data.link ? `<a href='${data.link}' target='_blank'>${data.name}</a>` : `${data.name}`) +
            `</div>` +
            `<div class='recruitment-table-row-column'>${data.type}</div>
            <div class='recruitment-table-row-column'>${data.number}</div>
            <div class='recruitment-table-row-column'>${data.workLocation}</div>
            <div class='recruitment-table-row-column'>${data.publishTime}</div>
        </div>`)
    }
    private loadData() {
        this._loadData().then((datas: Recruitment[] = []) => {
            datas.forEach((data, index) => {
                this.table.append(this.createTd(data, index % 2 === 0))
            })
        }).catch(console.log)
    }
    private _loadData(): Promise<Recruitment[]> {
        return new Promise<Recruitment[]>((resolve, reject) => {
            $.ajax({
                url: 'home/getListRecruitment',
                method: 'post',
                success: (data) => {
                    if (data.state) {
                        resolve(data.result)
                    } else {
                        reject()
                    }
                },
                error: reject
            })
        })
    }
}