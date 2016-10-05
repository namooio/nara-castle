package namoo.nara.castle.domain.service;

import namoo.nara.castle.domain.entity.metro.MetroBook;

public interface MetroService {
    //
    void createMetroBook(String id);
    MetroBook findMetroBook(String id);
    void removeMetroBook(String id);

    void addJoinedMetro(String metroId, String citizenId);
    void removeJoinedMetro(String metroId, String citizenId);
}
