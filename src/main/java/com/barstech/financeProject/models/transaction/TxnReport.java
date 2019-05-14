package com.barstech.financeProject.models.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TxnReport {

    private Integer count;
    private Long total;
    private String currency;
}