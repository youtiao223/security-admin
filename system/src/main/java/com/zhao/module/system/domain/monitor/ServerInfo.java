package com.zhao.module.system.domain.monitor;

import lombok.Data;

import java.io.Serializable;

@Data
public class ServerInfo implements Serializable {
    private CpuInfo cpuInfo;

    private MemoryInfo memoryInfo;

    private SysInfo sysInfo;

    private DiskInfo diskInfo;

    private SwapInfo swapInfo;

    private String time;
}
