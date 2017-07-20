
import { dramaContext as naraDramaContext } from 'nara-core';
import store from '../../store';


const actionTypes = {
  //
  SET_DRAMA_CONTEXT: 'dramaContext.SET_DRAMA_CONTEXT',
};


function action(type, payload) {
  return {
    type,
    payload,
  };
}

const dramaContextActions = {
  //
  setDramaContext(dramaContext) {
    //
    store.dispatch(action(actionTypes.SET_DRAMA_CONTEXT, dramaContext));
  }

};


const initialState = {
  //
  dramaContext: naraDramaContext.getDramaContext(),
};

function reducer(state = initialState, { type, payload }) {
  //
  switch (type) {
    case actionTypes.SET_DRAMA_CONTEXT:
      return { ...state,
        dramaContext: payload,
      };

    default:
      return state;
  }
}


export default reducer;
export { dramaContextActions };
