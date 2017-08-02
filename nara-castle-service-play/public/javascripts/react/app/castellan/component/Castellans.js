
import React, { Component } from 'react';
import PropTypes from 'prop-types';
import autoBind from 'react-autobind';
import { Col, Button, ButtonToolbar, Table, Row, FormGroup } from 'react-bootstrap';
import InfiniteScroll from '../../common/InfiniteScroller/InfiniteScroller';

import { NaraAutoSuggest } from 'nara-react';


class Castellans extends Component {
  //
  static propTypes = {
    castellans: PropTypes.array,
    searchConditionType: PropTypes.string,
  };

  constructor(props) {
    super(props);
    autoBind(this);
  }


  render() {
    //
    const props = this.props;

    return (
      <div className="panel mb0 castle-panel">

        <div className="panel-heading">
          <div className="form-horizontal">
            <FormGroup className="mb0">

              <Col md={ 5 } className="pull-right">
                <div className="input-group">
                  <input type="text" placeholder="Find castellan"
                         className="form-control form-control-rounded"/>
                  <span className="input-group-btn">
                    <Button bsStyle="default" className="btn-oval">
                      <span className="fa fa-search"/>
                    </Button>
                  </span>
                </div>
              </Col>
            </FormGroup>
          </div>
        </div>

        <div className="panel-body p0 bt bb">
          <div className="fixed-table-container" style={props.styles.fixedTable} >
            <div className="header-bg"></div>
            <div className="table-wrapper" ref={(element) => this.scrollComponent = element }>
              <div className="table-responsive">

                {/*<div className="form-inline">*/}
                { this.scrollComponent &&
                <InfiniteScroll
                  element="div"
                  className="form-inline"
                  pageStart={0}
                  hasMore={props.hasMoreCastellans}
                  useWindow={false}
                  threshold={300}
                  scrollComponent={this.scrollComponent}
                  loadMore={props.findCastellans}
                  loader={
                    <div className="basic-spinner">
                      <em className="fa fa-spinner fa-xl text-muted fa-spin text-lg "/>
                    </div>
                  }
                >

                  <Table className="table-hover table-striped mb-mails">
                    <thead>
                      <tr>
                        <th width="5%" className="text-center">
                          <div className="th-text"></div>
                        </th>
                        <th width="30%" className="text-center">
                          <div className="th-text">Primary email</div>
                        </th>
                        <th width="30%" className="text-center">
                          <div className="th-text">Display name</div>
                        </th>
                        <th width="35%" className="text-center">
                          <div className="th-text">Built time</div>
                        </th>
                      </tr>
                    </thead>
                    <tbody>
                        { props.castellans.map((castellan, index) =>
                          <tr className={`text-center ${props.selectedCastellan && props.selectedCastellan.id === castellan.id ? 'active' : null }`}
                              onClick={() => props.onSelectCastellan(castellan)}
                              key={index}
                          >
                            <td>{index + 1}</td>
                            <td>{castellan.primaryEmail}</td>
                            <td>{castellan.displayName}</td>
                            <td>{new Date(castellan.castleBuiltTime).toLocaleString()}</td>
                          </tr>
                        )}
                    </tbody>
                  </Table>

                {/*</div>*/}
                </InfiniteScroll>
                }

              </div>
            </div>
          </div>
        </div>

      </div>
    );
  }
}

export default Castellans;
