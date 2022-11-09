package liga.medical.medicalmonitoring.core.clients;

import liga.medical.commondto.DebugLogDto;
import liga.medical.commondto.ExceptionLogDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("common-service")
public interface CommonClient {

    @PostMapping("/debug")
    void saveDebugLog(DebugLogDto debugLog);

    @PostMapping("/exception")
    void saveExceptionLog(ExceptionLogDto exceptionLog);
}
