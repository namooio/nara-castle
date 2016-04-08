/**
 * Created by hkkang on 2016-04-05.
 */
CastellanComponent.Main = CastellanComponent.Main || {};


(function () {
    /**
     * Castellan 검색 폼 컴포넌트
     */
    TopMenu = React.createClass({
        inquiryBtnClick : function (event) {
            //
            $(event.target).tab();
            CastellanCommon.getJSX(CONST.CTX + '/js/castellan/inquiry.jsx');
        },
        registerBtnClick : function (event) {
            //
            $(event.target).tab();
            CastellanCommon.getJSX(CONST.CTX + '/js/castellan/register.jsx');
        },
        bundleBtnClick : function () {
            //

        },
        render: function () {
            return (
                <nav className="navbar navbar-inverse navbar-static-top">
                    <div className="container">
                        <div className="navbar-header">
                            <a className="navbar-brand" href="#">Castellan</a>
                        </div>
                        <div className="collapse navbar-collapse">
                            <ui className="nav navbar-nav">
                                <li><a href="#inquiry" onClick={this.inquiryBtnClick}>조회</a></li>
                                <li><a href="#register" onClick={this.registerBtnClick}>등록</a></li>
                                <li><a href="#/bundle">CastellanBundle</a></li>
                            </ui>
                        </div>
                    </div>
                </nav>
            );
        }
    });

    ReactDOM.render(
        <TopMenu />, $('#top-menu')[0]
    );
})();

