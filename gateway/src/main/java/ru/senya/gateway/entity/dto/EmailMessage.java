package ru.senya.gateway.entity.dto;

import lombok.*;
import ru.senya.gateway.entity.enums.Theme;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class EmailMessage {

    private String address;

    private Theme theme;

    private Long applicationId;

}
