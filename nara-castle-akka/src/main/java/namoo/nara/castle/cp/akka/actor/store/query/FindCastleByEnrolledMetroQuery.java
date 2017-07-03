package namoo.nara.castle.cp.akka.actor.store.query;

import namoo.nara.share.domain.protocol.NaraQuery;

public class FindCastleByEnrolledMetroQuery implements NaraQuery {
    //
    private String metroId;
    private String civilianId;

    public FindCastleByEnrolledMetroQuery(String metroId, String civilianId) {
        //
        this.metroId = metroId;
        this.civilianId = civilianId;
    }

    public String getMetroId() {
        return metroId;
    }

    public String getCivilianId() {
        return civilianId;
    }
}
