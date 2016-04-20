package namoo.nara.castle.adapter.dto;

import namoo.nara.castle.domain.entity.Castle;

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
    private long  buildTime;


    public CastleFindDto() {
        //
    }

    public static CastleFindDto newInstance(Castle castle) {
        //
        CastleFindDto castleFindDto = new CastleFindDto();
        castleFindDto.setId(castle.getId());
        castleFindDto.setName(castle.getName());
        castleFindDto.setLocale(castle.getLocale());
        castleFindDto.setState(castle.getState().toString());
        castleFindDto.setBuildTime(castle.getBuildTime());

        return castleFindDto;
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

    public long getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(long buildTime) {
        this.buildTime = buildTime;
    }

}
