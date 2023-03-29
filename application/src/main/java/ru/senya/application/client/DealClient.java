package ru.senya.application.client;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.senya.application.config.RestTemplateConfig;
import ru.senya.application.entity.dto.LoanApplicationRequestDTO;
import ru.senya.application.entity.dto.LoanOfferDTO;

import java.util.List;

@AllArgsConstructor
@Configuration
public class DealClient {

    RestTemplateConfig restTemplateConfig;

    public List<LoanOfferDTO> createPostRequestToDealApplication(LoanApplicationRequestDTO loanApplicationRequestDTO, String applicationsUrl) {
        RestTemplate restTemplate = restTemplateConfig.getRestTemplate();
        HttpHeaders headers = restTemplateConfig.getHeaders();

        HttpEntity<LoanApplicationRequestDTO> request = new HttpEntity<>(loanApplicationRequestDTO, headers);
        ResponseEntity<List<LoanOfferDTO>> responseEntity = restTemplate.exchange(applicationsUrl, HttpMethod.POST, request, new ParameterizedTypeReference<>() {});

        return responseEntity.getBody();
    }

    public ResponseEntity<Object> createPostRequestToDealOffer(LoanOfferDTO loanOfferDTO, String offersUrl) {
        RestTemplate restTemplate = restTemplateConfig.getRestTemplate();
        HttpHeaders headers = restTemplateConfig.getHeaders();

        HttpEntity<LoanOfferDTO> request = new HttpEntity<>(loanOfferDTO, headers);

        return restTemplate.exchange(offersUrl, HttpMethod.POST, request, new ParameterizedTypeReference<>() {});
    }

}
