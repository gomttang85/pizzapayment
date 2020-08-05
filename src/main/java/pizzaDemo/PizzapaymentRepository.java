package pizzaDemo;

import feign.Param;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface PizzapaymentRepository extends PagingAndSortingRepository<Pizzapayment, Long>{

    Optional<Pizzapayment> findByPizzaId(@Param("pizzaId") String pizzaId);

}