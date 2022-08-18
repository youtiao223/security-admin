package com.zhao.module.system.domain.vo;

import com.zhao.module.system.domain.monitor.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ServerInfoVo implements Serializable {
    private CpuInfo cpuInfo;

    private MemoryInfo memoryInfo;

    private SysInfo sysInfo;

    private DiskInfo diskInfo;

    private SwapInfo swapInfo;

    private String time;

    public ServerInfoVo(ServerInfo serverInfo) {
        this.cpuInfo = serverInfo.getCpuInfo();
        this.memoryInfo = serverInfo.getMemoryInfo();
        this.sysInfo = serverInfo.getSysInfo();
        this.diskInfo = serverInfo.getDiskInfo();
        this.time = serverInfo.getTime();
    }
}
