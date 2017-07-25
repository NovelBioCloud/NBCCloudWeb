<#import "common/page.ftl" as page/>
<!doctype html>
<html>

<head>
	<#include "common/lib.ftl"/>
	<title>首页</title> 
	<link rel="stylesheet" type="text/css" href="${contextPath}/core-module/home/css/index.css" />
	<script type="text/javascript" src="${contextPath}/core-module/home/js/index.js" ></script>
</head>
<body>
	<#include "common/head.ftl"/>
	<div class='nb-content nb-page-index'>
		<div class='nb-content-row'>
			<#include "index/content.ftl">
		</div>
	</div>
	<#include "common/foot.ftl"/>
</body>
</html>

