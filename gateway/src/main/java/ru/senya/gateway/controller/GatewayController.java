package ru.senya.gateway.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.senya.gateway.config.GatewayProperties;
import ru.senya.gateway.entity.dto.CreditDTO;
import ru.senya.gateway.entity.dto.FinishRegistrationRequestDTO;
import ru.senya.gateway.entity.dto.LoanApplicationRequestDTO;
import ru.senya.gateway.entity.dto.LoanOfferDTO;
import ru.senya.gateway.service.ApplicationService;
import ru.senya.gateway.service.DealService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/gateway")
@Tag(name = "Микросервис Gateway")
public class GatewayController {

    private final ApplicationService applicationService;
    private final DealService dealService;
    private final GatewayProperties gatewayProperties;

    @PostMapping("/application")
    @Operation(summary = "Получение списка из 4 LoanOfferDTO")
    public ResponseEntity<Object> getLoanOffers(@RequestBody LoanApplicationRequestDTO loanApplicationRequestDTO) {
        List<LoanOfferDTO> loanOfferDTOList = applicationService.makePostRequestToGetOffers(loanApplicationRequestDTO, gatewayProperties.getApplicationLink());
        return new ResponseEntity<>(loanOfferDTOList, HttpStatus.OK);
    }

    @PostMapping("/offer")
    @Operation(summary = "Отправка выбранного LoanOffer")
    public ResponseEntity<Object> chooseLoanOffer(@RequestBody LoanOfferDTO loanOfferDTO) {
        return applicationService.makePostRequestToChooseOffer(loanOfferDTO, gatewayProperties.getOffersLink());
    }

    @PostMapping("/calculation/{applicationId}")
    @Operation(summary = "Получение полностью рассчитанного кредита")
    public ResponseEntity<Object> calculateCredit(@RequestBody FinishRegistrationRequestDTO finishRegistrationRequestDTO, @PathVariable Long applicationId) {
        CreditDTO creditDTO = dealService.makePostRequestToCalculateCredit(finishRegistrationRequestDTO, gatewayProperties.getCalculationLink() + applicationId);
        return new ResponseEntity<>(creditDTO, HttpStatus.OK);
    }

    @PostMapping("/send/{applicationId}")
    @Operation(summary = "Формирование документов по кредиту")
    public ResponseEntity<Object> sendDocuments (@PathVariable Long applicationId) {
        return dealService.makePostRequestToSendDocuments(gatewayProperties.getDealDocumentsLink() + applicationId + gatewayProperties.getDealSendDomain());
    }

    @PostMapping("/sign/{applicationId}")
    @Operation(summary = "Запрос на подписание документов, отправка 4-х значного кода")
    public ResponseEntity<Object> signDocuments (@PathVariable Long applicationId) {
        return dealService.makePostRequestToSignDocuments(gatewayProperties.getDealDocumentsLink() + applicationId + gatewayProperties.getDealSignDomain());
    }

    @PostMapping("/code/{applicationId}")
    @Operation(summary = "Подписание документов")
    public ResponseEntity<Object> sendCode (@PathVariable Long applicationId) {
        return dealService.makePostRequestToSendCode(gatewayProperties.getDealDocumentsLink() + applicationId + gatewayProperties.getDealCodeDomain());
    }

}
