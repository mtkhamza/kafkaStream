package ma.cloud.consumer.service;

import ma.cloud.consumer.entities.Bill;
import ma.cloud.consumer.repository.BillRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private BillRepository billRepository;

    public KafkaConsumer(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @KafkaListener(topics = "FACTURATION", groupId = "sample_consumer")
    public void onMessage(Bill bill) {
        System.out.println("Receiving Bill => " + bill.toString());
//        billRepository.save(bill);
    }
}
