package namoo.nara.castle.da.mongo.document;

import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.entity.OpenState;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Locale;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 6..
 */
@Document(collection = "Castle")
public class CastleDoc {
    //
    @Id
    private String id;
    private String name;                // Castellan's display name
    private Locale locale;
    private String state;
    private long buildTime;

    public CastleDoc() {
        //
    }

    public static CastleDoc newInstance(Castle castle) {
        //
        CastleDoc castleDoc = new CastleDoc();
        castleDoc.setId(castle.getId());
        castleDoc.setName(castle.getName());
        castleDoc.setLocale(castle.getLocale());
        castleDoc.setState(castle.getState().name());
        castleDoc.setBuildTime(castle.getBuildTime());
        return castleDoc;
    }

    public Castle toDomain() {
        //
        Castle castle = new Castle();
        castle.setUsid(id);
        castle.setName(name);
        castle.setLocale(locale);
        castle.setBuildTime(buildTime);
        castle.setState(OpenState.valueOf(state));
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

}
