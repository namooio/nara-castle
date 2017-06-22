package namoo.nara.castle.domain.logic;

import namoo.nara.castle.domain.context.CastleIdBuilder;
import namoo.nara.castle.domain.entity.*;
import namoo.nara.castle.domain.event.CastleCreated;
import namoo.nara.castle.domain.event.EnrollmentAdded;
import namoo.nara.castle.domain.proxy.CastleProxyLycler;
import namoo.nara.castle.domain.spec.CastleService;
import namoo.nara.castle.domain.spec.sdo.MetroEnrollmentCdo;
import namoo.nara.castle.domain.store.CastellanStore;
import namoo.nara.castle.domain.store.CastleStore;
import namoo.nara.castle.domain.store.CastleStoreLycler;
import namoo.nara.share.domain.NameValueList;
import namoo.nara.share.event.worker.EventService;

import java.util.List;
import java.util.NoSuchElementException;

public class CastleServiceLogic implements CastleService {
    //
    private CastleStore castleStore;
    private CastellanStore castellanStore;

    private EventService eventService;

    public CastleServiceLogic(CastleStoreLycler storeLycler, CastleProxyLycler proxyLycler) {
        //
        this.castleStore = storeLycler.requestCastleStore();
        this.castellanStore = storeLycler.requestCastellanStore();

        this.eventService = proxyLycler.requestEventService();
    }

    @Override
    public String enrollMetro(MetroEnrollmentCdo metroEnrollmentCdo) {
        //
        String metroId = metroEnrollmentCdo.getMetroId();
        String civilianId = metroEnrollmentCdo.getCivilianId();

        MetroEnrollment enrollment = new MetroEnrollment(
                metroEnrollmentCdo.getMetroId(),
                metroEnrollmentCdo.getCivilianId(),
                metroEnrollmentCdo.getName(),
                metroEnrollmentCdo.getEmail(),
                metroEnrollmentCdo.getZone());

        Castle castle = findCastleByEnrolledMetro(metroId, civilianId);

        if (castle == null) {
            long castleSequence = nextCastleSequence();
            String castleId = CastleIdBuilder.makeCastleId(castleSequence);
            enrollment.setCastleId(castleId);

            castle = new Castle(castleId, enrollment);
            castleStore.create(castle, enrollment);
            eventService.produce(new CastleCreated(castle, enrollment));
        } else {
            enrollment.setCastleId(castle.getId());
            castleStore.createEnrollment(enrollment);
            eventService.produce(new EnrollmentAdded(enrollment));
        }

        return castle.getId();
    }

    @Override
    public void withdrawMetro(String castleId, String metroId, String civilianId) {
        //
        Castle castle = findCastle(castleId);

        MetroEnrollment enrollment = castleStore.retrieveEnrollment(metroId, civilianId);
        if(enrollment == null) {
            return;
        }

        enrollment.withdraw();

        castleStore.updateEnrollment(enrollment);
    }

    @Override
    public Castle findCastle(String castleId) {
        //
        Castle castle =  castleStore.retrieve(castleId);
        if(castle == null) {
            throw new NoSuchElementException("castleId: " + castleId);
        }

        return castle;
    }

    @Override
    public Castle findCastleByEmail(String email) {
        //
        List<UnitPlate> unitPlates = castellanStore.retrieveUnitPlate(email, KeyAttr.Email);
        if(unitPlates.size() == 0) {
            throw new NoSuchElementException("email: "+ email);
        }

        if(unitPlates.size() > 1) {
            throw new IllegalArgumentException("Email dupulication: " + unitPlates.toString());
        }

        String castleId = unitPlates.get(0).getCastleId();
        Castle castle = findCastle(castleId);

        return castle;
    }

    @Override
    public Castle findCastleByPhone(String phone) {
        //
        List<UnitPlate> unitPlates = castellanStore.retrieveUnitPlate(phone, KeyAttr.Phone);
        if(unitPlates.size() == 0) {
            throw new NoSuchElementException("phone: "+ phone);
        }

        if(unitPlates.size() > 1) {
            throw new IllegalArgumentException("Phone dupulication: " + unitPlates.toString());
        }

        String castleId = unitPlates.get(0).getCastleId();
        Castle castle = findCastle(castleId);

        return castle;
    }

    @Override
    public Castle findCastleByEnrolledMetro(String metroId, String civilianId) {
        //
        MetroEnrollment enrollment = castleStore.retrieveEnrollment(metroId, civilianId);
        if(enrollment == null) {
            return null;
//            throw new NoSuchElementException(String.format("metroId:%s, civilianId:%s", metroId, civilianId));
        }

        String castleId = enrollment.getCastleId();
        Castle castle = castleStore.retrieve(castleId);

        return castle;
    }

    @Override
    public void modifyCastle(String castleId, NameValueList nameValues) {
        //
        Castle castle = findCastle(castleId);
        castle.setValues(nameValues);

        castleStore.update(castle);
    }

    @Override
    public Castellan findCastellan(String castleId) {
        //
        Castellan castellan = castellanStore.retrieve(castleId);
        if(castellan == null) {
            throw new NoSuchElementException("castleId: " + castleId);
        }

        return castellan;
    }

    @Override
    public boolean existsCastellan(String castleId) {
        //
        return castellanStore.exists(castleId);
    }

    @Override
    public void modifyCastellan(String castleId, NameValueList nameValues) {
        //
        Castellan castellan = findCastellan(castleId);
        castellan.setValues(nameValues);

        castellanStore.update(castellan);
    }

    @Override
    public IdentityPlate findIdentityPlate(String castleId) {
        //
        Castellan castellan = findCastellan(castleId);

        IdentityPlate identityPlate = new IdentityPlate(castellan);

        return identityPlate;
    }

    private long nextCastleSequence() {
        //
        CastleBook book = castleStore.retrieveCastleBookWithNextCastleSequence();
        return book.getSequence();
    }
}