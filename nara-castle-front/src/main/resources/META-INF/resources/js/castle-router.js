/**
 * Created by hkkang on 2016-04-07.
 */

var CastleRouter = CastleRouter || {};


(function () {
    //
    // Import component module
    var castleCommon = CastleCommon;

    var namespace = {}
        , mapping = {}
        , componentCache = {};

    // Castellan mapping
    addRouterMapping('#/castellan/view',        [{path: '/js/castellan/view.jsx',       component: {namespace: Components.Castellan,    name: 'View',           render: 'renderLayout'}}]);
    addRouterMapping('#/castellan/register',    [{path: '/js/castellan/register.jsx',   component: {namespace: Components.Castellan,    name: 'Registerer',     render: 'renderLayout'}}]);

    // Castle mapping
    addRouterMapping('#/castles',               [{path: '/js/castle/list.jsx',          component: {namespace: Components.Castle,       name: 'List',           render: 'renderLayout'}}]);
    addRouterMapping('#/castle/detail',         [{path: '/js/castle/detail.jsx',        component: {namespace: Components.Castle,       name: 'Detail',         render: 'renderLayout'}}]);


    namespace.initialize = function (scriptLoadCallback) {
        //
        window.addEventListener('hashchange', function () {
            navigate(scriptLoadCallback);
        });
        navigate(scriptLoadCallback);
    };

    /**
     * Add url mappaing information at router
     *
     * <p>Router url mapping object structure</p>
     * <pre>
     * {
     *  'hashUrl' [object] : {
     *      resources [object array] : [
     *          {
     *              path [string] : 'resource path',
     *              render [function] : Layout rendering function of JavaScript React
     *          },
     *          ...
     *      ]
     *  },
     *  ...
     * }
     * </pre>
     * <p>Router url mapping sample</p>
     * <pre>
     * CastleRouter.mapping = {
     *  '#/inquiry' : {
     *      resources : [
     *          {
     *              path : '/js/castellan/view.jsx',
     *              render : CastleComponent.View.renderLayout
     *          }
     *      ]
     *  },
     *  '#/register' : {
     *      resources : [
     *          {
     *              path : '/js/castellan/register.jsx',
     *              render : CastleComponent.TopMenu.renderLayout
     *          }
     *      ]
     * }
     *</pre>
     *
     * @param url
     * @param resources
     * @param components
     */
    function addRouterMapping(url, resources) {
        //
        if (!mapping) {
            mapping = {};
        }

        if (typeof url === 'string' && Array.isArray(resources)) {
            mapping[url] = {resources: resources};
        }
        else if (typeof url === 'string' && typeof resources === 'string') {
            mapping[url]
        }
        else {
            console.error('Invaild url or resources for router mapping -> url: ' + url + ', resources' + resources);
        }
    }

    function navigate(scriptLoadCallback) {
        //
        var hashLocation = window.location.hash.split('?')
            , hashUrl = hashLocation[0]
            , paramsText = hashLocation[1]
            , paramObj = {}
            , mappingResources;

        console.debug('Execute castle-router.js navigate()');

        if (!mapping[hashUrl]) {
            console.error('Not found url mapping from router -> url: ' + hashUrl);

            var errorResourcePath = '/js/castle/error.jsx';


            castleCommon.getJSX(CastleConst.CTX + errorResourcePath, function () {
                //
                console.log('Execute castle-router.js navigate() error page');
                var componentNamespace = Components.Castle
                    , componentName = 'Common'
                    , component = componentNamespace[componentName];

                scriptLoadCallback(component);
            });
            return;
        }
        mappingResources = mapping[hashUrl].resources;
        if (!mappingResources || mappingResources.length === 0) {
            console.error('Resource is empty -> url: ' + hashUrl + ', resource: ' + mappingResources);
            return;
        }

        if (paramsText) {
            var paramItems = paramsText.split('&');

            if (paramItems) {
                paramItems.forEach(function (item, index) {
                    var paramName = item.split('=')[0]
                        , paramValue = item.split('=')[1];

                    paramObj[paramName] = paramValue;
                });
            }
        }


        mappingResources.forEach(function (resourceItem) {
            //
            var resourceComponent = resourceItem.component,
                component = resourceComponent.namespace[resourceComponent.name];

            if (component) {
                scriptLoadCallback(component, paramObj);
            }
            else {
                castleCommon.getJSX(CastleConst.CTX + resourceItem.path, function () {
                    //
                    console.log('Execute castle-router.js navigate() getJSX callback');
                    componentCache[resourceComponent.namespace.name + resourceComponent.name] = resourceComponent;
                    component = resourceComponent.namespace[resourceComponent.name];

                    scriptLoadCallback(component, paramObj);
                });
            }
        });
    }

    CastleRouter = namespace;
    CastleRouter.mapping = mapping;
})();

