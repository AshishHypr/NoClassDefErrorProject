package mobile.support;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("mobile")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "mobile.support, mobile.steps")
public class MobileTestRunner {

    private static final Logger log = LogManager.getLogger(MobileTestRunner.class.getName());
    @BeforeAll
    public static void beforeAll() {
        log.info("Test Suite starts here.");
    }

    @AfterAll
    public static void afterAll() {
        log.info("Test Suite ends here.");
    }
}
