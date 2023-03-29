package ru.senya.gateway.client;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.senya.gateway.config.RestTemplateConfig;
import ru.senya.gateway.entity.dto.LoanApplicationRequestDTO;
import ru.senya.gateway.entity.dto.LoanOfferDTO;

import java.util.List;

@AllArgsConstructor
@Configuration
public class ApplicationClient {

    RestTemplateConfig restTemplateConfig;

    public List<LoanOfferDTO> createPostRequestToGetOffers(LoanApplicationRequestDTO loanApplicationRequestDTO, String applicationsUrl) {
        RestTemplate restTemplate = restTemplateConfig.getRestTemplate();
        HttpHeaders headers = restTemplateConfig.getHeaders();

        HttpEntity<LoanApplicationRequestDTO> request = new HttpEntity<>(loanApplicationRequestDTO, headers);
        ResponseEntity<List<LoanOfferDTO>> responseEntity = restTemplate.exchange(applicationsUrl, HttpMethod.POST, request, new ParameterizedTypeReference<>() {});

        return responseEntity.getBody();
    }

    public ResponseEntity<Object> createPostRequestToChooseOffer(LoanOfferDTO loanOfferDTO, String offersUrl) {
        RestTemplate restTemplate = restTemplateConfig.getRestTemplate();
        HttpHeaders headers = restTemplateConfig.getHeaders();

        HttpEntity<LoanOfferDTO> request = new HttpEntity<>(loanOfferDTO, headers);
        return restTemplate.exchange(offersUrl, HttpMethod.POST, request, new ParameterizedTypeReference<>() {});
    }

}
