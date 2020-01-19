$(".answer-write input[type=submit]").click(addAnswer); // click 시 addAnswer method call

function addAnswer(e) {
	e.preventDefault();
	console.log("click me");

	var queryString = $(".answer-write").serialize();//입력방식에 적힌 값을 쿼리 문자열로 바꿈
	console.log("query : " + queryString);

	var url = $(".answer-write").attr("action"); // answer-write > action 속성 url 선택

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
	var answerTemplate = $("#answerTemplate").html(); // answerTemplate from show.html
	var template = answerTemplate.format(data.writer.userId, data.formattedCreateDate, data.contents, data.question.id, data.id);
	
	$(".qna-comment-slipp-articles").prepend(template); //prepend with template
	
	$(".answer-write textarea").val(""); //refresh
}



$("a.link-Delete-article").click(deleteAnswer); // click 시 deleteAnswer method call

function deleteAnswer(e){
	e.preventDefault();
	
	var deleteBtn = $(this); //ajax 안에서도 동일한 this유지하기 위해 선언
	var url = deleteBtn.attr("href"); //link-Delete-article > herf 속성 url 선택
	console.log("url : " + url);
	
	$.ajax({
		type : 'delete',
		url : url,
		datatype : 'json',
		error : function(xhr, status){
			console.log("error");
		},
		success : function(data, status){
			console.log(data);
			if(data.valid){
				deleteBtn.closest("article").remove();
			}else{
				alert(data.errorMessage);
			}
		}
	});
}
	





//default function
String.prototype.format = function() {
	var args = arguments;
	return this.replace(/{(\d+)}/g, function(match, number) {
		return typeof args[number] != 'undefined' ? args[number] : match;
	});
};