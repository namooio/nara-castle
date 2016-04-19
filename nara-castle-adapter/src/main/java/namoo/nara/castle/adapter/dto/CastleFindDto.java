package namoo.nara.castle.adapter.dto;

import namoo.nara.castle.domain.entity.Castle;

import java.util.Locale;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 7..
 */
public class CastleFindDto {
    //
    private String name;
    private String state;
    private Locale locale;

    public CastleFindDto() {
        //
    }

    public static CastleFindDto newInstance(Castle castle) {
        //
        CastleFindDto castleFindDto = new CastleFindDto();
        castleFindDto.setName(castle.getName());
        castleFindDto.setLocale(castle.getLocale());
        castleFindDto.setState(castle.getState().toString());
        return castleFindDto;
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

}
