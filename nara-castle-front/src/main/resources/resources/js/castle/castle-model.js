/**
 * Created by hkkang on 2016-04-12.
 */
Components.Castle.Model = Components.Castle.Model || {};

(function () {
    //
    // Import component module
    'use strict';

    // Define Content attributes name
    var castleModel = {
        buttons : {
            save:     { KOR: '저장', USA: 'Save'},
            modify:   { KOR: '수정', USA: 'Modify' },
            remove:   { KOR: '삭제', USA: 'Remove'},
            cancel:   { KOR: '취소', USA: 'Cancel'}
        },
        enum: {
            state: {
                Ready:      { KOR: '준비', USA: 'Ready' },
                Open:       { KOR: '사용', USA: 'Open' },
                Suspended:  { KOR: '중단', USA: 'Suspended' },
                Closed:     { KOR: '닫힘', USA: 'Closed' }
            },
            locale: {
                ko: { KOR: '대한민국',  USA: 'Republic of Korea' },
                us: { KOR: '미국',      USA: 'Unitied States of America' },
            },
            language: {
                KOR: { KOR: '한국어', USA: 'Korean' },
                ENG: { KOR: '영어', USA: 'English' }
            },
            countrycode: {
                82: { KOR: '대한민국',  USA: 'Republic of Korea' },
                1: { KOR: '미국',      USA: 'Unitied States of America' },
            },
            emailType: {
                Business:   { KOR: '업무용', USA: 'Businedss' },
                Private:    { KOR: '개인용', USA: 'Private' }
            },
            verified: {
                true:   { KOR: '확인완료', USA: 'Verified' },
                false:  { KOR: '미확인', USA: 'Unverified' }
            },
            addressStyle: {
                Korean: { KOR: '대한민국', USA: 'Korean' },
                US:     { KOR: '미국', USA: 'USA' }
            }
        },
        tabs: {
            basic:      { name: 'basic',    KOR: '기본정보',   USA: 'Basic information' },
            name:       { name: 'name',     KOR: '이름',       USA: 'Name' },
            phone:      { name: 'phone',    KOR: '전화번호',   USA: 'Phone number' },
            email:      { name: 'email',    KOR: '이메일',     USA: 'Email' },
            address:    { name: 'address',  KOR: '주소',       USA: 'Address' },
            account:    { name: 'account',  KOR: '계정내역',   USA: 'Account history' },
            state:      { name: 'state',    KOR: '상태내역',   USA: 'State history' },
            metro:      { name: 'metro',    KOR: '메트로내역', USA: 'Metro history' }
        },
        basicInfo: {
            id:         { name: 'id',         KOR: '아이디',   USA: 'Id' },
            name:       { name: 'name',       KOR: '이름',     USA: 'Name' },
            locale:     { name: 'locale',     KOR: '지역',     USA: 'Locale' },
            state:      { name: 'state',      KOR: '상태',     USA: 'State' },
            buildTime:  { name: 'buildTime',  KOR: '생성일시', USA: 'Build time' },
            castellan:  {
                primaryEmail:   { name: 'primaryEmail', KOR: '기본 이메일',   USA: 'Primary email' },
                primaryPhone:   { name: 'primaryPhone', KOR: '기본 전화번호', USA: 'Primary phone number' },
                photo:          { name: 'photoId',      KOR: '사진',          USA: 'Photo' }
            }
        },
        name: {
            familyName:     { name: 'familyName',   KOR: '성',       USA: 'Family name' },
            firstName:      { name: 'firstName',    KOR: '이름',     USA: 'First name' },
            displayName:    { name: 'displayName',  KOR: '전체이름', USA: 'Display name' },
            middleName:     { name: 'middleName',   KOR: '중간이름', USA: 'Middle name' },
            langCode:       { name: 'langCode',     KOR: '언어',     USA: 'Language' }
        },
        phone: {
            phoneNumber:    { name: 'phoneNumber',  KOR: '전체 번호', USA: 'Phone number' },
            countryCode:    { name: 'countryCode',  KOR: '국가코드',  USA: 'Country code' },
            areaCode:       { name: 'areaCode',     KOR: '지역코드',  USA: 'Area code' },
            number:         { name: 'number',       KOR: '번호',      USA: 'Number' }
        },
        email: {
            email:          { name: 'email',        KOR: '이메일',        USA: 'Email' },
            emailType:      { name: 'emailType',    KOR: '유형',          USA: 'Type' },
            verified:       { name: 'verified',     KOR: '유효확인 여부', USA: 'Verified' },
            verifiedTime:   { name: 'verifiedTime', KOR: '유효확인 일시', USA: 'Vefiried time' }
        },
        address: {
            title:          { name: 'title',            KOR: '주소명',    USA: 'Title' },
            langCode:       { name: 'langCode',         KOR: '언어코드',  USA: 'Language code' },
            style:          { name: 'style',            KOR: '유형',      USA: 'Style' },
            country:        { name: 'country',          KOR: '국가',      USA: 'Coutnry' },
            zipCode:        { name: 'zipCode',          KOR: '우편번호',  USA: 'Zip code' },
            state:          { name: 'state',            KOR: '지역',      USA: 'State' },
            city:           { name: 'city',             KOR: '시',        USA: 'City' },
            addressPartOne: { name: 'addressPartOne',   KOR: '주소1',     USA: 'Address part1' },
            addressPartTwo: { name: 'addressPartTwo',   KOR: '주소2',     USA: 'Address part2' },
            phoneNumber:    { name: 'phoneNumber',      KOR: '전화번호',  USA: 'Phone number' }
        },
        account: {
            loginUserId:    { name: 'loginUserId',      KOR: '로그인Id',    USA: 'Login user id' },
            channel:        { name: 'channel',          KOR: '접속방법',    USA: 'Channel' },
            createTime:     { name: 'createTime',       KOR: '생성일시',    USA: 'Create time' },
            deleteTime:     { name: 'deleteTime',       KOR: '삭제일시',    USA: 'Delete time' }
        },
        state: {
            currentState:   { name: 'currentState', KOR: '현재상태',    USA: 'Current state' },
            targetState:    { name: 'targetState',  KOR: '다음상태',    USA: 'Target state' },
            remarks:        { name: 'remarks',      KOR: '설명',        USA: 'Remarks' },
            modifiedTime:   { name: 'modifiedTime', KOR: '수정일시',    USA: 'Modified time' }
        },
        metro: {
            metroId:        { name: 'metroId',          KOR: '메트로Id',    USA: 'Metro id' },
            metroName:      { name: 'metroName',        KOR: '메트로명',    USA: 'Metro name' },
            joinTime:       { name: 'joinTime',         KOR: '가입일시',    USA: 'Join time' },
            withdrawalTime: { name: 'withdrawalTime',   KOR: '탈퇴일시',    USA: 'Withdrawal time' },
            remarks:        { name: 'remarks',          KOR: '설명',        USA: 'Remarks' }
        }
    };


    Components.Castle.Model = castleModel;
})();