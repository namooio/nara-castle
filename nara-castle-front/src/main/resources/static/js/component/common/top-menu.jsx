 /**
 * Created by hkkang on 2016-04-05.
 */
Components.Common.TopMenu = Components.Common.TopMenu || {};


 ( function () {
     //
     'use strict';

     // Define attributes name
     const castleTopMenuModel = {
         attrs: {
             castles: { KOR: '목록', USA: 'List'}
         }
     };

     /**
      * Castle 공통 상단 메뉴 컴포넌트
      */
     let TopMenu = React.createClass({
         //
         propTypes: {
             changeLanguage: React.PropTypes.func.isRequired
         },
         changeLanguageClick(event) {
             let lang = $(event.target).data('lang');
             this.props.changeLanguage(lang);
         },
         render() {
             let MENUS = castleTopMenuModel.attrs,
                 lang = this.props.getLanguage(),
                 displayLang;

             if (lang === 'KOR') {
                 displayLang = '한국어';
             } else if (lang === 'USA') {
                 displayLang = 'English';
             }

             return (
                 <nav className="navbar navbar-inverse navbar-static-top">
                     <div className="container">
                         <div className="navbar-header">
                             <a className="navbar-brand" href="#">Castle</a>
                         </div>
                         <div className="collapse navbar-collapse">
                             <ui className="nav navbar-nav">
                                 <li><a href="#/castles">{MENUS.castles[lang]}</a></li>
                             </ui>

                             <ul className="nav navbar-nav navbar-right">
                                 <li className="dropdown">
                                     <a href="#" className="dropdown-toggle" data-toggle="dropdown" role="button"
                                        aria-expanded="false">Language ({displayLang}) <span className="caret"/></a>
                                     <ul className="dropdown-menu" role="menu">
                                         <li><a href="javascript:" onClick={this.changeLanguageClick} data-lang="KOR">한국어</a>
                                         </li>
                                         <li><a href="javascript:" onClick={this.changeLanguageClick} data-lang="USA">English</a>
                                         </li>
                                     </ul>
                                 </li>
                             </ul>
                         </div>
                     </div>
                 </nav>
             );
         }
     });


     Components.Common.TopMenu = TopMenu;
 })();