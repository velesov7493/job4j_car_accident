<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Автонарушения" />
<%@ include file="../../modules/pageHeader.jsp" %>

<div class="container pt-3">
    <div class="row">
        <div class="col-sm-12 p-0 card">
            <div class="card-header">
                <h3 class="text-center">${empty accident ? 'Новое нарушение' : 'Редактирование нарушения'}</h3>
            </div>
            <div class="card-body">
                <form method="post" action="<c:url value="/save"/>">
                    <input type="hidden" name="id" value="${empty accident ? 0 : accident.id}"/>
                    <div class="mb-3">
                        <label for="aName">Наименование:</label>
                        <input id="aName" class="form-control" name="name" type="text" value="${empty accident ? '' : accident.name}"/>
                    </div>
                    <div class="mb-3">
                        <label for="aType">Тип:</label>
                        <select id="aType" name="type.id" class="form-select">
                            <c:forEach var="type" items="${types}">
                                <option value="${type.id}" ${not empty accident and accident.type.id == type.id ? 'selected' : ''}>${type.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="aRuleIds">Статьи:</label>
                        <select id="aRuleIds" name="ruleIds" class="form-select" multiple>
                            <c:forEach var="rule" items="${rules}">
                                <option value="${rule.id}" ${not empty accident and accident.rules.contains(rule) ? 'selected' : ''}>${rule.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="aDescription">Описание:</label>
                        <textarea id="aDescription" class="form-control" name="text">${empty accident ? '' : accident.text}</textarea>
                    </div>
                    <div class="mb-3">
                        <label for="aAddress">Адрес:</label>
                        <textarea id="aAddress" class="form-control" name="address">${empty accident ? '' : accident.address}</textarea>
                    </div>
                    <div class="mb-3">
                        <button type="submit" class="pull-right btn btn-primary">Сохранить</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<%@ include file="../../modules/pageFooter.jsp" %>


