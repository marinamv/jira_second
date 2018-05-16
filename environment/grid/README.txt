1. commands to run GRID
  1. In general:
    1.1 HUB  - java -jar selenium-server-standalone-3.4.0.jar -role hub
    1.2 NODE - java -jar selenium-server-standalone-3.4.0.jar -role node
    1.3 to provide path to drivers use next format
          java -Dwebdriver.gecko.driver=/./environment/grid/drivers_win/geckodriver -jar selenium-server-standalone-3.4.0.jar -role node


