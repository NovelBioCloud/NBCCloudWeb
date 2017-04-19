<#import "common/page.ftl" as page>
<!doctype html>
<html>

<head>
	<@page.head>
	<title>关于我们</title>
  	<link rel="stylesheet" type="text/css" href="core-module/home/css/about.css" />
	<script type="text/javascript" src="core-module/home/js/about.js" ></script>
	</@page.head>
</head>
<body>
	<@page.body>
	<div class='nb-content nb-page-about'>
		<div class='nb-content-row fn-container-tabs'>
			<div class='nb-page-about-title'>
				<ul class='nb-page-about-ul'>
					<li class='fn-about-part-title nb-page-about-li active'>公司简介<div class='under-line'></div></li>
					<li class='fn-about-part-title nb-page-about-li'>管理团队<div class='under-line'></div></li>
					<li class='fn-about-part-title nb-page-about-li'>烈冰文化<div class='under-line'></div></li>
					<li class='fn-about-part-title nb-page-about-li'>发展经历<div class='under-line'></div></li>
					<li class='fn-about-part-title nb-page-about-li'>加入我们<div class='under-line'></div></li>
					<li class='fn-about-part-title nb-page-about-li'>联系我们<div class='under-line'></div></li>
				</ul>
			</div>
			<div class='nb-page-about-content'>
				<div class='fn-about-part-content nb-page-about-content-row active'>
					<#include "about/introduction.ftl">
				</div>
				<div class='fn-about-part-content nb-page-about-content-row'>
					<#include "about/manager.ftl">
				</div>
				<div class='fn-about-part-content nb-page-about-content-row'>
					<#include "about/culture.ftl">
				</div>
				<div class='fn-about-part-content nb-page-about-content-row'>
					<#include "about/history.ftl">
				</div>
				<div class='fn-about-part-content nb-page-about-content-row '>
					<#include "about/recruitment.ftl">
				</div>
				<div class='fn-about-part-content nb-page-about-content-row'>
					<#include "about/contact.ftl">
				</div>
			</div>
		</div>
	</div>
	</@page.body>
</body>
</html>

