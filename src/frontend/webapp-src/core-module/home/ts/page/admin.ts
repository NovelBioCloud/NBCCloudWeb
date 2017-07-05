import * as polyfill from '../common/polyfill'
import { AdminApp } from '../admin/AdminApp'
declare const $: JQueryStatic
import * as qs from 'querystring'

$(() => {
    $.post('admin/isLogin').then((data) => {
        if (data.state) {
            new AdminApp()
        } else {
            doLogin()
        }
    })
    function doLogin() {
        const loginTemplate = `<div class='login'>
            <style>
                .login{
                    width:500px;
                    margin:auto;
                }
            </style>
            <div>
            用户名:<input type='text' name='username'></input>
            </div>
            <div>
            密码：<input type='password' name='password'></input>
            </div>
            <div>
                <button type='button' class='fn-submit'>确认</button>
            </div>
            <div class='fn-message'></div>
        </div>`
        const login = $(loginTemplate).appendTo(document.body)
        const submit = login.find('.fn-submit')
        submit.click(() => {
            const username = login.find('[name="username"]').val()
            const password = login.find('[name="password"]').val()
            $.post('admin/login', { username: username, password: password }).then(data => {
                if (data.state) {
                    login.remove()
                    new AdminApp()
                } else {
                    login.find('.fn-message').html('验证失败')
                }
            })
        })
    }
})