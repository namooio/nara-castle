package namoo.nara.castle.pr.springmvc;

import namoo.nara.castle.remote.CastellanRemote;
import namoo.nara.castle.remote.dto.CastellanReadDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 18..
 */
@Controller
public class DefaultResource {

    @Autowired
    private CastellanRemote castellanRemote;

    @RequestMapping("/main")
    public String main() {

        return "main";
    }
}
