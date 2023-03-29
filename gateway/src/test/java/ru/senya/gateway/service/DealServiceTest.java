package ru.senya.gateway.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.senya.gateway.client.DealClient;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DealServiceTest {

    @Mock
    private DealClient dealClient;

    @InjectMocks
    private DealService dealService;

    @Test
    @DisplayName("RuntimeException при запросе к calculateCredit")
    void makePostRequestToCalculateCreditTest() {
        when(dealClient.createOfferToCalculateCredit(any(), any())).thenThrow(RuntimeException.class);
        assertThrows(RuntimeException.class, () -> dealService.makePostRequestToCalculateCredit(any(), any()));
    }

    @Test
    @DisplayName("RuntimeException при запросе к sendDocuments")
    void makePostRequestToSendDocuments() {
        when(dealClient.createOfferToSendDocuments(any())).thenThrow(RuntimeException.class);
        assertThrows(RuntimeException.class, () -> dealService.makePostRequestToSendDocuments(any()));
    }

    @Test
    @DisplayName("RuntimeException при запросе к signDocuments")
    void makePostRequestToSignDocuments() {
        when(dealClient.createOfferToSignDocuments(any())).thenThrow(RuntimeException.class);
        assertThrows(RuntimeException.class, () -> dealService.makePostRequestToSignDocuments(any()));
    }

    @Test
    @DisplayName("RuntimeException при запросе к sendCode")
    void makePostRequestToSendCode() {
        when(dealClient.createOfferToCode(any())).thenThrow(RuntimeException.class);
        assertThrows(RuntimeException.class, () -> dealService.makePostRequestToSendCode(any()));
    }
}