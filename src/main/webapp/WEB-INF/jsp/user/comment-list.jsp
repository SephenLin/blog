<%--
  Created by IntelliJ IDEA.
  User: 林进华
  Date: 2017/7/14
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
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

    <title>意见反馈</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 评论管理 <span class="c-gray en">&gt;</span> 意见反馈 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div class="text-c"> 日期范围：
        <input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}' })" id="datemin" class="input-text Wdate" style="width:120px;">
        -
        <input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d' })" id="datemax" class="input-text Wdate" style="width:120px;">
        <input type="text" class="input-text" style="width:250px" placeholder="输入关键词" id="searchText" name="searchText">
        <button type="submit" onclick="selectSubmit()" class="btn btn-success radius" id="" name=""><i class="Hui-iconfont">&#xe665;</i> 搜意见</button>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> </span> <span class="r">共有数据：<strong id="number">88</strong> 条</span> </div>
    <div class="mt-20">
        <table class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr class="text-c" id="test">
                <th width="25"><input type="checkbox" name="" value=""></th>
                <th width="60">ID</th>
                <th width="130">文章标题</th>
                <th width="60">用户名</th>
                <th>留言内容</th>
                <th width="100">操作</th>
            </tr>
            <tr class="text-c"><td id="pagetool" colspan="12" align="center"></td></tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
</div>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/admin/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/admin/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/admin/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/admin/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer /作为公共模版分离出去-->

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
    var tempSize = 4;  // 连续分页的间隔数
    var selectStartTime = $("#datemin");   // 文章开始的时间
    var selectEndTime = $("#datemax");  // 文章结束的时间
    var searchText = $("#searchText");  // 文章标题
    alert("分页前面部分没问题");
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
            alert("分页的jump没问题");
            //得到了当前页，用于向服务端请求对应数据
            $.ajax({
                type: "POST",
                dataType: 'json',
                scriptCharset: 'utf-8',
                url: "${pageContext.request.contextPath }/user/comment-list.action",
                data: {"nowPage":obj.curr ,"size":5 ,"searchText":searchText.val()},
                success: function(data) {
                    $("#number").text(data.pageBean.recordCount);
                    obj.pages = data.pageBean.pageCount;
                    alert(data.pageBean.pageCount);
                    $("table #temp").remove();
                    var curr = obj.curr;
                    var comments = data.comments;
                    // 将当前页码的信息公共化，方便其他方法调用
                    tempNowPage = obj.curr;
                    tempSize = data.pageBean.size;
                    tempPageCount = obj.pages;
                    alert(comments);
                    alert(tempNowPage);
                    $(comments).insertAfter($("#test"));
                }
            });
        }
    });

    // 调用laypage函数
    function getLaypage(tempPageBean) {
        alert("1");
        var pageBean = $.extend({},tempPageBean);
        alert("调用的laypage 当前页数 " + pageBean.nowPage);
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
                    url: "${pageContext.request.contextPath }/user/comment-list.action",
                    data: {"nowPage":obj.curr ,"size":5 ,"searchText":searchText.val()},
                    success: function(data) {
                        $("#number").text(data.pageBean.recordCount);
                        obj.pages = data.pageBean.pageCount;
                        $("table #temp").remove();
                        var curr = obj.curr;
                        var comments = data.comments;
                        // 将当前页码的信息公共化，方便其他方法调用
                        tempNowPage = obj.curr;
                        tempSize = data.pageBean.size;
                        tempPageCount = obj.pages;
                        $(comments).insertAfter($("#test"));
                    }
                });
            }
        });
    }

    <!--文章收索查询-->
    function selectSubmit() {
        alert("3");
        //得到了当前页，用于向服务端请求对应数据
        $.ajax({
            type: "POST",
            dataType: 'json',
            scriptCharset: 'utf-8',
            url: "${pageContext.request.contextPath }/user/comment-list.action",
            data: {"nowPage":1 ,"size":5 ,"searchText":$("#searchText").val()},
            success: function(data) {
                getLaypage(data.pageBean);
            }
        });
    }

    <!--批量删除-->
    function datadel() {
        alert("dsada");
        // 判断是否至少选择一项
        var checkedNum = $("input[name='checkComment']:checked").length;
        if(checkedNum == 0) {
            layer.msg("请选择至少一项！",{icon:2,time:1000});
            return;
        }
        // 批量选择
        if(confirm("确定要删除所选留言？")) {
            var checkedList = new Array();
            $("input[name='checkComment']:checked").each(function() {
                checkedList.push($(this).val());
            });
            alert("总记录数" + $("#number").text());
            $.ajax({
                type: "POST",
                dataType: 'json',
                scriptCharset: 'utf-8',
                url: "${pageContext.request.contextPath }/user/comments-delete.action",
                data: {"ids":checkedList.toString(),"size":tempSize,"nowPage":tempNowPage,"recordCount":$("#number").text()},
                success: function(data) {
                    if(data.result) {
                        getLaypage(data.pageBean);
                        layer.msg('已删除!',{icon:1,time:1000});
                    }else {
                        if(data.errorType == '1') {
                            layer.msg(data.mesage,{icon:2,time:1000});
                        }
                    }
                }
            });
        }
    }

    /*资讯-删除*/
    function member_del(obj,id){
        if(confirm("确定要删除？")) {
            $.ajax({
                type: "POST",
                dataType: 'json',
                scriptCharset: 'utf-8',
                url: "${pageContext.request.contextPath }/user/comments-delete.action",
                data: {"ids":id,"size":tempSize,"nowPage":tempNowPage,},
                success: function(data) {
                    $("#number").text(data.pageBean.recordCount);
                    if(data.result) {
                        getLaypage(data.pageBean);
                        layer.msg('已删除!',{icon:1,time:1000});
                    }else {
                        if(data.errorType == '1') {
                            layer.msg(data.mesage,{icon:2,time:1000});
                        }
                    }
                }
            });
        }
    }
</script>

<script type="text/javascript">
    $(function() {

    })

    /*用户-查看*/
    function member_show(title,id,w,h) {
        var url = '${pageContext.request.contextPath }/comment/user-show.action?id=' + id;
        layer_show(title,url,w,h);
    }

    /*文章-查看*/
    function article_show(title,id,w,h){
        var url = '${pageContext.request.contextPath }/user/article-show.action?id=' + id;
        layer_show(title,url,w,h);
    }

</script>
</body>
</html>
