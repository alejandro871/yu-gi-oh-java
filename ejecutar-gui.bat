@echo off
echo ========================================
echo   YU-GI-OH JAVA - INTERFAZ GRAFICA
echo ========================================
echo.
echo Compilando...
javac -d out -sourcepath src src\vista\App.java
if errorlevel 1 (
    echo Error de compilacion
    pause
    exit /b 1
)
echo.
echo Ejecutando interfaz grafica...
java -cp out vista.App
pause