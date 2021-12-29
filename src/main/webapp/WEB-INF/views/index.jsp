<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Accident</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js" integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF" crossorigin="anonymous"></script>
</head>
<body>
    <h3 class="text-center">Проект &quot;Автонарушители&quot;</h3>
    <table class="table">
        <thead>
            <tr>
                <th>№ п/п</th>
                <th>Описание</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="i" begin="0" end="${itemList.size() - 1}">
            <tr><td>${i + 1}.</td><td>${itemList[i]}</td></tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>
