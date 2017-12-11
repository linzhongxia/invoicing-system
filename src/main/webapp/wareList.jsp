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
                        商品管理
                        <%--<small>Subheading</small>--%>
                    </h1>
                    <ol class="breadcrumb">
                        <li>
                            <i class="fa fa-dashboard"></i> <a href="${ctx}/dashboard.do">Dashboard</a>
                        </li>
                        <li class="active">
                            <i class="fa fa-file"></i> 商品管理
                        </li>
                    </ol>
                </div>
            </div>
            <form id="wareListForm" action="${ctx}/ware/list.do" method="POST">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="list-group">
                            <a class="list-group-item">
                                <input type="hidden" id="wareListForm_page" name="page" value="${wareVO.page}">
                                <input type="hidden" id="wareListForm_pageSize" name="pageSize"
                                       value="${wareVO.pageSize}">
                                <b>商品编号：</b><input type="number" id="wareId" name="wareId" value="${wareVO.wareId}">
                                <b>商品名称：</b><input type="text" id="name" name="name" value="${wareVO.name}">
                                <b style="margin-left: 20px">供货商选择：</b>
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
                                <button style="margin-left: 20px" type="button" onclick="pageUtil.submit(this.form.id)"
                                        class="btn btn-info">查询
                                </button>
                                <button style="margin-left: 10px" type="button"
                                        onclick="window.location='${ctx}/ware/add.do'" class="btn btn-info">添加
                                </button>
                            </a>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12" style="margin-bottom: 20px">
                        <h2></h2>
                        <div class="table-responsive">
                            <table class="table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>商品编号</th>
                                    <th>名称</th>
                                    <th>供货商</th>
                                    <th colspan="2">颜色</th>
                                    <th>尺寸</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:if test="${wareVOList !='null'}">
                                    <c:set var="index" value="${(wareVO.page-1)*wareVO.pageSize+1}"></c:set>
                                    <c:forEach items="${wareVOList}" var="ware">
                                        <tr>
                                            <td>${index}</td>
                                            <td>${ware.wareId}</td>
                                            <td>${ware.name}</td>
                                            <td>${ware.supplierId}</td>
                                            <td width="100px">
                                                <div class="dropdown">
                                                    <button type="button" class="btn dropdown-toggle" id="styleSelect"
                                                            data-toggle="dropdown">下拉选择
                                                        <span class="caret"></span>
                                                    </button>
                                                    <ul class="dropdown-menu" role="menu"
                                                        aria-labelledby="dropdownMenu1">
                                                        <c:if test="${ware.styleList !='null'}">
                                                            <c:forEach items="${ware.styleList}" var="styleVO">
                                                                <li role="presentation">
                                                                    <a role="menuitem" tabindex="-1"
                                                                       onclick="selectStyle(${ware.wareId},'${styleVO.name}','${styleVO.imgurl[0]}')">
                                                                        <img src="${styleVO.imgurl[0]}" width="60px"
                                                                             height="40px">【${styleVO.name}】</a>
                                                                </li>
                                                            </c:forEach>
                                                        </c:if>
                                                    </ul>
                                                </div>
                                            </td>
                                            <td>
                                                <div>
                                                    <img src="${ware.styleList[0].imgurl[0]}" id="img${ware.wareId}"
                                                         width="60px" height="40px">
                                                    <input hidden id="styleSelect${ware.wareId}"
                                                           value="${ware.styleList[0].name}">
                                                    <font id="style${ware.wareId}">${ware.styleList[0].name}</font>
                                                </div>
                                            </td>
                                            <td>
                                                <select class="" id="sizeSelect${ware.wareId}">
                                                    <c:if test="${ware.sizes !='null'}">
                                                        <c:forEach items="${ware.sizes}" var="size">
                                                            <option value="${size}">${size}</option>
                                                        </c:forEach>
                                                    </c:if>
                                                </select>
                                            </td>
                                            <td>
                                                <button type="button" class="btn btn-default"
                                                        onclick="showTr(${ware.wareId})">
                                                    添加订购
                                                </button>
                                                <button type="button" style="margin-left: 5px" class="btn btn-default"
                                                        onclick="edite(${ware.wareId})">
                                                    编辑
                                                </button>
                                            </td>
                                        </tr>
                                        <tr id="tr${ware.wareId}" hidden>
                                            <td></td>
                                            <td colspan="6" bgcolor="#b0c4de">
                                                商家名称：
                                                <select class="" id="venderSelect${ware.wareId}">
                                                    <c:if test="${venderList !='null'}">
                                                        <c:forEach items="${venderList}" var="vender">
                                                            <option value="${vender.venderId}">${vender.venderId}--${vender.name}</option>
                                                        </c:forEach>
                                                    </c:if>
                                                </select>
                                                数量：<select class="" id="numSelect${ware.wareId}">
                                                <option>1</option>
                                                <option>2</option>
                                                <option>3</option>
                                                <option>4</option>
                                                <option>5</option>
                                            </select>
                                                备注：<input type="text" id="remark${ware.wareId}" value="">
                                            </td>
                                            <td>
                                                <button type="button" class="btn btn-danger"
                                                        onclick="addTrade(${ware.wareId})">
                                                    确认订购
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
                            <select onchange="pageUtil.setPageSize(this,this.form.id)">
                                <c:if test="${page.size == '20'}">
                                    <option value="20" selected>20</option>
                                    <option value="50">50</option>
                                </c:if>
                                <c:if test="${page.size == '50'}">
                                    <option value="20">20</option>
                                    <option value="50" selected>50</option>
                                </c:if>
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
        </div>
    </div>
</div>
<script>
    function selectStyle(wareId, styleName, imgUrl) {
        $("#img" + wareId).attr("src", imgUrl);
        $("#styleSelect" + wareId).attr("value", styleName);
        $("#style" + wareId).text(styleName);
    }
    function showTr(wareId) {
        if ($("#tr" + wareId).is(':hidden')) {
            $("#tr" + wareId).show();
        } else {
            $("#tr" + wareId).hide();
        }
    }

    function addTrade(wareId) {
        var venderId = $("#venderSelect" + wareId).val();
        var styleName = $("#styleSelect" + wareId).val();
        var size = $("#sizeSelect" + wareId).val();
        var num = $("#numSelect" + wareId).val();
        var remark = $("#remark" + wareId).val();

        $.ajax({
            type: 'POST',
            url: "${ctx}/trade/add.do",
            data: {
                "venderId": venderId,
                "num": num,
                "remark": remark,
                "colour": styleName,
                "size": size,
                "wareId": wareId
            },
            cache: false,
            dataType: "JSON",
            success: function (data) {
                var result = eval(data);
                if(result.success){
                    $("#remark" + wareId).val("");
                    showTr(wareId);
                    alert("订购成功");
                    return;
                }

                alert(result.msg);
            }, error: function (data) {
                alert("出错了");
            }
        });

    }

    function edite(wareId) {
        var url = "${ctx}/ware/edite.do?wareId=" + wareId;
        window.location.href = url;
    }


</script>
</html>

