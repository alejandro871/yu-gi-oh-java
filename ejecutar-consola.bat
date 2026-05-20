@echo off
echo ========================================
echo   YU-GI-OH JAVA - MODO CONSOLA
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
echo Ejecutando en modo consola...
java -cp out vista.App
pause