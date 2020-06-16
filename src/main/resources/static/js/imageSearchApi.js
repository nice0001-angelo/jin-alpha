/*function submitFormReady() {
	$("#searchButton")
			.click(*/
$(document)
		.ready(
				function() {
					$("#searchButton")
							.click(
									function() {
										var bookname = $("#bookName").val();
										$
												.ajax(
														{
															type : "GET",
															url : "https://dapi.kakao.com/v2/search/image",
															dataType : "json",
															data : {
																query : bookname
															},
															headers : {
																Authorization : "KakaoAK 372e38774d57a8d9694e1f24e92a4a6d"
															}
														})
												.done(
														function(msg) {
															$("#api1").html("");
															$("#api1")
																	.append(
																			"<br /><br /> title: "
																					+ msg.documents[0].title);
															$("#api1")
																	.append(
																			"	image: <img src='"
																					+ msg.documents[0].thumbnail
																					+ "'/>");
															$("#api1")
															.append(
																	" price: "
																			+ msg.documents[0].price+"Won");
															$("#api1")
																	.append(
																			"<br /><br /> title: "
																					+ msg.documents[1].title);
															$("#api1")
																	.append(
																			"	image: <img src='"
																					+ msg.documents[1].thumbnail
																					+ "'/>");
															$("#api1")
															.append(
																	" price: "
																			+ msg.documents[1].price+"Won");
															$("#api1")
																	.append(
																			"<br /><br /> title: "
																					+ msg.documents[2].title);
															$("#api1")
																	.append(
																			"	image: <img src='"
																					+ msg.documents[2].thumbnail
																					+ "'/>");
															$("#api1")
															.append(
																	" price: "
																			+ msg.documents[2].price+"Won");
															$("#api1")
																	.append(
																			"<br /><br /> title: "
																					+ msg.documents[3].title);
															$("#api1")
																	.append(
																			"	image: <img src='"
																					+ msg.documents[3].thumbnail
																					+ "'/>");
															$("#api1")
															.append(
																	" price: "
																			+ msg.documents[3].price+"Won");
															$("#api1")
																	.append(
																			"<br /><br /> title: "
																					+ msg.documents[4].title);
															$("#api1")
																	.append(
																			"	image: <img src='"
																					+ msg.documents[4].thumbnail
																					+ "'/>");
															$("#api1")
															.append(
																	" price: "
																			+ msg.documents[4].price+"Won");

														});
									})
				});
