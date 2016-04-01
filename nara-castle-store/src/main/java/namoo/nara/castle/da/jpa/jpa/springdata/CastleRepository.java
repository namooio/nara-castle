package namoo.nara.castle.da.jpa.jpa.springdata;

import namoo.nara.castle.da.jpa.jpa.jpo.CastleJpo;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 11..
 */
public interface CastleRepository extends PagingAndSortingRepository<CastleJpo, String> {

    CastleJpo findByName(String name);

}
