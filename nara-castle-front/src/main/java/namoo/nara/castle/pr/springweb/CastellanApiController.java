package namoo.nara.castle.pr.springweb;

import namoo.nara.castle.adapter.CastellanAdapter;
import namoo.nara.castle.adapter.dto.CastellanFindDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 18..
 */
@RestController
@RequestMapping("api/castellans")
public class CastellanApiController {
    //
    @Autowired
    private CastellanAdapter castellanAdapter;

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public CastellanFindDto find(@PathVariable("id") String id) {
        //
        return castellanAdapter.findCastellan(id);
    }

}
