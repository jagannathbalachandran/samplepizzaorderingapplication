Pre requisites
---------------

Java - 1.6 or greater

Maven - 3.2.3

Chrome/Firefox

Tested on
----------

Windows , Mac OS

Chrome - version 39 , Firefox - version 28 (Supports only Chrome and Firefox)


Test framework Tech stack
---------------------------

Java

Cucumber-JVM

Selenium(Webdriver)

Maven

Picocontainer

Description of framework
--------------------------

1) Uses page object pattern to model the application

2) Pages folder contains all the pages of the application

3) Entity folder contains entities observed in the application like Pizza, Toppings etc.

4) Features folder contains the feature files

5) Steps folder contains the glue code - which delegates to the page object methods

6) SharedContext contains webdriver instance which is passed around the step code with the help of Picocontainer 

7) An external configuration file (config.properties) to hold key value pairs of configurable variables - ( browser , url , username , password)
	Eg -  If you want to run the tests against chrome , change the browser value to chrome
	

Steps to run tests from command line
-------------------------------------

1) Go to root folder of the project through commandline/terminal

2)Execute - mvn clean

3)Execute - mvn compile 

4)Execute - mvn test 


Running tests on chrome
---------------------------

Please follow the instructions here  
https://code.google.com/p/selenium/wiki/ChromeDriver
Setting chrome driver on Mac - http://splinter.cobrateam.info/en/0.1/setup-chrome.html