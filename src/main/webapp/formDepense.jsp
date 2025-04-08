<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Formulaire Dépense</title>
</head>
<body>
    <h1>${depense != null ? 'Modifier' : 'Ajouter'} une Dépense</h1>
    
    <form method="post">
        <input type="hidden" name="id" value="${depense.id}">
        
        <div>
            <label for="id_prevision">Prévision:</label>
            <select id="id_prevision" name="id_prevision" required>
                <c:forEach items="${previsions}" var="prev">
                    <option value="${prev.id}" 
                        ${depense != null && depense.idPrevision == prev.id ? 'selected' : ''}>
                        ${prev.libeller} (${prev.montant})
                    </option>
                </c:forEach>
            </select>
        </div>
        
        <div>
            <label for="montant">Montant:</label>
            <input type="number" id="montant" name="montant" 
                   value="${depense.montant}" required>
        </div>
        
        <div>
            <label for="date_crea">Date:</label>
            <input type="date" id="date_crea" name="date_crea" 
                   value="${depense.dateCrea}" required>
        </div>
        
        <button type="submit">Valider</button>
    </form>
    
    <a href="listeDepenses">Retour à la liste</a>
</body>
</html>