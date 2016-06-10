/**
 * Created by hkkang on 2016-04-07.
 */

//__nara[__nara.namespace].ReactRouter = __nara[__nara.namespace].ReactRouter || {};


/**
 * <p>Object structure of Router url mapping</p>
 *
 * <pre>
 *  {
 *      'hashUrl' [object] : {
 *          type [string] : Mapping type [REQUEST | REDIRECT],
 *          resources [object array] : [
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
 *          resources : [
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
 *          resources : [
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
import { Object as commonObject, Ajax as commonAjax } from './nara-common.js';


let routerPublicNamespace = {};

( function () {
    //
    'use strict';

    let naraNamespace = __nara.namespace;
    //let publicNamespace = {};

    // Import module
    //let commonObject = __nara[naraNamespace].Object,
    //    commonAjax = __nara[naraNamespace].Ajax;



    // URL mapping type object
    let mappingType = {};

    commonObject.defineConstProperties(mappingType, {
        REQUEST: 'REQ',
        REDIRECT: 'RDR'
    });

    routerPublicNamespace.MappingType = mappingType;


    // URL mapper object
    class UrlMapper {
        //
        constructor() {
            this.mappings = {};
        }
        _convertUrl(url) {
            return url[url.length - 1] === '/' ? url.substring(0, url.length - 1) : url;
        }
        addRequest(url, resources) {
            //
            if (typeof url !== 'string' || !Array.isArray(resources) || resources.length === 0) {
                console.error('Invalid url or resources for ' + naraNamespace + ' router mapping -> url: ' + url + ', resources: ' + resources);
                return;
            }
            url = this._convertUrl(url);
            this.mappings[url] = {type: mappingType.REQUEST, resources: resources};
        }
        addRedirect(url, redirectUrl) {
            //
            if (typeof url !== 'string' || typeof redirectUrl !== 'string') {
                console.error('Invalid url or redirectUrl for ' + naraNamespace + ' router redirect mapping -> url: ' + url + ', redirectUrl: ' + redirectUrl);
                return;
            }
            url = this._convertUrl(url);
            this.mappings[url] = {type: mappingType.REDIRECT, redirectUrl: redirectUrl};
        }
        getMapping(url) {
            url = this._convertUrl(url);
            return this.mappings[url];
        }
    }

    let urlMapper = new UrlMapper();


    // Component cache object
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
        addErrorPage(errorCode, resourcePath, componentNamespace, componentName) {
            //
            this.errorPages[errorCode] = {
                path: resourcePath,
                componentNamespace: componentNamespace,
                componentName: componentName
            };
        }
        initRouter() {
            //
            if (!this.routerCallback || typeof this.routerCallback !== 'function') {
                console.error('Invalid router callback of ' + naraNamespace + ' nara-react-router initialization -> ' + this.callback);
            }
            let callback = this.routerCallback,
                errorPage = this.errorPages['404'];

            window.addEventListener('hashchange', function () {
                navigate(callback, errorPage);
            });
            navigate(callback, errorPage);
        }
    }

    routerPublicNamespace.createInitializer = function () {
        return new Initializer();
    };


    /**
     * Add url mappaing information at router
     *
     * @param url
     * @param resources
     */
    routerPublicNamespace.addMapping = function (url, resources) {
        //
        urlMapper.addRequest(url, resources);
    };

    /**
     * Add url mapping for redirect
     *
     * @param url
     * @param redirectUrl
     */
    routerPublicNamespace.addRedirect = function (url, redirectUrl) {
        //
        urlMapper.addRedirect(url, redirectUrl);
    };

    let navigate = function (loadedScriptCallback, errorPageMapping) {
        //
        let hashUrlAndParams = getHashUrlAndParams(),
            hashUrl = hashUrlAndParams.hashUrl,
            paramsObj = hashUrlAndParams.params,
            mappingInfo = urlMapper.getMapping(hashUrl);

        // Not exists mapping information
        if (!mappingInfo) {
            console.error('Not found url mapping from ' + naraNamespace + ' router -> url: ' + hashUrl);
            /*
            let errorResourcePath = errorPageMapping.path;

            commonAjax.getScript(errorResourcePath, function () {
                //
                let componentNamespace = errorPageMapping.componentNamespace,
                    componentName = errorPageMapping.componentName,
                    component = componentNamespace[componentName];

                loadedScriptCallback(component);
            });
            */
            return;
        }


        if (mappingInfo.type === mappingType.REQUEST) {
            doRequest(hashUrl, mappingInfo.resources, paramsObj, loadedScriptCallback);
        }
        else if (mappingInfo.type === mappingType.REDIRECT) {
            doRedirect(mappingInfo.redirectUrl);
        }
        else {
            console.error('Invalid mapping type in router mappings, something wrong...');
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


    //__nara[naraNamespace].ReactRouter = publicNamespace;
})();
export default routerPublicNamespace
