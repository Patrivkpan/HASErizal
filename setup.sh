echo "Compiling classes..."

javac -d bin -sourcepath src src/Main.java

echo "Compilation done! Running main program..."

java -classpath bin Main

echo "Main program is done executing!"

echo "Press enter to continue..."

read dummy
