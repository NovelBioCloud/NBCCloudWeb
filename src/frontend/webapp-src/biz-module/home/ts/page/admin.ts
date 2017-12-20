import * as polyfill from '../common/polyfill'
import { AdminApp } from '../admin/AdminApp'
declare const $: JQueryStatic
import * as qs from 'querystring'
import * as _ from 'lodash'
declare const toastr: any
declare const contextPath: string
$(() => {
	$.post('admin/isLogin').then((data) => {
		if (data.state) {
			new AdminApp()
		} else {
			doLogin()
		}
	})
	function doLogin() {
		const loginTemplate = `
	<div class='form-bg'>
		<div class="corporation-name">NovelBrain</div>
		<div class="container">
			<div class="row">
				<div class="col-md-offset-3 col-md-6">
					<form class="form-horizontal">
						<span class="heading">管理员登录</span>
						<div class="form-group">
							<input type="text" class="form-control fn-username username" name='username' placeholder="用户名">
							<i class="input-icon-group fa fa-user"></i>
						</div>
						<div class="form-group help">
							<input type="password" class="form-control fn-password password" name='password' placeholder="密　码">
							<i class="input-icon-group fa fa-lock"></i>
						</div>
						<div class="form-group">
							
						</div>
						<div class="form-group">
							<button type='button' class="fn-submit btn btn-default submit-btn">登 录</button>
							<button type='button' class="fn-back btn btn-default back-btn">返 回</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>	
		`
		const login = $(loginTemplate).appendTo(document.body)
		const submit = login.find('.fn-submit')
		submit.click(() => {
			const username = login.find('.fn-username').val()
			_.isEmpty(username) && toastr.warning('用户名不能为空')
			const password = login.find('.fn-password').val()
			_.isEmpty(password) && toastr.warning('密码不能为空')
			$.post('admin/login', { username: username, password: password }).then(data => {
				if (data.state) {
					login.remove()
					new AdminApp()
				} else {
					toastr.warning('验证失败')
				}
			})
		})
		const back = login.find('.fn-back')
		back.click(() => {
			window.location.href = contextPath
		})
	}
})