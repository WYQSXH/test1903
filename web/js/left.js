$(document).ready(function() {
	//隐藏二级菜单
	// $(".menu-body").hide()
	$(".menu-head + div").hide()
	$(".menu-head").click(function() {
		//设置当前菜单右侧图表样式+/-
		$(this).css('background-image', 'url(../images/img/pro_down.png)')
		//显示菜单
		$(this).next('div').show()
		//获取其他菜单的li元素
		var partenli = $(this).parent('li')
		var lis = partenli.siblings('li')
		//找到li元素下的每个主菜单，设置菜单右侧图标样式
		lis.children('p').css('background-image', 'url(../images/img/pro_left.png)')
		//隐藏其他主菜单下的子菜单
		lis.children('div').hide()
	})
});

$(document).ready(function() {
	var isHiden = true; /*控制切换菜单*/
	$('#side_btn').click(function() {
		if (isHiden) {
			$('#side_open').animate({
				left: '-=190px'
			}); //菜单块向右移动
		} else {
			$('#side_open').animate({
				left: '+=190px'
			}); //菜单块向左移动
		}
		isHiden = !isHiden;
	});
	// isHiden只是返回部件的隐藏属性，并不能表示部件当前的真实状态。比如A部件有个子部件B，而A处于隐藏状态，子部件B必然也不可见，但子部件B本身的isHiden还是为false。
	// 信号量
	var index = 0;
	// 更改按钮文字の点击事件
	$('#side_btn').click(function() {
		//防流氓点击
		if ($('#side_btn ul li').is(":animated")) {
			return;
		}
		// 老文字淡出
		$('#side_btn ul li').eq(index).fadeOut(0, function() {
			// 信号量
			index--;
			if (index < 0) {
				index = 1;
			}
			// 新文字淡入
			$('#side_btn ul li').eq(index).fadeIn(0);
		});
	});
});
