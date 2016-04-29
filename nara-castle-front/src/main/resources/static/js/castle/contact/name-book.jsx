/**
 * Created by hkkang on 2016-04-12.
 */
Components.Castle.NameBook = Components.Castle.NameBook || { };

( function () {
    //
    'use strict';

    // Import component module
    var commonAjax = NaraCommon.Ajax,
        constant = CastleCommon.Const,
        mainComponent = Components.Main,
        castleModel = Components.Castle.Model;


    // Define Content attributes name
    var castleNameModel = {
        attrs: {
            familyName:     { name: 'familyName',   KOR: '성',       USA: 'Family name' },
            firstName:      { name: 'firstName',    KOR: '이름',     USA: 'First name' },
            displayName:    { name: 'displayName',  KOR: '전체이름', USA: 'Display name' },
            middleName:     { name: 'middleName',   KOR: '중간이름', USA: 'Middle name' },
            langCode:       { name: 'langCode',     KOR: '언어',     USA: 'Language' }
        },
        messages: {
            notRegisteredName: { KOR: '등록된 Name이 없습니다', USA: 'Not registered the name' }
        }
    };

    // Define components
    var CastleDetailPage = React.createClass({
        //
        statics : {
            FIND_NAME_BOOK_URL: constant.CTX + '/api/castellans/{id}/contacts/name-book'
        },
        propTypes : {
            id: React.PropTypes.string
        },
        getInitialState: function () {
            return {
                nameBook: { names: [] },
                contentModifiable: false
            };
        },
        componentDidMount: function () {
            //
            this.requestNameBook(this.props);
        },
        changeModifiableMode: function () {
            this.setState({contentModifiable: true});
        },
        changeViewMode: function () {
            this.setState({contentModifiable: false});
        },
        requestNameBook: function (props) {
            commonAjax
                .getJSON(CastleDetailPage.FIND_NAME_BOOK_URL.replace('{id}', props.id))
                .done( function (nameBookResult) {
                    this.setState({ nameBook: nameBookResult });
                }.bind(this));
        },
        render: function () {
            return (
                <Tab
                    castleId={this.props.id}
                    nameBook={this.state.nameBook}
                    modifiable={this.state.contentModifiable}
                    changeModifiableMode={this.changeModifiableMode}
                    changeViewMode={this.changeViewMode}
                />
            );
        }
    });

    var Tab = React.createClass({
        //
        propTypes: {
            castleId: React.PropTypes.string.isRequired,
            nameBook: React.PropTypes.object,
            modifiable: React.PropTypes.bool.isRequired,

            changeModifiableMode: React.PropTypes.func.isRequired
        },
        render: function () {
            var TAB_NAMES = castleModel.tabs,
                lang = mainComponent.lang;

            return (
                <div className="container" >
                    <div className="panel panel-success">
                        <div className="panel-body">
                            <ul className="nav nav-tabs">
                                <li>
                                    <a href={"#/castle/basic?contentType=" + TAB_NAMES.basic.name + '&id=' + this.props.castleId}>{TAB_NAMES.basic[lang]}</a>
                                </li>
                                <li className="active">
                                    <a href={"#/castle/contact/name-book?contentType=" + TAB_NAMES.name.name + '&id=' + this.props.castleId}>{TAB_NAMES.name[lang]}</a>
                                </li>
                                <li>
                                    <a href={"#/castle/contact/phone-book?contentType=" + TAB_NAMES.phone.name + '&id=' + this.props.castleId}>{TAB_NAMES.phone[lang]}</a>
                                </li>
                                <li>
                                    <a href={"#/castle/contact/email-book?contentType=" + TAB_NAMES.email.name + '&id=' + this.props.castleId}>{TAB_NAMES.email[lang]}</a>
                                </li>
                                <li>
                                    <a href={"#/castle/contact/address-book?contentType=" + TAB_NAMES.address.name + '&id=' + this.props.castleId}>{TAB_NAMES.address[lang]}</a>
                                </li>
                                <li>
                                    <a href={"#/castle/history/account-book?contentType=" + TAB_NAMES.account.name + '&id=' + this.props.castleId}>{TAB_NAMES.account[lang]}</a>
                                </li>
                                <li>
                                    <a href={"#/castle/history/state-book?contentType=" + TAB_NAMES.state.name + '&id=' + this.props.castleId}>{TAB_NAMES.state[lang]}</a>
                                </li>
                                <li>
                                    <a href={"#/castle/history/metro-book?contentType=" + TAB_NAMES.metro.name + '&id=' + this.props.castleId}>{TAB_NAMES.metro[lang]}</a>
                                </li>
                            </ul>
                            <div className="tab-content">
                                <div className="tab-pane active">
                                    <NameContent nameBook={this.props.nameBook} />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            );
        }
    });

    var Content = React.createClass({
        propTypes: {

        },
        render : function () {

        }
    });

    var ButtonGroup = React.createClass({
        render : function () {
            var BUTTON_NAMES = castleModel.buttons,
                lang = mainComponent.lang,
                buttonRender;

            if (this.props.modifiable) {
                buttonRender = (
                    <div className="btn-toolbar pull-right">
                        <button type="button" className="btn-group btn btn-primary" onClick={this.modifiableModeBtnClick}>{BUTTON_NAMES.save[lang]}</button>
                        <button type="button" className="btn-group btn btn-default" onClick={this.cancelBtnClick}>{BUTTON_NAMES.cancel[lang]}</button>
                    </div>
                );
            }
            else {
                buttonRender = (
                    <div className="btn-toolbar pull-right">
                        <button type="button" className="btn-group btn btn-default" onClick={this.modifiableModeBtnClick}>{BUTTON_NAMES.modify[lang]}</button>
                        <button type="button" className="btn-group btn btn-danger" onClick={this.modifiableModeBtnClick}>{BUTTON_NAMES.remove[lang]}</button>
                    </div>
                );
            }
            return buttonRender;
        }
    });


    var NameContent = React.createClass({
        propTypes: {
            nameBook: React.PropTypes.shape({
                names: React.PropTypes.array.isRequired
            }).isRequired
        },
        render: function () {
            var ATTRS = castleNameModel.attrs,
                MESSAGES = castleNameModel.messages,
                lang = mainComponent.lang,
                existsNameBook = (this.props.nameBook && this.props.nameBook.names.length > 0) ? true : false;

            return (
                <article>
                    <table className="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>{ATTRS.familyName[lang]}</th>
                                <th>{ATTRS.firstName[lang]}</th>
                                <th>{ATTRS.displayName[lang]}</th>
                                <th>{ATTRS.middleName[lang]}</th>
                                <th>{ATTRS.langCode[lang]}</th>
                            </tr>
                        </thead>
                        <tbody>
                            { existsNameBook ?
                                this.props.nameBook.names.map( function (name) {
                                    return (
                                        <tr key={name[ATTRS.familyName.name]}>
                                            <td>{name[ATTRS.familyName.name]}</td>
                                            <td>{name[ATTRS.firstName.name]}</td>
                                            <td>{name[ATTRS.displayName.name]}</td>
                                            <td>{name[ATTRS.middleName.name]}</td>
                                            <td>{name[ATTRS.langCode.name]}</td>
                                        </tr>
                                    )
                                })
                                :
                                <tr><td colSpan="5">{MESSAGES.notRegisteredName[lang]}</td></tr>
                            }
                        </tbody>
                    </table>
                    <ButtonGroup modifiable={this.props.modifiable}/>
                </article>
            );
        }
    });


    Components.Castle.NameBook = CastleDetailPage;
})();