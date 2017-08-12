<%--
  Created by IntelliJ IDEA.
  User: 林进华
  Date: 2017/7/31
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Minimal and Clean Sign up / Login and Forgot Form by FreeHTML5.co</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Free HTML5 Template by FreeHTML5.co" />
    <meta name="keywords" content="free html5, free template, free bootstrap, html5, css3, mobile first, responsive" />




    <!-- Facebook and Twitter integration -->
    <meta property="og:title" content=""/>
    <meta property="og:image" content=""/>
    <meta property="og:url" content=""/>
    <meta property="og:site_name" content=""/>
    <meta property="og:description" content=""/>
    <meta name="twitter:title" content="" />
    <meta name="twitter:image" content="" />
    <meta name="twitter:url" content="" />
    <meta name="twitter:card" content="" />

    <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
    <link rel="shortcut icon" href="favicon.ico">

    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,700,300' rel='stylesheet' type='text/css'>

    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/admin/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/admin/css/animate.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/admin/css/style.css">

    <!-- Modernizr JS -->
    <script src="${pageContext.request.contextPath }/resources/admin/js/modernizr-2.6.2.min.js"></script>
    <!-- FOR IE9 below -->
    <!--[if lt IE 9]-->
    <script src="${pageContext.request.contextPath }/resources/admin/js/respond.min.js"></script>
    <![endif]-->

    <!--
    表单验证神器
    -->
    <link href="${pageContext.request.contextPath }/resources/bootstrapValidate/css/bootstrap.min.css" rel="stylesheet"/>
    <script src="${pageContext.request.contextPath }/resources/bootstrapValidate/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/bootstrapValidate/jquery-1.7.2.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath }/resources/bootstrapValidate/js/bootstrapValidator.min.js"></script>
    <link href="${pageContext.request.contextPath }/resources/bootstrapValidate/css/bootstrapValidator.min.css" rel="stylesheet" />

</head>
<body class="style-2">

<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">


            <!-- Start Sign In Form -->
            <form action="${pageContext.request.contextPath }/userLoginAndRegisterController/sendEmailForPassword.action" id="sendEmailForPassword" method="post" class="fh5co-form animate-box" data-animate-effect="fadeInLeft">
                <h2>找回密码</h2>
                <div class="form-group">
                    <div class="alert alert-success" role="alert">填写下面信息找回密码</div>
                </div>
                <div class="form-group">
                    <label for="name" class="sr-only">name</label>
                    <input type="text" class="form-control" id="name" name="name" placeholder="name" autocomplete="off">
                </div>
                <div class="form-group">
                    <label for="account" class="sr-only">account</label>
                    <input type="text" class="form-control" id="account" name="account" placeholder="account" autocomplete="off">
                </div>
                <div class="form-group">
                    <label for="email" class="sr-only">email</label>
                    <input type="email" class="form-control" id="email" name="email" placeholder="email" autocomplete="off">
                </div>
                <div class="form-group">
                    <p><a href="${pageContext.request.contextPath }/login.jsp">登录</a> or <a href="${pageContext.request.contextPath }/register.jsp">注册</a></p>
                </div>
                <div class="form-group">
                    <input type="submit" value="Send Email" id="validateBtn" class="btn btn-primary">
                </div>
            </form>
            <!-- END Sign In Form -->


        </div>
    </div>
    <div class="row" style="padding-top: 60px; clear: both;">
        <div class="col-md-12 text-center"><p><small>&copy; All Rights Reserved. More Templates <a href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a> - Collect from <a href="http://www.cssmoban.com/" title="网页模板" target="_blank">网页模板</a></small></p></div>
    </div>
</div>

<!-- Placeholder -->
<script src="${pageContext.request.contextPath }/resources/admin/js/jquery.placeholder.min.js"></script>
<!-- Waypoints -->
<script src="${pageContext.request.contextPath }/resources/admin/js/jquery.waypoints.min.js"></script>
<!-- Main JS -->
<script src="${pageContext.request.contextPath }/resources/admin/js/main.js"></script>

<script type="text/javascript">
    $(function(){
        $('form').bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh',
            },
            fields: {
                name: {
                    validators: {
                        notEmpty: {
                            message: '名字不能为空'
                        },
                        stringLength: {
                            max: 18,
                            message: '用户名长度必须在18位之内'
                        }
                    }
                },
                account: {
                    validators: {
                        notEmpty: {
                            message: '账号不能为空'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9_]+$/,
                            message: '账号只能包含大写、小写、数字和下划线'
                        },
                        stringLength: {
                            max: 18,
                            message: '账号长度必须在18位之内'
                        }
                    }
                },
                email: {
                    validators: {
                        notEmpty: {
                            message: '邮箱不能为空'
                        },
                        regexp: {
                            regexp: /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/,
                            message: '邮箱地址格式有误'
                        }
                    }
                }
            },
        })
    });

    $("#validateBtn").on("click",function() {
//获取表单对象
        $('#sendEmailForPassword').bootstrapValidator('disableSubmitButtons', false);
        var bootstrapValidator = $("#sendEmailForPassword").data('bootstrapValidator');
//手动触发验证
        bootstrapValidator.validate();
        if(bootstrapValidator.isValid()){
            alert("进来了");
            $.ajax({
                type: "POST",
                dataType: 'json',
                scriptCharset: 'utf-8',
                url: "${pageContext.request.contextPath }/userLoginAndRegisterController/sendEmailForPassword.action",
                data: {"name":$("#name").val(),"account":$("#account").val(),"email":$("#email").val()},
                success: function(data) {
                    if(data.result) {
                        alert(data.message);
                        window.location.href="login.jsp";//你可以跟换里面的网址，以便成功后跳转
                    }else {
                        alert(data.message);
                    }
                }
            });
        }
    });
</script>
</body>
</html>


