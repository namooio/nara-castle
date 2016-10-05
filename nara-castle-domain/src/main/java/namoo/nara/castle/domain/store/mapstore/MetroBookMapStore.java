package namoo.nara.castle.domain.store.mapstore;

import namoo.nara.castle.domain.entity.metro.MetroBook;
import namoo.nara.castle.domain.store.MetroBookStore;

import java.util.HashMap;
import java.util.Map;

public class MetroBookMapStore implements MetroBookStore {
    //
    private Map<String, MetroBook> metroBookMap;

    public MetroBookMapStore() {
        //
        this.metroBookMap = new HashMap<>();
    }

    @Override
    public void create(MetroBook metroBook) {
        //
        this.metroBookMap.put(metroBook.getId(), metroBook);
    }

    @Override
    public MetroBook retrieve(String id) {
        //
        return metroBookMap.get(id);
    }

    @Override
    public void update(MetroBook metroBook) {
        //
        this.metroBookMap.put(metroBook.getId(), metroBook);
    }

    @Override
    public void delete(String id) {
        //
        this.metroBookMap.remove(id);
    }
}
