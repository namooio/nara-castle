package namoo.nara.castle.da.mongo.document.history;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 7..
 */
public class MetroBookDoc {

    private List<ParticipantMetroDoc> metroList;

    public MetroBookDoc() {
        //
    }

    public static MetroBookDoc newInstance(MetroBook metroBook) {
        //
        MetroBookDoc metroBookDoc = new MetroBookDoc();
        List<ParticipantMetro> metroList = metroBook.findAll();
        if (metroList != null) {
            for(ParticipantMetro participantMetro : metroList) {
                metroBookDoc.addParticipantMetro(ParticipantMetroDoc.newInstance(participantMetro));
            }
        }

        return metroBookDoc;
    }

    public MetroBook toDomain() {
        //
        MetroBook metroBook = new MetroBook();
        if (metroList != null) {
            for(ParticipantMetroDoc participantMetroDoc : metroList) {
                metroBook.addMetro(participantMetroDoc.toDomain());
            }
        }

        return metroBook;
    }

    public void addParticipantMetro(ParticipantMetroDoc participantMetro) {
        //
        if (metroList == null) metroList = new ArrayList<>();
        metroList.add(participantMetro);
    }

    public List<ParticipantMetroDoc> getMetroList() {
        return metroList;
    }

    public void setMetroList(List<ParticipantMetroDoc> metroList) {
        this.metroList = metroList;
    }
}
