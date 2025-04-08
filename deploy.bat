@echo off
set APP_NAME=ETU003273
set SRC_DIR=src\main\java
set WEB_DIR=src\main\webapp
set BUILD_DIR=build
set LIB_DIR=lib
set "TOMCAT_WEBAPPS=C:\apache-tomcat-10.1.28\apache-tomcat-10.1.28\webapps"
set "SERVLET_API_JAR=%LIB_DIR%\jakarta.servlet-api-6.0.0.jar"
set "MYSQL_CONNECTOR_JAR=%LIB_DIR%\mysql-connector-j-8.0.33.jar"
set "JSTL_API_JAR=%LIB_DIR%\jakarta.servlet.jsp.jstl-api-2.0.0.jar"
set "JSTL_IMPL_JAR=%LIB_DIR%\jakarta.servlet.jsp.jstl-2.0.0.jar"
REM Suppression et recréation du dossier temporaire
if exist "%BUILD_DIR%" rmdir /s /q "%BUILD_DIR%"
mkdir "%BUILD_DIR%\WEB-INF\classes"
@REM mkdir "%BUILD_DIR%\WEB-INF\lib"
REM Compilation des fichiers Java avec le JAR des Servlets
javac -cp "%SERVLET_API_JAR%;%MYSQL_CONNECTOR_JAR%" -d "%BUILD_DIR%\WEB-INF\classes" "%SRC_DIR%\UtilDB.java"
if errorlevel 1 (
    echo Erreur lors de la compilation de UtilDB.java.
    pause
    exit /b 1
)
javac -cp "%SERVLET_API_JAR%;%MYSQL_CONNECTOR_JAR%;%BUILD_DIR%\WEB-INF\classes" -d "%BUILD_DIR%\WEB-INF\classes" "%SRC_DIR%\BaseObject.java"
if errorlevel 1 (
    echo Erreur lors de la compilation de BaseObject.java.
    pause
    exit /b 1
)

javac -cp "%SERVLET_API_JAR%;%MYSQL_CONNECTOR_JAR%;%BUILD_DIR%\WEB-INF\classes" -d "%BUILD_DIR%\WEB-INF\classes" "%SRC_DIR%\Prevision.java"
if errorlevel 1 (
    echo Erreur lors de la compilation de DeptServlet.java.
    pause
    exit /b 1
)

javac -cp "%SERVLET_API_JAR%;%MYSQL_CONNECTOR_JAR%;%BUILD_DIR%\WEB-INF\classes" -d "%BUILD_DIR%\WEB-INF\classes" "%SRC_DIR%\Depense.java"
if errorlevel 1 (
    echo Erreur lors de la compilation de Departement.java.
    pause
    exit /b 1
)

javac -cp "%SERVLET_API_JAR%;%MYSQL_CONNECTOR_JAR%;%BUILD_DIR%\WEB-INF\classes" -d "%BUILD_DIR%\WEB-INF\classes" "%SRC_DIR%\FormDepenseServlet.java"
if errorlevel 1 (
    echo Erreur lors de la compilation de DeptServlet.java.
    pause
    exit /b 1
)
javac -cp "%SERVLET_API_JAR%;%MYSQL_CONNECTOR_JAR%;%BUILD_DIR%\WEB-INF\classes" -d "%BUILD_DIR%\WEB-INF\classes" "%SRC_DIR%\ListeDepenseServlet.java"
if errorlevel 1 (
    echo Erreur lors de la compilation de DeptServlet.java.
    pause
    exit /b 1
)

javac -cp "%SERVLET_API_JAR%;%MYSQL_CONNECTOR_JAR%;%BUILD_DIR%\WEB-INF\classes" -d "%BUILD_DIR%\WEB-INF\classes" "%SRC_DIR%\FormPrevisionServlet.java"
if errorlevel 1 (
    echo Erreur lors de la compilation de FormPrevisionServlet.java.
    pause
    exit /b 1
)

@REM javac -cp "%SERVLET_API_JAR%;%MYSQL_CONNECTOR_JAR%;%BUILD_DIR%\WEB-INF\classes" -d "%BUILD_DIR%\WEB-INF\classes" "%SRC_DIR%\ListeEmpServlet.java"
@REM if errorlevel 1 (
    @REM echo Erreur lors de la compilation de DeptServlet.java.
    @REM pause
    @REM exit /b 1
@REM )
@REM del sources.txt
REM Copier les fichiers web
xcopy "%WEB_DIR%" "%BUILD_DIR%" /E /I /Y
REM Copier les fichiers JAR nécessaires
mkdir "%BUILD_DIR%\WEB-INF\lib"
copy "%MYSQL_CONNECTOR_JAR%" "%BUILD_DIR%\WEB-INF\lib\"
copy "%SERVLET_API_JAR%" "%BUILD_DIR%\WEB-INF\lib\"
copy "%JSTL_API_JAR%" "%BUILD_DIR%\WEB-INF\lib\"
copy "%JSTL_IMPL_JAR%" "%BUILD_DIR%\WEB-INF\lib\"
REM Création du fichier .war dans le dossier build
cd "%BUILD_DIR%"
jar -cvf "%APP_NAME%.war" *
if errorlevel 1 (
    echo Erreur lors de la création du fichier .war.
    cd ..
    pause
    exit /b 1
)
cd ..
REM Déploiement vers Tomcat
copy "%BUILD_DIR%\%APP_NAME%.war" "%TOMCAT_WEBAPPS%"
if errorlevel 1 (
    echo Erreur lors du déploiement vers Tomcat.
    pause
    exit /b 1
)
echo Déploiement terminé. Redémarrez Tomcat si nécessaire.
pause
