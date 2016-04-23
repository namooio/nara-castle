/**
 * Created by hkkang on 2016-04-07.
 */

var NaraReactRouter = NaraReactRouter || {};


(function () {
    //
    // Import component module
    'use strict';

    var namespace = {},
        mapping = {},
        componentCache = {};

    function initialize(scriptLoadCallback) {
        //
        window.addEventListener('hashchange', function () {
            navigate(scriptLoadCallback);
        });
        navigate(scriptLoadCallback);
    }

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
     */
    function addRouterMapping(url, resources) {
        //
        if (!mapping) {
            mapping = {};
        }

        if (typeof url === 'string' && Array.isArray(resources)) {
            mapping[url] = { resources: resources };
        }
        else if (typeof url === 'string' && typeof resources === 'string') {
            mapping[url] = { resource: resources };
        }
        else {
            console.error('Invaild url or resources for router mapping -> url: ' + url + ', resources' + resources);
        }
    }

    function navigate(scriptLoadCallback) {
        //
        console.debug('Navigate!!');

        var hashLocation = window.location.hash.split('?'),
            hashUrl = hashLocation[0],
            paramsText = hashLocation[1],
            paramObj = {},
            mappingResources;

        console.info('Execute castle-router.js navigate()');

        if (!mapping[hashUrl]) {
            console.error('Not found url mapping from router -> url: ' + hashUrl);
            var errorResourcePath = '/js/castle/error.jsx';

            getJSX(errorResourcePath, function () {
                //
                console.info('Execute castle-router.js navigate() error page');
                var componentNamespace = Components.Castle,
                    componentName = 'Common',
                    component = componentNamespace[componentName];

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
                paramItems.forEach( function (item) {
                    var paramName = item.split('=')[0],
                        paramValue = item.split('=')[1];

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
                getJSX(resourceItem.path, function () {
                    //
                    componentCache[resourceComponent.namespace.name + resourceComponent.name] = resourceComponent;
                    component = resourceComponent.namespace[resourceComponent.name];

                    scriptLoadCallback(component, paramObj);
                });
            }
        });
    }

    function getJSX(url, callback) {
        //
        $.ajax({
            url: url,
            method: 'GET',
            cache: false,
            success : function (result) {
                babel.transform.run(result);

                if (callback && typeof callback === 'function') {
                    callback(result);
                }
            },
            error: function (result) {
                alert('Fail CastleCommon.getJSX');
                console.info(result);
            }
        });
    }

    namespace.initialize = initialize;
    namespace.addRouterMapping = addRouterMapping;

    NaraReactRouter = namespace;
    NaraReactRouter.mapping = mapping;
})();

