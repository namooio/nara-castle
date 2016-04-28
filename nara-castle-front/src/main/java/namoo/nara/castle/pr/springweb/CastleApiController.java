package namoo.nara.castle.pr.springweb;

import namoo.nara.castle.adapter.CastellanAdapter;
import namoo.nara.castle.adapter.CastleAdapter;
import namoo.nara.castle.adapter.dto.CastellanFindDto;
import namoo.nara.castle.adapter.dto.CastleFindDto;
import namoo.nara.castle.adapter.dto.contact.*;
import namoo.nara.castle.adapter.dto.history.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 18..
 */
@RestController
@RequestMapping("api/castle")
public class CastleApiController {
    //
    @Autowired
    private CastleAdapter castleAdapter;

    @Autowired
    private CastellanAdapter castellanAdapter;


    @RequestMapping(method = RequestMethod.GET)
    public List<CastleFindDto> findAllCastles() {
        //
        // TODO : Adapter에 FindAll 추가 되어야 함
        System.out.println("Execute findAllCastles");
        return TemporaryCastleStore.findAllCastles();
    }

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public CastleFindDto find(@PathVariable("id") String castleId) {
        //
        CastleFindDto castle = castleAdapter.findCastle(castleId);
        CastellanFindDto castellan = castellanAdapter.findCastellan(castleId);

        castle.setCastellan(castellan);

        return castle;
    }



    private static class TemporaryCastleStore {
        //
        private static Map<String, CastleFindDto> temporaryCastleMap;

        static {
            int countOfCastle = 15;
            temporaryCastleMap = new LinkedHashMap<>(countOfCastle);

            for (int i = 0; i < countOfCastle; i++) {

                int sequence = i + 1;

                CastleFindDto dto = new CastleFindDto();

                // Basic Information
                String idSeq = String.valueOf(sequence);
                String id = "CT" + StringUtils.leftPad(idSeq, 6, "0");

                dto.setId(id);
                dto.setName("Castle" + sequence);
                dto.setLocale(Locale.KOREAN);
                dto.setState("Ready");
                dto.setBuildTime(new Date(System.currentTimeMillis()));

                // Castellan
                CastellanFindDto castellanDto = new CastellanFindDto();
                castellanDto.setPhotoId("PT" + idSeq);
                castellanDto.setPrimaryEmail(dto.getName() + "@test");
                castellanDto.setPrimaryPhone("010-0000-" + sequence);

                dto.setCastellan(castellanDto);

                // Books
                NameBookDto nameBookDto = new NameBookDto();
                PhoneBookDto phoneBookDto = new PhoneBookDto();
                EmailBookDto emailBookDto = new EmailBookDto();
                AddressBookDto addressBookDto = new AddressBookDto();

                for (int j = 0; j < 3; j ++) {
                    //
                    int bookSeq = j + 1;

                    // Name book
                    UserNameDto nameDto = new UserNameDto();
                    nameDto.setFamilyName("Family name " + sequence + "-" + bookSeq);
                    nameDto.setFirstName("First name " + sequence + "-" + bookSeq);
                    nameDto.setDisplayName("Display name " + sequence + "-" + bookSeq);
                    nameDto.setMiddleName("Middle name " + sequence + "-" + bookSeq);
                    nameDto.setLangCode("ko");
                    nameBookDto.addNameDto(nameDto);

                    // Phone book
                    UserPhoneDto phoneDto = new UserPhoneDto();
                    phoneDto.setCountryCode("+82");
                    phoneDto.setAreaCode("10");
                    phoneDto.setNumber(String.valueOf(sequence + "-" + bookSeq));
                    phoneDto.setPhoneNumber(phoneDto.getCountryCode() + "-" + phoneDto.getAreaCode() + "-" + phoneDto.getNumber());
                    phoneBookDto.addPhoneDto(phoneDto);

                    // Email book
                    UserEmailDto emailDto = new UserEmailDto();
                    emailDto.setEmail(dto.getName() + "@test" + bookSeq);
                    emailDto.setEmailType("Private");
                    emailDto.setVerified(true);
                    emailDto.setVerifiedTime(System.currentTimeMillis());
                    emailBookDto.addEmailDto(emailDto);

                    // Address book
                    UserAddressDto addressDto = new UserAddressDto();
                    addressDto.setTitle("주소 " + sequence + "-" + bookSeq);
                    addressDto.setStyle("Korean");
                    addressDto.setLangCode("ko");
                    addressDto.setCountry("korea");
                    addressDto.setZipCode("111-" + sequence + bookSeq);
                    addressDto.setCity("Seoul");
                    addressDto.setAddressPartOne("금천구 가산디지털1로 " + bookSeq);
                    addressDto.setAddressPartTwo("제이플라츠 101호 " + bookSeq);
                    addressDto.setPhoneNumber("02-" + sequence + "-" + bookSeq);
                    addressBookDto.addAddressDto(addressDto);
                }
                dto.setNameBook(nameBookDto);
                dto.setPhoneBook(phoneBookDto);
                dto.setEmailBook(emailBookDto);
                dto.setAddressBook(addressBookDto);


                // Histories
                AccountBookDto accountBook = new AccountBookDto();
                CastleStateBookDto stateBook = new CastleStateBookDto();
                MetroBookDto metroBook = new MetroBookDto();

                for (int j = 0; j < 3; j ++) {
                    //
                    int bookSeq = j + 1;

                    LoginAccountDto account = new LoginAccountDto();
                    account.setLoginUserId(dto.getName() + " account " + bookSeq);
                    account.setChannel("Facebook");
                    account.setCreateTime(System.currentTimeMillis());
                    account.setDeleteTime(System.currentTimeMillis() + 1000000);

                    accountBook.addAccountDto(account);


                    CastleStateDto state = new CastleStateDto();
                    state.setCurrentState("Ready");
                    state.setTargetState("Open");
                    state.setRemarks("This is state remarks " + sequence + "-" + bookSeq);
                    state.setModifiedTime(System.currentTimeMillis());

                    stateBook.addStateDto(state);


                    ParticipantMetroDto participantMetro = new ParticipantMetroDto();
                    participantMetro.setMetroId("MT" + sequence + bookSeq);
                    participantMetro.setMetroName("Metro " + sequence + "-" + bookSeq);
                    participantMetro.setJoinTime(System.currentTimeMillis() - 100000);
                    participantMetro.setWithdrawalTime(System.currentTimeMillis());
                    participantMetro.setRemarks("This is participant Metro remarks " + sequence + "-" + bookSeq);

                    metroBook.addMetroDto(participantMetro);
                }
                dto.setAccountBook(accountBook);
                dto.setStateBook(stateBook);
                dto.setMetroBook(metroBook);


                temporaryCastleMap.put(id, dto);
            }
        }

        public static List<CastleFindDto> findAllCastles() {
            System.out.println("Execute findAllCastles");
            return new ArrayList<>(temporaryCastleMap.values());
        }

        public static CastleFindDto findCastle(String castleId) {
            System.out.println("Execute findCastle");
            return temporaryCastleMap.get(castleId);
        }

        public static NameBookDto findNameBook(String castleId) {
            System.out.println("Execute findNameBook -> length: " + temporaryCastleMap.get(castleId).getNameBook().getNames().size());
            return temporaryCastleMap.get(castleId).getNameBook();
        }

        public static PhoneBookDto findPhoneBook(String castleId) {
            System.out.println("Execute findPhoneBook -> length: " + temporaryCastleMap.get(castleId).getPhoneBook().getPhones().size());
            return temporaryCastleMap.get(castleId).getPhoneBook();
        }

        public static EmailBookDto findEmailBook(String castleId) {
            System.out.println("Execute findEmailBook -> length: " + temporaryCastleMap.get(castleId).getEmailBook().getEmails().size());
            return temporaryCastleMap.get(castleId).getEmailBook();
        }

        public static AddressBookDto findAddressBook(String castleId) {
            System.out.println("Execute findAddressBook -> length: " + temporaryCastleMap.get(castleId).getAddressBook().getAddresses().size());
            return temporaryCastleMap.get(castleId).getAddressBook();
        }

        public static AccountBookDto findAccountBook(String castleId) {
            System.out.println("Execute findAccountBook -> length: " + temporaryCastleMap.get(castleId).getAccountBook().getAccounts().size());
            return temporaryCastleMap.get(castleId).getAccountBook();
        }

        public static CastleStateBookDto findStateBook(String castleId) {
            System.out.println("Execute findStateBook -> length: " + temporaryCastleMap.get(castleId).getStateBook().getStates().size());
            return temporaryCastleMap.get(castleId).getStateBook();
        }

        public static MetroBookDto findMetroBook(String castleId) {
            System.out.println("Execute findMetroBook -> length: " + temporaryCastleMap.get(castleId).getMetroBook().getMetros().size());
            return temporaryCastleMap.get(castleId).getMetroBook();
        }

    }

}
