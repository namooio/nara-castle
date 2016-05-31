/**
 * Created by hkkang on 2016-04-12.
 */

castle.component.common.Error = castle.component.common.Error || {};


( function () {
    //
    'use strict';

    // Import component module
    const MainComponent = castle.component.common.Main;


    // Define Content properties name
    const ErrorModel = {
         //
        messages: {
            notFoundPage: {
                title:      { KOR: '404', USA: '404' },
                message:    { KOR: '요청 된 URL 주소가 잘못 되었습니다.', USA: 'Invalid the requested URL' }
            }
        },
        buttons: {
            back: { KOR: '뒤로가기', USA: 'Previous page' }
        }
    };


    let ErrorPage = React.createClass({
        //
        // event
        backBtnClick() {
            window.history.back();
        },
        render() {
            const MESSAGE = ErrorModel.messages,
                BUTTONS = ErrorModel.buttons,
                lang = MainComponent.lang;

            return (
                <article>
                    <div className="jumbotron">
                        <div className="container">
                            <h1>{MESSAGE.notFoundPage.title[lang]}</h1>
                            <p>{MESSAGE.notFoundPage.message[lang]}</p>
                            <p><a href="javascript:;" className="btn btn-primary btn-lg"
                                  onClick={this.backBtnClick}>{BUTTONS.back[lang]}</a></p>
                        </div>
                    </div>
                </article>
            );
        }
    });


    castle.component.common.Error = ErrorPage;
})();