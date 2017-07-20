
import React, { Component } from 'react';
import autoBind from 'react-autobind';
import { connect } from 'react-redux';

import { dramaContextActions } from '../module/dramaContext';
import LeftNavigationContainer from './LeftNavigationContainer';


class BaseContainer extends Component {
  //
  constructor(props) {
    //
    super(props);
    autoBind(this);
  }

  // override
  componentDidMount() {
    //
  }


  setLocalDramaContext() {
    //
    if (!this.props.dramaContext) {
      dramaContextActions.setDramaContext({
        basePath: '/',
        roles: ['admin', 'user'],
        edition: 'Professional',
        userId: 'tester',
        userName: 'tester',
        contextId: 'testContext',
        stageId: 'testTown'
      });
      console.log('[NaraCastle] Start with standard alone mode');
    }
    else {
      console.log('[NaraCastle] Start with pavilion mode');
      console.debug(`[NaraCastle] base-path : ${this.props.dramaContext.basePath}`);
    }
    // ex) standard-alone => "/", pavilion => "/pav-proxy/****/"
    //     window.document.getElementsByTagName('base')[0].href = DRAMA_CONTEXT.basePath;
  }


  render() {
    return (
      <div className="wrapper">

        {/* sidebar  */}
        <LeftNavigationContainer
          router={this.props.router}
          location={this.props.location}
        />

        {/* Main section */}
        <section style={{ marginLeft: 220 }}>
          {/* Page content */}
          { this.props.children }
        </section>

      </div>
    );
  }
}

const mapStateToProps = ({ dramaContextStore }) => {
  return {
    dramaContext: dramaContextStore.dramaContext,
  };
};

export default connect(mapStateToProps)(BaseContainer);
