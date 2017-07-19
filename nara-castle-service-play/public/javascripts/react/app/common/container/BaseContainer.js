
import React, { Component } from 'react';
import autoBind from 'react-autobind';

import LeftNavigationContainer from './LeftNavigationContainer';


class BaseContainer extends Component {
  //
  constructor(props) {
    //
    super(props);
    autoBind(this);
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

export default BaseContainer;
