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
        componentCache = {},
        mappingType = {
            REQUEST: "REQ",
            REDIRECT: "RDR"
        };

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
    function addMapping(url, resources) {
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
            var errorResourcePath = '/resources/js/common/error.jsx';

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

        // TODO: 콜백 함수 비동기 아닌 동기로 작동하도록 (이전 스크립트 로드 된 후 다음 스크립트 로드의 콜백 동작하도록 보장해야함)
        mappingResources.forEach(function (resourceItem, index) {
            //
            var resourceComponent = resourceItem.component,
                component = resourceComponent.namespace[resourceComponent.name],
                executable = false;

            if (index === (mappingResources.length - 1)) {
               executable = true;
            }

            if (executable && component) {
                scriptLoadCallback(component, paramObj);
            }
            else {
                getJSX(resourceItem.path, function () {
                    //
                    if (executable) {
                        componentCache[resourceComponent.namespace.name + resourceComponent.name] = resourceComponent;
                        component = resourceComponent.namespace[resourceComponent.name];

                        scriptLoadCallback(component, paramObj);
                    }
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
    namespace.addMapping = addMapping;
    namespace.MappingType = mappingType;
    namespace.mapping = mapping;

    NaraReactRouter = namespace;
})();

