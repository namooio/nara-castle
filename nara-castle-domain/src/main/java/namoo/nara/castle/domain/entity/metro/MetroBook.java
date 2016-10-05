package namoo.nara.castle.domain.entity.metro;

import namoo.nara.share.domain.Aggregate;
import namoo.nara.share.domain.Entity;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class MetroBook extends Entity implements Aggregate {
    //
    private List<JoinedMetro> joinedMetros;

    public MetroBook(String id) {
        super(id);
    }

    public static MetroBook newInstance(String id) {
        //
        MetroBook metroBook = new MetroBook(id);
        metroBook.setJoinedMetros(new ArrayList<>());
        return metroBook;
    }

    public void addJoinedMetro(String metroId, String citizenId) {
        //
        JoinedMetro joinedMetro = new JoinedMetro();
        joinedMetro.setMetroId(metroId);
        joinedMetro.setCitizenId(citizenId);
        joinedMetro.setJoinedTime(ZonedDateTime.now());
        this.joinedMetros.add(joinedMetro);
    }

    public void removeJoinedMetro(String metroId, String citizenId) {
        //
        JoinedMetro joinedMetro = findJoinedMetro(metroId, citizenId);
        this.joinedMetros.remove(joinedMetro);
    }

    public JoinedMetro findJoinedMetro(String metroId, String citizenId) {
        //
        for(JoinedMetro joinedMetro : this.joinedMetros) {
            if (metroId.equals(joinedMetro.getMetroId()) && citizenId.equals(joinedMetro.getCitizenId())) {
                return joinedMetro;
            }
        }
        return null;
    }

    public List<JoinedMetro> getJoinedMetros() {
        return joinedMetros;
    }

    public void setJoinedMetros(List<JoinedMetro> joinedMetros) {
        this.joinedMetros = joinedMetros;
    }

    @Override
    public String toString() {
        return "MetroBook{" +
                "joinedMetros=" + joinedMetros +
                '}';
    }

    public static MetroBook getSample() {
        //
        MetroBook metroBook = MetroBook.newInstance("1");
        metroBook.addJoinedMetro("1", "1@1");
        metroBook.addJoinedMetro("2", "1@2");
        metroBook.addJoinedMetro("3", "1@3");
        return metroBook;
    }

    public static void main(String[] args) {
        //
        MetroBook sample = MetroBook.getSample();
        System.out.println(sample);
    }
}
