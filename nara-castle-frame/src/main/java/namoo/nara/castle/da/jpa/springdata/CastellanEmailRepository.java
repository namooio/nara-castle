package namoo.nara.castle.da.jpa.springdata;

import namoo.nara.castle.da.jpa.jpo.CastellanEmailId;
import namoo.nara.castle.da.jpa.jpo.CastellanEmailJpo;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 16..
 */
public interface CastellanEmailRepository extends PagingAndSortingRepository<CastellanEmailJpo, CastellanEmailId> {

    CastellanEmailJpo findByCastellanEmailIdCastellanIdAndPrimaryEmail(String castellanId, boolean primaryEmail);

    int countByCastellanEmailIdCastellanId(String castellanId);

    List<CastellanEmailJpo> findByCastellanEmailIdCastellanId(String castellanId);

    CastellanEmailJpo findByCastellanEmailIdEmailAndVerified(String email, boolean verified);

    void deleteByCastellanEmailIdCastellanId(String castellanId);
}
