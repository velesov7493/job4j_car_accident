<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Вход"/>
<%@ include file="../../modules/pageHeader.jsp" %>

<div class="container pt-3">
    <div class="row">
        <div class="col-sm-12 p-0 card">
            <div class="card-header">
                <h3 class="text-center">Вход</h3>
            </div>
            <div class="card-body">
                <c:if test="${not empty message}">
                    <div class="${empty error ? 'status alert alert-success' : 'status alert alert-danger'}">
                        <p>${message}</p></div>
                </c:if>
                <form name="login" method="post" action="<c:url value="/login"/>">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <div class="mb-3">
                        <label for="aEmail">Ваш email:</label>
                        <input id="aEmail" class="form-control" name="username" type="email" required/>
                    </div>
                    <div class="mb-3">
                        <label for="aPass">Пароль:</label>
                        <input id="aPass" class="form-control" name="password" type="password" required/>
                    </div>
                    <div class="mb-3">
                        <a class="pull-left btn btn-outline-primary" href="<c:url value="/register"/>">Регистрация</a>
                        <button type="submit" class="pull-right btn btn-primary">Войти</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<%@ include file="../../modules/pageFooter.jsp" %>