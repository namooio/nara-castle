package namoo.nara.castle.adapter.dto.history;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
public class AccountBookDto {
    //
    private List<LoginAccountDto> accounts;

    public AccountBookDto() {
        //
    }

    public void addAccountDto(LoginAccountDto loginAccountDto) {
        //
        if (accounts == null) {
            accounts = new ArrayList<>();
        }
        accounts.add(loginAccountDto);
    }

    public List<LoginAccountDto> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<LoginAccountDto> accounts) {
        this.accounts = accounts;
    }

}
