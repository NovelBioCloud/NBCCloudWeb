module.exports = {
	clear : [ 'webapp/base-lib/lib' ],
	copy : [ {
		source : 'webapp-src/base-lib/lib/bootstrap/**/*',
		target : 'webapp/base-lib/lib/bootstrap/',
	}, {
		source : 'webapp-src/base-lib/lib/font-awesome/**/*',
		target : 'webapp/base-lib/lib/font-awesome/',
	} , {
		source : 'webapp-src/base-lib/lib/bootstrap-dialog/**/*',
		target : 'webapp/base-lib/lib/bootstrap-dialog/',
	} , {
		source : 'webapp-src/base-lib/lib/toastr/**/*',
		target : 'webapp/base-lib/lib/toastr/',
	} , {
		source : 'webapp-src/base-lib/lib/jquery/**/*',
		target : 'webapp/base-lib/lib/jquery/',
	}, {
		source : 'webapp-src/base-lib/lib/jquery-easyui/**/**/**/**/**/*',
		target : 'webapp/base-lib/lib/jquery-easyui/',
	} ],
}
