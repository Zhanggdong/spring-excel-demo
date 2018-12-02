package com.vivo.soft.excel.springexceldemo.dto;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author 风骚的GRE
 * @Descriptions TODO
 * @date 2018/12/2.
 */
public class AssociationKeyDto {
    private String keyName;
    private Timestamp fromDate;
    private Timestamp endDate;
    List<AssociationGameDto> games;

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

    public List<AssociationGameDto> getGames() {
        return games;
    }

    public void setGames(List<AssociationGameDto> games) {
        this.games = games;
    }
}
