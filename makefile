LIB = lib
JAR_CC = commons-collections4-4.5.0-M3.jar
JAR_CL = commons-lang3-3.17.0.jar
JAR_SA = slf4j-api-2.0.16.jar
JAR_SS = slf4j-simple-2.0.16.jar
JAR_VE = velocity-engine-core-2.4.1.jar
LIBS = $(LIB)/$(JAR_CC):$(LIB)/$(JAR_CL):$(LIB)/$(JAR_SA):$(LIB)/$(JAR_SS):$(LIB)/$(JAR_VE)
JAR_NAME = compiler-tools-413.jar

clean:
	@echo Cleaning...
	@rm -rf out
	@rm -rf META-INF
	@rm -rf org

compile: clean
	@echo Compiling...
	@javac -cp src:$(LIBS) -d out src/tools/CompilerTools.java

tools: compile
	@java -cp out:$(LIBS) tools.CompilerTools src/tools/definition/grammar.txt -2 -s

visitor-test: compile
	@java -cp out:$(LIBS) tools.CompilerTools src/tools/definition/grammar.txt -n ExampleVisitor

jar: compile
	@cp -R out/tools .
	@rm -f $(JAR_NAME)
	@jar xf $(LIB)/$(JAR_CC)
	@jar xf $(LIB)/$(JAR_CL)
	@jar xf $(LIB)/$(JAR_SA)
	@jar xf $(LIB)/$(JAR_SS)
	@jar xf $(LIB)/$(JAR_VE)
	@echo "Manifest-Version: 1.0\nMain-Class: tools.CompilerTools" > MANIFEST.MF
	@jar cfm $(JAR_NAME) MANIFEST.MF org META-INF tools templates
	@rm -rf $(JAR_DIR) META-INF org MANIFEST.MF tools
