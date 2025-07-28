package eu.europa.ec.fhir;

import eu.europa.ec.fhir.gitb.ReportingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(StartupRunner.class);

    @Autowired
    private ReportingService reportingService;

    @Override
    public void run(ApplicationArguments args) {
        log.info("Running startup logic...");
        reportingService.report();
    }
}
