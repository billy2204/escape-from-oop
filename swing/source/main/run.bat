@echo off
echo Compiling...
javac *.java ui/*.java

if errorlevel 1 (
    echo Compilation failed!
    pause
    exit /b 1
)

echo Running Application...
java Application

echo Cleaning up...
del /s *.class 2>nul

echo Done!
pause
