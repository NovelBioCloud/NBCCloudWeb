<#import "common/page.ftl" as page/>
<!DOCTYPE>
<html>

<head>
	<title>首页</title> 
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<link rel="shortcut icon" href="common-resource/themes/${theme}/images/logo.png" type="image/x-icon">
	<base href="${contextPath}/"/>
	<link rel="stylesheet" type="text/css" href="base-lib/lib/toastr/toastr.css"></link>
	<link rel="stylesheet" type="text/css" href="base-lib/lib/font-awesome/css/font-awesome.min.css" />
	<link rel="stylesheet" type="text/css" href="base-lib/lib/bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="base-lib/lib/bootstrap-dialog/css/bootstrap-dialog.min.css"></link>
	<script type="text/javascript" src="base-lib/lib/jquery/jquery-1.11.3.min.js" ></script>
	<script type="text/javascript" src="base-lib/lib/toastr/toastr.min.js"></script>
	<script type="text/javascript" src="base-lib/lib/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="base-lib/lib/bootstrap-dialog/js/bootstrap-dialog.min.js"></script>
	<script>

	function test(){


		setTimeout(function(){console.log(123)},3000)
	}
	</script>
</head>
<body onload='test()'>

</body>
</html>

