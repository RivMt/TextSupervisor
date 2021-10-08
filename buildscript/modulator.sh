clear
kotlinc $(find BookEditor/src/io/rivmt -name "*.kt") -verbose -include-runtime -d .jar/temp.jar
jar cmvf manifest/modulator.mf .jar/modulator.jar .jar/temp.jar
java -jar .jar/modulator.jar