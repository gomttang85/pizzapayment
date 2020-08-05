package pizzaDemo;

import pizzaDemo.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{

    @Autowired
    PizzapaymentRepository pizzapaymentRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPizzaorderCanceled_Pizzapaymentcancel(@Payload PizzaorderCanceled pizzaorderCanceled){

        if(pizzaorderCanceled.isMe()){

            Pizzapayment pizzapayment = new Pizzapayment();
            pizzapayment.setPizzaId(pizzaorderCanceled.getPizzaId());
            pizzapayment.setPaymentStatus("CANCELED");

            pizzapaymentRepository.save(pizzapayment);

        }
    }

}
