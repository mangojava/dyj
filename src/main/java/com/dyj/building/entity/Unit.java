package com.dyj.building.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 单元。实体类。
 *
 * @author 林久久, created at 2016/8/10.
 */
@SuppressWarnings("unused")
@Entity
@Table(name = "T_UNIT")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "storey"})
public class Unit implements Serializable {

    // 表ID，UUID。
    private String tid;
    // 单元名称。
    private String unitName;
    // 单元的结构图。
    private String structChartUrl;
    // 备注。
    private String remark;
    // 状态。0：禁用；1：启用。
    private Integer status = 0;
    // 创建时间。
    private Date createTime = new Date();
    // 创建者。
    private String creatorId = "sys";
    // 所属的楼层。
    private Storey storey;

    /* setter 和 getter 方法 */
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "TID", unique = true, updatable = false, length = 36)
    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    @Column(name = "UNIT_NAME", length = 200)
    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    @Column(name = "STRUCT_CHART_URL", nullable = false)
    public String getStructChartUrl() {
        return structChartUrl;
    }

    public void setStructChartUrl(String structChartUrl) {
        this.structChartUrl = structChartUrl;
    }

    @Column(name = "REMARK", length = 600)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "STATUS", nullable = false, length = 2)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME", nullable = false, updatable = false)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "CREATOR_ID", nullable = false, length = 36)
    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "STOREY_ID")
    public Storey getStorey() {
        return storey;
    }

    public void setStorey(Storey storey) {
        this.storey = storey;
    }
}
