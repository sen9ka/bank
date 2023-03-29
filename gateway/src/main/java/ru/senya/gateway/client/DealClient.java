package ru.senya.gateway.client;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import ru.senya.gateway.config.RestTemplateConfig;
import ru.senya.gateway.entity.dto.CreditDTO;
import ru.senya.gateway.entity.dto.FinishRegistrationRequestDTO;

@Configuration
@AllArgsConstructor
public class DealClient {

    RestTemplateConfig restTemplateConfig;

    public CreditDTO createOfferToCalculateCredit(FinishRegistrationRequestDTO finishRegistrationRequestDTO, String calculationUrl) {
        RestTemplate restTemplate = restTemplateConfig.getRestTemplate();
        HttpHeaders headers = restTemplateConfig.getHeaders();

        HttpEntity<FinishRegistrationRequestDTO> request = new HttpEntity<>(finishRegistrationRequestDTO, headers);
        ResponseEntity<CreditDTO> responseEntity = restTemplate.exchange(calculationUrl, HttpMethod.POST, request, new ParameterizedTypeReference<>() {});

        return responseEntity.getBody();
    }

    public ResponseEntity<Object> createOfferToSendDocuments(String sendDocumentsUrl) {
        RestTemplate restTemplate = restTemplateConfig.getRestTemplate();
        HttpHeaders headers = restTemplateConfig.getHeaders();

        HttpEntity<?> request = new HttpEntity<>(headers);
        return restTemplate.exchange(sendDocumentsUrl, HttpMethod.POST, request, new ParameterizedTypeReference<>() {});
    }

    public ResponseEntity<Object> createOfferToSignDocuments(String signDocumentsUrl) {
        RestTemplate restTemplate = restTemplateConfig.getRestTemplate();
        HttpHeaders headers = restTemplateConfig.getHeaders();

        HttpEntity<?> request = new HttpEntity<>(headers);
        return restTemplate.exchange(signDocumentsUrl, HttpMethod.POST, request, new ParameterizedTypeReference<>() {});
    }

    public ResponseEntity<Object> createOfferToCode(String codeUrl) {
        RestTemplate restTemplate = restTemplateConfig.getRestTemplate();
        HttpHeaders headers = restTemplateConfig.getHeaders();

        HttpEntity<?> request = new HttpEntity<>(headers);
        return restTemplate.exchange(codeUrl, HttpMethod.POST, request, new ParameterizedTypeReference<>() {});
    }

}
