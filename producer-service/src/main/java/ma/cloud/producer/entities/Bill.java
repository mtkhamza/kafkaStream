package ma.cloud.producer.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Bill {
    private Long id;
    private String number;
    private String clientName;
    private double amount;
}