$(function() {

	var myaudio = document.getElementById("myaudio");
	var settimer; //设置定时器
	var duration = 0; //音乐总时长
	var audiowidth = 0; //进度条宽度
	myaudio.oncanplay = function() { //audio加载完毕获取audio总时长
		duration = myaudio.duration;
	};

	function playVid() {
		if (myaudio.paused) { //如果是暂停状态，开始播放并每一秒中获新已播放时长
			myaudio.play();
			settimer = setInterval(function() {
				var currentTime = myaudio.currentTime;
				if (currentTime >= duration) { //已播放时长大于总时长
					pauseVid();
				} else { //换算百分比赋值给进度条宽度
					audiowidth = currentTime / duration * 100;
					$(".audiobg").css("width", audiowidth + '%')
				}
			}, 1000);
		} else {
			pauseVid();
		}
	}

	function pauseVid() { //进度条清零、音频暂停、音频时长清零、清除定时器
		$(".audiobg").css("width", 0 + '%')
		myaudio.pause();
		myaudio.currentTime = 0;
		clearInterval(settimer);
	}
})
