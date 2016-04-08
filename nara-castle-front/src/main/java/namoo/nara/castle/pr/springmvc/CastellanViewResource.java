package namoo.nara.castle.pr.springmvc;

import namoo.nara.castle.remote.CastellanRemote;
import namoo.nara.castle.remote.dto.CastellanCreateDto;
import namoo.nara.castle.remote.dto.CastellanReadDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 18..
 */
@Controller
@RequestMapping("castellan/view")
public class CastellanViewResource {
    //
    @Autowired
    private CastellanRemote castellanRemote;


    @RequestMapping(value="/register", method= RequestMethod.GET)
    public String registerView() {
        //
        return "registerCastellan";
    }

    @RequestMapping(value="/find", method= RequestMethod.GET)
    public String findView() {
        //
        return "findCastellan";
    }

}
