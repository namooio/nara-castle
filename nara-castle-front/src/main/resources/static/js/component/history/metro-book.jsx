/**
 * Created by hkkang on 2016-04-12.
 */

castle.component.MetroBook = castle.component.MetroBook || {};


( function () {
    //
    'use strict';

    // Import component module
    const NaraAjax = NaraCommon.Ajax,
        NaraDate = NaraCommon.Date,
        Constant = castle.common.Const,
        MainComponent = castle.component.common.Main;



    // Define Content attributes name
    const CastleMetroModel = {
        //
        attrs: {
            metroId:        { name: 'metroId',          KOR: '메트로Id', USA: 'Metro id' },
            metroName:      { name: 'metroName',        KOR: '메트로명', USA: 'Metro name' },
            joinTime:       { name: 'joinTime',         KOR: '가입일시', USA: 'Join time' },
            withdrawalTime: { name: 'withdrawalTime',   KOR: '탈퇴일시', USA: 'Withdrawal time' },
            remarks:        { name: 'remarks',          KOR: '설명',     USA: 'Remarks' }
        },
        messages: {
            notExistsMetro: { KOR: 'Metro 이력이 없습니다', USA: 'Not exists metro history' }
        }
    };


    // Define components
    let MetroContent = React.createClass({
        //
        statics: {
            FIND_METRO_BOOK: Constant.PAV_CTX_API + '/api/castles/{id}/histories/metro-book'
        },
        propTypes: {
            castleId: React.PropTypes.string.isRequired,
            castle: React.PropTypes.shape({
                history: React.PropTypes.shape({
                    metroBook: React.PropTypes.shape({
                        metros: React.PropTypes.array.isRequired
                    }).isRequired
                }).isRequired
            }).isRequired
        },
        // overriding
        componentDidMount() {
            this.requestFindMetroBook(this.props.castleId);
        },
        // custom
        setMetroBook(metroBook) {
            let castle = this.props.castle;

            castle.history.metroBook = metroBook;
            this.props.setCastle(castle);
        },
        // request
        requestFindMetroBook(castleId) {
            NaraAjax
                .getJSON(MetroContent.FIND_METRO_BOOK.replace('{id}', castleId))
                .done( function (resultMetroBook) {
                    this.setMetroBook(resultMetroBook);
                }.bind(this));
        },
        render() {
            //
            return (
                <MetroViewContent
                    metroBook={this.props.castle.history.metroBook}
                />
            );
        }
    });

    let MetroViewContent = React.createClass({
        //
        propTypes: {
            metroBook: React.PropTypes.shape({
                metros: React.PropTypes.array.isRequired
            }).isRequired
        },
        render() {
            //
            const ATTRS = CastleMetroModel.attrs,
                MESSAGES = CastleMetroModel.messages,
                LANG = MainComponent.lang;

            let existsMetroBook = this.props.metroBook.metros.length > 0;

            return (
                <table className="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th>{ATTRS.metroId[LANG]}</th>
                        <th>{ATTRS.metroName[LANG]}</th>
                        <th>{ATTRS.joinTime[LANG]}</th>
                        <th>{ATTRS.withdrawalTime[LANG]}</th>
                        <th>{ATTRS.remarks[LANG]}</th>
                    </tr>
                    </thead>
                    <tbody>
                    { existsMetroBook ?
                        this.props.metroBook.metros.map( function (metro, index) {
                            return (
                                <tr key={index}>
                                    <td>{metro[ATTRS.metroId.name]}</td>
                                    <td>{metro[ATTRS.metroName.name]}</td>
                                    <td>{NaraDate.parseToString(metro[ATTRS.joinTime.name])}</td>
                                    <td>{NaraDate.parseToString(metro[ATTRS.withdrawalTime.name])}</td>
                                    <td>{metro[ATTRS.remarks.name]}</td>
                                </tr>
                            )
                        })
                        :
                        <tr>
                            <td colSpan="5">{MESSAGES.notExistsMetro[LANG]}</td>
                        </tr>
                    }
                    </tbody>
                </table>
            );
        }
    });

    castle.component.MetroBook = MetroContent;
})();