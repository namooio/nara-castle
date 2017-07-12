
import { combineReducers, createStore, applyMiddleware } from 'redux';
import { routerReducer } from 'react-router-redux';
import thunk from 'redux-thunk';


const rootReducer = combineReducers({
  //
  routing: routerReducer,
  // add module
});

export default createStore(
  rootReducer,
  applyMiddleware(thunk),
);
