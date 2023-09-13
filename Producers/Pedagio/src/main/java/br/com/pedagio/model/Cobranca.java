package br.com.pedagio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cobranca implements Serializable {
    private String carPlate;
    private BigDecimal valueBilling;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime billingTime;
    private String carTicket;
}
