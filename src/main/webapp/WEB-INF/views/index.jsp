<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Accident</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link href="css/main.css" rel="stylesheet" type="text/css" />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>
    <div class="container pb-3">
        <div class="row">
            <div class="col-sm-12">
                <h3 class="text-center">Список нарушений</h3>
                <table class="table">
                    <thead>
                    <tr>
                        <th>Наименование</th>
                        <th class="big-col">Описание</th>
                        <th>Адрес</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="a" items="${accidents}">
                        <tr>
                            <td>${a.name}</td>
                            <td>${a.text}</td>
                            <td>${a.address}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
