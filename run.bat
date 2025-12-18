@echo off
rem Compile main.java, run it, then delete all .class files

cd /d C:\Users\Billy\Documents\oop

echo Compiling main.java...
javac main.java
if %ERRORLEVEL% NEQ 0 (
  echo Compilation failed. Press any key to exit.
  pause
  exit /b %ERRORLEVEL%
)

echo Running main...
java main

echo Deleting .class files (recursive)...
del /s /q *.class

echo Done.
pause
