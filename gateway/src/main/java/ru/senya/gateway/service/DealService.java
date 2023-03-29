package ru.senya.gateway.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.senya.gateway.client.DealClient;
import ru.senya.gateway.entity.dto.CreditDTO;
import ru.senya.gateway.entity.dto.FinishRegistrationRequestDTO;

@Service
@RequiredArgsConstructor
public class DealService {

    private final DealClient dealClient;

    public CreditDTO makePostRequestToCalculateCredit(FinishRegistrationRequestDTO finishRegistrationRequestDTO, String calculationsUrl) {
        return dealClient.createOfferToCalculateCredit(finishRegistrationRequestDTO, calculationsUrl);
    }

    public ResponseEntity<Object> makePostRequestToSendDocuments(String sendDocumentsUrl) {
        return dealClient.createOfferToSendDocuments(sendDocumentsUrl);
    }

    public ResponseEntity<Object> makePostRequestToSignDocuments(String signDocumentsUrl) {
        return dealClient.createOfferToSignDocuments(signDocumentsUrl);
    }

    public ResponseEntity<Object> makePostRequestToSendCode(String codeUrl) {
        return dealClient.createOfferToCode(codeUrl);
    }

}
