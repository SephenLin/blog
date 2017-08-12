<%--
  Created by IntelliJ IDEA.
  User: 林进华
  Date: 2017/7/14
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="Bookmark" href="${pageContext.request.contextPath }/resources/admin/favicon.ico" >
    <link rel="Shortcut Icon" href="${pageContext.request.contextPath }/resources/admin/favicon.ico" />
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
    <!--富文本编辑器-->
    <script type="text/javascript" src="${pageContext.request.contextPath }/resources/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/resources/ueditor/ueditor.all.min.js"> </script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/resources/ueditor/lang/zh-cn/zh-cn.js"></script>
    <!--/meta 作为公共模版分离出去-->

    <title>新增文章 - 资讯管理 - H-ui.admin 3.0</title>
    <meta name="keywords" content="H-ui.admin 3.0,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
    <meta name="description" content="H-ui.admin 3.0，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>
<body>
<article class="page-container">
    <form class="form form-horizontal" id="form-article-add" method="post" action="${pageContext.request.contextPath }/user/article-update.action">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>文章标题：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="${article.title}" placeholder="" id="articletitle" name="title">
            </div>
        </div>
${article.articleType}
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>文章类型：</label>
            <div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
				<select name="articleType" class="select">
                    <option value="1" <c:if test="${article.articleType == '1'}">selected="selected"</c:if>>治愈</option>
					<option value="2" <c:if test="${article.articleType == '2'}">selected="selected"</c:if>>技术</option>
					<option value="3" <c:if test="${article.articleType == '3'}">selected="selected"</c:if>>心情</option>
                    <option value="4" <c:if test="${article.articleType == '4'}">selected="selected"</c:if>>清晨</option>
                    <option value="5" <c:if test="${article.articleType == '5'}">selected="selected"</c:if>>中午</option>
                    <option value="6" <c:if test="${article.articleType == '6'}">selected="selected"</c:if>>下午</option>
                    <option value="7" <c:if test="${article.articleType == '7'}">selected="selected"</c:if>>夜晚</option>
                    <option value="8" <c:if test="${article.articleType == '8'}">selected="selected"</c:if>>深夜</option>
				</select>
				</span> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">关键词：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="<c:forEach items="${article.types}" var="type">${type.label},</c:forEach>" placeholder="" id="type" name="type">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">文章摘要：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <textarea name="introduce" cols="" rows="" class="textarea"  placeholder="说点什么...最少输入10个字符" datatype="*10-100" dragonfly="true" nullmsg="备注不能为空！" onKeyUp="$.Huitextarealength(this,200)">${article.introduce}</textarea>
                <p class="textarea-numberbar"><em class="textarea-length">0</em>/200</p>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">文章作者：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="${user.name}" placeholder="" id="author" name="author">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">允许评论：</label>
            <div class="formControls col-xs-8 col-sm-9 skin-minimal">
                <div class="check-box">
                    <input type="checkbox" id="allowcomments" name="isComment" <c:if test="${article.isComment != null}"> checked="checked"</c:if> value="1">
                    <label for="checkbox-pinglun">&nbsp;</label>
                </div>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">评论开始日期：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" onfocus="WdatePicker({ dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'commentdatemax\')||\'%y-%M-%d\'}' })" id="commentdatemin" name="startTime" class="input-text Wdate" value="<fmt:formatDate value="${article.startTime}" pattern="yyyy-MM-dd"/>">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">评论结束日期：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" onfocus="WdatePicker({ dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'commentdatemin\')}' })" id="commentdatemax" name="endTime" class="input-text Wdate" value="<fmt:formatDate value="${article.endTime}" pattern="yyyy-MM-dd"/>">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">文章内容：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <textarea name="content1" id="myEditor">
                    ${article.content}
                </textarea>
            </div>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                <button onClick="article_save_submit();" class="btn btn-primary radius" type="submit"><i class="Hui-iconfont">&#xe632;</i> 保存并提交审核</button>
                <button onClick="article_save();" class="btn btn-secondary radius" type="button"><i class="Hui-iconfont">&#xe632;</i> 保存草稿</button>
                <button onClick="removeIframe();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
            </div>
        </div>
        <input type="hidden" name="id" value="${article.id}">
    </form>
</article>

                <!--_footer 作为公共模版分离出去-->
                <script type="text/javascript" src="${pageContext.request.contextPath }/resources/admin/lib/jquery/1.9.1/jquery.min.js"></div>
                <script type="text/javascript" src="${pageContext.request.contextPath }/resources/admin/lib/layer/2.4/layer.js"></script>
                <script type="text/javascript" src="${pageContext.request.contextPath }/resources/admin/static/h-ui/js/H-ui.min.js"></script>
                <script type="text/javascript" src="${pageContext.request.contextPath }/resources/admin/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer /作为公共模版分离出去-->

                <!--请在下方写此页面业务相关的脚本-->
                <script type="text/javascript" src="${pageContext.request.contextPath }/resources/admin/lib/My97DatePicker/4.8/WdatePicker.js"></script>
                <script type="text/javascript" src="${pageContext.request.contextPath }/resources/admin/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
                <script type="text/javascript" src="${pageContext.request.contextPath }/resources/admin/lib/jquery.validation/1.14.0/validate-methods.js"></script>
                <script type="text/javascript" src="${pageContext.request.contextPath }/resources/admin/lib/jquery.validation/1.14.0/messages_zh.js"></script>
                <script type="text/javascript" src="${pageContext.request.contextPath }/resources/admin/lib/webuploader/0.1.5/webuploader.min.js"></script>
                <script type="text/javascript">
                    $(function(){
                        $('.skin-minimal input').iCheck({
                            checkboxClass: 'icheckbox-blue',
                            radioClass: 'iradio-blue',
                            increaseArea: '20%'
                        });

                        //表单验证
                            $("#form-article-add").validate({
                            debug: true, //调试模式取消submit的默认提交功能
                            //errorClass: "label.error", //默认为错误的样式类为：error
                            focusInvalid: false, //当为false时，验证无效时，没有焦点响应
                            onkeyup: false,
                            rules:{
                                title:{
                                    required:true,
                                },
                                articleType:{
                                    required:true,
                                },
                                type:{
                                    required:true,
                                },
                                introduce:{
                                    required:true,
                                },
                                author:{
                                    required:true,
                                },
                                content1:{
                                    required:true,
                                },

                            },
                            messages:{
                                title:{
                                    required:"标题必填"
                                },
                                type:{
                                    required:"类型必填",
                                },
                                introduce:{
                                    required: "简介不能为空",
                                },
                                content1:{
                                    equalTo:"文章内容不能为空"
                                }
                            },
                            onkeyup:false,
                            focusCleanup:true,
                            success:"valid",
                            submitHandler:function(form){
                                $(form).ajaxSubmit();
                                // 表单提交需要时间，所以需要延迟关闭
                                setTimeout(function () {
                                    window.parent.location.reload(); //刷新父页面
                                    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                                    parent.layer.close(index);  // 关闭layer
                                },6000);
                            }
                        });
                    });
                </script>
<SCRIPT type=text/javascript>
    var editor = new UE.ui.Editor();
    editor.render("myEditor");
    //1.2.4以后可以使用一下代码实例化编辑器
    //UE.getEditor('myEditor')
    UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
    UE.Editor.prototype.getActionUrl = function(action) {
        if (action == 'uploadimage' || action == 'uploadscrawl' || action == 'uploadvideo') {
            return 'http://localhost:8080/blog/image/image-add.action';
        } else {
            return this._bkGetActionUrl.call(this, action);
        }
    }
</SCRIPT>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>
