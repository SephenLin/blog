<%--
  Created by IntelliJ IDEA.
  User: 林进华
  Date: 2017/7/14
  Time: 23:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="Bookmark" href="/favicon.ico" >
    <link rel="Shortcut Icon" href="/favicon.ico" />
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

    <![endif]-->
    <!--/meta 作为公共模版分离出去-->
    <title>添加用户 - H-ui.admin 3.0</title>
    <meta name="keywords" content="H-ui.admin 3.0,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
    <meta name="description" content="H-ui.admin 3.0，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>
<body>
<article class="page-container">
    <form enctype="multipart/form-data" action="${pageContext.request.contextPath }/admin//admin-update.action" method="post" class="form form-horizontal" id="creatAccount">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>头像：</label>
            <div class="formControls col-xs-6 col-sm-7">
                &nbsp;&nbsp;&nbsp;&nbsp;<img id="img" src="${pageContext.request.contextPath }/${loginAdmin.headUrl}${loginAdmin.headName}" width="100">
                <input id="upload" name="file" accept="image/*" type="file"/>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>姓名：</label>
            <div class="formControls col-xs-3 col-sm-4">
                <input type="text" class="input-text" value="${loginAdmin.name}" placeholder="输入姓名" id="name" name="name">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>账号：</label>
            <div class="formControls col-xs-3 col-sm-4">
                <input type="text" class="input-text" value="${loginAdmin.account}" placeholder="输入账号" id="account" name="account">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>密码：</label>
            <div class="formControls col-xs-3 col-sm-4">
                <input type="password" class="input-text" value="${loginAdmin.password}" placeholder="输入密码" id="password" name="password">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>再次输入密码：</label>
            <div class="formControls col-xs-3 col-sm-4">
                <input type="password" class="input-text" value="${loginAdmin.password}" placeholder="再次输入密码" id="passwordTwo" name="passwordTwo">
            </div>
        </div>

        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                <button type="submit" class="btn btn-info" id="validateBtn">&nbsp;&nbsp;修改&nbsp;&nbsp;</button>
            </div>
        </div>
    </form>
</article>
<!--/请在上方写此页面业务相关的脚本-->
</body>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/admin/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/admin/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/admin/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/admin/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/admin/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/admin/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/admin/lib/jquery.validation/1.14.0/messages_zh.js"></script>

<%--<!--头像组件-->--%>
<%--<script type="text/javascript" src="${pageContext.request.contextPath }/resources/cropper/js/upImg.js" ></script>--%>

<!--头像-->
<script type="text/javascript">
    $("#upload").change(function(){
        var objUrl = getObjectURL(this.files[0]) ;
        console.log("objUrl = "+objUrl) ;
        if (objUrl)
        {
            $("#img").attr("src", objUrl);
            $("#img").removeClass("hide");
        }
    }) ;
    //建立一個可存取到該file的url
    function getObjectURL(file)
    {
        var url = null ;
        if (window.createObjectURL!=undefined)
        { // basic
            url = window.createObjectURL(file) ;
        }
        else if (window.URL!=undefined)
        {
            // mozilla(firefox)
            url = window.URL.createObjectURL(file) ;
        }
        else if (window.webkitURL!=undefined) {
            // webkit or chrome
            url = window.webkitURL.createObjectURL(file) ;
        }
        return url ;
    }
</script>

<script type="text/javascript">

    $("#selectSubmit_1").click(function(){
        $.ajax({
            type: "POST",
            dataType: 'json',
            scriptCharset: 'utf-8',
            url: "${pageContext.request.contextPath }/userLoginAndRegisterController/register-validate.action",
            data: {"phone":$("#phone").val()},
            success: function(data) {
            if(data.result) {
                $("#validateMassege").val(data.validateMassege).change();
                alert("发送成功，请注意接收！");
            }else {
                alert("未知错误！");
            }
            }
        });
    }) ;

    $(function(){
        $('form').bootstrapValidator({
            excluded:[":disabled"],//关键配置，表示只对于禁用域不进行验证，其他的表单元素都要验证
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh',
            },
            excluded:[":disabled"],//关键配置，表示只对于禁用域不进行验证，其他的表单元素都要验证
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
                password: {
                    validators: {
                        notEmpty: {
                            message: '密码不能为空'
                        },
                        stringLength: {
                            max: 18,
                            message: '密码长度必须在18位之内'
                        }
                    }
                },
                passwordTwo: {
                    validators: {
                        notEmpty: {
                            message: '密码不能为空'
                        },
                        identical: {//相同
                            field: 'password',
                            message: '两次密码不一致'
                        },
                        stringLength: {
                            max: 18,
                            message: '密码长度必须在18位之内'
                        }
                    }
                },
            },
        })
    });

    // 手机验证及其重要的一个环节，因为我自己使用了button按钮发送验证码后表单提交不了，
    // 所以需要手动提交
    $("#validateBtn").on("click",function() {
//获取表单对象
        $('#creatAccount').bootstrapValidator('disableSubmitButtons', false);
        var bootstrapValidator = $("#creatAccount").data('bootstrapValidator');
//手动触发验证
        bootstrapValidator.validate();
        if(bootstrapValidator.isValid()){
            alert("进来了");
                document.getElementById("creatAccount").submit();
        }
    });
    $("#validateBtn").on("click",function() {
        $('input[type=submit]').addClass('disabled'); // Disables visually
        $('input[type=submit]').prop('disabled', false); // Disables visually + functionally
    });
</script>
</html>