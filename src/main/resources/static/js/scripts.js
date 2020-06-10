$(".answer-write input[type=submit]").click(addAnswer); // click 시 addAnswer
														// method call

function addAnswer(e) {
	e.preventDefault();
	console.log("click me");

	var queryString = $(".answer-write").serialize();// 사용자 입력요소에 값을 입력한것을
														// 'name1=value1@name2=value2'와
														// 같은 쿼리스트링형식 데이터로
														// 반환함(Ajax data 가공메소드)
	console.log("query : " + queryString);

	var url = $(".answer-write").attr("action"); // answer-write > action 속성
													// url 선택

	console.log("url : " + url);

	$.ajax({
		type : 'post',
		url : url,
		data : queryString,
		dataType : 'json',
		error : onError,
		success : onSuccess
	});
}

function onError() {

}

function onSuccess(data, status) {
	console.log(data);
	var answerTemplate = $("#answerTemplate").html(); // answerTemplate from
														// show.html
	var template = answerTemplate.format(data.writer.userId,
			data.formattedCreateDate, data.contents, data.question.id, data.id);

	$(".qna-comment-slipp-articles").prepend(template); // prepend with template

	$(".answer-write textarea").val(""); // refresh
}

$("a.link-Delete-article").click(deleteAnswer); // click 시 deleteAnswer method
												// call

function deleteAnswer(e) {
	e.preventDefault();

	var deleteBtn = $(this); // ajax 안에서도 동일한 this유지하기 위해 선언
	var url = deleteBtn.attr("href"); // link-Delete-article > herf 속성 url 선택
	console.log("url : " + url);

	$.ajax({
		type : 'delete',
		url : url,
		datatype : 'json',
		error : function(xhr, status) {
			console.log("error");
		},
		success : function(data, status) {
			console.log(data);
			if (data.valid) {
				deleteBtn.closest("article").remove();
			} else {
				alert(data.errorMessage);
			}
		}
	});
}


function loginValidate() {
    var userid = document.getElementById("userId").value;
    var password = document.getElementById("password").value;
    if (userid == null || userid == "") {
        alert("Please enter the User ID.");
        return false;
    }
    alert('Sign Up successful');
} 



function signupValidate() {
    var userid = document.getElementById("userId").value;
    var password = document.getElementById("password").value;
    if (userid == null || userid == "") {
        alert("Please enter the User ID.");
        return false;
    }
    alert('Sign Up successful');
} 


$(function() {
	$(".slide_gallery").bxSlider({
		auto : true,
		autoControls : true,
		stopAutoOnClick : true,
		pager : true
	});
});


$( function() {
    $( "#datepicker" ).datepicker({
      numberOfMonths: 3,
      showButtonPanel: true
    });
  } );
// default function
String.prototype.format = function() {
	var args = arguments;
	return this.replace(/{(\d+)}/g, function(match, number) {
		return typeof args[number] != 'undefined' ? args[number] : match;
	});
};


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
	
	$("*").css("overflow","inherit !important");//overflow inherit 강제화
	
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



