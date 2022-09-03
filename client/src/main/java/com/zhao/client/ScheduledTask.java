package com.zhao.client;

import com.zhao.client.entity.*;
import com.zhao.client.tasks.StateTask;
import com.zhao.client.tasks.StateTaskImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhao
 */
@Component
public class ScheduledTask {
    private Logger logger = LoggerFactory.getLogger(ScheduledTask.class);

    /**
     * 线程池
     */
    static ThreadPoolExecutor executor = new ThreadPoolExecutor(
            5,
            10,
            2,
            TimeUnit.MINUTES,
            new LinkedBlockingDeque<>());

    /**
     * 获取主机信息任务
     */
    private StateTask stateTask = new StateTaskImpl();

    /**
     * 定时发送客户端数据
     * 定时任务
     *      * initialDelay : 程序启动后延迟多久开始执行
     *      * fixedRate : 之后的间隔时间
     */

    @Scheduled(initialDelay = 5 * 1000L, fixedRate = 10 * 1000)
    public void sendData() {

    }


    private void doStateTask() {

        try {
            CpuState cpuState = stateTask.getCpuState();
        } catch (Exception e) {
            logger.error("获取cpu信息出错");
        }

        try {
            MemState memState = stateTask.getMemState();
        } catch (Exception e) {
            logger.error("获取内存信息出错");
        }

        try {
            List<DeskState> deskState = stateTask.getDeskState();
        } catch (Exception e) {
            logger.error("获取磁盘信息出错");
        }


        try {
            SysLoadState sysLoadState = stateTask.getSysLoadState();
        } catch (Exception e) {
            logger.error("获取系统负载出错");
        }

        try {
            NetIoState netIoState = stateTask.getNetIoState();
        } catch (Exception e) {
            logger.error("获取网络IO出错");
        }

        try {
            AppState appState = stateTask.getAppState();
        } catch (Exception e) {
            logger.error("获取进程信息出错");
        }

    }



}
