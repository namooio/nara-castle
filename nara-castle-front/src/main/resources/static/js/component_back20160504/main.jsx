/**
 * Created by hkkang on 2016-04-05.
 */
Components.Main = Components.Main || { };


( function () {
    //
    'use strict';

    // Import component module
    var naraReactRouter = NaraReactRouter,
        castleCommon = CastleCommon,
        castleConst = CastleCommon.Const;


    // Define component
    var MainPage = React.createClass({
        //
        statics: {
            lang: 'KOR'
        },
        propTypes: {
            contentComponent: React.PropTypes.func.isRequired,
            params: React.PropTypes.object
        },
        getInitialState : function () {
            return {};
        },
        componentDidMount : function () {
            var lang;

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
        changeLanguage : function (lang) {
            this.setState({lang: lang});
            MainPage.lang = lang;
        },
        render: function () {
            return (
                <div >
                    <header>
                        <TopMenu changeLanguage={this.changeLanguage}/>
                    </header>

                    <section >
                        <Content contentComponent={this.props.contentComponent} params={this.props.params}/>
                    </section>
                </div>
            );
        }
    });

    /**
     * Castellan 검색 폼 컴포넌트
     */
    var TopMenu = React.createClass({
        //
        propTypes: {
            changeLanguage: React.PropTypes.func.isRequired
        },
        changeLanguageClick : function (event) {
            var lang = $(event.target).data('lang');
            this.props.changeLanguage(lang);
        },
        render: function () {
            var lang = MainPage.lang
                , displayLang;

            if (lang === 'KOR') {
                displayLang = '한국어';
            } else if (lang === 'USA') {
                displayLang = 'English';
            }

            return (
                <nav className="navbar navbar-inverse navbar-static-top">
                    <div className="container">
                        <div className="navbar-header">
                            <a className="navbar-brand" href="#">Castellan</a>
                        </div>
                        <div className="collapse navbar-collapse">
                            <ui className="nav navbar-nav">
                                <li><a href="#/castles" >목록</a></li>
                            </ui>

                            <ul className="nav navbar-nav navbar-right">
                                <li className="dropdown">
                                    <a href="#" className="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Language ({displayLang}) <span className="caret"/></a>
                                    <ul className="dropdown-menu" role="menu">
                                        <li><a href="javascript:" onClick={this.changeLanguageClick} data-lang="KOR">한국어</a></li>
                                        <li><a href="javascript:" onClick={this.changeLanguageClick} data-lang="USA">English</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                    </div>
                </nav>
            );
        }
    });

    var Content = React.createClass({
        //
        propTypes: {
            contentComponent: React.PropTypes.func.isRequired,
            params: React.PropTypes.object
        },
        render: function () {
            return (
                React.createElement(this.props.contentComponent, this.props.params)
            );
        }
    });

    // Initialize nara router
    var renderLayout = function (contentComponent, params) {
        ReactDOM.render(<MainPage contentComponent={contentComponent} params={params}/>, castleCommon.getContentsJDom());
    };

    var initParam = {
        loadedScriptCallback: renderLayout,
        pageNotFoundMapping: {
            path: castleConst.CTX + '/resources/js/common/error.jsx',
            componentNameSpace: Components.Common,
            componentName: 'Error'
        }
    };

    naraReactRouter.initialize(initParam);

    Components.Main = MainPage;
})();

