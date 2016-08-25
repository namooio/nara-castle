package namoo.nara.castle.rep.dto.history;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
public class AccountBookDto implements Serializable {
    //
    private static final long serialVersionUID = 8762480099508491207L;

    private List<LoginAccountDto> accounts = new ArrayList<>();

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
