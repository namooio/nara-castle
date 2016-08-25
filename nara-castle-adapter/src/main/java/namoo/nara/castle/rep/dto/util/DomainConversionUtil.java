package namoo.nara.castle.rep.dto.util;

import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.entity.OpenState;
import namoo.nara.castle.domain.entity.contact.*;
import namoo.nara.castle.domain.entity.history.*;
import namoo.nara.castle.rep.dto.CastellanFindDto;
import namoo.nara.castle.rep.dto.CastleFindDto;
import namoo.nara.castle.rep.dto.contact.*;
import namoo.nara.castle.rep.dto.history.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 22..
 */
public class DomainConversionUtil {
    //
    public static CastellanFindDto toCastellanFindDto(Castellan castellan) {
        //
        CastellanFindDto castellanFindDto = new CastellanFindDto();
        castellanFindDto.setDisplayName(castellan.getDisplayName());
        castellanFindDto.setPhotoId(castellan.getPhotoId());
        castellanFindDto.setPrimaryEmail(castellan.getPrimaryEmail());
        castellanFindDto.setPrimaryPhone(castellan.getPrimaryPhone());
        return castellanFindDto;
    }

    public static List<CastleFindDto> toCastleFindDtoList(List<Castle> castles) {
        return castles.stream()
                .map(DomainConversionUtil::toCastleFindDto)
                .collect(Collectors.toList());
    }

    public static CastleFindDto toCastleFindDto(Castle castle) {
        //
        if (castle == null) return null;
        CastleFindDto castleFindDto = new CastleFindDto();
        castleFindDto.setId(castle.getId());
        castleFindDto.setName(castle.getName());
        castleFindDto.setLocale(castle.getLocale());
        castleFindDto.setState(castle.getState().toString());
        castleFindDto.setBuildTime(castle.getBuildTime());

        castleFindDto.setOwner(toCastellanFindDto(castle.getOwner()));

        return castleFindDto;
    }

    public static AddressBook toAddressBook(AddressBookDto addressBookDto) {
        //
        List<UserAddressDto> userAddresseDtos = addressBookDto.getAddresses();
        AddressBook addressBook = new AddressBook();
        if (userAddresseDtos != null) {
            for(UserAddressDto userAddressDto : userAddresseDtos) {
                addressBook.add(toUserAddress(userAddressDto));
            }
        }
        return addressBook;
    }

    public static AddressBookDto toAddressBookDto(AddressBook addressBook) {
        //
        AddressBookDto addressBookDto = new AddressBookDto();

        for(UserAddress userAddress : addressBook.findAll()) {
            addressBookDto.addAddressDto(toUserAddressDto(userAddress));
        }
        return addressBookDto;
    }

    public static EmailBook toEmailBook(EmailBookDto emailBookDto) {
        //
        List<UserEmailDto> emailDtos = emailBookDto.getEmails();
        EmailBook emailBook = new EmailBook();
        if (emailDtos != null) {
            for(UserEmailDto userEmailDto : emailDtos) {
                emailBook.addEmail(toUserEmail(userEmailDto));
            }
        }
        return emailBook;
    }

    public static EmailBookDto toEmailBookDto(EmailBook emailBook) {
        //
        EmailBookDto emailBookDto = new EmailBookDto();

        for(UserEmail userEmail : emailBook.findAll()) {
            emailBookDto.addEmailDto(toUserEmailDto(userEmail));
        }
        return emailBookDto;
    }

    public static NameBook toNameBook(NameBookDto nameBookDto) {
        //
        List<UserNameDto> nameDtos = nameBookDto.getNames();
        NameBook nameBook = new NameBook();
        if (nameDtos != null) {
            for(UserNameDto userNameDto : nameDtos) {
                nameBook.add(toUserName(userNameDto));
            }
        }
        return nameBook;
    }

    public static NameBookDto toNameBookDto(NameBook nameBook) {
        //
        NameBookDto nameBookDto = new NameBookDto();

        for(UserName userName : nameBook.findAll()) {
            nameBookDto.addNameDto(toUserNameDto(userName));
        }
        return nameBookDto;
    }

    public static PhoneBook toPhoneBook(PhoneBookDto phoneBookDto) {
        //
        List<UserPhoneDto> phoneDtos = phoneBookDto.getPhones();
        PhoneBook phoneBook = new PhoneBook();
        if (phoneDtos != null) {
            for(UserPhoneDto userPhoneDto : phoneDtos) {
                phoneBook.add(toUserPhone(userPhoneDto));
            }
        }
        return phoneBook;
    }

    public static PhoneBookDto toPhoneBookDto(PhoneBook phoneBook) {
        //
        PhoneBookDto phoneBookDto = new PhoneBookDto();

        for(UserPhone userPhone : phoneBook.findAll()) {
            phoneBookDto.addPhoneDto(toUserPhoneDto(userPhone));
        }
        return phoneBookDto;
    }

    public static UserAddress toUserAddress(UserAddressDto addressDto) {
        //
        UserAddress userAddress = new UserAddress();
        userAddress.setStyle(UserAddress.AddressStyle.valueOf(addressDto.getStyle()));
        userAddress.setAddressPartOne(addressDto.getAddressPartOne());
        userAddress.setAddressPartTwo(addressDto.getAddressPartTwo());
        userAddress.setCity(addressDto.getCity());
        userAddress.setState(addressDto.getState());
        userAddress.setZipCode(addressDto.getZipCode());
        userAddress.setCountry(addressDto.getCountry());

        userAddress.setTitle(addressDto.getTitle());
        userAddress.setPhoneNumber(addressDto.getPhoneNumber());
        userAddress.setLangCode(addressDto.getLangCode());
        return userAddress;
    }

    public static UserAddressDto toUserAddressDto(UserAddress userAddress) {
        //
        UserAddressDto userAddressDto = new UserAddressDto();
        userAddressDto.setStyle(userAddress.getStyle().name());
        userAddressDto.setAddressPartOne(userAddress.getAddressPartOne());
        userAddressDto.setAddressPartTwo(userAddress.getAddressPartTwo());
        userAddressDto.setCity(userAddress.getCity());
        userAddressDto.setState(userAddress.getState());
        userAddressDto.setZipCode(userAddress.getZipCode());
        userAddressDto.setCountry(userAddress.getCountry());

        userAddressDto.setTitle(userAddress.getTitle());
        userAddressDto.setPhoneNumber(userAddress.getPhoneNumber());
        userAddressDto.setLangCode(userAddress.getLangCode());
        return userAddressDto;
    }

    public static UserEmail toUserEmail(UserEmailDto userEmailDto) {
        //
        UserEmail userEmail = new UserEmail();
        userEmail.setEmail(userEmailDto.getEmail());
        userEmail.setEmailType(UserEmail.EmailType.valueOf(userEmailDto.getEmailType()));
        userEmail.setVerified(userEmailDto.isVerified());
        userEmail.setVerifiedTime(userEmailDto.getVerifiedTime());
        return userEmail;
    }

    public static UserEmailDto toUserEmailDto(UserEmail userEmail) {
        //
        UserEmailDto userEmailDto = new UserEmailDto();
        userEmailDto.setEmail(userEmail.getEmail());
        userEmailDto.setEmailType(userEmail.getEmailType().name());
        userEmailDto.setVerified(userEmail.isVerified());
        userEmailDto.setVerifiedTime(userEmail.getVerifiedTime());
        return userEmailDto;
    }

    public static UserName toUserName(UserNameDto userNameDto) {
        //
        UserName userName = new UserName();
        userName.setFamilyName(userNameDto.getFamilyName());
        userName.setFirstName(userNameDto.getFirstName());
        userName.setDisplayName(userNameDto.getDisplayName());
        userName.setMiddleName(userNameDto.getMiddleName());
        userName.setLangCode(userNameDto.getLangCode());
        return userName;
    }

    public static UserNameDto toUserNameDto(UserName userName) {
        //
        UserNameDto userNameDto = new UserNameDto();
        userNameDto.setFamilyName(userName.getFamilyName());
        userNameDto.setFirstName(userName.getFirstName());
        userNameDto.setDisplayName(userName.getDisplayName());
        userNameDto.setMiddleName(userName.getMiddleName());
        userNameDto.setLangCode(userName.getLangCode());
        return userNameDto;
    }

    public static UserPhone toUserPhone(UserPhoneDto userPhoneDto) {
        //
        UserPhone userPhone = new UserPhone();
        userPhone.setPhoneNumber(userPhoneDto.getPhoneNumber());
        userPhone.setCountryCode(userPhoneDto.getCountryCode());
        userPhone.setAreaCode(userPhoneDto.getAreaCode());
        userPhone.setNumber(userPhoneDto.getNumber());
        return userPhone;
    }

    public static UserPhoneDto toUserPhoneDto(UserPhone userPhone) {
        //
        UserPhoneDto userPhoneDto = new UserPhoneDto();
        userPhoneDto.setPhoneNumber(userPhone.getPhoneNumber());
        userPhoneDto.setCountryCode(userPhone.getCountryCode());
        userPhoneDto.setAreaCode(userPhone.getAreaCode());
        userPhoneDto.setNumber(userPhone.getNumber());
        return userPhoneDto;
    }

    public static AccountBook toAccountBook(AccountBookDto accountBookDto) {
        //
        List<LoginAccountDto> accountDtos = accountBookDto.getAccounts();
        AccountBook accountBook = new AccountBook();
        if (accountDtos != null) {
            for(LoginAccountDto loginAccountDto : accountDtos) {
                accountBook.addAccount(toLoginAccount(loginAccountDto));
            }
        }

        return accountBook;
    }

    public static AccountBookDto toAccountBookDto(AccountBook accountBook) {
        //
        AccountBookDto accountBookDto = new AccountBookDto();

        for(LoginAccount loginAccount : accountBook.findAll()) {
            accountBookDto.addAccountDto(toLoginAccountDto(loginAccount));
        }
        return accountBookDto;
    }

    public static CastleStateBook toCastleStateBook(CastleStateBookDto castleStateBookDto) {
        //
        List<CastleStateDto> castleStateDtos = castleStateBookDto.getStates();
        CastleStateBook castleStateBook = new CastleStateBook();
        if (castleStateDtos != null) {
            for(CastleStateDto castleStateDto : castleStateDtos) {
                castleStateBook.attachCastleState(toCastleState(castleStateDto));
            }
        }

        return castleStateBook;
    }

    public static CastleStateBookDto toCastleStateBookDto(CastleStateBook castleStateBook) {
        //
        CastleStateBookDto castleStateBookDto = new CastleStateBookDto();

        for(CastleState castleState : castleStateBook.findAll()) {
            castleStateBookDto.addStateDto(toCastleStateDto(castleState));
        }
        return castleStateBookDto;
    }

    public static CastleStateDto toCastleStateDto(CastleState castleState) {
        //
        CastleStateDto castleStateDto = new CastleStateDto();
        castleStateDto.setCurrentState(castleState.getCurrentState().name());
        castleStateDto.setTargetState(castleState.getTargetState().name());
        castleStateDto.setRemarks(castleState.getRemarks());
        castleStateDto.setModifiedTime(castleState.getModifiedTime());
        return castleStateDto;
    }

    public static CastleState toCastleState(CastleStateDto castleStateDto) {
        //
        CastleState castleState = new CastleState();
        castleState.setCurrentState(OpenState.valueOf(castleStateDto.getCurrentState()));
        castleState.setTargetState(OpenState.valueOf(castleStateDto.getTargetState()));
        castleState.setRemarks(castleStateDto.getRemarks());
        castleState.setModifiedTime(castleStateDto.getModifiedTime());
        return castleState;
    }

    public static LoginAccountDto toLoginAccountDto(LoginAccount loginAccount) {
        //
        LoginAccountDto loginAccountDto = new LoginAccountDto();
        loginAccountDto.setLoginUserId(loginAccount.getLoginId());
        loginAccountDto.setChannel(loginAccount.getChannel().name());
        loginAccountDto.setCreateTime(loginAccount.getCreateTime());
        loginAccountDto.setDeleteTime(loginAccount.getDeleteTime());
        return loginAccountDto;
    }

    public static LoginAccount toLoginAccount(LoginAccountDto loginAccountDto) {
        //
        LoginAccount loginAccount = new LoginAccount();
        loginAccount.setLoginId(loginAccountDto.getLoginUserId());
        loginAccount.setChannel(LoginAccount.LoginChannel.valueOf(loginAccountDto.getChannel()));
        loginAccount.setCreateTime(loginAccountDto.getCreateTime());
        loginAccount.setDeleteTime(loginAccountDto.getDeleteTime());
        return loginAccount;
    }

    public static MetroBook toMetroBook(MetroBookDto metroBookDto) {
        //
        List<ParticipantMetroDto> metroDtos = metroBookDto.getMetros();
        MetroBook metroBook = new MetroBook();
        if (metroDtos != null) {
            for(ParticipantMetroDto metroDto : metroDtos) {
                metroBook.addMetro(toParticipantMetro(metroDto));
            }
        }
        return metroBook;
    }

    public static MetroBookDto toMetroBookDto(MetroBook metroBook) {
        //
        MetroBookDto metroBookDto = new MetroBookDto();

        for(ParticipantMetro metro : metroBook.findAll()) {
            metroBookDto.addMetroDto(toParticipantMetroDto(metro));
        }
        return metroBookDto;
    }

    public static ParticipantMetroDto toParticipantMetroDto(ParticipantMetro participantMetro) {
        //
        ParticipantMetroDto participantMetroDto = new ParticipantMetroDto();
        participantMetroDto.setMetroId(participantMetro.getMetroId());
        participantMetroDto.setMetroName(participantMetro.getMetroName());
        participantMetroDto.setJoinTime(participantMetro.getJoinTime());
        participantMetroDto.setWithdrawalTime(participantMetro.getWithdrawalTime());
        participantMetroDto.setRemarks(participantMetro.getRemarks());
        return participantMetroDto;
    }

    public static ParticipantMetro toParticipantMetro(ParticipantMetroDto participantMetroDto) {
        //
        ParticipantMetro participantMetro = new ParticipantMetro();
        participantMetro.setMetroId(participantMetroDto.getMetroId());
        participantMetro.setMetroName(participantMetroDto.getMetroName());
        participantMetro.setJoinTime(participantMetroDto.getJoinTime());
        participantMetro.setWithdrawalTime(participantMetroDto.getWithdrawalTime());
        participantMetro.setRemarks(participantMetroDto.getRemarks());
        return participantMetro;
    }
}
