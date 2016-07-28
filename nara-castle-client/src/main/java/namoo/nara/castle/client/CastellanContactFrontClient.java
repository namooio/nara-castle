package namoo.nara.castle.client;

import namoo.nara.castle.front.dto.contact.*;
import namoo.nara.castle.front.CastellanContactFrontService;
import namoo.nara.share.restclient.NaraRestClient;
import namoo.nara.share.restclient.RequestBuilder;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
public class CastellanContactFrontClient implements CastellanContactFrontService {
    //
    private NaraRestClient naraRestClient;

    public CastellanContactFrontClient(NaraRestClient naraRestClient) {
        //
        this.naraRestClient = naraRestClient;
    }

    @Override
    public void attachNameBook(String castleId, NameBookDto nameBookDto) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTELLAN_CONTACT_ATTACH_NAME_BOOK)
                        .addPathParam("id", castleId)
                        .setRequestDto(nameBookDto)
        );
    }

    @Override
    public void detachNameBook(String castleId) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTELLAN_CONTACT_DETACH_NAME_BOOK)
                        .addPathParam("id", castleId)
        );
    }

    @Override
    public NameBookDto findNameBook(String castleId) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTELLAN_CONTACT_FIND_NAME_BOOK)
                        .addPathParam("id", castleId)
                        .setResponseType(NameBookDto.class)
        );
    }

    @Override
    public void attachEmailBook(String castleId, EmailBookDto emailBookDto) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTELLAN_CONTACT_ATTACH_EMAIL_BOOK)
                        .addPathParam("id", castleId)
                        .setRequestDto(emailBookDto)
        );
    }

    @Override
    public void detachEmailBook(String castleId) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTELLAN_CONTACT_DETACH_EMAIL_BOOK)
                        .addPathParam("id", castleId)
        );
    }

    @Override
    public EmailBookDto findEmailBook(String castleId) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTELLAN_CONTACT_FIND_EMAIL_BOOK)
                        .addPathParam("id", castleId)
                        .setResponseType(EmailBookDto.class)
        );
    }

    @Override
    public void attachPhoneBook(String castleId, PhoneBookDto phoneBookDto) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTELLAN_CONTACT_ATTACH_PHONE_BOOK)
                        .addPathParam("id", castleId)
                        .setRequestDto(phoneBookDto)
        );
    }

    @Override
    public void detachPhoneBook(String castleId) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTELLAN_CONTACT_DETACH_PHONE_BOOK)
                        .addPathParam("id", castleId)
        );
    }

    @Override
    public PhoneBookDto findPhoneBook(String castleId) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTELLAN_CONTACT_FIND_PHONE_BOOK)
                        .addPathParam("id", castleId)
                        .setResponseType(PhoneBookDto.class)
        );
    }

    @Override
    public void attachAddressBook(String castleId, AddressBookDto addressBookDto) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTELLAN_CONTACT_ATTACH_ADDRESS_BOOK)
                        .addPathParam("id", castleId)
                        .setRequestDto(addressBookDto)
        );
    }

    @Override
    public void detachAddressBook(String castleId) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTELLAN_CONTACT_DETACH_ADDRESS_BOOK)
                        .addPathParam("id", castleId)
        );
    }

    @Override
    public void addUserAddress(String castleId, UserAddressDto addressDto) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTELLAN_CONTACT_ADD_ADDRESS)
                        .addPathParam("id", castleId)
                        .setRequestDto(addressDto)
        );
    }

    @Override
    public void removeUserAddress(String castleId, String addressTitle) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTELLAN_CONTACT_REMOVE_ADDRESS)
                        .addPathParam("id", castleId)
                        .addPathParam("title", addressTitle)
        );
    }

    @Override
    public void modifyUserAddress(String castleId, UserAddressDto addressDto) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTELLAN_CONTACT_MODIFY_ADDRESS)
                        .addPathParam("id", castleId)
                        .setRequestDto(addressDto)
        );
    }

    @Override
    public AddressBookDto findAddressBook(String castleId) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTELLAN_CONTACT_FIND_ADDRESS_BOOK)
                        .addPathParam("id", castleId)
                        .setResponseType(AddressBookDto.class)
        );
    }
}
