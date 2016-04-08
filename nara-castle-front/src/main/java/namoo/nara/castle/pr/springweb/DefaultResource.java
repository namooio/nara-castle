package namoo.nara.castle.pr.springweb;

import namoo.nara.castle.remote.CastellanRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 18..
 */
@Controller
@RequestMapping("view")
public class DefaultResource {

    @Autowired
    private CastellanRemote castellanRemote;

    @RequestMapping("/main")
    public String main() {

        return "main";
    }
}
