#!/bin/bash

echo "Compiling..."
javac *.java ui/*.java controllers/*.java

echo "Running Application..."
java Application

echo1
echo "Done!"
