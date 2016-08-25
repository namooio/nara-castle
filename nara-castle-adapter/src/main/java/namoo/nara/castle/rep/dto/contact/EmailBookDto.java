package namoo.nara.castle.rep.dto.contact;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
public class EmailBookDto implements Serializable {
    //
    private static final long serialVersionUID = 1085868938683842491L;

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
