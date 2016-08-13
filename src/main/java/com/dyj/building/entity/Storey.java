package com.dyj.building.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 楼层。实体类。
 *
 * @author 林久久, created at 2016/8/10.
 */
@SuppressWarnings("unused")
@Entity
@Table(name = "T_STOREY")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "building", "units"})
public class Storey implements Serializable {

    // 表ID，UUID。
    private String tid;
    // 楼层名称。
    private String storeyName;
    // 楼层号。
    private Integer floorNum;
    // 楼层的结构图。
    private String structChartUrl;
    // 备注。
    private String remark;
    // 状态。0：禁用；1：启用。
    private Integer status = 0;
    // 创建时间。
    private Date createTime = new Date();
    // 创建者。
    private String creatorId = "sys";
    // 所属建筑。
    private Building building;
    // 拥有的单元。
    private List<Unit> units = new ArrayList<>();

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

    @Column(name = "STOREY_NAME", length = 200)
    public String getStoreyName() {
        return storeyName;
    }

    public void setStoreyName(String storeyName) {
        this.storeyName = storeyName;
    }

    @Column(name = "FLOOR_NUM", nullable = false)
    public Integer getFloorNum() {
        return floorNum;
    }

    public void setFloorNum(Integer floorNum) {
        this.floorNum = floorNum;
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

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "BUILDING_ID")
    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    @OneToMany(mappedBy = "storey", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @LazyCollection(value = LazyCollectionOption.EXTRA)
    public List<Unit> getUnits() {
        return units;
    }

    public void setUnits(List<Unit> units) {
        this.units = units;
    }
}
