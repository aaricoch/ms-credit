package pe.com.nttdbank.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Credit extends BaseEntity {
    @Id
    @GeneratedValue
    private Long idCredit;
    private Long idCustomer;
    private String creditNumber;
    private Date disbursementDate;
    private Date endDate;
    private int numberPayments;
    private int monthlyPaymentDay;
    private Double initialBalance;
    private Double currentBalance;
    private Double paymentAmountMonthly;

    public Credit() {
        super();
    }
}
