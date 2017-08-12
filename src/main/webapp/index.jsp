<%--
  Created by IntelliJ IDEA.
  User: 林进华
  Date: 2017/7/14
  Time: 23:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Classic - Responsive Bootstrap 4.0 Template</title>

    <!-- load stylesheets -->
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:300,400">  <!-- Google web font "Open Sans" -->
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/UI/css/bootstrap.min.css">                                      <!-- Bootstrap style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/UI/css/templatemo-style.css">                                   <!-- Templatemo style -->

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

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
                        <li class="nav-item active">
                            <a href="${pageContext.request.contextPath }/index.jsp" class="nav-link">主页</a>
                        </li>
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath }/single.jsp" class="nav-link">博客</a>
                        </li>
                        <li class="nav-item">
                            <a href="login.jsp" target="_blank" class="nav-link">登录</a>
                        </li>
                        <c:if test="${loginUser != null}">
                            <li class="nav-item">
                                <a href="${pageContext.request.contextPath }/user/indexUI.action" title="用户后台" target="_blank" class="nav-link">用户后台</a>
                            </li>
                        </c:if>
                    </ul>
                </div>

            </nav>

        </div>
    </div>
</div>

<div class="tm-home-img-container">
    <img src="${pageContext.request.contextPath }/resources/UI/img/tm-home-img.jpg" alt="Image" class="hidden-lg-up img-fluid">
</div>

<section class="tm-section">
    <div class="container-fluid">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-xs-center">
                <h2 class="tm-gold-text tm-title">清晨，一天美好的开始</h2>
                <p class="tm-subtitle">Suspendisse ut magna vel velit cursus tempor ut nec nunc. Mauris vehicula, augue in tincidunt porta, purus ipsum blandit massa.</p>
            </div>
        </div>
        <div class="row">

            <c:forEach items="${topArticles}" var="topArticle">

            <div class="col-xs-12 col-sm-6 col-md-6 col-lg-3 col-xl-3">

                <div class="tm-content-box divcss">
                    <img src="${pageContext.request.contextPath }/resources/UI/img/tm-img-310x180-1.jpg" alt="Image" class="tm-margin-b-20 img-fluid">
                    <h4 class="tm-margin-b-20 tm-gold-text">${topArticle.title}</h4>
                    <p class="tm-margin-b-20">${topArticle.introduce}</p>
                    <a href="${pageContext.request.contextPath }/visitor/blog-look.action?id=${topArticle.id}" target="_blank" class="tm-btn text-uppercase">Detail</a>
                </div>

            </div>

            </c:forEach>
        </div> <!-- row -->

        <div class="row tm-margin-t-big">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-6 col-xl-6">
                <div class="tm-2-col-left divcss">

                    <h3 class="tm-gold-text tm-title">${leftArticle.title}</h3>
                    <p class="tm-margin-b-30">${leftArticle.introduce}</p>
                    ${leftArticle.content}
                    <a href="${pageContext.request.contextPath }/visitor/blog-look.action?id=${leftArticle.id}" target="_blank" class="tm-btn text-uppercase">Read More</a>

                </div>
            </div>

            <div class="copyrights">Collect from <a href="http://www.cssmoban.com/"  title="网站模板">网站模板</a></div>

            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-6 col-xl-6">

                <div class="tm-2-col-right">

                    <div class="tm-2-rows-md-swap">
                        <div class="tm-overflow-auto row tm-2-rows-md-down-2">
                            <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6 col-xl-6">
                                <h3 class="tm-gold-text tm-title">
                                    标签分类
                                </h3>
                                <nav>
                                    <ul class="nav">
                                        <li><a href="${pageContext.request.contextPath }/visitor/article-listUI.action?articleType=1"  class="tm-text-link">治愈</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath }/visitor/article-listUI.action?articleType=2" class="tm-text-link">技术</a></li>
                                        <li><a href="${pageContext.request.contextPath }/visitor/article-listUI.action?articleType=3" class="tm-text-link">心情</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath }/visitor/article-listUI.action?articleType=4" class="tm-text-link">清晨</a></li>
                                        <li><a href="${pageContext.request.contextPath }/visitor/article-listUI.action?articleType=5" class="tm-text-link">中午</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath }/visitor/article-listUI.action?articleType=6" class="tm-text-link">下午</a></li>
                                        <li><a href="${pageContext.request.contextPath }/visitor/article-listUI.action?articleType=7" class="tm-text-link">夜晚</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath }/visitor/article-listUI.action?articleType=8" class="tm-text-link">深夜</a></li>
                                    </ul>
                                </nav>
                            </div> <!-- col -->
                            <%--建立一个隐藏的表单提交标签--%>
                            <%--<form action="${pageContext.request.contextPath }/visitor/article-listUI.action" method="get" id="submitArticleType">--%>
                                <%--<input type="hidden" name="articleType" id="articleType">--%>
                            <%--</form>--%>

                            <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6 col-xl-6 tm-xs-m-t">
                                <h3 class="tm-gold-text tm-title">
                                    好文推荐
                                </h3>
                                <nav>
                                    <ul class="nav">
                                        <c:forEach items="${rightArticles}" var="rightArticle">
                                            <li><a href="${pageContext.request.contextPath }/visitor/blog-look.action?id=${rightArticle.id}" target="_blank"  class="tm-text-link">${rightArticle.title}</a></li>
                                        </c:forEach>
                                    </ul>
                                </nav>
                            </div> <!-- col -->
                        </div>

                        <div class="row tm-2-rows-md-down-1 tm-margin-t-mid">
                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                                <h3 class="tm-gold-text tm-title tm-margin-b-30">技术文章</h3>
                                    <c:forEach items="${buttonArticles}" var="buttonArticle">
                                        <div class="media tm-related-post">
                                            <div class="media-left media-middle">
                                                <a href="#">
                                                    <img class="media-object" src="${pageContext.request.contextPath }/resources/UI/img/tm-img-240x120-1.jpg" alt="Generic placeholder image">
                                                </a>
                                            </div>
                                            <div class="media-body" class="divcss">
                                                <a href="${pageContext.request.contextPath }/visitor/blog-look.action?id=${buttonArticle.id}" target="_blank"><h4 class="media-heading tm-gold-text tm-margin-b-15">${buttonArticle.title}</h4></a>
                                                <p class="tm-small-font tm-media-description">${buttonArticle.introduce}</p>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>

                </div>

            </div>
        </div> <!-- row -->

    </div>
</section>

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

<!-- load JS files -->
<script src="${pageContext.request.contextPath }/resources/UI/js/jquery-1.11.3.min.js"></script>             <!-- jQuery (https://jquery.com/download/) -->
<script src="${pageContext.request.contextPath }/resources/UI/js/tether.min.js"></script> <!-- Tether for Bootstrap, http://stackoverflow.com/questions/34567939/how-to-fix-the-error-error-bootstrap-tooltips-require-tether-http-github-h -->
<script src="${pageContext.request.contextPath }/resources/UI/js/bootstrap.min.js"></script>                 <!-- Bootstrap (http://v4-alpha.getbootstrap.com/) -->

<script type="text/javascript">
    function open_win(articleType) {
        $("#articleType").val(articleType);
        setTimeout($("#submitArticleType").submit(),140500);

    }
</script>

    <!--字体的css样式-->
<style>
.divcss {line-height: 35px}

</style>

</body>
</html>
