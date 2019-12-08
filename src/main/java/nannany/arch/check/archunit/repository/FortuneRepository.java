package nannany.arch.check.archunit.repository;

import nannany.arch.check.archunit.domain.Fortune;
import nannany.arch.check.archunit.service.FortuneTellingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

@Repository
public class FortuneRepository {
    private static final Logger logger = LoggerFactory.getLogger(FortuneRepository.class);

    public void writeToFile(LocalDateTime now, Fortune fortune) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("fortunes", true))) {
            writer.write(now.toString() + fortune.toString() + System.lineSeparator());

        } catch (IOException e) {
            logger.error("IOException is occurred", e);
        }
    }
}
