package liga.medical.medicalmonitoring.core.service;

import liga.medical.medicalmonitoring.core.api.RabbitRouterService;
import liga.medical.medicalmonitoring.core.model.QueueNames;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitRouterListener {

    private final RabbitRouterService rabbitRouterService;

    @RabbitListener(queues = QueueNames.COMMON_MONITORING_QUEUE)
    public void receiveAndRedirectMessage(String message) {
        rabbitRouterService.routeMessage(message);
    }
}
