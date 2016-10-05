package namoo.nara.castle.domain.store.mapstore;

import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.LoginAccount;
import namoo.nara.castle.domain.entity.LoginIdType;
import namoo.nara.castle.domain.store.CastellanStore;

import java.util.*;

public class CastellanMapStore implements CastellanStore {
    //
    private Map<String, Castellan> castellanMap;

    public CastellanMapStore() {
        //
        this.castellanMap = new HashMap<>();
    }

    @Override
    public void create(Castellan castellan) {
        //
        this.castellanMap.put(castellan.getId(), castellan);
    }

    @Override
    public Castellan retrieve(String id) {
        //
        return this.castellanMap.get(id);
    }

    @Override
    public Castellan retrieveByLoginIdAndLoginIdType(String loginId, LoginIdType loginIdType) {
        //
        Collection<Castellan> castellens = this.castellanMap.values();
        for(Castellan castellan : castellens) {
            Set<LoginAccount> accounts = castellan.getLoginAccounts();
            for(LoginAccount account : accounts) {
                if (loginId.equals(account.getLoginId()) && loginIdType.equals(account.getLoginIdType())) {
                    return castellan;
                }
            }
        }
        return null;
    }

    @Override
    public List<Castellan> retrieveAll() {
        //
        return new ArrayList<>(this.castellanMap.values());
    }

    @Override
    public void update(Castellan castellan) {
        //
        this.castellanMap.put(castellan.getId(), castellan);
    }

    @Override
    public void delete(String id) {
        //
        this.castellanMap.remove(id);
    }
}
