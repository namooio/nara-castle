package namoo.nara.castle.adapter.dto;

import namoo.nara.castle.adapter.dto.contact.NameBookDto;
import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.Castle;

import java.util.Date;
import java.util.Locale;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 7..
 */
public class CastleFindDto {
    //
    private String id;
    private String name;
    private Locale locale;
    private String state;
    private Date  buildTime;

    private CastellanFindDto castellan;
    // TODO : 뷰모델(DTO) 구조 고민 필요 -> 도메인과 동일하게 갈 것인가..
    private NameBookDto nameBookDto;


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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public Date getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(Date buildTime) {
        this.buildTime = buildTime;
    }

    public CastellanFindDto getCastellan() {
        return castellan;
    }

    public void setCastellan(CastellanFindDto castellan) {
        this.castellan = castellan;
    }

    public NameBookDto getNameBookDto() {
        return nameBookDto;
    }

    public void setNameBookDto(NameBookDto nameBookDto) {
        this.nameBookDto = nameBookDto;
    }
}
