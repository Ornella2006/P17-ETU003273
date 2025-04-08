<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
    <style>
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        .negative { color: red; }
    </style>
</head>
<body>
    <h1>Dashboard</h1>
    
    <table>
        <tr>
            <th>Libellé</th>
            <th>Montant Prévu</th>
            <th>Montant Dépensé</th>
            <th>Reste</th>
        </tr>
        <c:forEach items="${items}" var="item">
            <tr>
                <td>${item.libeller}</td>
                <td>${item.montantPrevu}</td>
                <td>${item.montantDepense}</td>
                <td class="${item.reste < 0 ? 'negative' : ''}">${item.reste}</td>
            </tr>
        </c:forEach>
    </table>
    
    <a href="listeDepenses">Gérer les dépenses</a>
</body>
</html>