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
<title>用户管理</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 资讯管理 <span class="c-gray en">&gt;</span> 资讯列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="text-c">
		<button onclick="removeIframe()" class="btn btn-primary radius">关闭选项卡</button>
		日期范围：
		<input type="text" name="selectStartTime" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'logmax\')||\'%y-%M-%d\'}' })" id="logmin" class="input-text Wdate" style="width:120px;">
		-
		<input type="text" name="selectEndTime" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'logmin\')}',maxDate:'%y-%M-%d' })" id="logmax" class="input-text Wdate" style="width:120px;">
		<input type="text" name="searchText" id="searchText" placeholder=" 资讯名称" style="width:250px" class="input-text">
		<button name="selectSubmit" onclick="selectSubmit()" id="selectSubmit" class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i> 搜资讯</button>
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> </span> <span class="r">共有数据：<strong id="number">54</strong> 条</span> </div>
	<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c" id="test">
				<th width="25"><input type="checkbox" name="" value=""></th>
				<th width="80">ID</th>
				<th width="40">头像</th>
				<th width="100">用户名</th>
				<th width="70">账号</th>
				<th width="100">密码</th>
				<th width="100">手机</th>
				<th width="150">邮箱</th>
				<th width="20">等级</th>
				<th width="">人气值</th>
				<th width="130">加入时间</th>
				<th width="70">状态</th>
				<th width="100">操作</th>
			</tr>
		</thead>
		<tbody>
			<%--填充数据--%>
		</tbody>
		<tr class="text-c"><td id="pagetool" colspan="12" align="center"></td></tr>
	</table>
	</div>
</div>
----------------------------------------${initPageSize}${Excel}
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
                url: "${pageContext.request.contextPath }/admin/user-list.action",
                data: {"nowPage":obj.curr ,"size":5 ,"searchText":searchText.val()},
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
                    url: "${pageContext.request.contextPath }/admin/user-list.action",
                    data: {"nowPage":obj.curr ,"size":5 ,"searchText":searchText.val()},
                    success: function(data) {
                        alert("第0步");
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
    }

    <!--文章收索查询-->
    function selectSubmit() {
        alert("3");
        //得到了当前页，用于向服务端请求对应数据
        $.ajax({
            type: "POST",
            dataType: 'json',
            scriptCharset: 'utf-8',
            url: "${pageContext.request.contextPath }/admin/user-list.action",
            data: {"nowPage":1 ,"size":5 ,"searchText":$("#searchText").val() ,
                "selectType":$("#selectType").val()},
            success: function(data) {
                getLaypage(data.pageBean);
                alert("当前页数" + tempNowPage);
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
                url: "${pageContext.request.contextPath }/admin/users-delete.action",
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
    function user_del(obj,id){
        if(confirm("确定要删除？")) {
            $.ajax({
                type: "POST",
                dataType: 'json',
                scriptCharset: 'utf-8',
                url: "${pageContext.request.contextPath }/admin/users-delete.action",
                data: {"ids":id,"size":tempSize,"nowPage":tempNowPage,"recordCount":$("#number").text()},
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


	// 分页end

	/*用户-查看*/
    function member_show(title,id,w,h){
        var url = '${pageContext.request.contextPath }/admin/user-show.action?id=' + id;
        layer_show(title,url,w,h);
    }
	/*用户-停用*/
	function member_stop(obj,id){
		layer.confirm('确认要停用吗？',function(index){
			$.ajax({
                dataType: 'json',
                scriptCharset: 'utf-8',
				type: 'POST',
                url: '${pageContext.request.contextPath }/admin/user-audit-stop.action',
                data: {"user_audit_id":id},
				success: function(data){
					if(data.result) {
                        $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="member_start(this,' + id + ')" href="javascript:;" title="启用"><i class="Hui-iconfont">&#xe6e1;</i></a>');
                        $(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已停用</span>');
                        $(obj).remove();
                        layer.msg('已停用!',{icon: 5,time:1000});
                    }else {
                        layer.msg(data.message,{icon: 5,time:1000});
                    }
				},
				error:function(data) {
					console.log(data.msg);
				},
			});
		});
	}

	/*用户-启用*/
	function member_start(obj,id){
		layer.confirm('确认要启用吗？',function(index){
			$.ajax({
                scriptCharset: 'utf-8',
                type: 'POST',
                dataType: 'json',
				url: '${pageContext.request.contextPath }/admin/user-audit-start.action',
                data: {"user_audit_id":id},
				success: function(data){
					if(data.result) {
                        $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="member_stop(this,' + id + ')" href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i></a>');
                        $(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
                        $(obj).remove();
                        layer.msg('已启用!',{icon: 6,time:1000});
                    }else {
                        layer.msg(data.message,{icon: 5,time:1000});
                    }
				},
				error:function(data) {
					console.log(data.msg);
				},
			});
		});
	}

	/*密码-修改*/
	function change_password(title,id,w,h){
	    var url = '${pageContext.request.contextPath }/admin/user-passwordUI.action?id=' + id;
		layer_show(title,url,w,h);
	}
</script> 
</body>
</html>