<%@ page import="com.FinalTerm.entity.Songs" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: wangyongqi
  Date: 2021/5/14
  Time: 8:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="/Demo13Web/css/bootstrap.min.css" />

    <link rel="stylesheet" type="text/css" href="/Demo13Web/css/nav.css" />
    <link rel="stylesheet" type="text/css" href="/Demo13Web/css/search.css" />
    <link rel="stylesheet" type="text/css" href="/Demo13Web/css/main.css" />
    <link rel="stylesheet" type="text/css" href="/Demo13Web/css/left.css" />
    <link rel="stylesheet" type="text/css" href="/Demo13Web/css/footer.css" />

    <script src="/Demo13Web/js/jquery-3.3.1.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="/Demo13Web/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>

    <script type="text/javascript" src="/Demo13Web/js/nav.js"></script>
    <script type="text/javascript" src="/Demo13Web/js/mian.js"></script>
    <script type="text/javascript" src="/Demo13Web/js/left.js"></script>
    <script type="text/javascript" src="/Demo13Web/js/footer.js"></script>
</head>
<body>
<div class="bg"></div>
<div class="bg-mask"></div>
<!-- 导航栏 -->
<div class="container">
<%--    <video src="../music/bilibili.webm" loop width="1893" height="162" style="object-fit: cover; transform: scale(1) translate(45px, -4.5px) rotate(0deg); opacity: 1;" data-height="180" data-width="2104"></video>--%>
    <div class="nav col-md-12">
        <ul class="nav-ul col-md-8">
            <li class="nav-li col-md-3 col-sm-3">
                <a href="/Demo13Web/main/index.jsp">首页</a>
            </li>
            <li class="nav-li col-md-3 col-sm-3">
                <a href="/Demo13Web/main/songAdd.jsp">添加歌曲</a>
            </li>
            <li class="nav-li col-md-3 col-sm-3">
                <a href="/Demo13Web/song/query">我的歌曲</a>
            </li>
            <li class="nav-li col-md-3 col-sm-3">
                <a href="/Demo13Web/main/userQuery.jsp">个人信息</a>
            </li>
        </ul>
        <ul class="nav-ul col-md-4">
            <li class="nav-li col-md-2 col-sm-2 col-md-offset-2">
                <img src="/Demo13Web/images/登录.png">
            </li>
            <li class="nav-li col-md-7 col-sm-7">
                <a href="/Demo13Web/main/loginOut.jsp" id="loginname">欢迎您：${sessionScope.user.getUserName()}</a>
            </li>
        </ul>
    </div>
</div>
<!-- 虚化层 -->
<div class="layer" id="back" disabled="disabled"></div>
<!-- 搜索栏 -->
<div class="search">
    <div class="searchbg">

    </div>

    <div class="inputsearch">
        <form action="/Demo13Web/song/find" method="post">
            <input type="text" name="textbooksearcg" id="textbooksearcg" autocomplete="off" placeholder="输入歌名/歌手" />
            <input type="submit" value="搜索" id="btnbooksearcg" />
        </form>
    </div>
</div>

<!-- 左侧 -->
<div class="left side_open" id="side_open">
    <a href="javascript:void(0);" class="side_btn" id="side_btn">
        <ul class="side_btn_ul">
            <li>收起</li>
            <li>展开</li>
        </ul>
    </a>
    <p style="text-align: center; font-size: 30px; color: #D97A03;">著名歌手</p>
    <ul class="menu-list">
        <li>
            <p class="menu-head">邓紫棋</p>
            <div class="menu-body">
                <a href="#">画</a>
                <a href="#">喜欢你</a>
                <a href="#">泡沫</a>
                <a href="#">光年之外</a>
            </div>
        </li>
        <li>
            <p class="menu-head">周杰伦</p>
            <div class="menu-body">
                <a href="#">稻香</a>
                <a href="#">听妈妈的话</a>
                <a href="#">青花瓷</a>
                <a href="#">快使用双截棍</a>
                <a href="#">告白气球</a>
            </div>
        </li>
        <li>
            <p class="menu-head">BEYOND</p>
            <div class="menu-body">
                <a href="#">光辉岁月</a>
                <a href="#">海阔天空</a>
                <a href="#">真的爱你</a>
                <a href="#">灰色轨迹</a>
            </div>
        </li>
        <li>
            <p class="menu-head">杨宗伟</p>
            <div class="menu-body">
                <a href="#">凉凉</a>
                <a href="#">洋葱</a>
                <a href="#">一次就好</a>
                <a href="#">其实都没有</a>
            </div>
        </li>
        <li>
            <p class="menu-head">梁静茹</p>
            <div class="menu-body">
                <a href="#">勇气</a>
                <a href="#">爱你不是两三天</a>
                <a href="#">暖暖</a>
                <a href="#">可惜不是你</a>
                <a href="#">宁夏</a>
            </div>
        </li>
    </ul>
</div>
<!-- 主体 -->
<div class="main">
    <div class="content">
        <div class="main-content-div">
            <p>歌曲</p>
        </div>
        <%
            List<Songs> list=null;
            list =(List)request.getAttribute("songs");
            if (list != null){
                for(Songs s:list){
        %>
                    <ul>
                        <li><img src="<%=s.getImgUrl()%>"></li>
                        <li><%=s.getSongName() %> - <%=s.getSinger() %></li>
                        <li><audio src="<%=s.getSongUrl()%>" controls='controls'>
                            您的浏览器不支持 audio 标签。
                        </audio></li>
                    </ul>
        <%
                }
            } else{
        %>
        <ul>
            <li><img src="../music/稻香/稻香.png"></li>
            <li>稻香-周杰伦</li>
            <li><audio src="../music/稻香/稻香.mp3" controls='controls'>
                当前浏览器不支持audio
            </audio></li>
        </ul>
        <ul>
            <li><img src="../music/倒数/倒数.png"></li>
            <li>倒数-邓紫棋</li>
            <li><audio src="../music/倒数/倒数.mp3" controls='controls'>
                当前浏览器不支持audio
            </audio></li>
        </ul>
        <ul>
            <li><img src="../music/告白气球/告白气球.png"></li>
            <li>告白气球-周杰伦</li>
            <li><audio src="../music/告白气球/告白气球.mp3" controls='controls'>
                当前浏览器不支持audio
            </audio></li>
        </ul>
        <ul>
            <li><img src="../music/勇气/勇气.png"></li>
            <li>勇气-梁静茹</li>
            <li><audio src="../music/勇气/勇气.mp3" controls='controls'>
                当前浏览器不支持audio
            </audio></li>
        </ul>
        <%}%>
    </div>
</div>
<!-- 底部播放栏 -->
<div class="footer">
    <div class="musiclogo1" id="musiclogo1">
        <a href="javascript:void(0);"><img src="../images/音乐图标.png"></a>
    </div>
    <div class="musiclogo2" id="musiclogo2">
        <a href="javascript:void(0);"><img id="beformusic" src="../images/上一首.png"></a>
        <div class="music">
            <audio src="../music/Spongebob.mp3" controls="controls" loop="loop" id="myaudio">
                当前浏览器不支持audio
            </audio>
        </div>
        <a href="javascript:void(0);"><img id="nextmusic" src="../images/下一首.png"></a>
        <a href="javascript:void(0);"><img id="zdmusic" src="../images/折叠.png"></a>
    </div>
</div>
<div class="goTop">
    <a href="#"><img src="../images/置顶.png"></a>
</div>

</body>
</html>

