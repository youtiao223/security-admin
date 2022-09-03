package com.zhao.client.tasks;

import com.zhao.client.entity.*;

import java.util.List;

/**
 * @author zhao
 */
public interface StateTask {
    /**
     * 获取内存信息
     * @return
     */
    MemState getMemState() throws Exception;

    /**
     * 获取CPU信息
     * @return
     */
    CpuState getCpuState() throws Exception;

    /**
     * 获取磁盘信息
     * @return
     */
    List<DeskState> getDeskState() throws Exception;

    /**
     * 获取网络IO信息
     * @return
     */
    NetIoState getNetIoState() throws Exception;


    /**
     * 获取进程信息
     * @return
     */
    AppState getAppState() throws Exception;

    /**
     * 获取系统负载信息
     * @return
     */
    SysLoadState getSysLoadState() throws Exception;
}
