package namoo.nara.castle.da.mongo.mdo;

import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.entity.OpenState;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Locale;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 6..
 */
@Document(collection = "Castle")
public class CastleMdo {
    //
    @Id
    private String id;
    private String name;                // Castellan's display name
    private Locale locale;
    private OpenState state;
    private long buildTime;

    public CastleMdo() {
        //
    }

    public static CastleMdo newInstance(Castle castle) {
        //
        CastleMdo castleMdo = new CastleMdo();
        castleMdo.setId(castle.getId());
        castleMdo.setName(castle.getName());
        castleMdo.setLocale(castle.getLocale());
        castleMdo.setState(castle.getState());
        castleMdo.setBuildTime(castle.getBuildTime());
        return castleMdo;
    }

    public Castle getDomain() {
        //
        Castle castle = Castle.newInstance(this.id, this.name, this.locale);
        castle.setBuildTime(this.buildTime);
        castle.setState(this.state);
        return castle;
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

    public OpenState getState() {
        return state;
    }

    public void setState(OpenState state) {
        this.state = state;
    }

    public long getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(long buildTime) {
        this.buildTime = buildTime;
    }

}
