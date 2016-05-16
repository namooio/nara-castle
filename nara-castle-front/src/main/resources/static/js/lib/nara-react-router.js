/**
 * Created by hkkang on 2016-04-07.
 */

NaraCommon.ReactRouter = NaraCommon.ReactRouter || {};

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

    let publicNamespace = {};

    // Import module
    let commonObject = NaraCommon.Object,
        commonAjax = NaraCommon.Ajax;


    // URL mapping type object
    let mappingType = {};
    commonObject.defineConstProperties(mappingType, {
        REQUEST: 'REQ',
        REDIRECT: 'RDR'
    });

    publicNamespace.MappingType = mappingType;


    // URL mapper object
    /*
    let urlMapper = {
        //
        mappings: {},
        getMapping: function (url) {
            return this.mappings[url];
        },
        addRequest: function (url, resources) {
            //
            if (typeof url !== 'string' || !Array.isArray(resources) || resources.length === 0) {
                console.error('Invaild url or resourcess for router mapping -> url: ' + url + ', resourcess: ' + resources);
                return;
            }
            this.mappings[url] = {type: mappingType.REQUEST, resources: resources};
        },
        addRedirect: function (url, redirectUrl) {
            //
            if (typeof url !== 'string' || typeof redirectUrl !== 'string') {
                console.error('Invaild url or redirectUrl for router redirect mapping -> url: ' + url + ', redirectUrl: ' + redirectUrl);
                return;
            }
            this.mappings[url] = {type: mappingType.REDIRECT, redirectUrl: redirectUrl};
        }
    };
    */
    class UrlMapper {
        //
        constructor() {
            this.mappings = {name: 'kim'};
        }
        addRequest(url, resources) {
            //
            if (typeof url !== 'string' || !Array.isArray(resources) || resources.length === 0) {
                console.error('Invaild url or resourcess for router mapping -> url: ' + url + ', resourcess: ' + resources);
                return;
            }
            this.mappings[url] = {type: mappingType.REQUEST, resources: resources};
        }
        addRedirect(url, redirectUrl) {
            //
            if (typeof url !== 'string' || typeof redirectUrl !== 'string') {
                console.error('Invaild url or redirectUrl for router redirect mapping -> url: ' + url + ', redirectUrl: ' + redirectUrl);
                return;
            }
            this.mappings[url] = {type: mappingType.REDIRECT, redirectUrl: redirectUrl};
        }
        getMapping(url) {
            return this.mappings[url];
        }
    }
    let urlMapper = new UrlMapper();


    // Component cache object
    /*
    let componentCache = {
        //
        caches: {},
        add: function (hashUrl, componentName, component) {
            this.caches[hashUrl + '_' + componentName] = component;
        },
        get: function (hashUrl, componentName) {
            return this.caches[hashUrl + '_' + componentName];
        }
    };
    */

    class ComponentCache {
        //
        constructor() {
            this.caches = {};
        }
        get(hashUrl, componentName) {
            return this.caches[hashUrl + '_' + componentName];
        }
        add(hashUrl, componentName, component) {
            this.caches[hashUrl + '_' + componentName] = component;
        }
    }
    let componentCache = new ComponentCache();


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
    publicNamespace.initialize = function (initParam) {
        //
        if (!initParam) {
            alert('Invaild initialization param of nara-react-router -> ' + initParam);
        }
        let callback = initParam.loadedScriptCallback,
            pageNotFoundMapping = initParam.pageNotFoundMapping;


        window.addEventListener('hashchange', function () {
            navigate(callback, pageNotFoundMapping);
        });
        navigate(callback, pageNotFoundMapping);
    };

    /*
    let Initializer = function () {
        //
        this.routerCallback = function () {
        };
        this.errorPages = {};
    };
    Initializer.prototype.setRouterCallback = function (callback) {
        //
        this.routerCallback = callback;
    };
    Initializer.prototype.addErrorPage = function (errorCode, resourcePath, componeneNamespace, componentName) {
        //
        this.errorPages[errorCode] = {
            path: resourcePath,
            componentNamespace: Components.Common,
            componentName: 'Error'
        };
    };
    Initializer.prototype.initialize = function () {
        //
        if (!this.routerCallback || typeof this.routerCallback !== 'function') {
            console.error('Invaild router callback of nara-react-router initialization -> ' + this.callback);
        }
        let callback = this.routerCallback,
            errorPage = this.errorPages['404'];

        window.addEventListener('hashchange', function () {
            navigate(callback, errorPage);
        });
        navigate(callback, errorPage);
    };
    */

    class Initializer {
        //
        constructor() {
            this.errorPages = {};
            this.routerCallback = function () {};
        }
        setRouterCallback(callback) {
            //
            this.routerCallback = callback;
        }
        addErrorPage(errorCode, resourcePath, componeneNamespace, componentName) {
            //
            this.errorPages[errorCode] = {
                path: resourcePath,
                componentNamespace: Components.Common,
                componentName: 'Error'
            };
        }
        initRouter() {
            //
            if (!this.routerCallback || typeof this.routerCallback !== 'function') {
                console.error('Invaild router callback of nara-react-router initialization -> ' + this.callback);
            }
            let callback = this.routerCallback,
                errorPage = this.errorPages['404'];

            window.addEventListener('hashchange', function () {
                navigate(callback, errorPage);
            });
            navigate(callback, errorPage);
        }
    }


    publicNamespace.createInitializer = function () {
        return new Initializer();
    };

    /**
     * Add url mappaing information at router
     *
     * @param url
     * @param resources
     */
    publicNamespace.addMapping = function (url, resources) {
        //
        urlMapper.addRequest(url, resources);
    };

    /**
     * Add url mapping for redirect
     *
     * @param url
     * @param redirectUrl
     */
    publicNamespace.addRedirect = function (url, redirectUrl) {
        //
        urlMapper.addRedirect(url, redirectUrl);
    };

    let navigate = function (loadedScriptCallback, pageNotFoundMapping) {
        //
        let hashUrlAndParams = getHashUrlAndParams(),
            hashUrl = hashUrlAndParams.hashUrl,
            paramsObj = hashUrlAndParams.params,
            mappingInfo = urlMapper.getMapping(hashUrl);

        // Not exists mapping information
        if (!mappingInfo) {
            console.error('Not found url mapping from router -> url: ' + hashUrl);
            let errorResourcePath = pageNotFoundMapping.path;

            commonAjax.getScript(errorResourcePath, function () {
                //
                let componentNamespace = pageNotFoundMapping.componentNameSpace,
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

    let getHashUrlAndParams = function () {
        //
        let hashLocation = window.location.hash.split('?'),
            hashUrl = hashLocation[0],
            paramsText = hashLocation[1],
            paramsObj = {};

        if (paramsText) {
            let paramItems = paramsText.split('&');

            if (paramItems) {
                paramItems.forEach(function (item) {
                    let paramName = item.split('=')[0],
                        paramValue = item.split('=')[1];

                    paramsObj[paramName] = paramValue;
                });
            }
        }

        return {
            hashUrl: hashUrl,
            params: paramsObj
        };
    };

    let doRequest = function (hashUrl, mappingResources, paramsObj, callback) {
        //
        let callbackComponent,
            getScripts = [];

        mappingResources.forEach(function (resourceItem, index) {
            //
            let componentInfo = resourceItem.component,
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
                mappingResources.forEach(function (resourceItem, index) {
                    //
                    let componentInfo = resourceItem.component,
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

    let doRedirect = function (redirectUrl) {
        //
        window.location.hash = redirectUrl;
    };


    NaraCommon.ReactRouter = publicNamespace;
})();
