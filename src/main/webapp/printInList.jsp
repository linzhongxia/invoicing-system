<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>供货管理系统</title>

    <link href="${ctx}/css/bootstrap.min.css" rel="stylesheet">

    <link href="${ctx}/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <script src="${ctx}/js/jquery.js"></script>
    <script src="${ctx}/js/common.js"></script>

    <script src="${ctx}/js/bootstrap.min.js"></script>
</head>
<style type="text/css">
    b {
        margin-left: 20px
    }
</style>
<html>
<body>
<div>
    <div class="col-lg-8">
        <h2 class="page-header">
            提货单
        </h2>
        <div class="list-group">
            <a class="list-group-item">
                <b>记账日期：</b>${tradeVO.marketDay}
                <b>商品编号：</b>${tradeVO.wareId}
                <b>供货商编号：</b>${tradeVO.supplierId}
            </a>
        </div>
    </div>
    <div class="col-lg-8">
        <div>
            <table class="table table-bordered table-hover">
                <thead>
                <tr>
                    <th>序号</th>
                    <th colspan="2">款式</th>
                    <th>商品编号</th>
                    <th>供货商号</th>
                    <th>尺码</th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${printList !='null'}">
                    <c:set var="index" value="1"></c:set>
                    <c:forEach items="${printList}" var="print">
                        <tr>
                            <td>${index}</td>
                            <td width="60px"><img src="${print.img}" width="60px" height="40px"></td>
                            <td>${print.name}</td>
                            <td>${print.wareId}</td>
                            <td>${print.supplier}</td>
                            <td>${print.detail}</td>
                        </tr>
                        <c:set var="index" value="${index+1}"></c:set>
                    </c:forEach>
                </c:if>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script>

    window.onload = function () {
        window.print();
    };

</script>
</body>
</html>
