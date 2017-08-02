
import { combineReducers, createStore, applyMiddleware } from 'redux';
import { routerReducer } from 'react-router-redux';
import thunk from 'redux-thunk';

import dramaContextStore from './common/module/dramaContext';
import castellansStore from './castellan/module/castellans';
import castellanStore from './castellan/module/castellan';


const rootReducer = combineReducers({
  //
  routing: routerReducer,
  dramaContextStore,

  // add module
  castellansStore,
  castellanStore,
});

const store = createStore(
  rootReducer,
  applyMiddleware(thunk),
);


export default store;
export const dispatch = store.dispatch;

export const getCastellansState = () => store.getState().castellansStore;
export const getCastellanState = () => store.getState().castellanStore;

