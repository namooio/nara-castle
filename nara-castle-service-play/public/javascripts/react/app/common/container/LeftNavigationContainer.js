
import React, { Component } from 'react';
import autoBind from 'react-autobind';

import LeftNavigationItem from '../component/LeftNavigation';


class LeftNavigationContainer extends Component {
  //
  static items = [
    { name: 'Castle', url: '/castle'},
  ];

  constructor(props) {
    super(props);
    autoBind(this);
  }

  render() {
    return (
      <LeftNavigationItem
        location={this.props.location}
        title="Castle"
        items={LeftNavigationContainer.items}
      />
    );
  }
}

export default LeftNavigationContainer;
