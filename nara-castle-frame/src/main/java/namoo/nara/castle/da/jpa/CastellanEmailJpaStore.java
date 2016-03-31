package namoo.nara.castle.da.jpa;

import namoo.nara.castle.da.jpa.jpo.CastellanEmailId;
import namoo.nara.castle.da.jpa.jpo.CastellanEmailJpo;
import namoo.nara.castle.da.jpa.springdata.CastellanEmailRepository;
import namoo.nara.castle.domain.entity.CastellanEmail;
import namoo.nara.castle.domain.store.CastellanEmailStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 16..
 */
@Repository
public class CastellanEmailJpaStore implements CastellanEmailStore {

    @Autowired
    private CastellanEmailRepository castellanEmailRepository;

    @Override
    public void create(CastellanEmail castellanEmail, String castellanOid) {
        castellanEmailRepository.save(CastellanEmailJpo.create(castellanEmail, castellanOid));
    }

    @Override
    public boolean hasPrimaryEmail(String castellanOid) {
        CastellanEmailJpo castellanEmailJpo = castellanEmailRepository.findByCastellanEmailIdCastellanOidAndPrimaryEmail(castellanOid, true);
        return castellanEmailJpo != null;
    }

    @Override
    public CastellanEmail getPrimaryEmail(String castellanOid) {
        CastellanEmailJpo castellanEmailJpo = castellanEmailRepository.findByCastellanEmailIdCastellanOidAndPrimaryEmail(castellanOid, true);
        if (castellanEmailJpo == null) return null;
        return castellanEmailJpo.toDomain();
    }

    @Override
    public CastellanEmail retrieve(String email, String castellanOid) {
        CastellanEmailJpo castellanEmailJpo = castellanEmailRepository.findOne(new CastellanEmailId(email, castellanOid));
        return castellanEmailJpo.toDomain();
    }

    @Override
    public void updatePrimaryEmail(String email, String castellanOid, boolean primaryEmail) {
        CastellanEmailJpo castellanEmailJpo = castellanEmailRepository.findOne(new CastellanEmailId(email, castellanOid));
        castellanEmailJpo.setPrimaryEmail(primaryEmail);
        castellanEmailRepository.save(castellanEmailJpo);
    }

    @Override
    public String retrieveCastellanOidByVerifiedEmail(String email) {
        CastellanEmailJpo castellanEmailJpo = castellanEmailRepository.findByCastellanEmailIdEmailAndVerified(email, true);
        if (castellanEmailJpo == null) return null;
        return castellanEmailJpo.getCastellanEmailId().getCastellanOid();
    }

    @Override
    public int countCastellanEmail(String castellanOid) {
        return castellanEmailRepository.countByCastellanEmailIdCastellanOid(castellanOid);
    }

    @Override
    public void delete(String email, String castellanOid) {
        castellanEmailRepository.delete(new CastellanEmailId(email, castellanOid));
    }

    @Override
    public List<CastellanEmail> retrieveCastellanEmails(String castellanOid) {
        List<CastellanEmailJpo> castellanEmailJpos = castellanEmailRepository.findByCastellanEmailIdCastellanOid(castellanOid);
        if (castellanEmailJpos == null) return null;
        List<CastellanEmail> castellanEmails = new ArrayList<>(castellanEmailJpos.size());
        for(CastellanEmailJpo castellanEmailJpo : castellanEmailJpos) {
            castellanEmails.add(castellanEmailJpo.toDomain());
        }
        return castellanEmails;
    }

    @Override
    public void update(CastellanEmail castellanEmail, String castellanOid) {
        castellanEmailRepository.save(CastellanEmailJpo.create(castellanEmail, castellanOid));
    }

    @Override
    public boolean exist(String email, String castellanOid) {
        return castellanEmailRepository.exists(new CastellanEmailId(email, castellanOid));
    }

    @Override
    public boolean existVerifiedEmail(String email) {
        CastellanEmailJpo castellanEmailJpo = castellanEmailRepository.findByCastellanEmailIdEmailAndVerified(email, true);
        return castellanEmailJpo != null;
    }

    @Override
    public void deleteByCastellanOid(String castellanOid) {
        castellanEmailRepository.deleteByCastellanEmailIdCastellanOid(castellanOid);
    }
}
