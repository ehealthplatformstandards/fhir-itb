package eu.europa.ec.fhir.handlers;

import eu.europa.ec.fhir.gitb.ReportingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportingController {

    @Autowired
    private ReportingService reportingService;

    @PostMapping("/reporting")
    public ResponseEntity<Void> handleRequest() {
        reportingService.report();
        return ResponseEntity.ok().build();
    }
}
