package ma.cloud.producer;

import lombok.extern.slf4j.Slf4j;
import ma.cloud.producer.entities.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class BillSource implements ApplicationRunner {
    @Autowired
    private KafkaTemplate<String, Bill> kafkaTemplate;

    @Override
    public void run(ApplicationArguments applicationArguments) {
        System.out.println("..................................................;");
        List<String> numbers = Arrays.asList("Hassan", "Mohamed", "Hanane", "Yassine", "Samir", "Aziz");
        List<String> clientsName = Arrays.asList("blog", "chat", "profile", "about", "contact", "vote", "search");
        List<Double> amounts = Arrays.asList(1250.0, 16988.0, 6651.0, 2653.0, 8455.0, 4889.0, 11244.0);

        Runnable runnable = () -> {
            String number = numbers.get(new Random().nextInt(numbers.size()));
            String clientName = clientsName.get(new Random().nextInt(clientsName.size()));
            double amount = amounts.get((new Random().nextInt(amounts.size())));

            Bill bill = new Bill(null, number, clientName, amount);
            System.out.println("## Sending Bill ...");
            kafkaTemplate.send("FACTURATION", bill);
            log.info("Sending bill =>" + bill.toString());
        };
        System.out.println("--------------------------------------------");
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(runnable, 1, 1, TimeUnit.SECONDS);
    }
}
