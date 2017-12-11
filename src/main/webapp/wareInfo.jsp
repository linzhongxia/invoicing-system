<%@ page import="com.alibaba.fastjson.JSON" %>
<%@ page import="org.apache.commons.lang.ArrayUtils" %>
<%@ page import="rml.vo.WareVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<jsp:include page="header.jsp"></jsp:include>
<%
    WareVO vo = (WareVO) request.getAttribute("wareVO");
//    int index = CollectionUtils.isEmpty(vo.getStyleList()) ? 0 : vo.getStyleList().size();
    String sizes = "";
    if (!ArrayUtils.isEmpty(vo.getSizes())) {
        for (Double size : vo.getSizes()) {
            sizes += "," + String.valueOf(size);
        }
        sizes = sizes.substring(1);
    }
    String styles = JSON.toJSONString(vo.getStyleList());
%>
<div id="wrapper">
    <div id="page-wrapper">
        <div class="container-fluid">
            <form id="wareInfoForm" action="${ctx}/ware/add.do" method="POST">

                <!-- Page Heading -->
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">
                            商品管理
                            <%--<small>Subheading</small>--%>
                        </h1>
                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-dashboard"></i> <a href="index.html">Dashboard</a>
                            </li>
                            <li class="active">
                                <i class="fa fa-file"></i> 商品管理
                            </li>
                        </ol>
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-6">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title"><i class="fa fa-clock-o fa-fw"></i>基本信息</h3>
                            </div>
                            <div class="panel-body">
                                <div class="list-group">
                                    <a class="list-group-item">
                                        <b>商品名称：</b>
                                        <input type="text" name="wareId" hidden value="${wareVO.wareId}">
                                        <input type="text" id="name" name="name" value="${wareVO.name}">
                                    </a>
                                    <a class="list-group-item"><b>供货商编号：</b>
                                        <select name="supplierId" style="height: 26px">
                                            <option value="">--未选择--</option>
                                            <c:if test="${supplierList !='null'}">
                                                <c:forEach items="${supplierList}" var="supplier">
                                                    <c:if test="${supplier.supplierId == wareVO.supplierId}">
                                                        <option value="${supplier.supplierId}"
                                                                selected>${supplier.supplierId}--${supplier.name}</option>
                                                    </c:if>
                                                    <c:if test="${supplier.supplierId != wareVO.supplierId}">
                                                        <option value="${supplier.supplierId}">${supplier.supplierId}--${supplier.name}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </c:if>
                                        </select>
                                    </a>
                                    <a class="list-group-item">
                                        <b>尺码：</b>
                                        <c:forEach items="${sizes}" var="size">
                                            <label class="checkbox-inline">
                                                <input type="checkbox" name="sizes" checked value="${size}">${size}
                                            </label>
                                        </c:forEach>
                                    </a>
                                </div>
                                <div class="text-center">
                                    <a onclick="showStyleDemoDiv()">添加款式<i class="fa fa-arrow-circle-right"></i></a>
                                </div>
                                <div class="list-group-item text-center">
                                    <div id="styleDemoDiv" hidden style="background:silver">
                                        <hr/>
                                        款名：<input type="text" id="style_name">
                                        图片:<input type="url" id="style_img">
                                        <button type="button" class="btn btn-default" onclick="addStyle()">确认</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title"><i class="fa fa-money fa-fw"></i>款式列表</h3>
                            </div>
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered table-hover table-striped" width="450px">
                                        <thead>
                                        <tr style="text-align: center">
                                            <th>图片</th>
                                            <th>款名</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody id="styleTbody">
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-12">
                        <div style='text-align: center'>
                            <button type="button" onclick="window.history.go(-1)" class="btn btn-info">返回</button>
                            <button type="submit" class="btn btn-info" style="margin-left: 20px">提交</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</html>

<script>

    var i = 0;

    window.onload = function () {
        var fields = document.getElementsByName("sizes");
        var doubles = new Array();
        var sizes = "<%=sizes %>";
        doubles = sizes.split(",");
        for (var _x = 0; _x < fields.length; _x++) {
            if (doubles.length != 0) {
                if (isInArray(doubles, fields[_x].value)) {
                    fields[_x].checked = true;
                } else {
                    fields[_x].checked = false;
                }
            }
        }
        var styles = eval(<%= styles%>);
        if (styles.length > 0) {
            for (var _y = 0; _y < styles.length; _y++) {
                var style = styles[_y];
                addStyleTr(style.name, style.imgurl[0], 1);
            }
        }

    };

    /**
     * 使用循环的方式判断一个元素是否存在于一个数组中
     * @param {Object} arr 数组
     * @param {Object} value 元素值
     */
    function isInArray(arr, value) {
        for (var i = 0; i < arr.length; i++) {
            if (value === arr[i]) {
                return true;
            }
        }
        return false;
    }

    function showStyleDemoDiv() {
        $("#styleDemoDiv").show();
    }

    function addStyle() {
        var styleName = $("#style_name").val();
        var styleImg = $("#style_img").val();
        addStyleTr(styleName, styleImg, 1);
    }

    function addStyleTr(styleName, styleImg, status) {

        $("#styleTbody").append("<tr><td style='width: 180px;border:1px'>" +
                "<input hidden type='text' name='styleList[" + i + "].imgurl' value='" + styleImg + "'>" +
                "<img style='height: 120px;width: 180px' src='" + styleImg + "'>" +
                "</td>" +
                "<td style='border: 1px'>" +
                "<input type='text' style='border:none;' hidden readonly style='text-align: center' name='styleList[" + i + "].name' value='" + styleName + "'>" + styleName +
                "</td>" +
                "<td style='border: 1px' style='width: 80px'>" +
                "<button onclick='deleteTr(this)'>删除</button>" +
                "</td></tr>");
        i = i + 1;
        $("#style_name").value = "";
        $("#style_img").value = "";
        $("#styleDemoDiv").hide();
    }

    function deleteTr(field) {
        $(field).parents('tr').remove();
    }

</script>
