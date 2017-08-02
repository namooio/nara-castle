
import { dispatch, getCastellansState } from '../../store';
import castellanApi from '../api/castellanApi';
import CastellanModel from './castellanModel';


const actionTypes = {
  //
  SET_CASTELLANS: 'castellans.SET_CASTELLANS',
  FILTER_CASTELLANS: 'castellans.FILTER_CASTELLANS',

  SET_HAS_MORE_CASTELLANS: 'castellans.SET_HAS_MORE_CASTELLANS',
};

function action(type, payload) {
  return {
    type,
    payload,
  };
}

const constants = {
  ROW_LIMIT: 50,
};

const castellansActions = {
  //
  findCastellans() {
    //
    const lastCastellan = getCastellansState().castellans[getCastellansState().castellans.length - 1],
      lastCastellanId = lastCastellan ? lastCastellan.id : null;

    return castellanApi
      .findCastellans(lastCastellanId, constants.ROW_LIMIT)
      .then((castellans) => {
        //
        dispatch(action(actionTypes.SET_CASTELLANS, castellans));

        if (castellans.length < 1) {
          dispatch(action(actionTypes.SET_HAS_MORE_CASTELLANS, false));
        }
      });
  }
};


const searchActions = {
  //
  filter(type, text, castellans) {
    //
    if (!text) {
      dispatch(action(actionTypes.FILTER_CASTELLANS, castellans));
    }
    else {
      const filtered = castellans.filter((castellan) => {
        if (type === 'email') {
          return castellan.contact.emails.emails.some((email) => {
            // return email.email.toLowerCase().slice(0, text.length) === text
            return email.email.toLowerCase().search(text) >= 0;
          });
        }
        else if (type === 'localeName') {
          return castellan.displayName.toLowerCase().search(text) >= 0;
        }
        else if (type === 'englisnName') {
          return false;
        }
        else if (type === 'phone') {
          return false;
        }
      });
      dispatch(action(actionTypes.FILTER_CASTELLANS, filtered));
    }
  }
};


const initialState = {
  //
  castellans: [],
  filteredCastellans: [],

  hasMoreCastellans: true,
};

function reducer(state = initialState, { type, payload }) {
  //
  switch (type) {
    case actionTypes.SET_CASTELLANS:
      return { ...state,
        castellans: [ ...state.castellans, ...CastellanModel.toUMList(payload)],
      };

    case actionTypes.SET_HAS_MORE_CASTELLANS:
      return { ...state,
        hasMoreCastellans: payload,
      };
    case actionTypes.FILTER_CASTELLANS:
      return { ...state,
        filteredCastellans: payload,
      };

    default:
      return state;
  }
}


export default reducer;
export { castellansActions, searchActions };
