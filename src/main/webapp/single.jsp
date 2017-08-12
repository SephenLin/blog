<%--
  Created by IntelliJ IDEA.
  User: 林进华
  Date: 2017/7/14
  Time: 23:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Single Post</title>
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap styles -->
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/UI/css/bootstrap2.min.css/bootstrap.min.css">


    <title>Classic - Responsive Bootstrap 4.0 Template</title>

    <!-- load stylesheets -->
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:300,400">  <!-- Google web font "Open Sans" -->
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/UI/css/bootstrap.min.css">                                      <!-- Bootstrap style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/UI/css/templatemo-style.css">                                   <!-- Templatemo style -->

    <!-- Font-Awesome -->
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/UI/css/font-awesome/css/font-awesome.min.css">


    <!-- Google Webfonts -->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,600|PT+Serif:400,400italic' rel='stylesheet' type='text/css'>

    <!-- Styles -->
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/UI/css/style.css" id="theme-styles">

    <!--[if lt IE 9]-->
    <script src="${pageContext.request.contextPath }/resources/UI/js/vendor/google/html5-3.6-respond-1.1.0.min.js"></script>
    <![endif]-->


</head>
<body>
<header>
    <div class="tm-header">
        <div class="container-fluid">
            <div class="tm-header-inner">
                <a href="#" class="navbar-brand tm-site-name">Classic</a>

                <!-- navbar -->
                <nav class="navbar tm-main-nav">

                    <button class="navbar-toggler hidden-md-up" type="button" data-toggle="collapse" data-target="#tmNavbar">
                        &#9776;
                    </button>

                    <div class="collapse navbar-toggleable-sm" id="tmNavbar">
                        <ul class="nav navbar-nav">
                            <li class="nav-item">
                                <a href="${pageContext.request.contextPath }/visitor/blog-init.action" class="nav-link">主页</a>
                            </li>
                            <li class="nav-item active">
                                <a href="${pageContext.request.contextPath }/single.jsp" class="nav-link">博客</a>
                            </li>
                            <li class="nav-item">
                                <a href="" onclick="open_win('login.jsp')" class="nav-link">登录</a>
                            </li>
                            <c:if test="${loginUser != null}">
                                <li class="nav-item">
                                    <a href="" title="用户后台" onclick="open_win('${pageContext.request.contextPath }/user/indexUI')" class="nav-link">用户后台</a>
                                </li>
                            </c:if>
                        </ul>
                    </div>

                </nav>

            </div>
        </div>
    </div>

    <div class="widewrapper subheader">
        <div class="container">
            <div class="clean-breadcrumb">
                <a href="#">博客</a>
                <span class="separator">&#x2F;</span>
                <a href="#">Bootstrap</a>
                <span class="separator">&#x2F;</span>
                <a href="#">HTML Template</a>
            </div>


            <div class="clean-searchbox">
                <form action="#" method="get" accept-charset="utf-8">

                    <input class="searchfield" id="searchbox" type="text" placeholder="Search">
                    <button class="searchbutton" type="submit">
                        <i class="fa fa-search"></i>
                    </button>
                </form>
            </div>
        </div>
    </div>
</header>

<div class="widewrapper main">
    <div class="container">
        <div class="row">
            <div class="col-md-8 blog-main">
                <article class="blog-post">
                    <div class="body">
                        <h1>${article.title}</h1>
                        <div class="meta">
                            <i class="fa fa-user"></i> ${article.user.account} <i class="fa fa-calendar"></i>${article.time}<i class="fa fa-comments"></i><span class="data"><a href="#comments"><span id="commentNumber1"></span> Comments</a></span>
                        </div>

                        ${article.content}

                    </div>
                </article>

                <aside class="social-icons clearfix">
                    <h3>Share on </h3>
                    <a href="#"><i class="fa fa-facebook"></i></a> <a href="#"><i class="fa fa-twitter"></i></a> <a href="#"><i class="fa fa-google"></i></a>
                </aside>

                <aside class="comments" id="comments">
                    <hr>

                    <h2><i class="fa fa-comments"></i> <span id="commentNumber2"></span> Comments</h2>

                    <div>
                        <div id="test"></div>

                        <hr>
                        <hr>
                        <div id="pagetool" align="center">-------------------------</div>
                    </div>
                </aside>

                <aside class="create-comment" id="create-comment">
                    <hr>
















                    <h2><i class="fa fa-pencil"></i> Add Comment</h2>

                    <form action="#" name="comment" id="comment-form" method="get" accept-charset="utf-8">

                        <textarea rows="10" name="comment-content" id="comment-content" placeholder="输入你的内容" class="form-control input-lg"></textarea>

                        <div class="buttons clearfix">
                            <button type="button" id="submitComment" class="btn btn-xlarge btn-clean-one">Submit</button>
                        </div>
                    </form>

                </aside>
            </div>
            <aside class="col-md-4 blog-aside">

                <div class="aside-widget">
                    <header>
                        <h3>精选帖子</h3>
                    </header>
                    <div class="body">
                        <ul class="clean-list">
                            <c:forEach items="${nevoArticles}" var="nevoArticle">
                                <li><a href="${pageContext.request.contextPath }/visitor/blog-look.action?id=${nevoArticle.id}">${nevoArticle.title}</a></li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
                <div class="aside-widget">
                    <header>
                        <h3>相关文章</h3>
                    </header>
                    <div class="body">
                        <ul class="clean-list">
                            <c:forEach items="${sameArticles}" var="sameArticle">
                                <li><a href="${pageContext.request.contextPath }/visitor/blog-look.action?id=${sameArticle.id}">${sameArticle.title}</a></li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>

                <div class="aside-widget">
                    <header>
                        <h3>文章类别</h3>
                    </header>
                    <div class="body clearfix">
                        <c:if test="${article.articleType eq '1'}">
                            <ul class="tags">
                                <li><a href="${pageContext.request.contextPath }/visitor/article-listUI.action?articleType=${article.articleType}">治愈</a></li>
                            </ul>
                        </c:if>
                        <c:if test="${article.articleType eq '2'}">
                            <ul class="tags">
                                <li><a href="${pageContext.request.contextPath }/visitor/article-listUI.action?articleType=${article.articleType}">技术</a></li>
                            </ul>
                        </c:if>
                        <c:if test="${article.articleType eq '3'}">
                            <ul class="tags">
                                <li><a href="${pageContext.request.contextPath }/visitor/article-listUI.action?articleType=${article.articleType}">心情</a></li>
                            </ul>
                        </c:if>
                        <c:if test="${article.articleType eq '4'}">
                            <ul class="tags">
                                <li><a href="${pageContext.request.contextPath }/visitor/article-listUI.action?articleType=${article.articleType}">清晨</a></li>
                            </ul>
                        </c:if>
                        <c:if test="${article.articleType eq '5'}">
                            <ul class="tags">
                                <li><a href="${pageContext.request.contextPath }/visitor/article-listUI.action?articleType=${article.articleType}">中午</a></li>
                            </ul>
                        </c:if>
                        <c:if test="${article.articleType eq '6'}">
                            <ul class="tags">
                                <li><a href="${pageContext.request.contextPath }/visitor/article-listUI.action?articleType=${article.articleType}">下午</a></li>
                            </ul>
                        </c:if>
                        <c:if test="${article.articleType eq '7'}">
                            <ul class="tags">
                                <li><a href="${pageContext.request.contextPath }/visitor/article-listUI.action?articleType=${article.articleType}">夜晚</a></li>
                            </ul>
                        </c:if>
                        <c:if test="${article.articleType eq '8'}">
                            <ul class="tags">
                                <li><a href="${pageContext.request.contextPath }/visitor/article-listUI.action?articleType=${article.articleType}">深夜</a></li>
                            </ul>
                        </c:if>
                    </div>
                </div>

                <div class="aside-widget">
                    <header>
                        <h3>标签</h3>
                    </header>
                    <div class="body clearfix">
                        <ul class="tags">
                            <c:forEach items="${article.types}" var="type">
                                <li><a href="javascript:return false;">${type.label}</a></li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </aside>
        </div>
    </div>
</div>

<footer class="tm-footer">
    <div class="container-fluid">
        <div class="row">

            <div class="col-xs-12 col-sm-6 col-md-6 col-lg-3 col-xl-3">

                <div class="tm-footer-content-box">
                    <h3 class="tm-gold-text tm-title tm-footer-content-box-title">Proin eu posuere felis</h3>
                    <div class="tm-gray-bg">
                        <p>Classic is free HTML CSS website template provided by templatemo for everyone. Feel free to use it.</p>
                        <p>Aenean cursus tellus mauris, quis consequat mauris dapibus id. Donec scelerisque porttitor pharetra.</p>
                        <p><strong>Danny Egg (Executive)</strong></p>
                    </div>
                </div>

            </div>

            <div class="col-xs-12 col-sm-6 col-md-6 col-lg-3 col-xl-3">
                <div class="tm-footer-content-box tm-footer-links-container">

                    <h3 class="tm-gold-text tm-title tm-footer-content-box-title">Nulla tortor dolor</h3>
                    <nav>
                        <ul class="nav">
                            <li><a href="#" class="tm-footer-link">Tincidunt non faucibus</a></li>
                            <li><a href="#" class="tm-footer-link">Vestibulum tempor</a></li>
                            <li><a href="#" class="tm-footer-link">Fusce non turpis euismod</a></li>
                            <li><a href="#" class="tm-footer-link">Lorem ipsum dolor sit</a></li>
                            <li><a href="#" class="tm-footer-link">Nam in augue consectetur</a></li>
                            <li><a href="#" class="tm-footer-link">Text Link Color #CCCC66</a></li>
                        </ul>
                    </nav>

                </div>

            </div>

            <!-- Add the extra clearfix for only the required viewport
                http://stackoverflow.com/questions/24590222/bootstrap-3-grid-with-different-height-in-each-item-is-it-solvable-using-only
            -->
            <div class="clearfix hidden-lg-up"></div>

            <div class="col-xs-12 col-sm-6 col-md-6 col-lg-3 col-xl-3">

                <div class="tm-footer-content-box">

                    <h3 class="tm-gold-text tm-title tm-footer-content-box-title">Etiam mollis ornare</h3>
                    <p class="tm-margin-b-30">Aenean cursus tellus mauris, quis consequat mauris dapibus id. Donec scelerisque porttitor pharetra.</p><hr class="tm-margin-b-30">
                    <p class="tm-margin-b-30">Aenean cursus tellus mauris, quis consequat mauris dapibus id. Donec scelerisque porttitor pharetra.</p><hr class="tm-margin-b-30">
                    <p class="tm-margin-b-30">Aenean cursus tellus mauris, quis consequat mauris dapibus id. Donec scelerisque porttitor pharetra.</p>
                    <a href="#" class="tm-btn tm-btn-gray text-uppercase">Read More</a>

                </div>

            </div>

            <div class="col-xs-12 col-sm-6 col-md-6 col-lg-3 col-xl-3">

                <div class="tm-footer-content-box">

                    <h3 class="tm-gold-text tm-title tm-footer-content-box-title">Fusce non turpis</h3>
                    <div class="tm-margin-b-30">
                        <img src="${pageContext.request.contextPath }/resources/UI/img/tm-img-100x100-1.jpg" alt="Image" class="tm-footer-thumbnail">
                        <img src="${pageContext.request.contextPath }/resources/UI/img/tm-img-100x100-2.jpg" alt="Image" class="tm-footer-thumbnail">
                        <img src="${pageContext.request.contextPath }/resources/UI/img/tm-img-100x100-3.jpg" alt="Image" class="tm-footer-thumbnail">
                        <img src="${pageContext.request.contextPath }/resources/UI/img/tm-img-100x100-4.jpg" alt="Image" class="tm-footer-thumbnail">
                        <img src="${pageContext.request.contextPath }/resources/UI/img/tm-img-100x100-5.jpg" alt="Image" class="tm-footer-thumbnail">
                        <img src="${pageContext.request.contextPath }/resources/UI/img/tm-img-100x100-6.jpg" alt="Image" class="tm-footer-thumbnail">
                    </div>
                    <p class="tm-margin-b-20">Curabitur dui massa, aliquam quis mi sed, tempor vulputate tellus. Sed vestibulum non neque.</p>
                    <a href="#" class="tm-btn tm-btn-gray text-uppercase">Browse</a>

                </div>

            </div>
        </div>
        <div class="row">
            <div class="col-xs-12 tm-copyright-col">
                <p class="tm-copyright-text">Copyright 2016 Your Company Name. More Templates <a href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a> - Collect from <a href="http://www.cssmoban.com/" title="网页模板" target="_blank">网页模板</a></p>
            </div>
        </div>
    </div>
</footer>
<script src="${pageContext.request.contextPath }/resources/UI/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/UI/js/modernizr.js"></script>
<!-- load JS files -->
<script src="${pageContext.request.contextPath }/resources/UI/js/jquery-1.11.3.min.js"></script>             <!-- jQuery (https://jquery.com/download/) -->
<script src="${pageContext.request.contextPath }/resources/UI/js/tether.min.js"></script> <!-- Tether for Bootstrap, http://stackoverflow.com/questions/34567939/how-to-fix-the-error-error-bootstrap-tooltips-require-tether-http-github-h -->
<script src="${pageContext.request.contextPath }/resources/UI/js/bootstrap.min.js"></script>                 <!-- Bootstrap (http://v4-alpha.getbootstrap.com/) -->

<!--分页插件-->
<script src="${pageContext.request.contextPath }/resources/laypage/laypage/laypage.js" charset="utf-8"></script>

<script>

    var nowPage = 1;
    var size = 3;
    var initPageCount = ${initPage.pageCount};
    var initPageSize = ${initPageSize};

    function open_win(url) {
        window.open(url);
    }

    $("#submitComment").click(function () {
        alert("sdsa");
        var topicId = ${article.id};
        var fromId = '${loginUser.id}';
        var content = $("#comment-content").val();
        if(content == '') {
            alert("请输入内容！");
        }else {
            if(${loginUser.id == null}) {
                alert("请登录");
            }else {
                $.ajax({
                    type: "POST",
                    dataType: 'json',
                    scriptCharset: 'utf-8',
                    url: "${pageContext.request.contextPath }/comment/add-comment.action",
                    data: {"nowPage":nowPage ,"size":size,"topicAid":topicId ,"fromUid":fromId ,"content":content},
                    success: function(data) {
                        alert("成功了");
                        alert(data.comments);
                        while($("#temp").length > 0) {
                            $("#temp").remove();
                        }
                        alert($("#temp").length);
                        $("#commentNumber1").html(data.pageBean.recordCount);
                        $("#commentNumber2").html(data.pageBean.recordCount);
                        $(data.comments).insertAfter($("#test"));
                        initPageCount = data.pageBean.pageCount;
                        alert("拼接成功了" + "data.pageBean.pageCount :" + data.pageBean.pageCount);
                        alert("initPageCount ： " + initPageSize);
                    }
                });
            }
        }
    })


    // 分页操作
    laypage({
        cont: 'pagetool', //分页所摆放的容器
        pages: initPageCount, //总页数,
        groups: initPageSize, //连续显示分页数
        skip: true, //显示跳转
        first : "首页",
        curr : 1,
        skin: '#1E9FFF', //颜色
        jump: function(obj){
            alert("分页成功了");
            //得到了当前页，用于向服务端请求对应数据
            $.ajax({
                type: "POST",
                dataType: 'json',
                scriptCharset: 'utf-8',
                url: "${pageContext.request.contextPath }/comment/comment-list.action",
                data: {"nowPage":obj.curr ,"size":3,"topicAid":${article.id}},
                success: function(data) {
                    alert("分页成功而且进入了");
                    obj.pages = data.pageBean.pageCount;
                    alert("总页数" + data.pageBean.pageCount);
                    while($("#temp").length > 0) {
                        $("#temp").remove();
                    }
                    $("#commentNumber1").html(data.pageBean.recordCount);
                    $("#commentNumber2").html(data.pageBean.recordCount);
                    // 将当前页码的信息公共化，方便其他方法调用
                    nowPage = obj.curr;
                    size = data.pageBean.size;
                    $(data.comments).insertAfter($("#test"));
                }
            });
        }
    });
</script>

<!--字体的css样式-->
<style>
    .divcss {line-height: 35px}

</style>

</body>
</html>
