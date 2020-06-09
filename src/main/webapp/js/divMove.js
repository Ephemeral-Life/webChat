var div2Height=Number($('.div2').offsetTop);
var clientHeight=Number($(document).clientHeight);
var totalHeight=div2Height-clientHeight;
var objOffset=$('.div2').offset().top;
$(document).ready(function () {
$(window).scroll(function () {
var scrollTop=$(window).scrollTop();
var objHeight=objOffset-scrollTop;
console.log(scrollTop);
if(scrollTop>=0){
$('.div2').css({'left':objOffsetLf,'top':objHeight,'position':'absolute','margin-top':'0px'});
}else{
$('.div2').css({'position':'static','margin-top':'500px'});
}
});
});