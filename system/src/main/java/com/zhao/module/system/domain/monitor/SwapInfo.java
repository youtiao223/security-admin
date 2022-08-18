package com.zhao.module.system.domain.monitor;

import lombok.Data;

import java.io.Serializable;

@Data
public class SwapInfo implements Serializable {
    private String total;
    private String used;
    private String available;
    private String usageRate;
}
