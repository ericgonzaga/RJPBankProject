package com.equadis.bank.presentation.viewmodels;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferOperationModel {
    private Long customerIdFrom;
    private Long customerIdTo;
    private Long value;
}
