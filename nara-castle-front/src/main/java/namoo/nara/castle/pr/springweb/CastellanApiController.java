package namoo.nara.castle.pr.springweb;

import namoo.nara.castle.adapter.CastellanAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 18..
 */
@RestController
@RequestMapping("api/castellan")
public class CastellanApiController {
    //
    @Autowired
    private CastellanAdapter castellanAdapter;


    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public TempCastellanReadDto find(@PathVariable("id") String castellanId) {
        //
        System.out.println("Input param castellan id : " + castellanId);

        TempCastellanReadDto readDto = null;

//        readDto = castellanAdapter.findCastellan(castellanId);
        readDto = new TempCastellanReadDto();
        readDto.setId(castellanId);
        readDto.setDisplayName("Tester");
        readDto.setPrimaryEmail("test@test.io");

        return readDto;
    }

    @RequestMapping(method= RequestMethod.POST)
    public void register(@RequestBody TempCastellanCreateDto cDto) {
        //
        System.out.println("ID   : " + cDto.getId());
        System.out.println("Email: " + cDto.getEmail());

//        castellanAdapter.create(cDto);
    }
}
