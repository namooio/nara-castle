package namoo.nara.castle.domain.entity;

import namoo.nara.castle.domain.context.CastleContext;
import namoo.nara.castle.domain.context.CastleIdBuilder;
import namoo.nara.share.domain.Aggregate;
import namoo.nara.share.domain.Entity;

import java.time.ZonedDateTime;
import java.util.Locale;

public class Castle extends Entity implements Aggregate {
    //
    private Locale locale; // optional
    private ZonedDateTime builtTime;

    public Castle(String id) {
        //
        super(id);
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
        Castle castle = Castle.newInstance(1, Locale.KOREA);
        return castle;
    }


    public static Castle newInstance(long castleSequence) {
        //
        CastleIdBuilder castleIdBuilder = CastleContext.getCastleIdBuilder();
        String castleId = castleIdBuilder.makeCastleId(castleSequence);
        Castle castle = new Castle(castleId);
        castle.setBuiltTime(ZonedDateTime.now());
        return castle;
    }

    public static Castle newInstance(long castleSequence, Locale locale) {
        //
        Castle castle = newInstance(castleSequence);
        castle.setLocale(locale);
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

    public static void main(String[] args) {
        //
        System.out.println(Castle.getSample());
    }

}