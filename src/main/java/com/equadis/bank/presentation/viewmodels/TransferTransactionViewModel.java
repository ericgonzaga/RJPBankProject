package com.equadis.bank.presentation.viewmodels;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferTransactionViewModel {
    private Long accountIdFrom;
    private Long accountIdTo;
    private Long value;
}
