
# Define the source file(s) for your Java program
SOURCE_FILES = PatientRecord.java PatientRecordNode.java PatientRecordTree.java 
TEST_FILES = PatientRecordTreeTester.java

# Build the Java program
all: $(SOURCE_FILES)
	javac -cp .:junit5.jar $(SOURCE_FILES)

# Run JUnit tests
test: $(TEST_FILES)
	javac -cp .:junit5.jar $(TEST_FILES)
	java -jar junit5.jar -cp . --scan-classpath -n PatientRecordTreeTester

# Clean the generated .class files
clean:
	rm -f *.class