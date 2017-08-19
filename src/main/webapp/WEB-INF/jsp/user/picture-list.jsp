<%--
  Created by IntelliJ IDEA.
  User: 林进华
  Date: 2017/7/14
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<link href="${pageContext.request.contextPath }/resources/admin/lib/lightbox2/2.8.1/css/lightbox.css" rel="stylesheet" type="text/css" >
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
    <title>图片列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 图片管理 <span class="c-gray en">&gt;</span> 图片列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div class="text-c">
        <button onclick="removeIframe()" class="btn btn-primary radius">关闭选项卡</button>
        <span class="select-box inline">
		<select name="" class="select" id="selectType">
         <option value="0">全部分类</option>
         <option value="1">分类一</option>
         <option value="2">分类二</option>
         </select>
         </span> 日期范围：
        <input type="text" name="selectStartTime" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'logmax\')||\'%y-%M-%d\'}' })" id="logmin" class="input-text Wdate" style="width:120px;">
        -
        <input type="text" name="selectEndTime" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'logmin\')}',maxDate:'%y-%M-%d' })" id="logmax" class="input-text Wdate" style="width:120px;">
        <input type="text" name="searchText" id="searchText" placeholder=" 资讯名称" style="width:250px" class="input-text">
        <button name="selectSubmit" onclick="selectSubmit()" id="selectSubmit" class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i> 搜资讯</button>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> </span> <span class="r">共有数据：<strong id="number">54</strong> 条</span> </div>
    <div class="mt-20">
        <table class="table table-border table-bordered table-bg table-hover table-sort">
            <thead>
            <tr class="text-c" id="test">
                <th width="40"><input name="" type="checkbox" value=""></th>
                <th width="80">ID</th>
                <th width="100">分类</th>
                <th width="100">封面</th>
                <th width="100">文章名字</th>
                <th>备注</th>
                <th width="150">更新时间</th>
                <th width="100">状态</th>
                <th width="100">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${images}" var="image">
                <tr class="text-c">
                    <td><input name="subChk" type="checkbox" value="${image.id}"></td>
                    <td>${image.id}</td>
                    <td>${image.id}</td>
                    <td>
                        <li class="item">
                            <div class="portfoliobox">
                                <div class="picbox"><a href="${pageContext.request.contextPath }/${image.url}${image.name}" data-lightbox="gallery" data-title="卫生间1"><img src="${pageContext.request.contextPath }/${image.url}${image.name}" height="110" width="210"></a></div>
                            </div>
                        </li>
                    </td>
                    <td>${image.title}</td>
                    <td>${image.introduce}</td>
                    <td>2014-6-11 11:11:42</td>
                    <td class="td-manage"> <a style="text-decoration:none" class="ml-5" onClick="picture_edit('图库编辑','${pageContext.request.contextPath }/user/picture-updateUI.action','${image.id}')" href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a> <a style="text-decoration:none" class="ml-5" onClick="picture_del(this,${image.id})" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
                </tr>
            </c:forEach>
            ${pageBean.getEndPageIndex() - pageBean.getStartPageIndex() > 2 ? 3 : pageBean.getEndPageIndex() - pageBean.getStartPageIndex()}
            ${initPageSize}

            <tr class="text-c"><td id="pagetool" colspan="12" align="center"></td></tr>
            </tbody>
        </table>
    </div>
</div>
<a style="text-decoration:none" onClick="picture_stop(this,'252')" href="javascript:;" title="下架"><i class="Hui-iconfont">&#xe6de;</i></a>
<a style="text-decoration:none" onClick="picture_audit(this,252)" href="javascript:;" title="提交审核"><i class="Hui-iconfont">&#xe603;</i></a>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/admin/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/admin/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/admin/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/admin/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/admin/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/admin/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/admin/lib/laypage/1.2/laypage.js"></script>

<!--分页插件-->
<script src="${pageContext.request.contextPath }/resources/laypage/laypage/laypage.js" charset="utf-8"></script>

<script type="text/javascript">
    <!--公共化的信息-->
    var tempPageCount = 15;  // 总页数
    var tempNowPage = 1;  // 现在的页数
    var tempSize = 5;  // 连续分页的间隔数
    var selectStartTime = $("#logmin");   // 文章开始的时间
    var selectEndTime = $("#logmax");  // 文章结束的时间
    var searchText = $("#searchText");  // 文章标题
    var selectType = $("#selectType");  // 文章类型

    /*laypage分页插件*/
    laypage({
        cont: 'pagetool', //分页所摆放的容器
        pages: ${initPage.pageCount}, //总页数,
        groups: ${initPageSize}, //连续显示分页数
        skip: true, //显示跳转
        first : "首页",
        curr : tempNowPage,
        skin: '#1E9FFF', //颜色
        jump: function(obj){
            //得到了当前页，用于向服务端请求对应数据
            $.ajax({
                type: "POST",
                dataType: 'json',
                scriptCharset: 'utf-8',
                url: "${pageContext.request.contextPath }/image/image-list.action",
                data: {"nowPage":obj.curr ,"size":5 ,"searchText":searchText.val() ,"selectType":selectType.val()},
                success: function(data) {
                    $("#number").text(data.pageBean.recordCount);
                    obj.pages = data.pageBean.pageCount;
                    $("table #temp").remove();
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
                    url: "${pageContext.request.contextPath }/image/image-list.action",
                    data: {"nowPage":obj.curr ,"size":5 ,"searchText":searchText.val() ,"selectType":selectType.val()},
                    success: function(data) {
                        alert("ajax进来了");
                        $("#number").text(data.pageBean.recordCount);
                        obj.pages = data.pageBean.pageCount;
                        $("table #temp").remove();
                        var curr = obj.curr;
                        var htmls = data.htmls;
                        // 将当前页码的信息公共化，方便其他方法调用
                        tempNowPage = obj.curr;
                        tempSize = data.pageBean.size;
                        tempPageCount = obj.pages;
                        $(data.htmls).insertAfter($("#test"));
                    }
                });
            }
        });
    }

    <!--文章收索查询-->
    function selectSubmit() {
        alert("3" + $("#searchText").val());
        //得到了当前页，用于向服务端请求对应数据
        $.ajax({
            type: "POST",
            dataType: 'json',
            scriptCharset: 'utf-8',
            url: "${pageContext.request.contextPath }/image/image-list.action",
            data: {"nowPage":1 ,"size":5 ,"searchText":$("#searchText").val() ,
                "selectType":$("#selectType").val()},
            success: function(data) {
                getLaypage(data.pageBean);
            }
        });
    }

    <!--批量删除-->
    function datadel() {
        var ll = $("#1235").val();
        alert("dsada");
        // 判断是否至少选择一项
        var checkedNum = $("input[name='subChk']:checked").length;
        if(checkedNum == 0) {
            layer.msg("请选择至少一项！",{icon:2,time:1000});
            return;
        }
        // 批量选择
        if(confirm("确定要删除所选项目？")) {
            var checkedList = new Array();
            $("input[name='subChk']:checked").each(function() {
                checkedList.push($(this).val());
            });
            alert("总记录数" + $("#number").text());
            $.ajax({
                type: "POST",
                dataType: 'json',
                scriptCharset: 'utf-8',
                url: "${pageContext.request.contextPath }/image/images-delete.action",
                data: {"ids":checkedList.toString(),"size":tempSize,"nowPage":tempNowPage,"recordCount":$("#number").text()},
                success: function(data) {
                    if(data.result) {
                        getLaypage(data.pageBean);
                        layer.msg('已删除!',{icon:1,time:1000});
                    }else {
                        alert("2");
                        alert(data.result);
                        if(data.errorType == '1') {
                            layer.msg(data.mesage,{icon:2,time:1000});
                        }
                    }
                }
            });
        }
    }

    /*图片-编辑*/
    function picture_edit(title,id){
        var index = layer.open({
            type: 2,
            title: title,
            content: "${pageContext.request.contextPath }/image/image-updateUI.action?id=" + id,
        });
        layer.full(index);
    }

    /*图片-删除*/
    function picture_del(obj,id){
        if(confirm("确定要删除？")) {
            $.ajax({
                type: 'POST',
                dataType: 'json',
                scriptCharset: 'utf-8',
                url: '${pageContext.request.contextPath }/image/images-delete.action',
                data: {"ids":id,"size":tempSize,"nowPage":tempNowPage,"recordCount":$("#number").text()},
                success: function(data){
                    $("#number").text(data.pageBean.recordCount);
                    if(data.result) {
                        getLaypage(data.pageBean);
                        layer.msg('已删除!',{icon:1,time:1000});
                    }else {
                        alert("2");
                        alert(data.result);
                        if(data.errorType == '1') {
                            layer.msg(data.mesage,{icon:2,time:1000});
                        }
                    }
                }
            });
        }
    }

    /*资讯-下架*/
    function picture_stop(obj,id){
        if(confirm("确定要下架？")) {
            var articleId = $(obj).parents("tr").find("#articleId").val();
            alert(articleId);
            $.ajax({
                type: "get",
                dataType: "json",
                url: '${pageContext.request.contextPath }/image/image-audit.action?id=' + id + "&audit=2" ,
                success: function (data) {
                    alert("下架");
                    if (data.result) {
                        var name1 = '<a style="text-decoration:none" onClick="picture_audit(this,';
                        var name2 = name1 +id;
                        var name3 = ')" href="javascript:;" title="提交审核"><i class="Hui-iconfont">&#xe603;</i></a>';
                        var name4 = name2 + name3;
                        $(obj).parents("tr").find(".td-manage").prepend(name4);
                        $(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已下架</span>');
                        $(obj).remove();
                        alert(name4);
                        layer.msg('已下架!',{icon: 5,time:1000});
                    }else {
                        layer.msg('发生未知错误!',{icon: 5,time:1000});
                    }
                }
            });
        }
    }

    /*资讯-提交审核*/
    function picture_audit(obj,id){
        if(confirm("确定要提交审核？")) {
            $.ajax({
                type: "get",
                dataType: "json",
                url: '${pageContext.request.contextPath }/image/image-audit.action?id=' + id + "&audit=0",
                success: function (data) {
                    alert("提交");
                    if (data.result) {
                        var name1 = '<a style="text-decoration:none" onClick="picture_stop(this,';
                        var name2 = name1 + id;
                        var name3 = ')" href="javascript:;" title="下架"><i class="Hui-iconfont">&#xe6de;</i></a>';
                        var name4 = name2 + name3;
                        alert()
                        $(obj).parents("tr").find(".td-manage").prepend(name4);
                        $(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">审核中</span>');
                        $(obj).remove();
                        layer.msg('已提交，请耐心等待结果!',{icon: 6,time:1000});
                    }else {
                        layer.msg('发生未知错误!',{icon: 5,time:1000});
                    }
                }
            });
        }
    }


    /*资讯-发布*/
    function picture_start(obj,id){
        if(confirm("确定要发布？")) {
            $.ajax({
                type: "get",
                dataType: "json",
                url: '${pageContext.request.contextPath }/image/image-audit.action?id=' + id + "&audit=1",
                success: function (data) {
                    if (data.result) {
                        var name1 = '<a style="text-decoration:none" onClick="article_stop(this,';
                        var name2 = name1 + id;
                        var name3 = ')" href="javascript:;" title="下架"><i class="Hui-iconfont">&#xe6de;</i></a>';
                        var name4 = name2 + name3;
                        $(obj).parents("tr").find(".td-manage").prepend(name4);
                        $(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已发布</span>');
                        $(obj).remove();
                        layer.msg('已发布!',{icon: 6,time:1000});
                    }else {
                        layer.msg('发生未知错误!',{icon: 5,time:1000});
                    }
                }
            });
        }
    }

    /*文章-查看*/
    function article_show(title,id,w,h){
        var url = '${pageContext.request.contextPath }/user/article-show.action?id=' + id;
        layer_show(title,url,w,h);
    }

</script>

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/admin/lib/lightbox2/2.8.1/js/lightbox.min.js"></script>
<script type="text/javascript">
    $(function(){
        $(".portfolio-area li").Huihover();
    });
</script>
</body>
</html>
