package com.vivo.soft.excel.springexceldemo.dto;

import com.vivo.soft.excel.springexceldemo.entity.AssociationKey;

import java.sql.Timestamp;

/**
 * @author 风骚的GRE
 * @Descriptions TODO
 * @date 2018/12/2.
 */
public class AssociationGameDto extends AssociationKeyDto{
    private Long keyId;
    private Long gameId;
    private String gameName;
    private Long priority;
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

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }
}
