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
                        供货商管理
                        <%--<small>Subheading</small>--%>
                    </h1>
                    <ol class="breadcrumb">
                        <li>
                            <i class="fa fa-dashboard"></i> <a href="${ctx}/dashboard.do">Dashboard</a>
                        </li>
                        <li class="active">
                            <i class="fa fa-file"></i> 供货商管理
                        </li>
                    </ol>
                </div>
            </div>
            <form id="supplierForm" action="${ctx}/supplier/list.do" method="POST">
                <div class="row">
                    <div class="col-lg-10">
                        <div class="list-group">
                            <a class="list-group-item">
                                <input type="hidden" id="venderForm_page" name="page" value="${supplierVO.page}">
                                <input type="hidden" id="venderForm_pageSize" name="pageSize"
                                       value="${supplierVO.pageSize}">
                                <b>名称：</b>
                                <input type="text" id="name" name="name" value="${supplierVO.name}">
                                <button type="button" onclick="pageUtil.submit(this.form.id)" class="btn btn-info"
                                        style="margin-left: 20px">查询
                                </button>
                                <button type="button" class="btn btn-info" onclick="showAddSupplierDiv()">添加</button>
                            </a>
                            <div id="addSupplierDiv" hidden>
                                <br>
                                <a class="list-group-item">
                                    <b>新增供货商名称：</b><input type="text" id="add_name" name="add_name">
                                    <button type="button" class="btn btn-danger" onclick="addSupplier()">确认添加</button>
                                </a>
                            </div>

                            <div id="editeSupplierDiv" hidden>
                                <br>
                                <a class="list-group-item">
                                    <b>编辑供货商编号：</b><input type="text" id="edite_id" name="edite_id" readonly>
                                    <b>供货商名称：</b><input type="text" id="edite_name" name="edite_name">
                                    <button type="button" class="btn btn-default" onclick="cancelEdite()">取消</button>
                                    <button type="button" class="btn btn-danger" onclick="submitEdite()">保存修改</button>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-10">
                        <div class="table-responsive">
                            <table class="table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th style="width: 80px">序号</th>
                                    <th style="width: 80px">编号</th>
                                    <th>名称</th>
                                    <th style="width: 80px">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:if test="${supplierList !='null'}">
                                    <c:set var="index" value="${(wareVO.page-1)*wareVO.pageSize+1}"></c:set>
                                    <c:forEach items="${supplierList}" var="suupplier">
                                        <tr>
                                            <td>${index}</td>
                                            <td>${suupplier.supplierId}</td>
                                            <td>${suupplier.name}</td>
                                            <td>
                                                <button type="button" class="btn btn-default"
                                                        onclick="editeSupplier(${suupplier.supplierId},'${suupplier.name}')">
                                                    编译
                                                </button>
                                            </td>
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

    function showAddSupplierDiv() {
        if ($("#addSupplierDiv").is(':hidden')) {
            $("#addSupplierDiv").show();
        } else {
            $("#addSupplierDiv").hide();
        }
    }


    function addSupplier() {
        $.ajax({
            type: 'POST',
            url: "${ctx}/supplier/add.do",
            data: "name=" + $("#add_name").val(),
            cache: false,
            dataType: "JSON",
            success: function (data) {
                alert("添加供货商成功");
                pageUtil.submit("supplierForm");
            }, error: function (data) {
                alert("出错了");
            }
        });
    }

    function submitEdite() {
        $.ajax({
            type: 'POST',
            url: "${ctx}/supplier/edite.do",
            data: "supplierId=" + $("#edite_id").val()+"&name="+$("#edite_name").val(),
            cache: false,
            dataType: "JSON",
            success: function (data) {
                alert("修改供货商信息成功");
                pageUtil.submit("supplierForm");
            }, error: function (data) {
                alert("出错了");
            }
        });
    }


    function editeSupplier(supplierId, supplierName) {
        $("#editeSupplierDiv").show();
        $("#edite_id").attr("value", supplierId);
        $("#edite_name").attr("value", supplierName);
    }

    function cancelEdite() {
        $("#editeSupplierDiv").hide();
    }


</script>
</html>
