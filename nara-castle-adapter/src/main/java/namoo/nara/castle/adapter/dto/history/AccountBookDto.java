package namoo.nara.castle.adapter.dto.history;

import namoo.nara.castle.domain.entity.history.AccountBook;
import namoo.nara.castle.domain.entity.history.LoginAccount;

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

    public AccountBook toDomain() {
        //
        AccountBook accountBook = new AccountBook();
        if (accountDtos != null) {
            for(LoginAccountDto loginAccountDto : accountDtos) {
                accountBook.addAccount(loginAccountDto.toDomain());
            }
        }

        return accountBook;
    }

    public static AccountBookDto newInstance(AccountBook accountBook) {
        //
        AccountBookDto accountBookDto = new AccountBookDto();
        List<LoginAccount> accountList = accountBook.findAll();
        if (accountList != null) {
            for(LoginAccount loginAccount : accountList) {
                accountBookDto.addAccountDto(LoginAccountDto.newInstance(loginAccount));
            }
        }

        return accountBookDto;
    }

    private void addAccountDto(LoginAccountDto loginAccountDto) {
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
