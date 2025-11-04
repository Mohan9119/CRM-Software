package com.crm.backend.dto;

import com.crm.backend.model.Sale;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class SaleDto {
    private Long id;
    private Long customerId;
    private BigDecimal amount;
    private Sale.Status status;
    private LocalDate date;
    private Long salesRepId;
}