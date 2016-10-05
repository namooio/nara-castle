package namoo.nara.castle.domain.context;

import namoo.nara.share.exception.NaraException;
import namoo.nara.share.validation.EmailValidator;

public class CastleEmailValidator {
    //
    private EmailValidator emailValidator;

    public CastleEmailValidator() {
        //
        this.emailValidator = new EmailValidator();
    }

    public void validate(String email) {
        //
        boolean valid = this.emailValidator.validate(email);
        if (!valid) throw new NaraException(String.format("Email[%s] is not valid.", email));
    }
}
