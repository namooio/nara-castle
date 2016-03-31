package namoo.nara.castle.da.jpa.springdata;

import namoo.nara.castle.da.jpa.jpo.CastellanJpo;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 16..
 */
public interface CastellanRepository extends PagingAndSortingRepository<CastellanJpo, String> {
}
