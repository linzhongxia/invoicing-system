<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: linzhongxia
  Date: 2017/10/10
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<style type="text/css">
    b{
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
                        提货单
                        <%--<small>Subheading</small>--%>
                    </h1>
                    <ol class="breadcrumb">
                        <li>
                            <i class="fa fa-dashboard"></i> <a href="${ctx}/dashboard.do">Dashboard</a>
                        </li>
                        <li class="active">
                            <i class="fa fa-file"></i> 提货单
                        </li>
                    </ol>
                </div>
            </div>
            <form id="tradeInForm" action="${ctx}/trade/in/list.do" method="POST">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="list-group">
                            <a class="list-group-item">
                                <input type="hidden" id="tradeInForm_page" name="page" value="${tradeVO.page}">
                                <input type="hidden" id="tradeInForm_pageSize" name="pageSize"
                                       value="${tradeVO.pageSize}">
                                <b>记账日期：</b>
                                <input type="date" id="marketDay" name="marketDay" value="${tradeVO.marketDay}">
                                <b>商品编号：</b>
                                <input type="number" id="wareId" name="wareId" value="${tradeVO.wareId}">
                                <b>供货商选择：</b>
                                <select id="supplierId" name="supplierId" style="height: 26px">
                                    <option value="">--未选择--</option>
                                    <c:if test="${supplierList !='null'}">
                                        <c:forEach items="${supplierList}" var="supplier">
                                            <c:if test="${supplier.supplierId == tradeVO.supplierId}">
                                                <option value="${supplier.supplierId}"
                                                        selected>${supplier.supplierId}--${supplier.name}</option>
                                            </c:if>
                                            <c:if test="${supplier.supplierId != tradeVO.supplierId}">
                                                <option value="${supplier.supplierId}">${supplier.supplierId}--${supplier.name}</option>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                </select>
                                <button type="button" onclick="pageUtil.submit(this.form.id)" class="btn btn-info"
                                        style="margin-left: 20px">查询
                                </button>
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
                                    <th colspan="2">款式</th>
                                    <th>商品编号</th>
                                    <th>供货商</th>
                                    <th>数量</th>
                                    <th>记账日期</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:if test="${tradeVOList !='null'}">
                                    <c:set var="index" value="${(tradeVO.page-1)*tradeVO.pageSize+1}"></c:set>
                                    <c:forEach items="${tradeVOList}" var="vo">
                                        <tr>
                                            <td>${index}</td>
                                            <td width="60px"><img src="${vo.img[0]}" width="60px" height="40px"></td>
                                            <td>${vo.skuName}</td>
                                            <td>${vo.wareId}</td>
                                            <td>${vo.supplierId}</td>
                                            <td>${vo.num}</td>
                                            <td>${tradeVO.marketDay}</td>
                                        </tr>
                                        <c:set var="index" value="${index+1}"></c:set>
                                    </c:forEach>
                                </c:if>
                                </tbody>
                            </table>
                        </div>
                        <div style="width: 100%">
                            <%--<div style="float: left">--%>
                                <%--<input type="button" value="下载提货单" onclick="downloadInReport()" class="btn btn-info">--%>
                            <%--</div>--%>
                            <div style="float: left">
                                <input type="button" value="打印提货单" onclick="printInReport()" class="btn btn-info" style="margin-left: 20px">
                            </div>
                            <div  style="float: right">
                                共${page.totalIndex}页，共计${page.totalNum}条记录，每页显示
                                <select value="${page.size}" onchange="pageUtil.setPageSize(this,this.form.id)">
                                    <option value="20">20</option>
                                    <option value="50">50</option>
                                </select>条
                                <c:if test="${page.index == '1'}">
                                    <input type="button"  disabled value="上一页">
                                </c:if>
                                <c:if test="${page.index != '1'}">
                                    <input type="button" value="上一页" onclick="pageUtil.setPage(${page.index}-1,this.form.id)">
                                </c:if>
                                ${page.index}
                                <c:if test="${page.index == page.totalIndex}">
                                    <input type="button" disabled value="下一页">
                                </c:if>
                                <c:if test="${page.index != page.totalIndex}">
                                    <input type="button" value="下一页" onclick="pageUtil.setPage(${page.index}+1,this.form.id)">
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <!-- /.row -->

        </div>
    </div>
</div>


<script>
    function downloadInReport() {
        var url = "${ctx}/trade/in/report.do?marketDay=" + $("#marketDay").val() + "&wareId=" + $("#wareId").val() + "&supplierId=" + $("#supplierId").val();
        window.location.href = url;
    }
    
    function printInReport() {
        var url = "${ctx}/trade/in/print.do?marketDay=" + $("#marketDay").val() + "&wareId=" + $("#wareId").val() + "&supplierId=" + $("#supplierId").val();
        window.open(url);
    }
</script>
</html>
