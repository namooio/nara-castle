/**
 * Created by hkkang on 2016-04-07.
 */

var NaraReactRouter = NaraReactRouter || { };

/**
 * <p>Router url mapping object structure</p>
 *
 * <pre>
 *  {
 *      'hashUrl' [object] : {
 *          type [string] : Mapping type [REQUEST | REDIRECT],
 *          resourcess [object array] : [
 *              {
 *                  path [string] : 'resource path',
 *                  component [object] : {
 *                      namespace [object] : Namespace of Component,
 *                      name [string] : Component name in namespace
 *                  }
 *              },
 *              ...
 *          ]
 *      },
 *      ...
 *  }
 * </pre>
 */

/**
 * <p>Router url mapping sample</p>
 *
 * <pre>
 *  {
 *      '#' : {
 *          type : 'REDIRECT',
 *          redirectUrl : '#/inquiery'
 *      },
 *      '#/inquiry' : {
 *          type : 'REQUEST',
 *          resourcess : [
 *              {
 *                  path : '/js/castellan/view.jsx',
 *                  component : {
 *                      namespace : Components.Castellan,
 *                      name : 'View'
 *                  }
 *              }
 *          ]
 *      },
 *      '#/castle/list' : {
 *          type : 'REQUEST',
 *          resourcess : [
 *              {
 *                  path : '/js/castle/list.jsx',
 *                  component : {
 *                      namespace : Components.Castle,
 *                      name : 'List'
 *                  }
 *              }
 *          ]
 *  }
 * </pre>
 */

( function () {
    //
    'use strict';

    // Import  module
    var commonAjax = NaraCommon.Ajax;


    var namespace = { };


    // Mapping type object
    var mappingType = {
        REQUEST: "REQ",
        REDIRECT: "RDR"
    };

    // URL mapping object
    var urlMapper = {
        mappings: { },
        getMapping: function (url) {
            return this.mappings[url];
        },
        addRequest: function (url, resources) {
            //
            if (typeof url !== 'string' || !Array.isArray(resources) || resources.length === 0) {
                console.error('Invaild url or resourcess for router mapping -> url: ' + url + ', resourcess: ' + resources);
                return;
            }
            this.mappings[url] = { type: mappingType.REQUEST, resources: resources };
        },
        addRedirect: function (url, redirectUrl) {
            //
            if (typeof url !== 'string' || typeof redirectUrl !== 'string' ) {
                console.error('Invaild url or redirectUrl for router redirect mapping -> url: ' + url + ', redirectUrl: ' + redirectUrl);
                return;
            }
            this.mappings[url] = { type: mappingType.REDIRECT, redirectUrl: redirectUrl };
        }
    };

    // Component cache object
    var componentCache = {
        caches : { },
        add: function (hashUrl, index, componentName, component) {
            this.caches[hashUrl + '_' + index + '_' + componentName] = component;
        },
        get: function (hashUrl, index, componentName) {
            return this.caches[hashUrl + '_' + index + '_' + componentName];
        }
    };


    /**
     *
     * <pre>
     *  initParam = {
     *      loadedScriptCallback [function] : Callback after script loading,
     *      pageNotFoundMapping [object] : {
     *          path [string] : Error page resource path,
     *          componentNameSapce [object] : Component namespace object,
     *          componentName [string] : 'Error'
     *      }
     *  }
     * </pre>
     *
     * @param initParam
     */
    namespace.initialize = function initialize(initParam) {
        //
        if (!initParam) {
            alert('Invaild initialization param of nara-react-router -> ' + initParam);
        }
        var callback = initParam.loadedScriptCallback,
            pageNotFoundMapping = initParam.pageNotFoundMapping;


        window.addEventListener('hashchange', function () {
            navigate(callback, pageNotFoundMapping);
        });
        navigate(callback, pageNotFoundMapping);
    }

    /**
     * Add url mappaing information at router
     *
     * @param url
     * @param resources
     */
    namespace.addMapping = function addMapping(url, resources) {
        //
        urlMapper.addRequest(url, resources);
    };

    /**
     * Add url mapping for redirect
     *
     * @param url
     * @param redirectUrl
     */
    namespace.addRedirect = function addRedirect(url, redirectUrl) {
        //
        urlMapper.addRedirect(url, redirectUrl);
    };

    function navigate(loadedScriptCallback, pageNotFoundMapping) {
        //
        var hashUrlAndParams = getHashUrlAndParams(),
            hashUrl = hashUrlAndParams.hashUrl,
            paramsObj = hashUrlAndParams.params,
            mappingInfo = urlMapper.getMapping(hashUrl);

        // Not exists mapping information
        if (!mappingInfo) {
            console.error('Not found url mapping from router -> url: ' + hashUrl);
            var errorResourcePath = pageNotFoundMapping.path;

            commonAjax.getScript(errorResourcePath, function () {
                //
                var componentNamespace = pageNotFoundMapping.componentNameSpace,
                    componentName = pageNotFoundMapping.componentName,
                    component = componentNamespace[componentName];

                loadedScriptCallback(component);
            });

            return;
        }


        if (mappingInfo.type === mappingType.REQUEST) {
            doRequest(hashUrl, mappingInfo.resources, paramsObj, loadedScriptCallback);
        }
        else if (mappingInfo.type === mappingType.REDIRECT) {
            doRedirect(mappingInfo.redirectUrl);
        }
        else {
            console.error('invalid mapping type in router mappings, something wrong...');
        }
    }

    function getHashUrlAndParams() {
        //
        var hashLocation = window.location.hash.split('?'),
            hashUrl = hashLocation[0],
            paramsText = hashLocation[1],
            paramsObj = {};

        if (paramsText) {
            var paramItems = paramsText.split('&');

            if (paramItems) {
                paramItems.forEach( function (item) {
                    var paramName = item.split('=')[0],
                        paramValue = item.split('=')[1];

                    paramsObj[paramName] = paramValue;
                });
            }
        }

        return {
            hashUrl: hashUrl,
            params : paramsObj
        };
    }

    function doRequest(hashUrl, mappingResources, paramsObj, callback) {
        //
        // TODO: 콜백 함수 비동기 아닌 동기로 작동하도록 (이전 스크립트 로드 된 후 다음 스크립트 로드의 콜백 동작하도록 보장해야함)
        mappingResources.forEach( function (resourceItem, index) {
            //
            var componentInfo = resourceItem.component,
                component = componentCache.get(hashUrl, index, componentInfo.name),
                executable = false;

            if (index === (mappingResources.length - 1)) {
                executable = true;
            }

            // Component exists in the cache
            if (component) {
                if (executable) {
                    callback(component, paramsObj)
                }
            }
            // Component not exists, get server
            else {
                commonAjax.getScript(resourceItem.path, function () {
                    //
                    component = componentInfo.namespace[componentInfo.name];
                    componentCache.add(hashUrl, index, componentInfo.name, component);

                    if (executable) {
                        callback(component, paramsObj);
                    }
                });
            }
        });
    }

    function doRedirect(redirectUrl) {
        //
        window.location.hash = redirectUrl;
    }


    namespace.MappingType = mappingType;

    NaraReactRouter = namespace;
})();

