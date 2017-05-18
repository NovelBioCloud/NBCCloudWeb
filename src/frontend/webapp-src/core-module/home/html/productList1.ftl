<#import "common/page.ftl" as page>
<!doctype html>
<html>

<head>
	<@page.head>
		<title>功能介绍</title>
		<link rel="stylesheet" type="text/css" href="core-module/home/css/productList.css" />
		<script type="text/javascript" src="core-module/home/js/product.js" ></script>
	</@page.head>
</head>
<body>
	<@page.body>
	<div class='nb-content nb-page-productList'>
		<div class='nb-content-row'>
			<#include "product/partContent1.ftl">
		</div>
	</div>
	</@page.body>
</body>
</html>

