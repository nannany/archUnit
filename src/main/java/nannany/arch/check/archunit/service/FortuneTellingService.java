package nannany.arch.check.archunit.service;

import nannany.arch.check.archunit.domain.Fortune;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class FortuneTellingService {
    private static final Logger logger = LoggerFactory.getLogger(FortuneTellingService.class);
    private static final Fortune bad = new Fortune("凶", 4);
    private static final Fortune good = new Fortune("吉", 1);

    public Fortune fortuneTelling(int seed) {
        logger.info(String.valueOf(seed));
        return seed % 2 == 0 ? good : bad;
    }

}
