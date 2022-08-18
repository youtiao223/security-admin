package com.zhao.module.system.service.impl;

import com.zhao.module.system.domain.monitor.ServerInfo;
import com.zhao.module.system.service.MonitorService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MonitorServiceImplTest {

    private MonitorService monitorService = new MonitorServiceImpl();

    @Test
    void getServers() {
        ServerInfo serversInfo = monitorService.getServers();
        System.out.println(serversInfo);
    }
}