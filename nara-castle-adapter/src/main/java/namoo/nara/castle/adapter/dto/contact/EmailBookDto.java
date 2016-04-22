package namoo.nara.castle.adapter.dto.contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
public class EmailBookDto extends ArrayList<UserEmailDto> {
    //
//    private List<UserEmailDto> emailDtoList;

    public EmailBookDto() {
        //
    }

    public void addEmailDto(UserEmailDto userEmailDto) {
        //
//        if(emailDtoList == null) {
//            emailDtoList = new ArrayList<>();
//        }
//        emailDtoList.add(userEmailDto);
        this.add(userEmailDto);
    }

//    public List<UserEmailDto> getEmailDtoList() {
//        return emailDtoList;
//    }
//
//    public void setEmailDtoList(List<UserEmailDto> emailDtoList) {
//        this.emailDtoList = emailDtoList;
//    }
}
