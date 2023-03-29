package ru.senya.application.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ru.senya.application.client.DealClient;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.senya.application.entity.dto.LoanApplicationRequestDTO;
import ru.senya.application.entity.dto.LoanOfferDTO;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceTest {

    public static final String URL = "url";

    @Mock
    private DealClient dealClient;

    @InjectMocks
    private ApplicationService applicationService;

    @Test
    @DisplayName("RuntimeException при запросе к Application")
    void makePostRequestToApplication() {
        LoanApplicationRequestDTO loanApplicationRequestDTO = LoanApplicationRequestDTO.builder().build();
        when(dealClient.createPostRequestToDealApplication(loanApplicationRequestDTO, URL)).thenThrow(RuntimeException.class);
        assertThrows(RuntimeException.class, () -> applicationService.makePostRequestToApplication(loanApplicationRequestDTO, URL));
    }

    @Test
    @DisplayName("RuntimeException при запросе к Offer")
    void makePostRequestToOffer() {
        LoanOfferDTO loanOfferDTO = LoanOfferDTO.builder().build();
        when(dealClient.createPostRequestToDealOffer(any(), any())).thenThrow(RuntimeException.class);
        assertThrows(RuntimeException.class, () -> applicationService.makePostRequestToOffer(loanOfferDTO, URL));
    }
}