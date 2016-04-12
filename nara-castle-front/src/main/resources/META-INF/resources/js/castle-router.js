/**
 * Created by hkkang on 2016-04-07.
 */

var CastleRouter = CastleRouter || {};

CastleRouter.mapping = {
    '#/inquiry' : {
        resource : '/js/castellan/inquiry.jsx'
        , component : CastellanComponent.Inquiry
    },
    '#/register' : {
        resource : '/js/castellan/register.jsx'

    }
};





(function () {
    //
    var initialize = function () {
        //
        console.log('nara-router initialize');
        window.addEventListener('hashchange', navigate);
        navigate();
    };

    var navigate = function () {
        //
        var hashLocation = window.location.hash,
            mappingResource;
        console.log('nara-router navigated -> ' + hashLocation);

        //window.history.pushState(hashLocation, hashLocation);
        if (!CastleRouter.mapping[hashLocation]) {
            alert('URL 라우터 매핑정보가 없습니다');
            return;
        }

        mappingResource = CastleRouter.mapping[hashLocation].resource;
        if (!mappingResource) {
            alert('URL 라우터 매핑정보의 resource가 없습니다.');
            return;
        }
        console.log('Mapping resource : '  + mappingResource);
        CastleCommon.getJSX(CastleConst.CTX + mappingResource);
    };

    initialize();

})();

