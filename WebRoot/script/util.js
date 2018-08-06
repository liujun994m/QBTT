/**
 * 
 */
/*用于根据窗口大小自动获取窗口可视区域宽和高*/
var winSize = function() { 
	var e = window, 
	a = 'inner'; 

	if (!('innerWidth' in window )){ 
		a = 'client'; 
		e = document.documentElement || document.body; 
	} 

	return { width : e[ a+'Width' ] , height : e[ a+'Height' ] }; 
}; 