import * as polyfill from '../common/polyfill'

import { ContentTabs } from '../about/ContentTabs'
declare const $

$(() => {
    const defaultPanel = (location.hash || '').replace('#', '')
    new ContentTabs(defaultPanel)
})