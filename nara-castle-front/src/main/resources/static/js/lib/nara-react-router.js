/**
 * Created by hkkang on 2016-04-07.
 */

var NaraReactRouter = NaraReactRouter || { };

/**
 * <p>Object structure of Router url mapping</p>
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

    var namespace = { };

    // Import module
    var commonAjax = NaraCommon.Ajax;


    // URL mapping type object
    var mappingType = {
        REQUEST: "REQ",
        REDIRECT: "RDT"
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
        add: function (hashUrl, componentName, component) {
            this.caches[hashUrl + '_' + componentName] = component;
        },
        get: function (hashUrl, componentName) {
            return this.caches[hashUrl + '_' + componentName];
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
    namespace.initialize = function (initParam) {
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
    };

    /**
     * Add url mappaing information at router
     *
     * @param url
     * @param resources
     */
    namespace.addMapping = function (url, resources) {
        //
        urlMapper.addRequest(url, resources);
    };

    /**
     * Add url mapping for redirect
     *
     * @param url
     * @param redirectUrl
     */
    namespace.addRedirect = function (url, redirectUrl) {
        //
        urlMapper.addRedirect(url, redirectUrl);
    };

    var navigate = function (loadedScriptCallback, pageNotFoundMapping) {
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
    };

    var getHashUrlAndParams = function () {
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
    };

    var doRequest = function (hashUrl, mappingResources, paramsObj, callback) {
        //
        var callbackComponent,
            getScripts = [];

        mappingResources.forEach( function (resourceItem, index) {
            //
            var componentInfo = resourceItem.component,
                component = componentCache.get(hashUrl, componentInfo.name),
                executable = (index === (mappingResources.length - 1));


            // Component exists in the cache
            if (component) {
                if (executable) {
                    callbackComponent = component;
                }
            }
            // Component not exists, get server
            else {
                getScripts.push(resourceItem.path);
            }
        });

        if (getScripts.length > 0) {
            commonAjax.getScripts(getScripts, function () {
                //
                mappingResources.forEach( function (resourceItem, index) {
                    //
                    var componentInfo = resourceItem.component,
                        component = componentInfo.namespace[componentInfo.name],
                        executable = (index === (mappingResources.length - 1));

                    componentCache.add(hashUrl, componentInfo.name, component);

                    if (executable) {
                        callbackComponent = component;
                    }
                });

                callback(callbackComponent, paramsObj);
            });
        }
        else {
            callback(callbackComponent, paramsObj);
        }
    };

    var doRedirect = function(redirectUrl) {
        //
        window.location.hash = redirectUrl;
    };


    namespace.MappingType = mappingType;

    NaraReactRouter = namespace;
})();

