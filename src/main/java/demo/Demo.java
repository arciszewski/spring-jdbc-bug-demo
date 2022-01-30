package demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Demo implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Demo.class);

    private final DataRepository dataRepository;

    public Demo(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Demo.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        final int loops = 1000;

        LOGGER.info("Adding records");
        int counter = 0;
        for (int i = 0; i < loops; i++) {
            String keyValue = String.format("%d", i);
            dataRepository.add(keyValue, keyValue);
            counter++;
        }
        LOGGER.info("Inserted {} records into the DB", counter);

        counter = 0;
        for (int i = 0; i < loops; i++) {
            String keyValue = String.format("%d", i);
            dataRepository.getFromList(keyValue).ifPresent(value -> {});
            counter++;
        }
        LOGGER.info("Extracted {} records with query() method", counter);

        counter = 0;
        for (int i = 0; i < loops; i++) {
            String keyValue = String.format("%d", i);
            dataRepository.getFromStream(keyValue).ifPresent(value -> {});
            counter++;
        }
        LOGGER.info("Extracted {} records with queryForStream() method", counter);

    }
}
