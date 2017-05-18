import * as polyfill from '../common/polyfill'


import { ContentTabs } from '../about/ContentTabs'
import * as $ from 'jquery'
import * as qs from 'querystring'
$(() => {
    const query = qs.parse('#test=fff&a=b')
    console.log(query)
})