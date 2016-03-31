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
     * @param castellanId
     */
    void registerCastellan(String castellanId);

    /**
     * Create castellan. This operation is called when user request sign up.
     * Castle status will be suspended until user verify email.
     *
     * @param castellanId
     * @param email
     */
    void registerCastellan(String castellanId, String email);

    void removeCastellan(String castellanId);

    Castellan findCastellan(String castellanId);

    Castellan findCastellanByVerifiedEmail(String email);

    /**
     * Add castellan email.
     * Email is not verified yet.
     *
     * @param email
     * @param castellanId
     */
    void addCastellanEmail(String email, String castellanId);

    /**
     * Verify castellan email.
     * And add login account(email type).
     *
     * @param email
     * @param castellanId
     */
    void verifyCastellanEmail(String email, String castellanId);

    /**
     * Set castellan primary email.
     *
     * @param email
     * @param castellanId
     */
    void setAsPrimaryEmail(String email, String castellanId);

    void addName(CastellanName castellanName, String castellanId);

    void removeCastellanEmail(String email, String castellanId);

    void changeCastleStatus(CastleStatus status, String castellanId);

    /**
     * Find castellan display name
     *
     * @param castellanId
     * @return
     */
    String findCastellanDisplayName(String castellanId);
}
