/* Raphael plugin SVG tag for World Map */
$(document).ready(function() {
	
	
	// Create Function
	function randomColor() {
		var letters = '0123456789ABCDEF'.split('');
		var color = '#';
		for (var i = 0; i < 6; i++)
			color = color + letters[Math.round(Math.random()*15)];
		return color;
	}
	
	var canvas = document.getElementById('worldmap_image');
	var paper = Raphael(canvas, 1000, 700);
	
	$("#worldmap_image").css("left", "210");//overflow inherit 강제화
	
	$.each(worldMapPathData, function (index, item) {
		// Create Path
		var path = paper.path(item['d']);
		
		// Stroke Path
		path.attr('stroke', item['stroke']);
		// Fill Path
		if(item['fill'] != 'none') {
				path.attr('fill', randomColor());		
		}
	});
});

/* Raphael plugin SVG tag for South Korean Map */
$(document).ready(function() {
	// Create Function
	function randomColor() {
		var letters = '0123456789ABCDEF'.split('');
		var color = '#';
		for (var i = 0; i < 6; i++)
			color = color + letters[Math.round(Math.random()*15)];
		return color;
	}
	
	var canvas = document.getElementById('koreamap_image');
	var paper = Raphael(canvas, 500, 716);
	
	$.each(koreaMapPathData, function (index, item) {
		// Create Path
		var path = paper.path(item['d']);
		
		// Stroke Path
		path.attr('stroke', item['stroke']);
		// Fill Path
		if(item['fill'] != 'none') {
				path.attr('fill', randomColor());		
		}
	});
});


/* Raphael plugin SVG tag for Canada Map */
$(document).ready(function() {
	// Create Function
	function randomColor() {
		var letters = '0123456789ABCDEF'.split('');
		var color = '#';
		for (var i = 0; i < 6; i++)
			color = color + letters[Math.round(Math.random()*15)];
		return color;
	} 
	
	var canvas = document.getElementById('canadamap_image');
	var paper = Raphael(canvas, 1000, 1000);
	
	$.each(canadaMapPathData, function (index, item) {
		// Create Path
		var path = paper.path(item['d']);
		
		// Stroke Path
		path.attr('stroke', item['stroke']);
		// Fill Path
		if(item['fill'] != 'none') {
				path.attr('fill', randomColor());		
		}
	});
});

/* Raphael plugin SVG tag for USA Map */
$(document).ready(function() {
	// Create Function
	function randomColor() {
		var letters = '0123456789ABCDEF'.split('');
		var color = '#';
		for (var i = 0; i < 6; i++)
			color = color + letters[Math.round(Math.random()*15)];
		return color;
	}
	
	var canvas = document.getElementById('usamap_image');
	var paper = Raphael(canvas, 1000, 716);
	
	$.each(usaMapPathData, function (index, item) {
		// Create Path
		var path = paper.path(item['d']);
		
		// Stroke Path
		path.attr('stroke', item['stroke']);
		// Fill Path
		if(item['fill'] != 'none') {
				path.attr('fill', randomColor());		
		}
	});
});