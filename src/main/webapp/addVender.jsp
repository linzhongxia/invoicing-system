<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: linzhongxia
  Date: 2017/10/10
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
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
                            <i class="fa fa-dashboard"></i> <a href="${ctx}/dashboard.do">Dashboard</a>
                        </li>
                        <li class="active">
                            <i class="fa fa-file"></i> 商家管理
                        </li>
                    </ol>
                </div>
            </div>
            <form id="venderForm" action="${ctx}/vender/add.do" method="POST">
                <div class="row">
                    <div class="col-lg-8">
                        <input type="hidden" id="id" name="id" value="${vender.id}">
                        昵称：
                        <input type="text" id="name" name="name" value="${venderVO.name}">
                    </div>
                    <div>
                        <input type="text" id="name" name="name" value="${venderVO.name}">
                    </div>
                    <div>
                        <input type="text" id="name" name="name" value="${venderVO.name}">
                    </div>
                    <div>
                        <input type="text" id="name" name="name" value="${venderVO.name}">
                    </div>
                    <div>
                        <input type="text" id="name" name="name" value="${venderVO.name}">
                    </div>
                </div>


                <div class="row">
                    <div class="col-lg-12">
                        <h2></h2>
                        <div class="table-responsive">
                            <table class="table table-bordered table-hover">
                                <thead>
                                <tr>
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
                                    <c:forEach items="${venderList}" var="vender">
                                        <tr>
                                            <td>${vender.id}</td>
                                            <td>${vender.name}</td>
                                            <td>${vender.qq}</td>
                                            <td>${vender.wx}</td>
                                            <td>${vender.telephone}</td>
                                            <td><a>编辑</a> <a>删除</a></td>
                                        </tr>
                                    </c:forEach>
                                </c:if>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </form>
            <!-- /.row -->

        </div>
    </div>
</html>
