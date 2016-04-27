/**
 * Created by hkkang on 2016-04-12.
 */
Components.Castle.Common = Components.Castle.Common || {};

(function () {
    //
    'use strict';
    console.debug('hello error');

    // Import component module
    var MainComponent = Components.Main;

    // Define Content properties name
    var contentProps = {
        messages : {
            404: { title: { KOR: '404', USA: '404'}, message: { KOR: '요청 된 URL 주소가 잘못 되었습니다.', USA: 'Invalid the requested URL'} }
        },
        buttons : {
            back: { KOR: '뒤로가기', USA: 'Prev page'}
        }
    };


    var ErrorPage = React.createClass({
        backBtnClick: function () {
            window.history.back();
        },
        render: function () {
            var lang = MainComponent.lang,
                messages = contentProps.messages,
                buttons = contentProps.buttons;

            return (
                <article>
                    <div className="jumbotron">
                        <div className="container">
                            <h1>{messages['404'].title[lang]}</h1>
                            <p>{messages['404'].message[lang]}</p>
                            <p><a href="javascript:;" className="btn btn-primary btn-lg" onClick={this.backBtnClick}>{buttons.back[lang]}</a></p>
                        </div>
                    </div>
                </article>
            );
        }
    });


    Components.Castle.Common = ErrorPage;
})();
