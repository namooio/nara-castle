package namoo.nara.castle.pr.springweb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 18..
 */
@Controller
public class DefaultViewController {
    //
    @RequestMapping("/")
    public String main(@RequestParam("townerId") String townerId) {
        System.out.println(townerId);
        return "/resources/index.html";
    }

}
