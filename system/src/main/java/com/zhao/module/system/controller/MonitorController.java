package com.zhao.module.system.controller;

import com.zhao.module.system.domain.monitor.ResponseResult;
import com.zhao.module.system.domain.monitor.ServerInfo;
import com.zhao.module.system.domain.vo.ServerInfoVo;
import com.zhao.module.system.service.MonitorService;
import com.zhao.module.system.service.impl.MonitorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/monitor")
public class MonitorController {

    @Autowired
    private MonitorService monitorService = new MonitorServiceImpl();

    @GetMapping
    @PreAuthorize("hasAuthority('monitor:list')")
    public ResponseResult<ServerInfoVo> queryMonitor () {
        ServerInfo serverInfo = monitorService.getServers();
        ServerInfoVo serverInfoVo = new ServerInfoVo(serverInfo);
        return new ResponseResult<>(HttpStatus.OK.value(), serverInfoVo);
    }
}
