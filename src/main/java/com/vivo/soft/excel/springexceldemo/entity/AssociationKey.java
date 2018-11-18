package com.vivo.soft.excel.springexceldemo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author 张贵东
 * @Company TODO
 * @date 2018-11-17.
 * @Time 13:37
 * @Description 联想词白名单关键字实体对象
 * @Version 2.0.0
 */
@Entity
@Table(name = "T_ASSOCIATIONKEY")
public class AssociationKey implements Serializable{
    private static final long serialVersionUID = 2013016000704322778L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long gameId;
    private String keyName;
    private Timestamp fromDate;
    private Timestamp endDate;
    private String createBy;
    private Timestamp createTime;
    private String updateBy;
    private Timestamp updateTime;
    private String cause;
    private Integer status;

    //@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "associationKey")
    //@JoinColumn(name="key_id")//在book表增加一个外键列来实现一对多的单向关联
    private transient List<AssociationKeyGame> keyGames;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public Timestamp getFromDate() {
        return fromDate;
    }

    public void setFromDate(Timestamp fromDate) {
        this.fromDate = fromDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<AssociationKeyGame> getKeyGames() {
        return keyGames;
    }

    public void setKeyGames(List<AssociationKeyGame> keyGames) {
        this.keyGames = keyGames;
    }
}
