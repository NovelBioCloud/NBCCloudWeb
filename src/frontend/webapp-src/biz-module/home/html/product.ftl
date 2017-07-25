<#import "common/page.ftl" as page>
<!doctype html>
<html>
<head>
	<@page.head>
		<title>功能介绍</title>
		<link rel="stylesheet" type="text/css" href="${contextPath}/biz-module/home/css/product.css" ></link>
		<script type="text/javascript" src="${contextPath}/biz-module/home/js/product.js" ></script>
	</@page.head>
</head>
<body>
	<@page.body>
	<div class='nb-content nb-page-product'>
		<div class='nb-content-row'>
			<#include "product/content.ftl">
		</div>
	</div>
	</@page.body>
</body>
</html>

