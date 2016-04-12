package namoo.nara.castle.pr.springweb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 18..
 */
@Controller
@RequestMapping("view/castellan")
public class CastellanViewController {
    //
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
