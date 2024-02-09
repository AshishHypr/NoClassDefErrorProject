package web.support;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.platform.engine.discovery.ClasspathResourceSelector;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.engine.discovery.FilePosition;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder.request;

/**
 * This Runner class does not have feature defined under @CucumberOptions.
 * As this Runner is to execute list of failed Scenarios which are listed under "FailedTestCaseDirectory" file,
 * we are passing that file path from maven command.
 */
public class FailedScenariosRunner {
    static Logger log = LogManager.getLogger(FailedScenariosRunner.class.getName());
    static String rerunFilePath;

    public static void main(String[] args) {
        log.info("FailedScenariosRunner-main()");
        rerunFilePath = System.getProperty("rerunFilePath");
        if (!Files.exists(Path.of(rerunFilePath))) {
            log.info("File:" + rerunFilePath + " doesn't exist!");
            System.exit(1);
        }
        try {
            LauncherDiscoveryRequest request = request()
                    .selectors(
                            getUniqueIds()
                    )
                    .build();

            Launcher launcher = LauncherFactory.create();
            SummaryGeneratingListener listener = new SummaryGeneratingListener();
            launcher.registerTestExecutionListeners(listener);
            launcher.execute(request, listener);
            TestExecutionSummary summary = listener.getSummary();
            log.info("Test will exit with:" + summary.getTotalFailureCount());

            // This exits the test execution with positive number if the tests are failed else it returns 0
            System.exit((int) summary.getTotalFailureCount());
        } catch (Exception e) {
            log.info("Exception encountered in FailedScenariosRunner-main()--" + e.getMessage());
            e.printStackTrace();
        }
    }

    private static List<ClasspathResourceSelector> getUniqueIds() throws IOException {
        log.info("System.getProperty('rerunFilePath'):" + rerunFilePath);
        List<ClasspathResourceSelector> failures = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Path.of(rerunFilePath))) {
            // Logic when the files contents are:
            // features/web/CCEnvtest.feature:9
            // features/web/CCLoginCRUDApplication.feature:15

            stream.forEach(line -> {
                        if (!line.trim().isEmpty()) {
                            log.info("Line:" + line);
                            String[] featureLines = line.split(":");
                            failures.add(DiscoverySelectors.selectClasspathResource(featureLines[0], FilePosition.from(Integer.parseInt(featureLines[1]))));
                        }
                    }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        failures.forEach(System.out::println);
        return failures;
    }
}