package com.zhao.module.system.service.impl;

import cn.hutool.core.date.BetweenFormatter;
import cn.hutool.core.date.DateUtil;
import com.zhao.module.system.domain.monitor.*;
import com.zhao.module.system.service.MonitorService;

import com.zhao.utils.Constant;
import com.zhao.utils.FileUtil;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.VirtualMemory;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;
import oshi.util.FormatUtil;
import oshi.util.Util;

import java.lang.management.ManagementFactory;
import java.text.DecimalFormat;
import java.util.*;

@Service
public class MonitorServiceImpl implements MonitorService {

    private final DecimalFormat df = new DecimalFormat("0.00");

    @Override
    public ServerInfo getServers() {
        ServerInfo serverInfo = new ServerInfo();
        try {
            SystemInfo si = new SystemInfo();
            OperatingSystem os = si.getOperatingSystem();
            HardwareAbstractionLayer hal = si.getHardware();
            // 系统信息
            serverInfo.setSysInfo(getSystemInfo(os));
            // cpu 信息
            serverInfo.setCpuInfo(getCpuInfo(hal.getProcessor()));
            // 内存信息
            serverInfo.setMemoryInfo(getMemoryInfo(hal.getMemory()));
            // 交换区信息
            serverInfo.setSwapInfo(getSwapInfo(hal.getMemory()));
            // 磁盘
            serverInfo.setDiskInfo(getDiskInfo(os));
            serverInfo.setTime(DateUtil.format(new Date(), "HH:mm:ss"));
        } catch (Exception e) {
            e.printStackTrace();
        }


        return serverInfo;
    }

    private CpuInfo getCpuInfo(CentralProcessor processor) {
        //Map<String,Object> cpuInfo = new LinkedHashMap<>();

        CpuInfo cpuInfo = new CpuInfo();

        // CPU信息
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        // 默认等待300毫秒...
        long time = 300;
        Util.sleep(time);
        long[] ticks = processor.getSystemCpuLoadTicks();
        while (Arrays.toString(prevTicks).equals(Arrays.toString(ticks)) && time < 1000){
            time += 25;
            Util.sleep(25);
            ticks = processor.getSystemCpuLoadTicks();
        }
        long user = ticks[CentralProcessor.TickType.USER.getIndex()] - prevTicks[CentralProcessor.TickType.USER.getIndex()];
        long nice = ticks[CentralProcessor.TickType.NICE.getIndex()] - prevTicks[CentralProcessor.TickType.NICE.getIndex()];
        long sys = ticks[CentralProcessor.TickType.SYSTEM.getIndex()] - prevTicks[CentralProcessor.TickType.SYSTEM.getIndex()];
        long idle = ticks[CentralProcessor.TickType.IDLE.getIndex()] - prevTicks[CentralProcessor.TickType.IDLE.getIndex()];
        long iowait = ticks[CentralProcessor.TickType.IOWAIT.getIndex()] - prevTicks[CentralProcessor.TickType.IOWAIT.getIndex()];
        long irq = ticks[CentralProcessor.TickType.IRQ.getIndex()] - prevTicks[CentralProcessor.TickType.IRQ.getIndex()];
        long softirq = ticks[CentralProcessor.TickType.SOFTIRQ.getIndex()] - prevTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()];
        long steal = ticks[CentralProcessor.TickType.STEAL.getIndex()] - prevTicks[CentralProcessor.TickType.STEAL.getIndex()];
        long totalCpu = user + nice + sys + idle + iowait + irq + softirq + steal;

        cpuInfo.setName(processor.getProcessorIdentifier().getName());
        cpuInfo.setCoreNumber(processor.getPhysicalPackageCount());
        cpuInfo.setUsed(df.format(100d * user / totalCpu + 100d * sys / totalCpu));
        cpuInfo.setIdle(df.format(100d * idle / totalCpu));

        return cpuInfo;
    }

    private DiskInfo getDiskInfo(OperatingSystem os) {
        DiskInfo diskInfo = new DiskInfo();
        FileSystem fileSystem = os.getFileSystem();
        List<OSFileStore> fsArray = fileSystem.getFileStores();
        String osName = System.getProperty("os.name");
        long available = 0, total = 0;
        for (OSFileStore fs : fsArray){
            // windows 需要将所有磁盘分区累加，linux 和 mac 直接累加会出现磁盘重复的问题，待修复
            if(osName.toLowerCase().startsWith(Constant.WIN)) {
                available += fs.getUsableSpace();
                total += fs.getTotalSpace();
            } else {
                available = fs.getUsableSpace();
                total = fs.getTotalSpace();
                break;
            }
        }
        long used = total - available;
        diskInfo.setTotal(total > 0 ? FileUtil.getSize(total) : "?");
        diskInfo.setAvailable(FileUtil.getSize(available));
        diskInfo.setUsed(FileUtil.getSize(used));
        if(total != 0){
            diskInfo.setUsageRate(df.format(used/(double)total * 100));
        } else {
            diskInfo.setUsageRate("0");
        }
        return diskInfo;
    }

    private SwapInfo getSwapInfo(GlobalMemory memory) {
        SwapInfo swapInfo = new SwapInfo();
        VirtualMemory virtualMemory = memory.getVirtualMemory();
        long total = virtualMemory.getSwapTotal();
        long used = virtualMemory.getSwapUsed();
        swapInfo.setTotal(FormatUtil.formatBytes(total));
        swapInfo.setUsed(FormatUtil.formatBytes(used));
        swapInfo.setAvailable(FormatUtil.formatBytes(total - used));
        if(used == 0){
            swapInfo.setUsageRate("0");
        } else {
            swapInfo.setUsageRate(df.format(used/(double)total * 100));
        }
        return swapInfo;
    }

    private MemoryInfo getMemoryInfo(GlobalMemory memory) {
        MemoryInfo memoryInfo = new MemoryInfo();
        memoryInfo.setTotal(FormatUtil.formatBytes(memory.getTotal()));
        memoryInfo.setAvailable(FormatUtil.formatBytes(memory.getAvailable()));
        memoryInfo.setUsed(FormatUtil.formatBytes(memory.getTotal() - memory.getAvailable()));
        memoryInfo.setUsageRate(df.format((memory.getTotal() - memory.getAvailable())/(double)memory.getTotal() * 100));
        return memoryInfo;
    }

    private SysInfo getSystemInfo(OperatingSystem os) {
        SysInfo systemInfo = new SysInfo();
        // jvm 运行时间
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        Date date = new Date(time);
        // 计算项目运行时间
        String formatBetween = DateUtil.formatBetween(date, new Date(), BetweenFormatter.Level.HOUR);
        // 系统信息
        systemInfo.setOs(os.toString());
        systemInfo.setDay(formatBetween);
        return systemInfo;
    }

    

}
