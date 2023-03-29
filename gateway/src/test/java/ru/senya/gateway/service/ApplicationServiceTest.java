package ru.senya.gateway.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.senya.gateway.client.ApplicationClient;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceTest {

    @Mock
    private ApplicationClient applicationClient;

    @InjectMocks
    private ApplicationService applicationService;

    @Test
    @DisplayName("Ошибка при вызове getOffers")
    void makePostRequestToGetOffers() {
        when(applicationClient.createPostRequestToGetOffers(any(), any())).thenThrow(RuntimeException.class);
        assertThrows(RuntimeException.class, () -> applicationService.makePostRequestToGetOffers(any(), any()));
    }

    @Test
    @DisplayName("Ошибка при вызове chooseOffer")
    void makePostRequestToChooseOffer() {
        when(applicationClient.createPostRequestToChooseOffer(any(), any())).thenThrow(RuntimeException.class);
        assertThrows(RuntimeException.class, () -> applicationService.makePostRequestToChooseOffer(any(), any()));
    }
}