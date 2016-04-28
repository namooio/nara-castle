/**
 * Created by hkkang on 2016-04-12.
 */
Components.Castle.Model = Components.Castle.Model || { };

( function () {
    //
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
                us: { KOR: '미국',      USA: 'Unitied States of America' }
            },
            language: {
                KOR: { KOR: '한국어', USA: 'Korean' },
                ENG: { KOR: '영어', USA: 'English' }
            },
            countrycode: {
                82: { KOR: '대한민국',  USA: 'Republic of Korea' },
                1: { KOR: '미국',      USA: 'Unitied States of America' }
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
            account:    { name: 'account',  KOR: '계정이력',   USA: 'Account history' },
            state:      { name: 'state',    KOR: '상태이력',   USA: 'State history' },
            metro:      { name: 'metro',    KOR: '메트로이력', USA: 'Metro history' }
        }
    };


    Components.Castle.Model = castleModel;
})();