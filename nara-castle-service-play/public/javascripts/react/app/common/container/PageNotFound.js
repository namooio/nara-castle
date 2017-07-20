
import React, { Component } from 'react';
import autoBind from 'react-autobind';
import { Link } from 'react-router';


class PageNotFound extends Component {
  //
  constructor(props) {
    super(props);
    autoBind(this);
  }

  onClickGoToBack() {
    //
    this.props.router.goBack();
  }

  render() {
    //
    return (
      <div className="abs-center wd-xl" style={{ height: 500 }}>
        <div className="text-center mb-xl">
          <div className="text-lg mb-lg">404</div>
          <p className="lead m0">We couldn't find this page.</p>
          <p>The page you are looking for does not exists.</p>
        </div>

        <ul className="list-inline text-center text-sm mb-xl">
          <li><Link to="/" className="text-muted">Go to App</Link></li>
          <li className="text-muted">|</li>
          <li><a href="javascript:" className="text-muted" onClick={this.onClickGoToBack}>Go to back</a></li>
        </ul>
      </div>
    );
  }

}

export default PageNotFound;
