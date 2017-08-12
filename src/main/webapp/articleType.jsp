<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>
<head>
<meta charset="gb2312">
<title>个人博客模板古典系列之――江南墨卷</title>
<meta name="keywords" content="个人博客模板古典系列之――江南墨卷" />
<meta name="description" content="个人博客模板古典系列之――江南墨卷" />
<link href="${pageContext.request.contextPath }/resources/articleType/css/base.css" rel="stylesheet">
<link href="${pageContext.request.contextPath }/resources/articleType/css/index.css" rel="stylesheet">
<!--[if lt IE 9]-->
<script src="${pageContext.request.contextPath }/resources/articleType/js/modernizr.js"></script>
<![endif]-->
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/articleType/js/jquery.js"></script>
</head>
<body>
<div class="topnav">
<a href="http://www.yangqq.com/download/div/2017-07-16/785.html" target="_blank">个人博客模板古典系列之――江南墨卷</a>――作品来自<a href="http://www.yangqq.com" target="_blank">杨青个人博客网站</a>
</div>
<div id="wrapper">
  <header>
    <div class="headtop"></div>
    <div class="contenttop">
      <div class="logo f_l">个人博客模板古典系列之――江南墨卷</div>
      <div class="search f_r">

          <input name="searchText" id="searchText" class="input_text"  style="color: rgb(153, 153, 153);" type="text">
        <button name="submitSelect"onclick=" selectSubmit()" id="submitSelect" class="input_submit" type="submit">收索</button>

      </div>
      <div class="blank"></div>
      <SCRIPT type=text/javascript>
	// Navigation Menu
	$(function() {
		$(".menu ul").css({display: "none"}); // Opera Fix
		$(".menu li").hover(function(){
			$(this).find('ul:first').css({visibility: "visible",display: "none"}).slideDown("normal");
		},function(){
			$(this).find('ul:first').css({visibility: "hidden"});
		});
	});
</SCRIPT> 
    </div>
    </header>
    <div class="jztop"></div>
    <div class="container">

      <div class="bloglist f_l"id="blog">
        <div id="test"></div>


       <div class="pagelist" id="pagetool" align="center"></div>
    </div>

    <div class="r_box f_r">
      <div class="tit01">
        <h3 class="tit">关注我</h3>
        <div class="gzwm">
          <ul>
            <li><a class="email" href="#" target="_blank">我的电话</a></li>
            <li><a class="qq" href="#mailto:admin@admin.com" target="_blank">我的邮箱</a></li>
            <li><a class="tel" href="#" target="_blank">我的QQ</a></li>
            <li><a class="prize" href="#">个人奖项</a></li>
          </ul>
        </div>
      </div>
      <!--tit01 end-->
      
      <div class="tuwen">
        <h3 class="tit">图文推荐</h3>
        <ul>
          <li><a href="#"><img src="images/01.jpg"><b>住在手机里的朋友</b></a>
            <p><span class="tulanmu"><a href="/">手机配件</a></span><span class="tutime">2015-02-15</span></p>
          </li>
          <li><a href="#"><img src="images/02.jpg"><b>教你怎样用欠费手机拨打电话</b></a>
            <p><span class="tulanmu"><a href="/">手机配件</a></span><span class="tutime">2015-02-15</span></p>
          </li>
          <li><a href="#" title="手机的16个惊人小秘密，据说99.999%的人都不知"><img src="images/03.jpg"><b>手机的16个惊人小秘密，据说...</b></a>
            <p><span class="tulanmu"><a href="/">手机配件</a></span><span class="tutime">2015-02-15</span></p>
          </li>
          <li><a href="#"><img src="images/06.jpg"><b>住在手机里的朋友</b></a>
            <p><span class="tulanmu"><a href="/">手机配件</a></span><span class="tutime">2015-02-15</span></p>
          </li>
          <li><a href="#"><img src="images/04.jpg"><b>教你怎样用欠费手机拨打电话</b></a>
            <p><span class="tulanmu"><a href="/">手机配件</a></span><span class="tutime">2015-02-15</span></p>
          </li>
          <li><a href="#"><img src="images/02.jpg"><b>教你怎样用欠费手机拨打电话</b></a>
            <p><span class="tulanmu"><a href="/">手机配件</a></span><span class="tutime">2015-02-15</span></p>
          </li>
          <li><a href="#" title="手机的16个惊人小秘密，据说99.999%的人都不知"><img src="images/03.jpg"><b>手机的16个惊人小秘密，据说...</b></a>
            <p><span class="tulanmu"><a href="/">手机配件</a></span><span class="tutime">2015-02-15</span></p>
          </li>
        </ul>
      </div>
      <div class="ph">
        <h3 class="tit">点击排行</h3>
        <ul class="rank">
          <c:forEach items="${goodArticles}" var="goodArticle">
            <li><a href="${pageContext.request.contextPath }/visitor/blog-look.action?id=${goodArticle.id}" title="${goodArticle.title}" target="_blank">${goodArticle.introduce}</a></li>
          </c:forEach>
        </ul>
      </div>
      <div class="ad"> <img src="images/03.jpg"> </div>
    </div>
  </div>
  <!-- container代码 结束 -->
  <div class="jzend"></div>
  <footer>
    <div class="footer">
      <div class="f_l">
        <p>All Rights Reserved 版权所有：<a href="http://www.yangqq.com">杨青个人博客</a> 备案号：蜀ICP备00000000号</p>
      </div>
      <div class="f_r textr">
        <p>Design by DanceSmile</p>
      </div>
    </div>
  </footer>
</div>
-----------------------${initPage.pageCount == null}
------------------------${initPageSize}
<!--分页插件-->
<script src="${pageContext.request.contextPath }/resources/laypage/laypage/laypage.js" charset="utf-8"></script>

<script type="text/javascript">
    <!--公共化的信息-->
    var tempPageCount = 15;  // 总页数
    var tempSize = 5;  // 连续分页的间隔数
    var tempNowPage = 1;

    alert("dggg");
    /*laypage分页插件*/
    laypage({
        cont: 'pagetool', //分页所摆放的容器
        pages: ${initPage.pageCount}, //总页数,
        groups: ${initPageSize}, //连续显示分页数
        skip: true, //显示跳转
        first : "首页",
        curr : 1,
        skin: '#1E9FFF', //颜色
        jump: function(obj){
            //得到了当前页，用于向服务端请求对应数据
            $.ajax({
                type: "POST",
                dataType: 'json',
                scriptCharset: 'utf-8',
                url: "${pageContext.request.contextPath }/visitor/article-list.action",
                data: {"nowPage":obj.curr ,"size":6 ,"searchText":$("#searchText").val(),"articleType":${articleType}},
                success: function(data) {
                    obj.pages = data.pageBean.pageCount;
                    alert("总页数：" +data.pageBean.pageCount);
                    while($("#temp").length > 0) {
                        $("#temp").remove();
                    }
                    var curr = obj.curr;
                    var htmls = data.htmls;
                    // 将当前页码的信息公共化，方便其他方法调用
                    tempNowPage = obj.curr;
                    tempSize = data.pageBean.size;
                    tempPageCount = obj.pages;
                    $(htmls).insertAfter($("#test"));
                }
            });
        }
    });

    // 调用laypage函数
    function getLaypage(tempPageBean) {
        alert("1");
        var pageBean = $.extend({},tempPageBean);
        alert("laypage 当前页数 " + pageBean.nowPage);
      /*laypage分页插件*/
        laypage({
            cont: 'pagetool', //分页所摆放的容器
            pages: pageBean.pageCount, //总页数,
            groups: pageBean.endPageIndex - pageBean.startPageIndex > 3 ? 4 : pageBean.endPageIndex, //连续显示分页数
            skip: true, //显示跳转
            first : "首页",
            curr : pageBean.nowPage,
            skin: '#1E9FFF', //颜色
            jump: function(obj){
                alert("2");
                $.ajax({
                    type: "POST",
                    dataType: 'json',
                    scriptCharset: 'utf-8',
                    url: "${pageContext.request.contextPath }/visitor/article-list.action",
                    data: {"nowPage":obj.curr ,"size":6 ,"searchText":$("#searchText ").val() ,"articleType":${articleType}},
                    success: function(data) {
                        alert("dsadsvvv");
                        obj.pages = data.pageBean.pageCount;
                        while($("#temp").length > 0) {
                            $("#temp").remove();
                        }
                        var curr = obj.curr;
                        var htmls = data.htmls;
                        // 将当前页码的信息公共化，方便其他方法调用
                        tempNowPage = obj.curr;
                        tempSize = data.pageBean.size;
                        tempPageCount = obj.pages;
                        $(htmls).insertAfter($("#test"));
                    }
                });
            }
        });
    }

    <!--文章收索查询-->
    function selectSubmit() {
        alert("3");
        //得到了当前页，用于向服务端请求对应数据
        $.ajax({
            type: "POST",
            dataType: 'json',
            scriptCharset: 'utf-8',
            url: "${pageContext.request.contextPath }/visitor/article-list.action",
            data: {"nowPage":1 ,"size":5 ,"searchText":$("#searchText").val() ,
                "articleType":${articleType}},
            success: function(data) {
                while($("#temp").length > 0) {
                    $("#temp").remove();
                }
                getLaypage(data.pageBean);
                alert("当前页数" + tempNowPage);
            }
        });
    }

</script>

</body>
</html>
