 /**
 * Created by hkkang on 2016-04-05.
 */
Components.Common.Main = Components.Common.Main || {};


 ( function () {
     //
     'use strict';

     // Import component module
     let naraReactRouter = NaraCommon.ReactRouter,
         castleCommon = CastleCommon,
         castleConst = CastleCommon.Const,
         castleTopMenu = Components.Common.TopMenu;


     // Define component
     let MainPage = React.createClass({
         //
         statics: {
             lang: 'KOR'
         },
         propTypes: {
             contentComponent: React.PropTypes.func.isRequired,
             params: React.PropTypes.object
         },
         getInitialState() {
             return {};
         },
         componentDidMount() {
             let lang;

             if (navigator.language) lang = navigator.language;
             else if (navigator.browserLanguage) lang = navigator.browserLanguage;
             else if (navigator.systemLanguage) lang = navigator.systemLanguage;
             else if (navigator.userLanguage) lang = navigator.userLanguage;

             if (lang === 'ko') {
                 lang = 'KOR';
             } else if (lang === 'en') {
                 lang = 'USA';
             }

             this.changeLanguage(lang);
         },
         getLanguage() {
             return this.state.lang;
         },
         changeLanguage(lang) {
             this.setState({ lang: lang });
             MainPage.lang = lang;
         },
         render() {
             return (
                 <div >
                     <header>
                         <Components.Common.TopMenu changeLanguage={this.changeLanguage} getLanguage={this.getLanguage}/>
                     </header>

                     <section >
                         <Content contentComponent={this.props.contentComponent} params={this.props.params}/>
                     </section>
                 </div>
             );
         }
     });


     let Content = React.createClass({
         //
         propTypes: {
             contentComponent: React.PropTypes.func.isRequired,
             params: React.PropTypes.object
         },
         render() {
             return (
                 React.createElement(this.props.contentComponent, this.props.params)
             );
         }
     });

     // Initialize nara router
     /*
      let renderLayout = function (contentComponent, params) {
      ReactDOM.render(<MainPage contentComponent={contentComponent} params={params}/>, castleCommon.getCastleMainJDom());
      };

      let initParam = {
      loadedScriptCallback: renderLayout,
      pageNotFoundMapping: {
      path: castleConst.CTX + '/resources/js/common/error.jsx',
      componentNameSpace: Components.Common,
      componentName: 'Error'
      }
      };

      naraReactRouter.initialize(initParam);
      */


     let initializer = naraReactRouter.createInitializer();

     initializer.setRouterCallback( function (contentComponent, params) {
         ReactDOM.render(
             <MainPage
                 contentComponent={contentComponent}
                 params={params}
             />, castleCommon.getCastleMainJDom());
     });
     initializer.addErrorPage('404', castleConst.CTX + '/resources/js/common/error.jsx', Components.Common, 'Error');


     initializer.initRouter();


     Components.Common.Main = MainPage;
 })();