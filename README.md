QA Project
========
This project is rewrite for testing the End-To-End aqarmap.com Website

Stack Requirements
--------
* Selenium using java
* Maven
* TestNG

### How to setup. 
1- make sure that you have the following 
- JDK 
- ChromeDriver 
- Firefox Driver
2- IDE installed in your machine (inteliJ or eclipse) 

Installation
--------
$ git clone git@github.com:aqarmap/QA-testing.git
if you are using Windows you must add drivers folder to the project 
then add the following line to TestBase class to startDriver method 
System.setProperty("webdriver.chrome.driver","path to driver.exe");




Then you need to create your configuration.properites file
```
