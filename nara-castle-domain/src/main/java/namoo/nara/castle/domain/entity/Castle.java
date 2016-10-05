package namoo.nara.castle.domain.entity;

import namoo.nara.castle.domain.context.CastleContext;
import namoo.nara.castle.domain.context.CastleIdBuilder;
import namoo.nara.share.domain.Aggregate;
import namoo.nara.share.domain.Entity;

import java.time.ZonedDateTime;
import java.util.Locale;

public class Castle extends Entity implements Aggregate {
    //
    private Locale locale;
    private ZonedDateTime builtTime;

    private Castellan owner;

    public Castle(String id) {
        //
        super(id);
    }

    public static Castle newInstance(Locale locale, String displayName, String primaryEmail, long castleSequence) {
        //
        CastleIdBuilder castleIdBuilder = CastleContext.getCastleIdBuilder();
        String castleId = castleIdBuilder.makeCastleId(castleSequence);
        Castle castle = new Castle(castleId);
        castle.setLocale(locale);
        castle.setBuiltTime(ZonedDateTime.now());
        Castellan castellan = new Castellan(displayName, primaryEmail);
        castle.setOwner(castellan);

        return castle;
    }

    public void setCastellanDisplayName(String displayName) {
        //
        this.owner.setDisplayName(displayName);
    }

    public void setCastellanPhotoId(String photoId) {
        //
        this.owner.setPhotoId(photoId);
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

    public Castellan getOwner() {
        return owner;
    }

    public void setOwner(Castellan owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Castle{" +
                "locale=" + locale +
                ", builtTime=" + builtTime +
                ", owner=" + owner +
                '}';
    }

    public static Castle getSample() {
        //
        Castle castle = Castle.newInstance(Locale.KOREA, "Ki Chul", "kchuh@nextree.co.kr", 1);
        return castle;
    }

    public static void main(String[] args) {
        //
        Castle sample = Castle.getSample();
        System.out.println(sample);
    }


}