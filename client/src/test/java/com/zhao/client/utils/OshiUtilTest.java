package com.zhao.client.utils;


import com.zhao.client.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;

import java.sql.Timestamp;
import java.util.List;

@SpringBootTest
public class OshiUtilTest {
    oshi.SystemInfo si = new oshi.SystemInfo();
    HardwareAbstractionLayer hal = si.getHardware();
    OperatingSystem os = si.getOperatingSystem();
    Timestamp t = FormatUtil.getNowTime();

    private SystemInfo systemInfo;


    @Test
    public void memory()  {
        try {
            MemState memState = OshiUtil.memory(hal.getMemory());
            System.out.println(memState);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void cpu() {
        try {
            CpuState cpuState = OshiUtil.cpu(hal.getProcessor());
            System.out.println(cpuState);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void os() {
        try {
            systemInfo = OshiUtil.os(hal.getProcessor(), os);
            systemInfo.setCreateTime(t);
            System.out.println(systemInfo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void file() {
        try {
            List<DeskState> files = OshiUtil.file(t, os.getFileSystem());
            System.out.println(files);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getLoadState() {
        try {
            SysLoadState sysLoadState = OshiUtil.getLoadState(systemInfo, hal.getProcessor());
            System.out.println(sysLoadState);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getLoadPid() {
    }

    @Test
    public void net() {
    }


    @Test
    public void testAll() {
        memory();
        cpu();
        os();
        file();
        getLoadState();
        getLoadPid();
        net();
    }
}