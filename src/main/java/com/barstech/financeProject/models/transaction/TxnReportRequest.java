package com.barstech.financeProject.models.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TxnReportRequest {
    private String startDate;
    private String endDate;
}