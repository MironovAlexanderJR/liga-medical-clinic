package liga.medical.medicalmonitoring.core.config;

import liga.medical.commondto.Type;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ExchangeConfig {

    public static final String DIRECT_EXCHANGE = "direct_exchange";

    private final Queue dailyQueue;
    private final Queue alertQueue;
    private final Queue errorQueue;

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(DIRECT_EXCHANGE);
    }

    @Bean
    public Binding bindingDailyMsg() {
        return BindingBuilder.bind(dailyQueue).to(directExchange()).with(Type.DAILY.toString());
    }

    @Bean
    public Binding bindingAlertMsg() {
        return BindingBuilder.bind(alertQueue).to(directExchange()).with(Type.ALERT.toString());
    }

    @Bean
    public Binding bindingErrorMsg() {
        return BindingBuilder.bind(errorQueue).to(directExchange()).with(Type.ERROR.toString());
    }
}
