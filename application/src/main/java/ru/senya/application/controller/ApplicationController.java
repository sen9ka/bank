package ru.senya.application.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.senya.application.entity.dto.LoanApplicationRequestDTO;
import ru.senya.application.entity.dto.LoanOfferDTO;
import ru.senya.application.service.ApplicationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/application")
@Tag(name = "Микросервис Application")
public class ApplicationController {

    @Value("${applicationLink}")
    private String applicationsUrl;

    @Value("${offersLink}")
    private String offersUrl;

    private final ApplicationService applicationService;

    Logger logger = LoggerFactory.getLogger(ApplicationController.class);

    @PostMapping("/")
    @Operation(summary = " Прескоринг + запрос на расчёт возможных условий кредита. Request - LoanApplicationRequestDTO, response List<LoanOfferDTO>")
    public ResponseEntity<Object> getLoanOffers(@RequestBody LoanApplicationRequestDTO loanApplicationRequestDTO) {
        logger.trace("Application API accessed");
        List<LoanOfferDTO> loanOfferDTOList = applicationService.makePostRequestToApplication(loanApplicationRequestDTO, applicationsUrl);
        return new ResponseEntity<>(loanOfferDTOList, HttpStatus.OK);
    }

    @PostMapping("/offer")
    @Operation(summary = "Выбор одного из предложений. Request LoanOfferDTO, response void")
    public ResponseEntity<Object> chooseLoanOffer(@RequestBody LoanOfferDTO loanOfferDTO) {
        logger.trace("Offers API accessed");
        return applicationService.makePostRequestToOffer(loanOfferDTO, offersUrl);
    }

}
