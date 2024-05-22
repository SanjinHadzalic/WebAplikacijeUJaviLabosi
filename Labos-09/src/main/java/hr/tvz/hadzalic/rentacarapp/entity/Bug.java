package hr.tvz.hadzalic.rentacarapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Bug {
    private Long id;
    private String issueName;
    private String issueMessage;
}
