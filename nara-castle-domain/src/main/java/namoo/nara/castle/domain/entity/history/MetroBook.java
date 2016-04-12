package namoo.nara.castle.domain.entity.history;

import java.util.ArrayList;
import java.util.List;

public class MetroBook {
    //
    private List<ParticipantMetro> metroList = new ArrayList<>();

    public MetroBook() {
        //
    }

    public void clear() {
        //
        metroList.clear();
    }

    public void addMetro(ParticipantMetro metro) {
        //
        metroList.add(metro);
    }

    public void removeMetro(ParticipantMetro metro) {
        //
        metroList.remove(metro);
    }

    public List<ParticipantMetro> findAll() {
        //
        return metroList;
    }
}