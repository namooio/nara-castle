package namoo.nara.castle.adapter.dto.history;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
public class AccountBookDto {
    //
    private List<LoginAccountDto> accountDtos;

    public AccountBookDto() {
        //
    }

    public void addAccountDto(LoginAccountDto loginAccountDto) {
        //
        if (accountDtos == null) {
            accountDtos = new ArrayList<>();
        }
        accountDtos.add(loginAccountDto);
    }

    public List<LoginAccountDto> getAccountDtos() {
        return accountDtos;
    }

    public void setAccountDtos(List<LoginAccountDto> accountDtos) {
        this.accountDtos = accountDtos;
    }

}
