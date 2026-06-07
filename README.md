# automationElitea

Java Selenium POM project scaffold using TestNG.

## Structure

- `src/main/java` - page objects, driver factory, configuration
- `src/test/java` - TestNG tests
- `src/test/resources` - `testng.xml` and `config.properties`

## Run tests

From the project root:

```bash
mvn test
```

## Configuration

Update `src/test/resources/config.properties` with the application URL and browser.
