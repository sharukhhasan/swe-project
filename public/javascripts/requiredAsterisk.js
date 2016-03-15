$( document ).ready(function() {
	var span = document.createElement('span')
	span.style.color = 'red'
	span.className = 'asterisk'
	$(".control-label").append(span);
	$(".asterisk").append(document.createTextNode("*"));
});