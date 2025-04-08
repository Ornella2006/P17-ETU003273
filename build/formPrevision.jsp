<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ajout de Prévision</title>
</head>
<body>
    <h1>Ajout de Prévision</h1>
    <form method="post">
        <div>
            <label for="libeller">Libellé:</label>
            <input type="text" id="libeller" name="libeller" required>
        </div>
        <div>
            <label for="montant">Montant:</label>
            <input type="number" id="montant" name="montant" required>
        </div>
        <button type="submit">Valider</button>
    </form>
    <a href="listeDepenses">Retour à la liste</a>
</body>
</html>