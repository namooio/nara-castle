package namoo.nara.castle.da.jpa.jpa.springdata;

import namoo.nara.castle.da.jpa.jpa.jpo.CastellanEmailJpo;
import namoo.nara.castle.da.jpa.jpa.jpo.CastellanEmailPk;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 16..
 */
public interface CastellanEmailRepository extends PagingAndSortingRepository<CastellanEmailJpo, CastellanEmailPk> {

    CastellanEmailJpo findByCastellanEmailPkCastellanIdAndPrimaryEmail(String castellanId, boolean primaryEmail);

    int countByCastellanEmailPkCastellanId(String castellanId);

    List<CastellanEmailJpo> findByCastellanEmailPkCastellanId(String castellanId);

    CastellanEmailJpo findByCastellanEmailPkEmailAndVerified(String email, boolean verified);

    void deleteByCastellanEmailPkCastellanId(String castellanId);
}
