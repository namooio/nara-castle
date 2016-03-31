package namoo.nara.castle.domain.service;

import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.CastellanName;
import namoo.nara.castle.domain.entity.CastleStatus;

/**
 * Created by kchuh@nextree.co.kr on 2016. 1. 29..
 */
public interface CastleService {

    /**
     * Register castellan. This operation is usually called when user sign in first time with oauth authentication.
     * Castle status will be opened.
     *
     * @param castellanOid
     */
    void registerCastellan(String castellanOid);

    /**
     * Create castellan. This operation is called when user request sign up.
     * Castle status will be suspended until user verify email.
     *
     * @param castellanOid
     * @param email
     */
    void registerCastellan(String castellanOid, String email);

    void removeCastellan(String castellanOid);

    Castellan findCastellan(String castellanOid);

    Castellan findCastellanByVerifiedEmail(String email);

    /**
     * Add castellan email.
     * Email is not verified yet.
     *
     * @param email
     * @param castellanOid
     */
    void addCastellanEmail(String email, String castellanOid);

    /**
     * Verify castellan email.
     * And add login account(email type).
     *
     * @param email
     * @param castellanOid
     */
    void verifyCastellanEmail(String email, String castellanOid);

    /**
     * Set castellan primary email.
     *
     * @param email
     * @param castellanOid
     */
    void setAsPrimaryEmail(String email, String castellanOid);

    void addName(CastellanName castellanName, String castellanOid);

    void removeCastellanEmail(String email, String castellanOid);

    void changeCastleStatus(CastleStatus status, String castellanOid);

    /**
     * Find castellan display name
     *
     * @param castellanOid
     * @return
     */
    String findCastellanDisplayName(String castellanOid);
}
