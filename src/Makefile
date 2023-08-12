JFLAGS = -g
JC = javac
SRC = src
BIN = bin
DATA = data
ARGS = 3000 3000 -300 300 -300 300 0.001



# Generate corresponding class file names
CLASSES := $(shell find $(SRC)/ MonteCarloMini -name "*.java")


default: classes

classes:
		$(JC) $(JFLAGS) -cp $(BIN) $(CLASSES) -d $(BIN)

clean:
		$(RM) $(BIN)/MonteCarloMinimizationParallel/*.class

run: 
	java -cp $(BIN) MonteCarloMini.MonteCarloMinimizationParallel $(ARGS)