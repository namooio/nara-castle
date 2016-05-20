package namoo.nara.castle.rep.dto;

import namoo.nara.castle.front.dto.CastellanFindDto;

import java.util.Date;
import java.util.Locale;

/**
 * Created by hkkang on 2016-05-20.
 */
public class CastleFindDto {
    //
    private String id;
    private String name;
    private Locale locale;
    private String state;
    private long buildTime;

    private CastellanFindDto castellan;

    // TODO : Contacts 정보 추가해야함..

    public CastleFindDto() {
        //
    }


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Locale getLocale() {
        return locale;
    }
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    public long getBuildTime() {
        return buildTime;
    }
    public void setBuildTime(long buildTime) {
        this.buildTime = buildTime;
    }

    public CastellanFindDto getCastellan() {
        return castellan;
    }
    public void setCastellan(CastellanFindDto castellan) {
        this.castellan = castellan;
    }
}
