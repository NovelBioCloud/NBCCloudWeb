import * as polyfill from '../common/polyfill'
import { AdminApp } from '../admin/AdminApp'
declare const $

$(() => {
    new AdminApp()
})