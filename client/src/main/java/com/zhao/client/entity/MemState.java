package com.zhao.client.entity;

import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.Date;


@Data
public class MemState extends BaseEntity {


    /**
     *
     */
    private static final long serialVersionUID = -1412473355088780549L;


    /**
     * host名称
     */
    private String hostname;

    /**
     * 总计内存，M
     */
    private String total;

    /**
     * 已使用多少，M
     */
    private String used;

    /**
     * 未使用，M
     */
    private String free;

    /**
     * 已使用百分比%
     */
    private Double usePer;

    /**
     * 添加时间
     * yyyy-MM-dd hh:mm:ss
     */
    private String dateStr;

    /**
     * 创建时间
     */
    private Date createTime;




    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDateStr() {
        if (!StringUtils.isEmpty(dateStr) && dateStr.length() > 16) {
            return dateStr.substring(5);
        }
        return dateStr;
    }



}