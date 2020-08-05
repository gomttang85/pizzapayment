package pizzaDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
 public class PizzapaymentController {
   @Autowired
   PizzapaymentRepository pizzapaymentRepository;

   @RequestMapping(method=RequestMethod.POST, path="/paymentscreations")
   public void cancelPizzaDelivery(@RequestBody Pizzapayment pizzapayment){

    Pizzapayment pizzaorder = new Pizzapayment();

    pizzaorder.setPizzaId(pizzapayment.getPizzaId());
    pizzaorder.setPaymentStatus(pizzapayment.getPaymentStatus());
    pizzapaymentRepository.save(pizzaorder);

   }
 }
