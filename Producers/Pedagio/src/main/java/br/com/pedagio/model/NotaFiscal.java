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
public class NotaFiscal implements Serializable {
    private BigDecimal valueBilling;
    private String businessName;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime generateNFTime;
    private String carIdentification;
    private BigDecimal valueTax;
    private String  NFe;
    private String cpnj;
}
