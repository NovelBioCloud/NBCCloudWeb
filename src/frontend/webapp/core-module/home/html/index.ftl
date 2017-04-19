<#import "common/page.ftl" as page/>
<!doctype html>
<html>

<head>
	<@page.head>
		<title>首页</title> 
		
		<link rel="stylesheet" type="text/css" href="core-module/home/css/index.css" />
		<script type="text/javascript" src="core-module/home/js/index.js" ></script>
	</@page.head>
</head>
<body>
	<@page.body>
	<div class='nb-content nb-page-index'>
		<div class='nb-content-row'>
			<#include "index/content.ftl">
		</div>
	</div>
	</@page.body>
</body>
</html>

