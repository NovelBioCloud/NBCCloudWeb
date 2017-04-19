import * as polyfill from '../common/polyfill'

import { ContentTabs } from '../about/ContentTabs'
import * as $ from 'jquery'

$(() => {
    const defaultPanel = (location.hash || '').replace('#', '')
    new ContentTabs(defaultPanel)
})