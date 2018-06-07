1. commands to run GRID
  1. In general:
    1.1 HUB  - java -jar selenium-server-standalone-3.11.0.jar -role hub
    1.2 NODE - java -jar selenium-server-standalone-3.11.0.jar -role node -hub http://localhost:4444/grid/register
    1.3 to provide path to drivers use next format
          java -Dwebdriver.gecko.driver=/./environment/grid/drivers_win/geckodriver -jar selenium-server-standalone-3.11.0.jar -role node


2. Please use 'api.xml' for running REST HTTP API requests
3. Please use 'parallel_execution.xml' for running tests in parallel
4. Please use 'testng.xml' for running UI tests

- pom.xml - две депенденси на testng, отставь какую-то одну
- может уже писал до этого, наличие readme файла +
- группировка в проперти файле +
- использование try/catch в методах наподобие waitToBePresentAndClick, часто происходит эксепшен?
- в чем была идея использования fieldProjectLocator.sendKeys(Keys.TAB)? переход в следующее поле?
- метод public LoginPage login(WebElement user,String password), похоже можно удалить
- issueTypeStory = Story и issueTypeBug = Bug, в принципе можно было вынести в enum, т.к. из пропертей ты его по факту не задаешь, а просто объявляешь
- assertEquals(loginPage.isOnThePage(), true, "Login Page should be display") - в данном случае лучше использовать assertTrue
- header("Content-Type" и header("Cookie", можно использовать встроенные методы restassured .contentType() cookie(), ятобы вручную не перечислять в хидерах
- в api тестах, у тебя в самих тестах отсутствуют ассерты, т.е. по факту у тебя весь тест находится в одном методе, например JiraApiActions.createIssue(issueJson), этот метод хорошо использовать например как прекондишен к UI тестам, что кстати, надо сделать, в презентации в задании это не написано, мы тогда на лекции проговаривали это
