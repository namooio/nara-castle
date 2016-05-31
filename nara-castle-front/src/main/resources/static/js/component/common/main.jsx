/**
 * Created by hkkang on 2016-04-05.
 */

castle.component.common.Main = castle.component.common.Main || {};


( function () {
    //
    'use strict';

    // Import component module
    let NaraReactRouter = NaraCommon.ReactRouter,
        common = castle.common,
        constant = castle.common.Const,
        commonComponent = castle.component.common;


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
        // overriding
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
            }
            else if (lang === 'en') {
                lang = 'USA';
            }

            this.changeLanguage(lang);
        },
        // custom
        getLanguage() {
            return this.state.lang;
        },
        changeLanguage(lang) {
            this.setState({ lang: lang });
            MainPage.lang = lang;
        },
        render() {
            //
            return (
                <div >
                    <header>
                        <commonComponent.TopMenu changeLanguage={this.changeLanguage} getLanguage={this.getLanguage}/>
                    </header>

                    <section>
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
            //
            return (
                React.createElement(this.props.contentComponent, this.props.params)
            );
        }
    });

    // Initialize nara router
    let initializer = NaraReactRouter.createInitializer();

    initializer.setRouterCallback( function (contentComponent, params) {
        //
        ReactDOM.render(
            <MainPage
                contentComponent={contentComponent}
                params={params}
            />, common.getCastleMainJDom());
    });
    initializer.addErrorPage('404', constant.PAV_CTX_RSRC + '/resources/js/component/common/error.jsx', commonComponent, 'Error');


    initializer.initRouter();


    castle.component.common.Main = MainPage;
})();