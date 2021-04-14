# Appium+Java+Cucumber framework for android/ios application test automation #

This framework purpose is the implementation and execution of acceptance tests for mobile applications on iOS and Android platforms. It consists of predefined dependencies and API's that will help to describe the expected behaviour of web app.

### Core dependencies ###

* Appium 7.5.1 - test automation framework for mobile applications
* Cucumber 6.9.1 - provides the natural Gherkin language used to describe application features, a basic API for binding that natural language to step definitions written in Java
* PageObject/PageElements - design patterns which helps you to encapsulate the expected structure and mechanics of your application's UI.
* JUnit4 - for asserting expectations of scenario outcomes
* Apache Maven - build automation tool
* Awaitility - alternative, more flexible way of using explicit waits
* Allure - test reports

## Getting Started ##

### Architecture ###

Framework is divided in such subsections:

#### Main ####
* src/main/java/allure
* src/main/java/base
* src/main/java/constants
* src/main/java/elements
* src/main/java/exceptions
* src/main/java/helpers
* src/main/java/hook
* src/main/java/utils
* src/main/resources

**allure** - allure reporter custom scenario state listeners

**base** - core step class, with data storage to share between steps/scenarios

**constants** - config fields

**elements** - page elements like buttons, labels, text fields etc.

**exceptions** - custom exception classes to help understand test failures

**helpers** - helper functions for helping writing test cases and page objects

**hook** - "before" and "after" annotations with different setups either for driver or test cases

**utils** - WebDriver setup and special utilities

**resources** - run configurations stored here with environment settings

#### Test ####
* src/test/java/custom_elements
* src/test/java/pages
* src/test/java/runners
* src/test/java/steps
* src/test/resources/features
* src/test/resources/datasets/devices

**custom_elements** - custom page elements which are specific to product under test

**pages** - web application page objects and components

**runners** - runners needed for execution

**steps** - cucumber step implementations mapped with gherkin

**features** - feature files written in Gherkin language

**devices** - list of mobile device settings needed for Appium


### Writing Test Cases ###

Acceptance TC are written in Gherkin language inside feature file which are stored in "Features" directory and then mapped to a step implementation in Java inside step classes.

### Running Test Cases ###

##### Precondition ####
Minimum requirements are JDK 11, Maven.

1. Install npm
2. Install appium from npm
3. Install appium-doctor from npm
4. Run appium-doctor and setup all the necessary preconditions for working with Appium

###### ANDROID ######
* Before executing tests you need to create an android emulator which will be used as a testing device or connect a real physical device to you machine. With existing config it should be an Android emulator with OS version 10
* Run the Android emulator you just created  - "emulator -avd <emulator_name>"

###### IOS ######
* Before executing tests you need download Xcode from appstore and check that simulators with correct ios version are installed. With existing config it should be an iPhone 8 simulator with iOS version 14
* Device config in yaml should contain correct udid of the simulator you are using (device.udid). You can find it in Xcode "Devices and Simulators" window as "Identifier" property for your device.

##### Execution ####
1. Check for the correct data in your config.properties file. You can create your own env config file and use it for execution specifying prefix in config.properties (Ex. app.environment=PROD means that there must be config-PROD.properties)
2. Open project dir in terminal and run "mvn clean test". Specify tags to run in TestRunner file or through terminal with -Dcucumber.filter.tags="@testSuite" - "mvn clean verify -Dcucumber.filter.tags="@testSuite""
   2.1. Also you can execute test from your IDE with TestRunner class as it is JUnit executable class.
3. Allure results are stored in ./allure-results directory. To generate html report install 'allure-commandline' npm module and run 'allure serve' from project core directory
   3.1 Cucumber json report is in "target/reports/cucumber.json". It can be used to generate report with other reporting tools.

##### Helpers ####
getAppActivity.sh - script for getting Android app launch activity needed for Appium
run_suite.sh - script for executing full test suite and getting Allure results