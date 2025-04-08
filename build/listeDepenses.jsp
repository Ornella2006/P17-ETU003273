<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des Dépenses</title>
</head>
<body>
    <h1>Liste des Dépenses</h1>
    <a href="formDepense">Nouvelle Dépense</a>
    
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Prévision</th>
            <th>Montant</th>
            <th>Date</th>
            <th>Actions</th>
        </tr>
        <c:forEach items="${depenses}" var="depense">
            <tr>
                <td>${depense.id}</td>
                <td>
                    <c:forEach items="${previsions}" var="prev">
                        <c:if test="${prev.id == depense.idPrevision}">
                            ${prev.libeller} (${prev.montant})
                        </c:if>
                    </c:forEach>
                </td>
                <td>${depense.montant}</td>
                <td>${depense.dateCrea}</td>
                <td>
                    <a href="formDepense?id=${depense.id}">Modifier</a>
                    <form action="listeDepenses" method="post" style="display:inline;">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="id" value="${depense.id}">
                        <button type="submit">Supprimer</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>