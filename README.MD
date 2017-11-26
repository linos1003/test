[![Build Status](https://travis-ci.org/linos1003/test.svg?branch=master)](https://travis-ci.org/linos1003/test)


## Synopsis
This is a scala program
## Prerequisite
To run the application, you need to install these softwares
### JDK 8
You can find instructions for installing the JDK for different platforms here: http://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html
### Apache Maven
You can find instructions for installing the Maven for different platforms here: https://maven.apache.org/install.html
## Build and run the application
- First, clone the project from github : git clone https://github.com/linos1003/test.git
- Enter to the root folder  "cd test" , and build the project using maven
```
mvn clean install
```
- run the program with  the folowing command : java -jar test.jar <your input path file> <your output path file>  <diplay details (true or false)>
The third parameter is optional.
Example  using the default file in the project:
```
java -jar target/test.jar mower.in mower.out
```
And the following result will be shown:
```
2017-11-26 04:18:57 INFO  AppMain$:37 - Initial mowers positions: (1,2,N), (3,3,E)
2017-11-26 04:18:57 INFO  AppMain$:38 - Final mowers positions  : (1,3,N), (5,1,E)
```
 Example with the display option true

```
java -jar target/test.jar mower.in mower.out true
```
And the following result will be shown:
```
2017-11-26 04:26:50 INFO  AppMain$:37 - Initial mowers positions: (1,2,N), (3,3,E)
+ - + - + - + - + - + - +
|   |   |   |   |   |   |
+ - + - + - + - + - + - +
|   |   |   |   |   |   |
+ - + - + - + - + - + - +
|   |   |   | X |   |   |
+ - + - + - + - + - + - +
|   | X |   |   |   |   |
+ - + - + - + - + - + - +
|   |   |   |   |   |   |
+ - + - + - + - + - + - +
|   |   |   |   |   |   |
+ - + - + - + - + - + - +
2017-11-26 04:26:50 INFO  AppMain$:38 - Final mowers positions  : (1,3,N), (5,1,E)
+ - + - + - + - + - + - +
|   |   |   |   |   |   |
+ - + - + - + - + - + - +
|   |   |   |   |   |   |
+ - + - + - + - + - + - +
|   | X |   |   |   |   |
+ - + - + - + - + - + - +
|   |   |   |   |   |   |
+ - + - + - + - + - + - +
|   |   |   |   |   | X |
+ - + - + - + - + - + - +
|   |   |   |   |   |   |
+ - + - + - + - + - + - +
```
