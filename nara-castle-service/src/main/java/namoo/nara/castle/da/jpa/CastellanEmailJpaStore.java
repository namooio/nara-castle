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
    public void create(CastellanEmail castellanEmail, String castellanId) {
        castellanEmailRepository.save(CastellanEmailJpo.create(castellanEmail, castellanId));
    }

    @Override
    public boolean hasPrimaryEmail(String castellanId) {
        CastellanEmailJpo castellanEmailJpo = castellanEmailRepository.findByCastellanEmailIdCastellanIdAndPrimaryEmail(castellanId, true);
        return castellanEmailJpo != null;
    }

    @Override
    public CastellanEmail getPrimaryEmail(String castellanId) {
        CastellanEmailJpo castellanEmailJpo = castellanEmailRepository.findByCastellanEmailIdCastellanIdAndPrimaryEmail(castellanId, true);
        if (castellanEmailJpo == null) return null;
        return castellanEmailJpo.toDomain();
    }

    @Override
    public CastellanEmail retrieve(String email, String castellanId) {
        CastellanEmailJpo castellanEmailJpo = castellanEmailRepository.findOne(new CastellanEmailId(email, castellanId));
        return castellanEmailJpo.toDomain();
    }

    @Override
    public void updatePrimaryEmail(String email, String castellanId, boolean primaryEmail) {
        CastellanEmailJpo castellanEmailJpo = castellanEmailRepository.findOne(new CastellanEmailId(email, castellanId));
        castellanEmailJpo.setPrimaryEmail(primaryEmail);
        castellanEmailRepository.save(castellanEmailJpo);
    }

    @Override
    public String retrieveCastellanIdByVerifiedEmail(String email) {
        CastellanEmailJpo castellanEmailJpo = castellanEmailRepository.findByCastellanEmailIdEmailAndVerified(email, true);
        if (castellanEmailJpo == null) return null;
        return castellanEmailJpo.getCastellanEmailId().getCastellanId();
    }

    @Override
    public int countCastellanEmail(String castellanId) {
        return castellanEmailRepository.countByCastellanEmailIdCastellanId(castellanId);
    }

    @Override
    public void delete(String email, String castellanId) {
        castellanEmailRepository.delete(new CastellanEmailId(email, castellanId));
    }

    @Override
    public List<CastellanEmail> retrieveCastellanEmails(String castellanId) {
        List<CastellanEmailJpo> castellanEmailJpos = castellanEmailRepository.findByCastellanEmailIdCastellanId(castellanId);
        if (castellanEmailJpos == null) return null;
        List<CastellanEmail> castellanEmails = new ArrayList<>(castellanEmailJpos.size());
        for(CastellanEmailJpo castellanEmailJpo : castellanEmailJpos) {
            castellanEmails.add(castellanEmailJpo.toDomain());
        }
        return castellanEmails;
    }

    @Override
    public void update(CastellanEmail castellanEmail, String castellanId) {
        castellanEmailRepository.save(CastellanEmailJpo.create(castellanEmail, castellanId));
    }

    @Override
    public boolean exist(String email, String castellanId) {
        return castellanEmailRepository.exists(new CastellanEmailId(email, castellanId));
    }

    @Override
    public boolean existVerifiedEmail(String email) {
        CastellanEmailJpo castellanEmailJpo = castellanEmailRepository.findByCastellanEmailIdEmailAndVerified(email, true);
        return castellanEmailJpo != null;
    }

    @Override
    public void deleteByCastellanId(String castellanId) {
        castellanEmailRepository.deleteByCastellanEmailIdCastellanId(castellanId);
    }
}
