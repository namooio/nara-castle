

class Castellan {
  //
  static toUM(castellanRM = {}) {
    return {
      ... new Castellan(),
      ...castellanRM,

      contact: {
        emails: ContactEmail.toUMList(castellanRM.contact.emails && castellanRM.contact.emails.emails),
        names: ContactName.toUMList(castellanRM.contact.names && castellanRM.contact.names.names),
        phones: ContactPhone.toUMList(castellanRM.contact.phones && castellanRM.contact.phones.phones),
        addresses: ContactAddress.toUMList(castellanRM.contact.addresses && castellanRM.contact.addresses.addresses),
      },
    };
  }
  static toUMList(castellanRMList) {
    return castellanRMList.map((castellanRM) => Castellan.toUM(castellanRM));
  }
}

class ContactEmail {
  //
  static toUM(emailRM) {
    //
    return {
      ... new ContactEmail(),
      ...emailRM,
    };
  }
  static toUMList(emailRMList) {
    return emailRMList ? emailRMList.map((emailRM) => ContactEmail.toUM(emailRM)) : [];
  }

  constructor(tier = 'Secondary', category = 'None', email = '', editMode = false) {
    //
    return {
      tier,
      category,
      email,

      editMode,
    };
  }
}

class ContactName {
  //
  static toUM(nameRM) {
    //
    return {
      ... new ContactName(),
      ... nameRM,
    };
  }
  static toUMList(nameRMList) {
    return nameRMList ? nameRMList.map((nameRM) => ContactName.toUM(nameRM)) : [];
  }

  constructor(tier = 'Secondary', locale, firstName, middleName, familyName, editMode = false) {
    //
    return {
      tier,
      locale,
      firstName,
      middleName,
      familyName,

      editMode,
    };
  }
}

class ContactPhone {
  //
  static toUM(phoneRM) {
    //
    return {
      ... new ContactPhone(),
      ... phoneRM,
    };
  }
  static toUMList(phoneRMList) {
    return phoneRMList ? phoneRMList.map((phoneRM) => ContactPhone.toUM(phoneRM)) : [];
  }

  constructor(tier = 'Secondary', category = 'Mobile', countryCode, carrierCode, frontNumber = '', backNumber = '', editMode = false) {
    //
    return {
      tier,
      category,
      countryCode,
      carrierCode,
      frontNumber,
      backNumber,
      fullNumber: frontNumber + backNumber,
      carrierFullNumber: carrierCode + frontNumber + backNumber,
      displayNumber: carrierCode + frontNumber + backNumber,

      editMode,
    };
  }
}

class ContactAddress {
  //
  static toUM(addressRM) {
    //
    return {
      ... new ContactAddress(),
      ... addressRM,
    };
  }
  static toUMList(addressRMList) {
    return addressRMList ? addressRMList.map((addressRM) => ContactAddress.toUM(addressRM)) : [];
  }

  constructor(category = 'Office', tag = '', country, zipCode = '', address = '', phoneNumber = '', editMode = false) {
    return {
      category,
      tag,
      country,
      zipCode,
      address,
      phoneNumber,

      editMode,
    };
  }
}

export default Castellan;
export { ContactEmail, ContactName, ContactPhone, ContactAddress };
