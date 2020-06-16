/*function submitFormReady() {
	$("#searchButton")
			.click(*/
$(document)
		.ready(
				function() {
					$("#imageSearchButton")
							.click(
									function() {
										var bookname = $("#imageName").val();
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
															$("#api2").html("");
															$("#api2")
																	.append(
																			"<br /><br /> collection: "
																					+ msg.documents[0].collection);
															$("#api2")
																	.append(
																			"	thumbnail image: <img src='"
																					+ msg.documents[0].thumbnail_url
																					+ "'/>");
															$("#api2")
																	.append(
																			"	image: <img src='"
																					+ msg.documents[0].image_url
																					+ "'/>");
															$("#api2")
																	.append(
																			"<br /><br /> collection: "
																					+ msg.documents[1].collection);
															$("#api2")
																	.append(
																			"	thumbnail image: <img src='"
																					+ msg.documents[1].thumbnail_url
																					+ "'/>");
															$("#api2")
																	.append(
																			"	image: <img src='"
																					+ msg.documents[1].image_url
																					+ "'/>");
															$("#api2")
																	.append(
																			"<br /><br /> collection: "
																					+ msg.documents[2].collection);
															$("#api2")
																	.append(
																			"	thumbnail image: <img src='"
																					+ msg.documents[2].thumbnail_url
																					+ "'/>");
															$("#api2")
																	.append(
																			"	image: <img src='"
																					+ msg.documents[2].image_url
																					+ "'/>");
															$("#api2")
																	.append(
																			"<br /><br /> collection: "
																					+ msg.documents[3].collection);
															$("#api2")
																	.append(
																			"	thumbnail image: <img src='"
																					+ msg.documents[3].thumbnail_url
																					+ "'/>");
															$("#api2")
																	.append(
																			"	image: <img src='"
																					+ msg.documents[3].image_url
																					+ "'/>");
															$("#api2")
																	.append(
																			"<br /><br /> collection: "
																					+ msg.documents[4].collection);
															$("#api2")
																	.append(
																			"	thumbnail image: <img src='"
																					+ msg.documents[4].thumbnail_url
																					+ "'/>");
															$("#api2")
																	.append(
																			"	image: <img src='"
																					+ msg.documents[4].image_url
																					+ "'/>");
														});
									})
				});
