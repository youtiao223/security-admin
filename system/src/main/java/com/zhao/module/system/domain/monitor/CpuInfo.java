package com.zhao.module.system.domain.monitor;

import lombok.Data;

import java.io.Serializable;

@Data
public class CpuInfo implements Serializable {
    private String name;
    /**
     * CPU 核心数
     */
    private int coreNumber;

    /**
     * 逻辑 CPU 数
     */
    private int logic;
    /**
     * CPU 占用率
     */
    private String used;

    /**
     * CPU 空闲率
     */
    private String idle;
}
