<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: linzhongxia
  Date: 2017/10/10
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<style type="text/css">
    b {
        margin-left: 20px
    }
</style>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="header.jsp"></jsp:include>
<div id="wrapper">
    <div id="page-wrapper">
        <div class="container-fluid">
            <!-- Page Heading -->
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        商家管理
                        <%--<small>Subheading</small>--%>
                    </h1>
                    <ol class="breadcrumb">
                        <li>
                            <i class="fa fa-dashboard"></i> <a href="index.html">Dashboard</a>
                        </li>
                        <li class="active">
                            <i class="fa fa-file"></i> 商家管理
                        </li>
                    </ol>
                </div>
            </div>
            <form id="venderForm" action="${ctx}/vender/list.do" method="POST">
                <div class="row">
                    <div class="col-lg-8">
                        <div class="list-group">
                            <a class="list-group-item">
                                <input type="hidden" id="venderForm_page" name="page" value="${venderVO.page}">
                                <input type="hidden" id="venderForm_pageSize" name="pageSize"
                                       value="${venderVO.pageSize}">
                                <b>昵称：</b>
                                <input type="text" id="name" name="name" value="${venderVO.name}">
                                <button type="button" onclick="pageUtil.submit(this.form.id)" class="btn btn-info"
                                        style="margin-left: 20px">查询
                                </button>
                                <button type="button" class="btn btn-info" onclick="showAddVenderDiv()">添加</button>
                            </a>
                        </div>
                    </div>
                </div>
                <div class="row" id="addVenderDiv" hidden>
                    <div class="col-lg-12">
                        <div class="list-group">
                            <a class="list-group-item">
                                <h4>添加商家</h4><br>
                                <b>昵称：</b><input type="text" id="add_name" name="add_name">
                                <b>QQ：</b><input type="text" id="add_qq" name="add_qq">
                                <b>WECHAT：</b><input type="text" id="add_wx" name="add_wx">
                                <b>电话：</b><input type="text" id="add_telephone" name="add_telephone">
                                <button type="button" class="btn btn-block" onclick="addVender()">确认添加</button>
                            </a>
                        </div>
                    </div>
                </div>


                <div class="row">
                    <div class="col-lg-12">
                        <h2></h2>
                        <div class="table-responsive">
                            <table class="table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>编号</th>
                                    <th>昵称</th>
                                    <th>QQ号</th>
                                    <th>微信号</th>
                                    <th>电话号码</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:if test="${venderList !='null'}">
                                    <c:set var="index" value="${(venderVO.page-1)*venderVO.pageSize+1}"></c:set>
                                    <c:forEach items="${venderList}" var="vender">
                                        <tr>
                                            <td>${index}</td>
                                            <td>${vender.venderId}</td>
                                            <td>${vender.name}</td>
                                            <td>${vender.qq}</td>
                                            <td>${vender.wx}</td>
                                            <td>${vender.telephone}</td>
                                            <td><a>编辑</a> <a>删除</a></td>
                                        </tr>
                                        <c:set var="index" value="${index+1}"></c:set>
                                    </c:forEach>
                                </c:if>
                                </tbody>
                            </table>
                        </div>

                        <div style="text-align: right;width:100% ">
                            共${page.totalIndex}页，共计${page.totalNum}条记录，每页显示
                            <select value="${page.size}" onchange="pageUtil.setPageSize(this,this.form.id)">
                                <option value="20">20</option>
                                <option value="50">50</option>
                            </select>条
                            <c:if test="${page.index == '1'}">
                                <input type="button" disabled value="上一页">
                            </c:if>
                            <c:if test="${page.index != '1'}">
                                <input type="button" value="上一页"
                                       onclick="pageUtil.setPage(${page.index}-1,this.form.id)">
                            </c:if>
                            ${page.index}
                            <c:if test="${page.index == page.totalIndex}">
                                <input type="button" disabled value="下一页">
                            </c:if>
                            <c:if test="${page.index != page.totalIndex}">
                                <input type="button" value="下一页"
                                       onclick="pageUtil.setPage(${page.index}+1,this.form.id)">
                            </c:if>
                        </div>
                    </div>
                </div>
            </form>
            <!-- /.row -->

        </div>
    </div>
</div>


<script>

    function showAddVenderDiv() {
        if ($("#addVenderDiv").is(':hidden')) {
            $("#addVenderDiv").show();
        } else {
            $("#addVenderDiv").hide();
        }
    }


    function addVender() {
        $.ajax({
            type: 'POST',
            url: "${ctx}/vender/add.do",
            data: "name=" + $("#add_name").val() + "&qq=" + $("#add_qq").val() + "&wx=" + $("#add_wx").val()
            + "&telephone=" + $("#add_telephone").val(),
            cache: false,
            dataType: "JSON",
            success: function (data) {
                pageUtil.submit("venderForm");
            }, error: function (data) {
                alert("出错了");
            }
        });
    }


</script>
</html>
