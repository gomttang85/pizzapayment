package pizzaDemo;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Pizzapayment_table")
public class Pizzapayment {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String pizzaId;
    private String paymentStatus;

    @PostPersist
    public void onPostPersist(){

        Pizzasettled pizzasettled = new Pizzasettled();
        BeanUtils.copyProperties(this, pizzasettled);
        pizzasettled.publishAfterCommit();

        // hystrix 테스트용
//        try {
//            Thread.currentThread().sleep((long) (400 + Math.random() * 220));
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }

    @PostRemove
    public void onPostRemove(){
        Pizzapaymentcanceled pizzapaymentcanceled = new Pizzapaymentcanceled();
        BeanUtils.copyProperties(this, pizzapaymentcanceled);
        pizzapaymentcanceled.publishAfterCommit();


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(String pizzaId) {
        this.pizzaId = pizzaId;
    }
    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }




}
