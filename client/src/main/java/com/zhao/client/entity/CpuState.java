package com.zhao.client.entity;

import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.Date;



@Data
public class CpuState extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = -2913111613773445949L;


    /**
     * host名称
     */
    private String hostname;

    /**
     * 用户态的CPU时间（%）废弃
     */
    private String user;

    /**
     * cpu使用率
     */
    private Double sys;

    /**
     * 当前空闲率
     */
    private Double idle;

    /**
     * cpu当前等待率
     */
    private Double iowait;

    /**
     * 硬中断时间（%） 废弃
     */
    private String irq;

    /**
     * 软中断时间（%） 废弃
     */
    private String soft;

    /**
     * 添加时间
     * MM-dd hh:mm:ss
     */
    private String dateStr;

    /**
     * 创建时间
     */
    private Date createTime;

    public String getDateStr() {
        if (!StringUtils.isEmpty(dateStr) && dateStr.length() > 16) {
            return dateStr.substring(5);
        }
        return dateStr;
    }



}