/**
 * Created by hkkang on 2016-04-05.
 */
CastellanComponent.Inquiry = CastellanComponent.Inquiry || {};


(function () {
    //
    var CastellanViewPage = React.createClass({
        getInitialState: function () {
            return {
                castellanView: {}
                , visibleView: false
                , newCastellan: {}
            };
        },
        changeCastellanInput: function (inquiryId) {
            this.setState({inquiryId: inquiryId});
        },
        inquiryCastellan: function (castellanId) {
            //
            $.getJSON(CONST.CTX + '/api/castellan/' + castellanId, castellanId)
                .done(function (castellanResult) {
                    this.setState({castellanView: castellanResult, visibleView: true});
                }.bind(this))
                .fail(function () {
                    this.setState({visibleView: false});
                    alert('해당 Id(' + castellanId + ')로 조회된 Castellan이 없습니다.');
                }.bind(this));
        },
        render: function () {
            var containerStyle = {width: '800px'};
            return (
                <article>
                    <FindForm containerStyle={containerStyle} inquiryId={this.state.inquiryId} change={this.changeCastellanInput} inquiry={this.inquiryCastellan}/>
                    {this.state.visibleView ? <CastellanView containerStyle={containerStyle} castellanView={this.state.castellanView}/> : null}
                </article>
            );
        }
    });

    /**
     * Castellan 검색 폼 컴포넌트
     */
    var FindForm = React.createClass({
        getInitialState: function () {
            return {};
        },
        inquiryBtnClick: function () {
            this.props.inquiry(this.props.inquiryId);
        },
        inputChange: function (event) {
            this.props.change(event.target.value);
        },
        render: function () {
            return (
                <div className="container" style={this.props.containerStyle}>
                    <div className="panel panel-success">
                        <div className="panel-heading">
                            <h4 className="panel-title">Castellan 조회</h4>
                        </div>
                        <div className="panel-body">
                            <div className="form-horizontal">
                                <div className="form-group">
                                    <label className="col-md-1 col-md-offset-6 control-label">ID</label>
                                    <div className="col-md-3">
                                        <input className="form-control" id="castellan-id" type="text" value={this.props.inquiryId} onChange={this.inputChange} placeholder="Castellan ID" />
                                    </div>
                                    <div className="col-md-2">
                                        <div className="btn-group">
                                            <button type="button" className="btn btn-default" id="inBtn" onClick={this.inquiryBtnClick}>조회</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            )
        }
    });

    /**
     * Castellan View 컴포넌트
     */
    var CastellanView = React.createClass({
        render: function () {
            return (
                <div className="container" style={this.props.containerStyle}>
                    <div className="panel panel-success">
                        <div className="panel-heading">
                            <h4 className="panel-title">Castellan 정보</h4>
                        </div>
                        <div className="panel-body">
                            <div className="form-horizontal">
                                <div className="form-group">
                                    <label className="col-md-2 text-right">ID</label>
                                    <p className="col-md-10">{this.props.castellanView.id}</p>
                                </div>
                                <div className="form-group">
                                    <label className="col-md-2 text-right">Email</label>
                                    <p className="col-md-10">{this.props.castellanView.primaryEmail}</p>
                                </div>
                                <div className="form-group">
                                    <label className="col-md-2 text-right">Display name</label>
                                    <p className="col-md-10">{this.props.castellanView.displayName}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            );
        }
    });

    ReactDOM.render(<CastellanViewPage />, $('#contents')[0]);

})();

