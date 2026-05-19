@echo off
echo ========================================
echo   YU-GI-OH JAVA - MENU DE INICIO
echo ========================================
echo.
echo Compilando menu...
javac -d out -sourcepath src src\MenuInicio.java
if errorlevel 1 (
    echo Error de compilacion
    pause
    exit /b 1
)
echo.
echo Abriendo menu de inicio...
java -cp out MenuInicio