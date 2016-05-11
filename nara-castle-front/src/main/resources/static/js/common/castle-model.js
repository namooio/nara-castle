/**
 * Created by hkkang on 2016-04-12.
 */
Components.Castle.Model = Components.Castle.Model || { };

( function () {
    //
    'use strict';


    // Define Content attributes name
    const castleModel = {
        buttons : {
            search:     { KOR: '검색',      USA: 'Search' },
            save:       { KOR: '저장',      USA: 'Save' },
            modify:     { KOR: '수정',      USA: 'Modify' },
            remove:     { KOR: '삭제',      USA: 'Remove' },
            cancel:     { KOR: '취소',      USA: 'Cancel' },
            complete:   { KOR: '입력완료',  USA: 'Complete' },
            add:        { KOR: '추가',      USA: 'Add' }
        },
        enums: {
            state: {
                Ready:      { name: 'Ready',        KOR: '준비', USA: 'Ready' },
                Open:       { name: 'Open',         KOR: '사용', USA: 'Open' },
                Suspended:  { name: 'Suspended',    KOR: '중단', USA: 'Suspended' },
                Closed:     { name: 'Closed',       KOR: '닫힘', USA: 'Closed' }
            },
            locale: {
                ko:     { name: 'ko',       KOR: '대한민국',  USA: 'Republic of Korea' },
                ko_KR:  { name: 'ko_KR',    KOR: '대한민국',  USA: 'Republic of Korea' },
                us:     { name: 'us',       KOR: '미국',      USA: 'Unitied States of America' },
                en_US:  { name: 'en_US',    KOR: '미국',      USA: 'Unitied States of America' }
            },
            language: {
                ko:     { name: 'ko',   KOR: '한국어', USA: 'Korean' },
                kor:    { name: 'kor',  KOR: '한국어', USA: 'Korean' },
                en:     { name: 'en',   KOR: '영어', USA: 'English' },
                eng:    { name: 'eng',  KOR: '영어', USA: 'English' }
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
        }
    };


    Components.Castle.Model = castleModel;
})();