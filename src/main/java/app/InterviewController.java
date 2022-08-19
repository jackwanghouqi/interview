package app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.inject.Inject;

@Slf4j
@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class InterviewController {
    @Inject
    InterviewService service;

    @GetMapping("/interview/{company}")
    public ResponseEntity count(@PathVariable String company) throws Exception {
        System.out.println("api call");
        return getResponseEntity(service.getInterview(company));
    }

    private ResponseEntity getResponseEntity(Object obj) {
        return ResponseEntity.ok().body(obj);
    }
}
