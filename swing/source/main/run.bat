@echo off
echo Compiling...
javac *.java ui/*.java

if errorlevel 1 (
    echo Compilation failed!
    goto cleanup
)

echo Running Application...
java Application

:cleanup
echo Cleaning up...
del /s *.class 2>nul
echo Done!
pause
