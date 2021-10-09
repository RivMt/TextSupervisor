#!/bin/bash
clear
kotlinc $(find BookEditor/src/* -name "*.kt") -verbose -include-runtime -d .jar/modulator.jar
java -jar .jar/modulator.jar