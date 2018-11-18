package com.vivo.soft.excel.springexceldemo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author 张贵东
 * @Company TODO
 * @date 2018-11-17.
 * @Time 13:43
 * @Description 联想词白名单对应游戏实体对象
 * @Version 2.0.0
 */
@Entity
@Table(name = "T_ASSOCIATIONKEY_GAME")
public class AssociationKeyGame implements Serializable{

    private static final long serialVersionUID = 6352340281890861539L;
    @Id
    @GeneratedValue
    private Long id;
    private Long keyId;
    private String gameName;
    private Long gameId;
    private Timestamp fromDate;
    private Timestamp endDate;
    private String createBy;
    private Timestamp createTime;
    private String updateBy;
    private Timestamp updateTime;
    private Long priority;
    private Integer status;
    private String cause;

   /* @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "keyId")*/
    private transient AssociationKey associationKey;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getKeyId() {
        return keyId;
    }

    public void setKeyId(Long keyId) {
        this.keyId = keyId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
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

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    /*public AssociationKey getAssociationKey() {
        return associationKey;
    }

    public void setAssociationKey(AssociationKey associationKey) {
        this.associationKey = associationKey;
    }*/
}
