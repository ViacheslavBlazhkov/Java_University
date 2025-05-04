package com.example.lab_9_kn_2_232_blazhkov_viacheslav;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class TransactionReportRequest {
    @NotNull
    private long userId;
    @NotNull
    private String type;

}
