package namoo.nara.castle.da.mongo.mdo.history;

import namoo.nara.castle.domain.entity.history.MetroBook;
import namoo.nara.castle.domain.entity.history.ParticipantMetro;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 7..
 */
public class MetroBookMdo {

    private List<ParticipantMetroMdo> metroMdoList;

    public MetroBookMdo() {
        //
    }

    public static MetroBookMdo newInstance(MetroBook metroBook) {
        //
        MetroBookMdo metroBookMdo = new MetroBookMdo();
        List<ParticipantMetro> metroList = metroBook.findAll();
        if (metroList != null) {
            for(ParticipantMetro participantMetro : metroList) {
                metroBookMdo.addParticipantMetroMdo(ParticipantMetroMdo.newInstance(participantMetro));
            }
        }

        return metroBookMdo;
    }

    public MetroBook toDomain() {
        //
        MetroBook metroBook = new MetroBook();
        if (metroMdoList != null) {
            for(ParticipantMetroMdo participantMetroMdo : metroMdoList) {
                metroBook.addMetro(participantMetroMdo.toDomain());
            }
        }

        return metroBook;
    }

    public void addParticipantMetroMdo(ParticipantMetroMdo participantMetroMdo) {
        //
        if (metroMdoList == null) metroMdoList = new ArrayList<>();
        metroMdoList.add(participantMetroMdo);
    }

    public List<ParticipantMetroMdo> getMetroMdoList() {
        return metroMdoList;
    }

    public void setMetroMdoList(List<ParticipantMetroMdo> metroMdoList) {
        this.metroMdoList = metroMdoList;
    }
}
