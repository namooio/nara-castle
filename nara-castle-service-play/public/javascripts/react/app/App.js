
// Application Styles
import '../../../stylesheets/app.scss';


import React from 'react';
import ReactDOM from 'react-dom';
import { Router, Route, IndexRoute, IndexRedirect, browserHistory } from 'react-router';
import { Provider } from 'react-redux';
import { syncHistoryWithStore } from 'react-router-redux';

import store from './store';

import Base from './common/BaseContainer';

const history = syncHistoryWithStore(browserHistory, store);

ReactDOM.render(
  <Provider store={store}>
    <Router history={history}>
      <Route path={DRAMA_CONTEXT.basePath} component={Base} >



      </Route>

      {/* Not found handler */}
      {/* <Route path="*" component={NotFound}/> */}
    </Router>
  </Provider>,
  document.getElementById('app'),
);
