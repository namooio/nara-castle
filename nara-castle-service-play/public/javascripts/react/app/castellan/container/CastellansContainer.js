
import React, { Component } from 'react';
import autoBind from 'react-autobind';
import { connect } from 'react-redux';
import { Col, Button, ButtonToolbar, Table, Row, FormGroup } from 'react-bootstrap';

import { dramaContext } from 'nara-core';
import { NaraWrapper, dialogUtil, NaraAutoSuggest } from 'nara-react';
import { castellansActions, searchActions } from '../module/castellans';
import { castellanActions } from '../module/castellan';
import Castellans from '../component/Castellans';
import CastellanContainer from './CastellanContainer';


class CastellansContainer extends Component {
  //
  static styles = {
    fixedTable: {
      height: "580px",
      overflowY: "scroll"
    },
  };

  constructor(props) {
    super(props);
    autoBind(this);
  }

  // override
  componentDidMount() {
    //
    const promise = castellansActions.findCastellans();

    promise.then(() => this.init(this.props));
  }
  componentWillReceiveProps(nextProps) {
    //
    if (this.props.params.castellanId !== nextProps.params.castellanId) {
      this.init(nextProps);
    }
  }

  init(props) {
    //
    const castellanId = props.params.castellanId;

    if (castellanId) {
      castellanActions.setCastellan(props.castellans, castellanId)
        .then(() => castellanActions.setOpened(true))
        .catch(() => props.router.push('/castellans'));
    }
    else {
      castellanActions.setOpened(false);
      castellanActions.clearCastellan();
    }
  }


  onSelectCastellan(castellan) {
    //
    this.props.router.push(`castellans/${castellan.id}`);
  }
  findCastellans() {
    castellansActions.findCastellans();
  }


  render() {
    //
    const {
      castellans,
      selectedCastellan,
      hasMoreCastellans,

      castellanOpened,
    } = this.props;


    return (
      <NaraWrapper>
        <h3 className="bg-white">Castle</h3>

        <div className="row">
          <div className="responsive-wrap">

            <div className="responsive-box">
              <Castellans
                styles={CastellansContainer.styles}

                castellans={castellans}
                selectedCastellan={selectedCastellan}
                hasMoreCastellans={hasMoreCastellans}

                findCastellans={this.findCastellans}
                onSelectCastellan={this.onSelectCastellan}
              />
            </div>

            { castellanOpened &&
            <div className="responsive-box">
              <CastellanContainer
                params={this.props.params}
                router={this.props.router}
                styles={CastellansContainer.styles}
              />
            </div>
            }

          </div>
        </div>
      </NaraWrapper>
    );
  }
}

const mapStateToProps = ({ castellansStore, castellanStore }) => {
  return {
    castellans: castellansStore.castellans,
    filteredCastellans: castellansStore.filteredCastellans,
    hasMoreCastellans: castellansStore.hasMoreCastellans,

    selectedCastellan: castellanStore.castellan,
    castellanOpened: castellanStore.opened,
  };
};

export default connect(mapStateToProps)(CastellansContainer);
