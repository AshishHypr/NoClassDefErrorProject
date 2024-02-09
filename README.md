Command:
 - Start 'appium'
 - command: mvn clean test -Dtest=MobileTestRunner -DmobilePlatform=Android
    - Working as expected
 - command: mvn clean test exec:java@rerunFailedTests -DmobilePlatform=Android -Dcucumber.glue="mobile.support, mobile.steps" -DrerunFilePath="{path_to_project}/NoClassDefErrorProject/RerunDir/rerun.txt"
    - Not Working
 - To view report, navigate to cucumber url printed at the end of execution.
