<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]-->
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/admin/lib/html5shiv.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/admin/lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/admin/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/admin/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/admin/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/admin/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/admin/static/h-ui.admin/css/style.css" />
<!--[if IE 6]-->
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/admin/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>用户查看</title>
</head>
<body>
<div class="cl pd-20" style=" background-color:#5bacb6">
	<dt><span class="f-18"><h3 align="center">张三</h3></span></dt>
	<dt>
		<span class="f-18"><h3 align="center">作者：${article.user.name}&nbsp;&nbsp;&nbsp;&nbsp;创作时间 : ${article.time}</h3></span>
		<span class="f-18"><h3 align="center">浏览人数 : ${article.traffics}</h3></span>
	</dt>
	<dd class="pt-10 f-12" style="margin-left:0"><h4 align="center">简介 : ${article.introduce}</h4></dd>
	</dl>
</div>
<div class="divcss pd-20" style="width:690px;">
	<h5 class="divcss">${article.content}</h5>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/admin/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/admin/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/admin/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/admin/static/h-ui.admin/js/H-ui.admin.js"></script>
<!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<!--字体的css样式-->
<style>
	.divcss {line-height: 35px}

</style>
</body>
</html>