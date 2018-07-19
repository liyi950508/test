var VideoPlay=function(){
	this.timer;
	this.oPlayTime=$("#playtiem");
	this.oPlayTime1=$("#playtime1");
	this.init();
};
VideoPlay.prototype={
	init:function(){
		var _this=this;
		_this.getCurrentime();
	},
	getCurrentime:function(){
		var _this=this;
		_this.timer=setInterval(function(){
			console.log(formatSeconds(document.getElementById("video").currentTime));
			_this.oPlayTime.val(formatSeconds(document.getElementById("video").currentTime));
			_this.oPlayTime1.val(formatSeconds(document.getElementById("video").duration));
			console.log("_this.oPlaytime:"+_this.oPlayTime.val(document.getElementById("video").currentTime));
			console.log("_this.oPlaytime1:"+_this.oPlayTime1.val(document.getElementById("video").duration));
		},1000);
	}
};
