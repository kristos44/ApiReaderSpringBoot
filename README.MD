cd to dir where repo cloned

copy StaticFeed-NO.json and DynamicFeed-NO.json to src/test/resources dir

make sure that src/main/resources/application.yml file define proper api addresses, key and names of two json files previously copied

run mvn clean test to perform tests

run mvn spring-boot:run

after app is started run in browser http://localhost:8080/swagger-ui.html#/fuel-controller/fetchStationInfoUsingGET to run the request