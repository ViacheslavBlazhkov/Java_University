package com.example.lab_9_kn_2_232_blazhkov_viacheslav;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class TransferRequest {
    @NotNull
    @Min(1)
    @Max(100000)
    private long amount;
    @NotNull
    @Min(1)
    @Max(31)
    private int day;
    @NotNull
    @Min(1)
    @Max(12)
    private int month;
    @NotNull
    @Min(1999)
    @Max(2025)
    private int year;
    @NotNull
    private long fromUserId;
    @NotNull
    private long toUserId;

}
