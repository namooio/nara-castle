
import { combineReducers, createStore, applyMiddleware } from 'redux';
import { routerReducer } from 'react-router-redux';
import thunk from 'redux-thunk';

import dramaContextStore from './common/module/dramaContext';


const rootReducer = combineReducers({
  //
  routing: routerReducer,
  dramaContextStore
  // add module

});

export default createStore(
  rootReducer,
  applyMiddleware(thunk),
);
