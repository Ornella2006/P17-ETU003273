<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gestion des Dépenses</title>
    <style>
        .menu-card {
            display: block;
            width: 300px;
            padding: 20px;
            margin: 20px auto;
            text-align: center;
            background: #fff;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            text-decoration: none;
            color: #333;
            transition: transform 0.3s;
        }
        .menu-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 6px 12px rgba(0,0,0,0.15);
        }
        .card-header {
            font-size: 48px;
            margin-bottom: 15px;
            color: #4CAF50;
        }
        .card-title {
            margin: 0 0 10px 0;
        }
        .card-text {
            color: #666;
            margin: 0;
        }
    </style>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
    <h1 style="text-align: center; margin-top: 50px;">Prevision et Dépense</h1>
    
    <a href="listeDepenses" class="menu-card">
        <div class="card-header">
            <i class="fas fa-money-bill-wave card-icon"></i>
        </div>
        <div class="card-body">
            <h3 class="card-title">Gestion des Dépenses</h3>
            <p class="card-text">Accéder à la liste des dépenses</p>
        </div>
    </a>

    <a href="formPrevision" class="menu-card">
        <div class="card-header">
            <i class="fas fa-money-bill-wave card-icon"></i>
        </div>
        <div class="card-body">
            <h3 class="card-title">Prevision</h3>
            <p class="card-text">Accéder à l'ajout de ligne de crédit</p>
        </div>
    </a>

    <a href="dashboard" class="menu-card">
        <div class="card-header">
            <i class="fas fa-chart-line card-icon"></i>
        </div>
        <div class="card-body">
            <h3 class="card-title">Dashboard</h3>
            <p class="card-text">Voir le tableau de bord</p>
        </div>
    </a>
</body>
</html>