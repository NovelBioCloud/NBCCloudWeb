	<base href="${contextPath}/"/>
	<script language="JavaScript">
		document.oncontextmenu=new Function("event","event.returnValue=false;");
		document.onselectstart=new Function("event","event.returnValue=false;");
		function MM_goToURL() { //v3.0
			var i, args=arguments; 
			document.MM_returnValue = false;
			for (i=0; i<(args.length-1); i+=2){
				eval(args[i]+".location='"+args[i+1]+"'");
			} 
		}
	</script>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<link rel="shortcut icon" href="common-resource/themes/${theme}/images/logo.png" type="image/x-icon">

	<link rel="stylesheet" type="text/css" href="base-lib/lib/toastr/toastr.css"></link>
	<link rel="stylesheet" type="text/css" href="base-lib/lib/font-awesome/css/font-awesome.min.css" />
	<link rel="stylesheet" type="text/css" href="base-lib/lib/bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="base-lib/lib/bootstrap-dialog/css/bootstrap-dialog.min.css"></link>
	<script type="text/javascript" src="base-lib/lib/jquery/jquery-2.1.1.min.js" ></script>
	<script type="text/javascript" src="base-lib/lib/toastr/toastr.min.js"></script>
	<script type="text/javascript" src="base-lib/lib/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="base-lib/lib/bootstrap-dialog/js/bootstrap-dialog.min.js"></script>