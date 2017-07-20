
import React, { Component } from 'react';
import autoBind from 'react-autobind';
import { connect } from 'react-redux';

import { NaraWrapper } from 'nara-react';
import { dramaContextActions } from '../module/dramaContext';


class IndexContainer extends Component {
  //
  constructor(props) {
    super(props);
    autoBind(this);
  }

  // override
  componentDidMount() {
    //
    // dramaContextActions.setDramaContext({ contextId: 'Oh yes.'});
  }

  render() {
    //
    const props = this.props;

    return (
      <NaraWrapper>
        <h2>Hello, nara castle.</h2>
        <h4>{props.dramaContext.contextId}</h4>
      </NaraWrapper>
    );
  }
}

const mapStateToProps = ({ dramaContextStore }) => {
  return {
    dramaContext: dramaContextStore.dramaContext,
  };
};

export default connect(mapStateToProps)(IndexContainer);
