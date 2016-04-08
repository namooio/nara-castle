/**
 * Created by hkkang on 2016-04-05.
 */
CastellanComponent.Registerer = CastellanComponent.Registerer || {};

(function () {
    //
    var CastellanRegisterer = React.createClass({
        getInitialState: function () {
            return {};
        },
        handleIdInputChange: function (event) {
            //
            this.setState({id: event.target.value});
        },
        handleEmailInputChange : function (event) {
            //
            this.setState({email: event.target.value});
        },
        registerBtnClick: function () {
            //
            var castellanCreateDto = {
                id: this.state.id
                , email: this.state.email
            };

            CastellanCommon.postJSON(CONST.CTX + '/castellan', castellanCreateDto)
                .done(function () {
                    alert('Castellan이 정상적으로 등록 되었습니다..');
                    this.setState({id: '', email: ''})
                }.bind(this))
                .fail(function (data, textStatus, error) {
                    alert('Failed register castellan2');
                    console.dir(data);
                    console.log(textStatus);
                    console.dir(error);
                });

        },
        render: function () {
            var containerStyle = {width: '800px'};
            return (
                <article>
                    <div className="container" style={containerStyle}>
                        <div className="panel panel-success">
                            <div className="panel-heading">
                                <h4 className="panel-title">Castellan 등록</h4>
                            </div>
                            <div className="panel-body">
                                <div className="form-horizontal">
                                    <div className="form-group">
                                        <label className="col-md-3 control-label">Id</label>
                                        <div className="col-md-9">
                                            <input type="text" className="form-control" value={this.state.id} onChange={this.handleIdInputChange} />
                                        </div>
                                    </div>
                                    <div className="form-group">
                                        <label className="col-md-3 control-label">Email</label>
                                        <div className="col-md-9">
                                            <input type="text" className="form-control" value={this.state.email} onChange={this.handleEmailInputChange} />
                                        </div>
                                    </div>
                                    <button type="button" className="btn btn-primary pull-right" onClick={this.registerBtnClick}>등록</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </article>
            );
        }
    });

    ReactDOM.render(
        <CastellanRegisterer />, $('#contents')[0]
    );
})();

