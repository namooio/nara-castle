package namoo.nara.castle.sp.springweb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 18..
 */
@Controller
public class DefaultFrontView {
    //
    @RequestMapping("/")
    public String main(@RequestParam("townId") String townId, @RequestParam("townerId") String townerId) {
        //
        System.out.println("townId: " + townId + ", townerId: " + townerId);
        return "/resources/index.html";
    }

}
