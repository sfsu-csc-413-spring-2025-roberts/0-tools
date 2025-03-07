LIBS = lib/commons-collections4-4.5.0-M3.jar:lib/commons-lang3-3.17.0.jar:lib/slf4j-api-2.0.16.jar:lib/slf4j-simple-2.0.16.jar:lib/velocity-engine-core-2.4.1.jar

clean:
	@echo Cleaning...
	@rm -rf out

compile: clean
	@echo Compiling...
	@javac -cp src:$(LIBS) -d out src/tools/CompilerTools.java

tools: compile
	@java -cp out:$(LIBS) tools.CompilerTools src/tools/definition/grammar.txt -2 -s

visitor-test: compile
	@java -cp out:$(LIBS) tools.CompilerTools src/tools/definition/grammar.txt -n ExampleVisitor
