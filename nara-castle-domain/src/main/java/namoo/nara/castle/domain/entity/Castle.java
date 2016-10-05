package namoo.nara.castle.domain.entity;

import namoo.nara.castle.domain.context.CastleContext;
import namoo.nara.castle.domain.context.CastleIdBuilder;
import namoo.nara.share.domain.Entity;

import java.time.ZonedDateTime;
import java.util.Locale;

public class Castle extends Entity {
    //
    private Locale locale;
    private ZonedDateTime builtTime;

    public Castle(String id) {
        //
        super(id);
    }

    public static Castle newInstance(Locale locale, long castleSequence) {
        //
        CastleIdBuilder castleIdBuilder = CastleContext.getCastleIdBuilder();
        String castleId = castleIdBuilder.makeCastleId(castleSequence);
        Castle castle = new Castle(castleId);
        castle.setLocale(locale);
        castle.setBuiltTime(ZonedDateTime.now());
        return castle;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public ZonedDateTime getBuiltTime() {
        return builtTime;
    }

    public void setBuiltTime(ZonedDateTime builtTime) {
        this.builtTime = builtTime;
    }

    @Override
    public String toString() {
        return "Castle{" +
                "locale=" + locale +
                ", builtTime=" + builtTime +
                '}';
    }

    public static Castle getSample() {
        //
        Castle castle = Castle.newInstance(Locale.KOREA, 1);
        return castle;
    }

    public static void main(String[] args) {
        //
        Castle sample = Castle.getSample();
        System.out.println(sample);
    }

}