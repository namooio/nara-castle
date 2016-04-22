package namoo.nara.castle.pr.springweb;

import namoo.nara.castle.adapter.dto.CastellanFindDto;
import namoo.nara.castle.adapter.dto.CastleFindDto;
import namoo.nara.castle.adapter.dto.contact.NameBookDto;
import namoo.nara.castle.adapter.dto.contact.PhoneBookDto;
import namoo.nara.castle.adapter.dto.contact.UserNameDto;
import namoo.nara.castle.adapter.CastleAdapter;
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
        System.out.println("Execute find -> id: " + castleId);
        return TemporaryCastleStore.findCastle(castleId);
    }

    @RequestMapping(value="/{id}/namebook", method= RequestMethod.GET)
    public NameBookDto findNameBook(@PathVariable("id") String castleId) {
        //
        System.out.println("Execute find nameBook -> id: " + castleId);
        return TemporaryCastleStore.findNameBook(castleId);
    }

    public PhoneBookDto findPhoneBook(@PathVariable("id") String castleId) {
        System.out.println("Execute find phoneBook -> id: " + castleId);
        return TemporaryCastleStore.findPhoneBook(castleId);
    }



    private static class TemporaryCastleStore {
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

                // NameBook
                NameBookDto nameBookDto = new NameBookDto();
//                nameBookDto.setCastleId(id);

                for (int j = 0; j < 3; j ++) {
                    int namebookSeq = j + 1;

                    UserNameDto nameDto = new UserNameDto();
                    nameDto.setFamilyName("Family name " + sequence + "-" + namebookSeq);
                    nameDto.setFirstName("First name " + sequence + "-" + namebookSeq);
                    nameDto.setDisplayName("Display name " + sequence + "-" + namebookSeq);
                    nameDto.setMiddleName("Middle name " + sequence + "-" + namebookSeq);
                    nameDto.setLangCode("ko");

                    nameBookDto.add(nameDto);
                }

                dto.setNameBookDto(nameBookDto);

                // EmailBook

                // AddressBook


                temporaryCastleMap.put(id, dto);
            }
        }

        public static List<CastleFindDto> findAllCastles() {
            System.out.println("Execute findAllCastles");
            return new ArrayList<CastleFindDto>(temporaryCastleMap.values());
        }

        public static CastleFindDto findCastle(String castleId) {
            System.out.println("Execute findCastle");
            System.out.println(temporaryCastleMap.get(castleId).toString());
            return temporaryCastleMap.get(castleId);
        }

        public static NameBookDto findNameBook(String castleId) {
            System.out.println("Execute findNameBook");
            System.out.println(temporaryCastleMap.get(castleId).getNameBookDto().toString());
            return temporaryCastleMap.get(castleId).getNameBookDto();
        }

        public static PhoneBookDto findPhoneBook(String castleId) {
            System.out.println("Execute findPhoneBook");
            System.out.println(temporaryCastleMap.get(castleId).getPhoneBookDto().toString());
            return temporaryCastleMap.get(castleId).getPhoneBookDto();
        }


    }

}
