package com.zhao.module.system.domain.monitor;

import lombok.Data;

import java.io.Serializable;
@Data
public class SysInfo implements Serializable {
    /**
     * 操作系统
     */
    private String os;

    /**
     * 运行时间
     */
    private String day;

}
