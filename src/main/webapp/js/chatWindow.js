function sendmanages(talkName)
{
    var Words = document.getElementById("words");
    var TalkWords = document.getElementById("talkwords");
    var TalkSub = document.getElementById("talksub");
    var who=talkName.slice(1,-1);
    //定义空字符串
    var str = "";
    if(TalkWords.value == "")
    {
         // 消息为空时弹窗
        alert("消息不能为空");
        return;
    }
    str = '<div class="atalk"><span>'+who+' :' + TalkWords.value +'</span></div>';
    Words.innerHTML = Words.innerHTML + str;
    document.getElementById("talkwords").value="";
}

function fillterMoveTo()
{
	var timer=null;
	var divNavigate=document.getElementById('navigate');
	var divMove=document.getElementById('content');
	clearInterval(timer);
	timer=setInterval
	(
		function()
		{
			var speed=3;
			if(divMove.offsetLeft>=(1 * divNavigate.clientWidth))
			{
				clearInterval(timer);
			}
			else
			{
				divMove.style.left=divMove.offsetLeft+speed+'px';
				divMove.style.width=divMove.clientWidth-speed+"px";
			}
		},30
	);
}
function fillterMoveBack()
{
	var timerBack=null;
	var divNavigate=document.getElementById('navigate');
	var divMove=document.getElementById('content');
	clearInterval(timerBack);
	timerBack=setInterval
	(
		function()
		{
			var speed=3;
			if(divMove.offsetLeft<=0)
			{
				clearInterval(timerBack);
			}
			else
			{
				divMove.style.left=divMove.offsetLeft-speed+'px';
				divMove.style.width=divMove.clientWidth+speed+"px";
			}
		},30
	);
}
function fillterMove()
{
	var divNavigate=document.getElementById('navigate');
	var divMove=document.getElementById('content');
	if(divMove.offsetLeft<=0)
	{
		fillterMoveTo();
	}
	else
	{
		fillterMoveBack();
	}
}
function checkTalkName(talkName)
{
	if(talkName=="{null}")
	{
		window.parent.document.getElementById("float-cover-getName").style.display="block";
	}
	else
	{
		var TalkWords = document.getElementById("talkwords");
		window.frames['exe_jsp'].location.href = "./do_sentMassage?content=" + TalkWords.value;
		document.getElementById("talkwords").value="";
	}
}
function checkTalkNameByInput(talkName)
{
	if(talkName=="{null}")
	{
		window.parent.document.getElementById("float-cover-getName").style.display="block";
	}
	else
	{

	}
}
function onUnload()
{
	window.frames['exe_jsp'].location.href = "./do_unRegis";
}
function catchUser(talkName, path)
{
	if(talkName=="{null}")
	{
		window.parent.document.getElementById("float-cover-getName").style.display="block";
	}
	else
	{
		
		window.frames['talk_show'].location.href = path + "action/talk_show.jsp";
		window.frames['exe_jsp'].location.href = "./do_catchUser";
		document.getElementById("talk_start").setAttribute("disabled",true);
	}
}
function gotIt() 
{
	window.parent.document.getElementById("talk_start").disabled=false;
	window.parent.document.getElementById("talk_start").innerHTML="CUT";
	window.parent.document.getElementById("talk_start").style.backgroundColor="red";
}
function missIt() 
{
	window.parent.document.getElementById("talk_start").disabled=false;
	window.parent.document.getElementById("talk_start").innerHTML="RESTART";
	window.parent.document.getElementById("talk_start").style.backgroundColor="green";
}