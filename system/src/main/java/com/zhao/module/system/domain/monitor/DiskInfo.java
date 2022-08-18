package com.zhao.module.system.domain.monitor;

import lombok.Data;

import java.io.Serializable;

@Data
public class DiskInfo implements Serializable {
    private String total;
    private String available;
    private String used;
    private String usageRate;
}
