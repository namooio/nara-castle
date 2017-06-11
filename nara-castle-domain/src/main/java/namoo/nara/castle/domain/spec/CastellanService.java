package namoo.nara.castle.domain.spec;

import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.share.domain.NameValueList;

public interface CastellanService {
    //
    Castellan findCastellan(String castleId);
    void modifyCastellan(NameValueList nameValues);
}