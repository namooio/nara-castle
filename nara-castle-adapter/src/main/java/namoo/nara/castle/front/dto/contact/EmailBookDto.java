package namoo.nara.castle.front.dto.contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
public class EmailBookDto {
    //
    private List<UserEmailDto> emails = new ArrayList<>();

    public EmailBookDto() {
        //
    }

    public void addEmailDto(UserEmailDto userEmailDto) {
        //
        if(emails == null) {
            emails = new ArrayList<>();
        }
        emails.add(userEmailDto);
    }

    public List<UserEmailDto> getEmails() {
        return emails;
    }

    public void setEmails(List<UserEmailDto> emails) {
        this.emails = emails;
    }
}
