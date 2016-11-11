package namoo.nara.castle.domain.entity;

import namoo.nara.castle.domain.context.CastleContext;
import namoo.nara.castle.domain.context.CastleIdBuilder;
import namoo.nara.share.domain.Aggregate;
import namoo.nara.share.domain.Entity;

import java.time.ZonedDateTime;
import java.util.Locale;

public class Castle extends Entity implements Aggregate {
    //
    private Castellan castellan;

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
        Castle castle = Castle.newInstance(1, "kchuh@nextree.co.kr", Locale.KOREA);
        return castle;
    }


    public static Castle newInstance(long castleSequence) {
        //
        CastleIdBuilder castleIdBuilder = CastleContext.getCastleIdBuilder();
        String castleId = castleIdBuilder.makeCastleId(castleSequence);
        Castle castle = new Castle(castleId);
        castle.setCastellan(Castellan.newInstance());
        castle.setBuiltTime(ZonedDateTime.now());

        return castle;
    }

    public static Castle newInstance(long castleSequence, String castellanEmail) {
        //
        Castle castle = newInstance(castleSequence);
        Castellan castellan = castle.getCastellan();
        castellan.addEmail(castellanEmail);
        return castle;
    }

    public static Castle newInstance(long castleSequence, String castellanEmail, Locale locale) {
        //
        Castle castle = newInstance(castleSequence, castellanEmail);
        castle.setLocale(locale);
        return castle;
    }

    public Castellan getCastellan() {
        return castellan;
    }

    public void setCastellan(Castellan castellan) {
        this.castellan = castellan;
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