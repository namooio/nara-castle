/**
 * Created by hkkang on 2016-04-07.
 */

var CastleRouter = CastleRouter || {};


(function () {
    //
    // Import component module
    'use strict';

    var castleConst = CastleConst,
        naraReactRouter = NaraReactRouter;

    // Castellan mapping
    naraReactRouter.addRouterMapping('#/castellan/view',        [{path: castleConst.CTX + '/js/castellan/view.jsx',       component: {namespace: Components.Castellan,    name: 'View',           render: 'renderLayout'}}]);
    naraReactRouter.addRouterMapping('#/castellan/register',    [{path: castleConst.CTX + '/js/castellan/register.jsx',   component: {namespace: Components.Castellan,    name: 'Registerer',     render: 'renderLayout'}}]);

    // Castle mapping
    naraReactRouter.addRouterMapping('#/castles',               [{path: castleConst.CTX + '/js/castle/list.jsx',          component: {namespace: Components.Castle,       name: 'List',           render: 'renderLayout'}}]);
    naraReactRouter.addRouterMapping('#/castle/detail',         [{path: castleConst.CTX + '/js/castle/detail.jsx',        component: {namespace: Components.Castle,       name: 'Detail',         render: 'renderLayout'}}]);


})();

