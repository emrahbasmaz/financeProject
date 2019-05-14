package com.barstech.financeProject.models.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TxnReportResponse {

    private String status;
    private String message;
    private List<TxnReport> response;
}
