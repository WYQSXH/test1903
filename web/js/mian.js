$(function() {

	/* 虚化层 */
	$("#textbooksearcg").click(function(e) {
		e.stopPropagation(); // 阻止冒泡
		$('#back').css("display", "block"); // 显示
		$(".inputsearch").animate({
			top: "100px",
			width: "580px",
			height: "140px",
		}, 200);

		$("#back").bind("click", function(e) { // 相当于点击空白消失
			$('#back').css("display", "none");
			$(".inputsearch").animate({
				top: "30px",
				width: "550px",
				height: "120px",
			}, 200);
			$(".inputsearch").stop(stopAll,goToEnd);
		});
	});

	$("#btnbooksearcg").on("click",function () {

	});
	// /* 点击搜索按钮 */
	// $("#btnbooksearcg").on("click", function() {
	// 	$('#back').css("display", "none");
	// 	$(".inputsearch").animate({
	// 		top: "30px",
	// 		width: "550px",
	// 		height: "120px",
	// 	}, 200);
	// 	html = ""; //还原
	// 	$(".content ul").empty(); //删除默认展示的歌曲
	// 	var val = $('#textbooksearcg').val();
	// 	$.get("../json/index.json", function(data, status) {
	// 		console.log(data);
	// 		var musicName;
	// 		var imgsrc;
	// 		var musicsrc;
	// 		for (var k in data) {
	// 			musicName = data[k].name;
	// 			imgsrc = data[k].imgsrc;
	// 			musicsrc = data[k].musicsrc;
	// 			html += "<ul>";
	// 			if (true) {
	// 				html += "<li>";
	// 				html += "<img src='" + imgsrc + "'/>";
	// 				html += "</li><li>";
	// 				html += musicName;
	// 				html += "</li><li>";
	// 				html += "<audio src='" + musicsrc + "' controls='controls'></audio>";
	// 				html += "</li>";
	// 			}
	// 			html += "</ul>";
	// 			// $(".content ul li:contains('"+val+"')").parent().show();
	// 			// $(".content ul ").hide().filter(".content ul li:contains('"+val+"')").parent("ul").show();
	// 		}
	// 		$(".content").append(html);
	// 		$(".content ul").hide();
	// 		console.log(html);
	// 		if (val != "") {	//输入为空值则不显示任何歌曲
	// 			$(".content ul li:contains('" + val + "')").parent().show();
	// 		}
	// 	}, 'json');
	// });
});





// 	$.ajax({
// 		type: "GET",
// 		url: "./json/index.json",
// 		dataType: "json",
// 		success: function(data) {
// 			var img = "<ul>";
// 			//i表示在data中的索引位置，n表示包含的信息的对象 
// 			$.each(data, function(i, n) {
// 				//获取对象中属性为optionsValue的值 
// 				img += "<li> <img id='img1'src='../images/ " + n["src"] + "'></li>";
// 			});
// 			img += "</ul>";
// 			$('.content').append(music);
// 		}
// 	})
// 	return false;
