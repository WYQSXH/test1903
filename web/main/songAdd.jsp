
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>添加歌曲</title>
    <link rel="stylesheet" type="text/css" href="/Demo13Web/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="/Demo13Web/css/nav.css" />
    <style type="text/css">
        * {font-size:12px;margin:0;}
        body {background:#fff;}
        form {margin:12px;}
        input.file{
            vertical-align:middle;
            position:relative;
            left:-218px;
            filter:alpha(opacity=0);
            opacity:0;
            z-index:1;
            *width:223px;
        }

        form input.viewfile {
            z-index:99;
            border:1px solid #ccc;
            padding:2px;
            width:150px;
            vertical-align:middle;
            color:#999;
        }

        form p span {
            float:left;
        }

        form label.bottom {
            border:1px solid #38597a;
            background:#4e7ba9;
            color:#fff;
            height:19px;
            line-height:19px;
            display:block;
            width:60px;
            text-align:center;
            cursor:pointer;
            float:left;
            position:relative;
            *top:1px;
        }

        form input.submit , form input.reset{
            border:0;
            background:#380;
            width:70px;
            height:22px;
            line-height:22px;
            color:#fff;
            cursor:pointer;
        }


    </style>
</head>
<center>

<!-- 导航栏 -->
<div class="container">
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
<!--表单必须包含 enctype=multipart/form-data
enctype：规定在发送到服务器之前应该如何对表单数据进行编码。有以下三个值：
（1）application/x-www-form-urlencoded：在发送前编码所有字符（默认）。
（2）multipart/form-data ：不对字符编码。在使用包含文件上传控件的表单时，必须使用该值。指定传输数据为二进制类型，比如图片、mp3、文件。
（3）text/plain：空格转换为 “+” 加号，但不对特殊字符编码
参考：https://blog.csdn.net/weixin_44554896/article/details/107883063-->
<center>
    <form action="/Demo13Web/song/add" method="post"  enctype="multipart/form-data">
        <table border="2">
            <tr><td>歌曲名称：<input type="text" name="songName"></td></tr>
            <tr><td>歌手：<input type="text" name="singer"></td></tr>
            <tr><td>上传歌曲：<input  type ="file" name="songFile" accept=".mp3,.flac,.qmcflac,ogg" >
            </td></tr>
            <tr><td>上传歌曲图片：<input  type ="file" name="songImage" accept ="image/gif,image/jpeg,image/jpg,image/png" ></td></tr>
            <tr>
                <td><input class="submit" type="submit" value="确定上传" />
                    <input class="reset" type="reset" value="重置"></td>
            </tr>
        </table>
    </form>
</center>
</body>
</html>