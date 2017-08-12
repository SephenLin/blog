<%--
  Created by IntelliJ IDEA.
  User: 林进华
  Date: 2017/7/14
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>修改图片</title>
    <link href="${pageContext.request.contextPath }/resources/admin/lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="page-container">
    <form class="form form-horizontal" id="form-article-add" method="post">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>图片所属文章标题：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="${image.article.title}" placeholder="" id="" name="title">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">作者：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="${loginUser.name}" placeholder="" id="" name="authorName">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">文章简介：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <textarea name="introduce" cols="" rows="" class="textarea"  placeholder="说点什么...最少输入10个字符" datatype="*10-100" dragonfly="true" nullmsg="备注不能为空！" onKeyUp="$.Huitextarealength(this,200)">${image.article.introduce}</textarea>
                <p class="textarea-numberbar"><em class="textarea-length">0</em>/200</p>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">缩略图：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <div class="uploader-thum-container">
                    <div id="fileList" class="uploader-list"></div>
                    <div id="filePicker">选择图片</div>
                    <button id="btn-star" class="btn btn-default btn-uploadstar radius ml-10">开始上传</button>
                </div>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">图片上传：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <div class="uploader-list-container">
                    <div id="dndArea" class="placeholder">
                        <div id="zyupload" class="zyupload"></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                <button onClick="article_save_submit();" class="btn btn-primary radius" type="submit"><i class="Hui-iconfont">&#xe632;</i> 保存并提交审核</button>
            </div>
        </div>
    </form>
</div>


<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/admin/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/admin/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/admin/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/admin/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer /作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/admin/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/admin/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/admin/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/admin/lib/webuploader/0.1.5/webuploader.min.js"></script>

<!--zyupload 插件-->
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/ZYupload/jquery-1.7.2.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/ZYupload/zyupload/skins/zyupload-1.0.0.min.css " type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/ZYupload/zyupload/zyupload.tailor-1.0.0.min.js"></script>
<!--zyupload 结束-->
<script type="text/javascript">
    $(function(){
        // 初始化插件
        $("#zyupload").zyUpload({
            width            :   "100%",                 // 宽度
            height           :   "100%",                 // 宽度
            itemWidth        :   "140px",                 // 文件项的宽度
            itemHeight       :   "115px",                 // 文件项的高度
            url              :   "${pageContext.request.contextPath }/image/image-update.action?id=" + ${image.id},  // 上传文件的路径
            fileType         :   ["jpg","png","js","exe","zip","mp4"],// 上传文件的类型
            fileSize         :   51200000,                // 上传文件的大小
            multiple         :   true,                    // 是否可以多个文件上传
            dragDrop         :   true,                    // 是否可以拖动上传文件
            tailor           :   true,                    // 是否可以裁剪图片
            del              :   true,                    // 是否可以删除文件
            finishDel        :   false,  				  // 是否在上传文件完成后删除预览
            /* 外部获得的回调接口 */
            onSelect: function(selectFiles, allFiles){    // 选择文件的回调方法  selectFile:当前选中的文件  allFiles:还没上传的全部文件
                console.info("当前选择了以下文件：");
                console.info(selectFiles);
            },
            onDelete: function(file, files){              // 删除一个文件的回调方法 file:当前删除的文件  files:删除之后的文件
                console.info("当前删除了此文件：");
                console.info(file.name);
            },
            onSuccess: function(file, response){          // 文件上传成功的回调方法
                console.info("此文件上传成功：");
                console.info(file.name);
                console.info("此文件上传到服务器地址：");
                console.info(response);
                var data = JSON.parse(response);
                alert(data.rc+","+data.msg+","+data.value);
                $("#uploadInf").append("<p>上传成功，文件地址是：" + response + "</p>");
            },
            onFailure: function(file, response){          // 文件上传失败的回调方法
                console.info("此文件上传失败：");
                console.info(file.name);
            },
            onComplete: function(response){           	  // 上传完成的回调方法
                console.info("文件上传完成");
                console.info(response);
            }
        });

    });

</script>

<script type="text/javascript">
function article_save(){
        alert("刷新父级的时候会自动关闭弹层。")
        window.parent.location.reload();
    }
</script>

</body>
</html>
