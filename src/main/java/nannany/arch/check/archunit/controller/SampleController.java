package nannany.arch.check.archunit.controller;

import nannany.arch.check.archunit.domain.Fortune;
import nannany.arch.check.archunit.service.FortuneTellingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class SampleController {

    private final FortuneTellingService fortuneTellingService;

    public SampleController(FortuneTellingService fortuneTellingService) {
        this.fortuneTellingService = fortuneTellingService;
    }

    @GetMapping("sample")
    public Fortune sample() {

        return fortuneTellingService.fortuneTelling(LocalDateTime.now().getSecond());

    }

}
