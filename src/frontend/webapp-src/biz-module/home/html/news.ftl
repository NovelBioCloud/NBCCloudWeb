<#import "common/page.ftl" as page>
<!doctype html>
<html>
<head>
	<@page.head>
	<title>行业动态</title>
  	<link rel="stylesheet" type="text/css" href="${contextPath}/core-module/home/css/news.css" />
	<script type="text/javascript" src="${contextPath}/core-module/home/js/news.js" ></script>
	</@page.head>
</head>
<body>
	<@page.body>
	<div class='nb-content nb-page-news'>
		<div class='nb-content-row'>
			<#include "news/content.ftl">
		</div>
	</div>
	</@page.body>

</body>
</html>

