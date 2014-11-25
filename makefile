All:
	cd src/ ; javac main.java
	mv ./src/*.class ./bin
	mv ./src/Items/*.class ./bin/Items
	mv ./src/Characters/*.class ./bin/Characters
	mv ./src/Time/*.class ./bin/Time
	cd ..
	
run:
	cd bin ; java main
	cd ..

