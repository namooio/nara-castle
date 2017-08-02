
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


  render() {
    return (
      <div className="wrapper">

        {/* sidebar  */}
        {/*
        <LeftNavigationContainer
          router={this.props.router}
          location={this.props.location}
        />
         */}

        {/* Main section */}
        <section>
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
