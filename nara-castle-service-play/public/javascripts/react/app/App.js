
// Application Styles
import '../../../stylesheets/app.scss';


import React from 'react';
import ReactDOM from 'react-dom';
import { browserHistory, Router, Route, IndexRoute, IndexRedirect } from 'react-router';
import { syncHistoryWithStore } from 'react-router-redux';
import { Provider } from 'react-redux';
import { dramaContext } from 'nara-core';

import store from './store';

import Base from './common/container/BaseContainer';
import PageNotFound from './common/container/PageNotFound';

import Index from './common/container/IndexContainer';


if (!dramaContext.getDramaContext() || Object.keys(dramaContext.getDramaContext()).length < 1) {
  dramaContext.__setLocalDramaContext({
    basePath: '/',
    roles: ['admin', 'user'],
    edition: 'Professional',
    userId: 'tester',
    userName: 'tester',
    contextId: 'testContext',
    stageId: 'testTown'
  });
  console.log('Start drama with stand alone mode');
}
else {
  console.log('Start drama with pavilion mode');
  console.debug(`Drama base-path : ${dramaContext.getDramaContext().basePath}`);
}
// ex) standard-alone => "/", pavilion => "/pav-proxy/****/"
//     window.document.getElementsByTagName('base')[0].href = DRAMA_CONTEXT.basePath;

const history = syncHistoryWithStore(browserHistory, store);


ReactDOM.render(
  <Provider store={store}>
    <Router history={history}>
      <Route path={dramaContext.getDramaContext().basePath} component={Base} >

        <IndexRoute component={Index} />

      </Route>

      {/* Not found handler */}
      <Route path="*" component={PageNotFound}/>
    </Router>
  </Provider>,
  document.getElementById('app'),
);
