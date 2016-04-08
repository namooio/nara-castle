package namoo.nara.castle.pr.springmvc;

import namoo.nara.castle.remote.CastellanRemote;
import namoo.nara.castle.remote.dto.CastellanCreateDto;
import namoo.nara.castle.remote.dto.CastellanReadDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 18..
 */
@RestController
@RequestMapping("castellan")
public class CastellanResource {
    //
    @Autowired
    private CastellanRemote castellanRemote;


    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    @ResponseBody public CastellanReadDto find(@PathVariable("id") String castellanId) {
        //
        CastellanReadDto readDto = null;
        try {
            readDto = castellanRemote.findCastellan(castellanId);
        } catch (Exception e) {
            return readDto;
        }

        System.out.println("ID           : " + readDto.getId());
        System.out.println("Primary email: " + readDto.getPrimaryEmail());
        System.out.println("Display name : " + readDto.getDisplayName());

        return readDto;
    }

    @RequestMapping(method= RequestMethod.POST)
    @ResponseBody public void register(@RequestBody CastellanCreateDto cDto) {
        //
        System.out.println("ID   : " + cDto.getId());
        System.out.println("Email: " + cDto.getEmail());

        castellanRemote.create(cDto);
    }
}
