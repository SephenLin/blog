<%--
  Created by IntelliJ IDEA.
  User: 林进华
  Date: 2017/7/14
  Time: 23:54
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


    <!--
        表单验证神器
        -->
    <link href="${pageContext.request.contextPath }/resources/bootstrapValidate/css/bootstrap.min.css" rel="stylesheet"/>
    <script src="${pageContext.request.contextPath }/resources/bootstrapValidate/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/bootstrapValidate/jquery-1.7.2.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath }/resources/bootstrapValidate/js/bootstrapValidator.min.js"></script>
    <link href="${pageContext.request.contextPath }/resources/bootstrapValidate/css/bootstrapValidator.min.css" rel="stylesheet" />


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
    <link rel="shortcut icon" href="${pageContext.request.contextPath }/resources/UI/favicon.ico">

    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,700,300' rel='stylesheet' type='text/css'>

    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/UI/css/bootstrap2.min.css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/UI/css/animate.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/UI/css/bootstrap2.min.css/style.css">


    <!-- Modernizr JS -->
    <script src="${pageContext.request.contextPath }/resources/UI/js/modernizr-2.6.2.min.js"></script>
    <!-- FOR IE9 below -->
    <!--[if lt IE 9]-->
    <script src="${pageContext.request.contextPath }/resources/UI/js/respond.min.js"></script>
    <![endif]-->

</head>
<body class="style-2">

<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">


            <!-- Start Sign In Form -->
            <form method="post" action="${pageContext.request.contextPath }/adminLoginController/login1.action" id="loginUI" class="fh5co-form animate-box" data-animate-effect="fadeInLeft">
                <h2>登录</h2>
                <div class="form-group">
                    <label for="account" class="sr-only">账号：</label>
                    <input type="text" name="account" class="form-control" id="account" placeholder="account" autocomplete="off">
                </div>
                <div class="form-group">
                    <label for="password" class="sr-only">密码：</label>
                    <input type="password" name="password" class="form-control" id="password" placeholder="password" autocomplete="off">
                </div>
                <div class="form-group">
                    <input type="submit" value="登 录" id="validateBtn" class="btn btn-primary">
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
<script src="${pageContext.request.contextPath }/resources/UI/js/jquery.placeholder.min.js"></script>
<!-- Waypoints -->
<script src="${pageContext.request.contextPath }/resources/UI/js/jquery.waypoints.min.js"></script>
<!-- Main JS -->
<script src="${pageContext.request.contextPath }/resources/UI/js/main.js"></script>

<!--分页插件-->
<script src="${pageContext.request.contextPath }/resources/laypage/laypage/laypage.js" charset="utf-8"></script>


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
                    password: {
                        validators: {
                            notEmpty: {
                                message: '密码不能为空'
                            },
                            regexp: {
                                regexp: /^[a-zA-Z0-9_]+$/,
                                message: '密码只能包含大写、小写、数字和下划线'
                            },
                            stringLength: {
                                max: 18,
                                message: '密码长度必须在18位之内'
                            }
                        }
                    },
                }
            })
        });

        if(${!result}) {
            alert("${message}");
        }

    $("#validateBtn").on("click",function() {
//获取表单对象
        $('#loginUI').bootstrapValidator('disableSubmitButtons', false);
        var bootstrapValidator = $("#loginUI").data('bootstrapValidator');
//手动触发验证
        bootstrapValidator.validate();
        if(bootstrapValidator.isValid()){
            alert("进来了");
            $.ajax({
                type: "POST",
                dataType: 'json',
                scriptCharset: 'utf-8',
                url: "${pageContext.request.contextPath }/adminLoginController/login1.action",
                data: {"account":$("#account").val(),"password":$("#password").val()},
                success: function(data) {
                    if(data.result) {
                        alert(data.message);
                        window.location.href="${pageContext.request.contextPath }/adminLoginController/login2.action";//你可以跟换里面的网址，以便成功后跳转
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

