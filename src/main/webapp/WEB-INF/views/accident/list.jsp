<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Автонарушения" />
<%@ include file="../../modules/pageHeader.jsp" %>

<div class="container pt-3">
    <div class="row">
        <div class="col-sm-12 p-0 card">
            <div class="card-header">
                <h3 class="text-center">Список нарушений</h3>
            </div>
            <div class="card-body">
                <a class="btn btn-primary" href="<c:url value="/create"/>"><i class="fa fa-plus"></i> Добавить инцидент</a>
                <table class="table">
                    <thead>
                    <tr>
                        <th>Наименование</th>
                        <th class="big-col">Описание</th>
                        <th>Операции</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="a" items="${accidents}">
                        <tr>
                            <td>${a.name}</td>
                            <td>${a.text}</td>
                            <td><a class="btn btn-secondary" href="<c:url value="/edit/${a.id}"/>"><i class="fa fa-edit"></i></a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<%@ include file="../../modules/pageHeader.jsp" %>