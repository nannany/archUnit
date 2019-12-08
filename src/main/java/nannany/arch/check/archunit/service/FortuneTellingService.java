package nannany.arch.check.archunit.service;

import nannany.arch.check.archunit.domain.Fortune;
import nannany.arch.check.archunit.repository.FortuneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class FortuneTellingService {
    private static final Logger logger = LoggerFactory.getLogger(FortuneTellingService.class);
    private static final Fortune bad = new Fortune("凶", 4);
    private static final Fortune good = new Fortune("吉", 1);

    private final FortuneRepository fortuneRepository;

    public FortuneTellingService(FortuneRepository fortuneRepository) {
        this.fortuneRepository = fortuneRepository;
    }

    public Fortune fortuneTelling(LocalDateTime now) {

        logger.info(now.toString());

        Fortune fortune = now.getSecond() % 2 == 0 ? good : bad;

        fortuneRepository.writeToFile(now, fortune);

        return fortune;
    }

}
