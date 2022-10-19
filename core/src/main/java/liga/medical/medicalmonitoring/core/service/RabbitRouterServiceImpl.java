package liga.medical.medicalmonitoring.core.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import liga.medical.medicalmonitoring.core.api.RabbitRouterService;
import liga.medical.medicalmonitoring.core.config.ExchangeConfig;
import liga.medical.medicalmonitoring.core.model.MessageType;
import liga.medical.medicalmonitoring.core.model.RabbitMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitRouterServiceImpl implements RabbitRouterService {

    private final ObjectMapper objectMapper;
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void routeMessage(String message) {
        rabbitTemplate.setExchange(ExchangeConfig.DIRECT_EXCHANGE);

        try {
            RabbitMessageDto rabbitMessageDto = objectMapper.readValue(message, RabbitMessageDto.class);
            rabbitTemplate.convertAndSend(rabbitMessageDto.getType().toString(), message);
            log.info("Роутер перенаправил сообшение {} при помощи обменника в очередь.",
                    rabbitMessageDto.getType().toString());
        } catch (Exception ex) {
            rabbitTemplate.convertAndSend(MessageType.ERROR.toString(), ex.getMessage());
            log.error("При перенаправлении сообщения произошла ошибка.");
        }
    }
}
