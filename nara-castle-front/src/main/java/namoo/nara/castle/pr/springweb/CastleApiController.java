package namoo.nara.castle.pr.springweb;

import namoo.nara.castle.adapter.dto.CastleFindDto;
import namoo.nara.castle.adapter.service.CastleAdapter;
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
    private static Map<String, CastleFindDto> temporaryCastleMap;

    @Autowired
    private CastleAdapter castleAdapter;

    static {
        int countOfCastle = 15;
        temporaryCastleMap = new LinkedHashMap<>(countOfCastle);

        for (int i = 0; i < countOfCastle; i++) {

            CastleFindDto dto = new CastleFindDto();

            String idSeq = String.valueOf(i + 1);
            String id = StringUtils.leftPad(idSeq, 6, "0");

            dto.setId(id);
            dto.setName("Castle" + (i+1));
            dto.setLocale(Locale.KOREAN);
            dto.setState("Ready");
            dto.setBuildTime(System.currentTimeMillis());

            temporaryCastleMap.put(id, dto);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<CastleFindDto> findAllCastles() {
        //
        // TODO : Adapter에 FindAll 추가 되어야 함
        return new ArrayList<CastleFindDto>(temporaryCastleMap.values());
    }

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public CastleFindDto find(@PathVariable("id") String castleId) {
        //
        return temporaryCastleMap.get(castleId);
    }


}
