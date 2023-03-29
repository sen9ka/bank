package ru.senya.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.senya.application.client.DealClient;
import ru.senya.application.entity.dto.LoanApplicationRequestDTO;
import ru.senya.application.entity.dto.LoanOfferDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final DealClient dealClient;

    public List<LoanOfferDTO> makePostRequestToApplication(LoanApplicationRequestDTO loanApplicationRequestDTO, String applicationUrl) {

        return dealClient.createPostRequestToDealApplication(loanApplicationRequestDTO, applicationUrl);

    }

    public ResponseEntity<Object> makePostRequestToOffer(LoanOfferDTO loanOfferDTO, String offersUrl) {

        return dealClient.createPostRequestToDealOffer(loanOfferDTO, offersUrl);

    }

}
