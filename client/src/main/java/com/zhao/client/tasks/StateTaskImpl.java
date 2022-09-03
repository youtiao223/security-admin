package com.zhao.client.tasks;

import com.zhao.client.config.CommonConfig;
import com.zhao.client.entity.*;
import com.zhao.client.utils.FormatUtil;
import com.zhao.client.utils.OshiUtil;
import com.zhao.client.utils.RestUtil;
import com.zhao.client.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class StateTaskImpl implements StateTask{


    public static List<AppInfo> appInfoList = Collections.synchronizedList(new ArrayList<AppInfo>());

    @Autowired
    private RestUtil restUtil;
    @Autowired
    private CommonConfig commonConfig;

    private oshi.SystemInfo si = new oshi.SystemInfo();
    private HardwareAbstractionLayer hal = si.getHardware();
    private OperatingSystem os = si.getOperatingSystem();
    private Timestamp t = FormatUtil.getNowTime();


    private SystemInfo systemInfo = null;

    @Override
    public MemState getMemState() throws Exception {
        return OshiUtil.memory(hal.getMemory());
    }

    @Override
    public CpuState getCpuState() throws Exception {
        return OshiUtil.cpu(hal.getProcessor());
    }

    @Override
    public List<DeskState> getDeskState() throws Exception {
        return OshiUtil.file(t, os.getFileSystem());
    }

    @Override
    public NetIoState getNetIoState() throws Exception {
        return OshiUtil.net(hal);
    }


    // todo
    @Override
    public AppState getAppState() throws Exception {
        return null;
    }

    @Override
    public SysLoadState getSysLoadState() throws Exception {
        return OshiUtil.getLoadState(systemInfo, hal.getProcessor());
    }
}
