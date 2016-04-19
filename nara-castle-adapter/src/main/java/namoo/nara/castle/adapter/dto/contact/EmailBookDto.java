package namoo.nara.castle.adapter.dto.contact;

import namoo.nara.castle.domain.entity.contact.EmailBook;
import namoo.nara.castle.domain.entity.contact.UserEmail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
public class EmailBookDto {
    //
    private List<UserEmailDto> emailDtoList;

    public EmailBookDto() {
        //
    }

    public EmailBook toDomain() {
        //
        EmailBook emailBook = new EmailBook();
        if (emailDtoList != null) {
            for(UserEmailDto userEmailDto : emailDtoList) {
                emailBook.addEmail(userEmailDto.toDomain());
            }
        }
        return emailBook;
    }

    public static EmailBookDto newInstance(EmailBook emailBook) {
        //
        EmailBookDto emailBookDto = new EmailBookDto();
        List<UserEmail> emailList = emailBook.findAll();
        if (emailList != null) {
            for(UserEmail userEmail : emailList) {
                emailBookDto.addEmailDto(UserEmailDto.newInstance(userEmail));
            }
        }
        return emailBookDto;
    }

    private void addEmailDto(UserEmailDto userEmailDto) {
        //
        if(emailDtoList == null) {
            emailDtoList = new ArrayList<>();
        }
        emailDtoList.add(userEmailDto);
    }

    public List<UserEmailDto> getEmailDtoList() {
        return emailDtoList;
    }

    public void setEmailDtoList(List<UserEmailDto> emailDtoList) {
        this.emailDtoList = emailDtoList;
    }
}
