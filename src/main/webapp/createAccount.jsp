<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Create Account</title>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!--[if lt IE 9]-->
    <script type="text/javascript" src="${pageContext.request.contextPath }/resources/admin/lib/html5shiv.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/resources/admin/lib/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/admin/static/h-ui/css/H-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/admin/static/h-ui.admin/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/admin/lib/Hui-iconfont/1.0.8/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/admin/static/h-ui.admin/skin/default/skin.css" id="skin" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/admin/static/h-ui.admin/css/style.css" />

    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/cropper/css/cropper.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/cropper/css/cropperindex.css" />

    <!--[if IE 6]-->
    <script type="text/javascript" src="${pageContext.request.contextPath }/resources/admin/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <!--
    表单验证神器
    -->
    <link href="${pageContext.request.contextPath }/resources/bootstrapValidate/css/bootstrap.min.css" rel="stylesheet"/>
    <script src="${pageContext.request.contextPath }/resources/bootstrapValidate/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/bootstrapValidate/jquery-1.7.2.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath }/resources/bootstrapValidate/js/bootstrapValidator.min.js"></script>
    <link href="${pageContext.request.contextPath }/resources/bootstrapValidate/css/bootstrapValidator.min.css" rel="stylesheet" />

</head>
<body class="templatemo-bg-gray">
${error == null}
${success == null}
<h1 class="margin-bottom-15">Create Account</h1>
<div class="container">
    <div class="col-md-12">
        <form class="form-horizontal templatemo-create-account templatemo-container" action="${pageContext.request.contextPath }/admin/createAccount.action" role="form" method="post">
            <div class="form-inner">
                <div class="form-group">
                    <div class="col-md-6">
                        <label for="first_name" class="control-label">名字</label>
                        <input type="text" class="form-control" id="first_name" placeholder="" name="name">
                    </div>
                    <div class="col-md-6">
                        <label for="first_name" class="control-label">部门</label>
                        <select class="form-control" name="department" >
                            <option value=".Net部">.Net部</option>
                            <option value="Java部">Java部</option>
                            <option value="美工部">美工部</option>
                            <option value="行政部">行政部</option>
                            <option value="php小组">php小组</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-6">
                        <label for="username" class="control-label">邮件</label>
                        <input type="email" class="form-control" id="email" name="email" placeholder="">
                    </div>
                    <div class="col-md-6">
                        <label for="photoNumber" class="control-label">电话号码</label>
                        <input type="tel" class="form-control" id="photoNumber" name="photoNumber" placeholder="">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-6">
                        <label for="username" class="control-label">用户名</label>
                        <input type="text" class="form-control" id="username" placeholder="" name="account">
                    </div>
                    <div class="col-md-6 templatemo-radio-group">
                        <label class="radio-inline">
                            <input type="radio" name="sex" id="optionsRadios1" value="1" checked> 男
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="sex" id="optionsRadios2" value="2"> 女
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-6">
                        <label for="password" class="control-label">密码</label>
                        <input type="password" class="form-control" id="password" name="password" placeholder="">
                    </div>
                    <div class="col-md-6">
                        <label for="password" class="control-label">确认密码</label>
                        <input type="password" name="password2" class="form-control" id="password_confirm" placeholder="">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-12">
                        <label><input type="checkbox">我同意 <a href="javascript:;" data-toggle="modal" data-target="#templatemo_modal">服务条款</a> 和 <a href="#">隐私政策.</a></label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-12">
                        <button type="submit" class="btn btn-default">注册</button>
                        <a href="login.html" class="pull-right">登陆</a>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="templatemo_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myModalLabel">创建账号</h4>
            </div>
            <div class="modal-body">
                <p>This form is provided by <a rel="nofollow" href="http://www.cssmoban.com/page/1">Free HTML5 Templates</a> that can be used for your websites. Cras mattis consectetur purus sit amet fermentum. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Morbi leo risus, porta ac consectetur ac, vestibulum at eros.</p>
                <p>Praesent commodo cursus magna, vel scelerisque nisl consectetur et. Vivamus sagittis lacus vel augue laoreet rutrum faucibus dolor auctor. Aenean lacinia bibendum nulla sed consectetur. Praesent commodo cursus magna, vel scelerisque nisl consectetur et. Donec sed odio dui. Donec ullamcorper nulla non metus auctor fringilla. Cras mattis consectetur purus sit amet fermentum. Cras justo odio, dapibus ac facilisis in, egestas eget quam.</p>
                <p>Morbi leo risus, porta ac consectetur ac, vestibulum at eros. Praesent commodo cursus magna, vel scelerisque nisl consectetur et. Vivamus sagittis lacus vel augue laoreet rutrum faucibus dolor auctor.</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">

    $(function () {
        $('form').bootstrapValidator({

            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh',
            },

            fields: {
                account: {
                    message: '用户名错误',
                    validators: {
                        notEmpty: {
                            message: '用户名不能为空'
                        },
                        remote: {
                            url: '${pageContext.request.contextPath }/admin/checkAdminAccount.action',//验证地址
                            message: '用户已存在',//提示消息
                            delay :  2000,//每输入一个字符，就发ajax请求，服务器压力还是太大，设置2秒发送一次ajax（默认输入一个字符，提交一次，服务器压力太大）
                            type: 'POST'//请求方式
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9_]+$/,
                            message: '用户名只能包含大写、小写、数字和下划线'
                        },
                        stringLength: {
                            max: 18,
                            message: '用户名长度必须在18位之内'
                        },
                    }
                },
                password: {
                    validators: {
                        notEmpty: {
                            message: '密码不能为空'
                        },
                        stringLength: {
                            max: 18,
                            message: '用户名长度必须在18位之内'
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
                },
                name: {
                    validators: {
                        notEmpty: {
                            message: '名字不能为空'
                        },
                    }
                },
                photoNumber: {
                    validators: {
                        notEmpty: {
                            message: '电话号码不能为空'
                        },
                        regexp: {
                            regexp: /^1(3|4|5|7|8)\d{9}$/,
                            message: '手机格式错误'
                        }
                    }
                }
            }
        });
    });
</script>
</html>
