1. commands to run GRID
  1. In general:
    1.1 HUB  - java -jar selenium-server-standalone-3.11.0.jar -role hub
    1.2 NODE - java -jar selenium-server-standalone-3.11.0.jar -role node -hub http://localhost:4444/grid/register
    1.3 to provide path to drivers use next format
          java -Dwebdriver.gecko.driver=/./environment/grid/drivers_win/geckodriver -jar selenium-server-standalone-3.11.0.jar -role node


2. Please use 'api.xml' for running REST HTTP API requests
3. Please use 'parallel_execution.xml' for running tests in parallel
4. Please use 'testng.xml' for running UI tests

