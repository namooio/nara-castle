package namoo.nara.castle.da.jpa.springdata;

import namoo.nara.castle.da.jpa.jpo.CastellanNameJpo;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 16..
 */
public interface CastellanNameRepository extends PagingAndSortingRepository<CastellanNameJpo, String> {

    void deleteByCastellanOid(String castellanOid);

}
