package ru.senya.gateway.entity.dto;

import lombok.*;
import ru.senya.gateway.entity.enums.Gender;
import ru.senya.gateway.entity.enums.MaritalStatus;


import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FinishRegistrationRequestDTO {

    private Gender gender;

    private MaritalStatus maritalStatus;

    private Integer dependentAmount;

    private LocalDate passportIssueDate;

    private String passportIssueBranch;

    private EmploymentDTO employment;

    private String account;

}
