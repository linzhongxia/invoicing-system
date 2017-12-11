<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: linzhongxia
  Date: 2017/10/10
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
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
                        Dashboard
                        <small>Statistics Overview</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li class="active">
                            <i class="fa fa-dashboard"></i> Dashboard
                        </li>
                    </ol>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-6 col-md-6">
                    <div class="panel panel-yellow">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-shopping-cart fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">${orderNum}</div>
                                    <div>订货总数</div>
                                </div>
                            </div>
                        </div>
                        <a href="${ctx}/trade/init.do">
                            <div class="panel-footer">
                                <span class="pull-left">View Details</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-comments fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">${venderNum}</div>
                                    <div>订货人数</div>
                                </div>
                            </div>
                        </div>
                        <a href="#">
                            <div class="panel-footer">
                                <span class="pull-left">View Details</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title"><i class="fa fa-money fa-fw"></i>订货单 Top20</h3>
                        </div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-bordered table-hover table-striped">
                                    <thead>
                                    <tr>
                                        <th>序号</th>
                                        <th colspan="2">名称</th>
                                        <th>数量</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:if test="${skuList !='null'}">
                                        <c:set var="index" value="1"></c:set>
                                        <c:forEach items="${skuList}" var="sku">
                                            <tr>
                                                <td>${index}</td>
                                                <td><img src="${sku.img[0]}" width="60px" height="40px"></td>
                                                <td>${sku.skuName}</td>
                                                <td>${sku.num}</td>
                                            </tr>
                                            <c:set var="index" value="${index+1}"></c:set>
                                        </c:forEach></c:if>
                                    </tbody>
                                </table>
                            </div>
                            <div class="text-right">
                                <a href="#">View More <i class="fa fa-arrow-circle-right"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title"><i class="fa fa-money fa-fw"></i>订货买家 Top20</h3>
                        </div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-bordered table-hover table-striped">
                                    <thead>
                                    <tr>
                                        <th>序号</th>
                                        <th>名称</th>
                                        <th>数量</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:if test="${venderList !='null'}">
                                        <c:set var="index" value="1"></c:set>
                                        <c:forEach items="${venderList}" var="vender">
                                            <tr>
                                                <td>${index}</td>
                                                <td>${vender.venderName} [${vender.venderId}]</td>
                                                <td>${vender.num}</td>
                                            </tr>
                                            <c:set var="index" value="${index+1}"></c:set>
                                        </c:forEach>
                                    </c:if>
                                    </tbody>
                                </table>
                            </div>
                            <div class="text-right">
                                <a href="#">View More<i class="fa fa-arrow-circle-right"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</html>
