
import { dispatch } from '../../store';
import castellanApi from '../api/castellanApi';
import { ContactEmail, ContactName, ContactPhone, ContactAddress } from './castellanModel';


const actionTypes = {
  //
  SET_CASTELLAN: 'castellan.SET_CASTELLAN',
  CLEAR_CASTELLAN: 'castellan.CLEAR_CASTELLAN',

  ADD_CONTACT_EMAIL: 'castellan.ADD_CONTACT_EMAIL',
  ADD_CONTACT_NAME: 'castellan.ADD_CONTACT_NAME',
  ADD_CONTACT_PHONE: 'castellan.ADD_CONTACT_PHONE',
  ADD_CONTACT_ADDRESS: 'castellan.ADD_CONTACT_ADDRESS',
  SET_CONTACT_EMAIL_PROP: 'castellan.SET_CONTACT_EMAIL_PROP',
  SET_CONTACT_NAME_PROP: 'castellan.SET_CONTACT_NAME_PROP',
  SET_CONTACT_PHONE_PROP: 'castellan.SET_CONTACT_PHONE_PROP',
  SET_CONTACT_ADDRESS_PROP: 'castellan.SET_CONTACT_ADDRESS_PROP',

  SET_CONTACT_NODE_PROP: 'castellan.SET_CONTACT_NODE_PROP',
  SET_CONTACT_ALL_NODES_PROP: 'castellan.SET_CONTACT_ALL_NODES_PROP',


  SET_ENROLLMENTS: 'castellan.SET_ENROLLMENTS',

  SET_OPENED: 'castellan.SET_OPENED',
};

function action(type, payload) {
  return {
    type,
    payload,
  };
}

const constants = {
  contactType: {
    email: { name: 'email', propName: 'emails' },
    name: { name: 'name', propName: 'names' },
    phones: { name: 'phone', propName: 'phones' },
    addresses: { name: 'address', propName: 'addresses' },
  },
};


const castellanActions = {
  //
  setCastellan(castellans, castellanId) {
    //
    const castellan = castellans.find((castellan) => castellan.id === castellanId);

    if (castellan) {
      dispatch(action(actionTypes.SET_CASTELLAN, castellan));

      return castellanApi
        .findEnrollments(castellanId)
        .then((enrollments) => dispatch(action(actionTypes.SET_ENROLLMENTS, enrollments)));
    }
    else {
      return Promise.reject();
    }
  },
  clearCastellan() {
    //
    dispatch(action(actionTypes.CLEAR_CASTELLAN));
  },

  addContact(contactType) {
    //
    switch (contactType) {
      case 'email':
        dispatch(action(actionTypes.ADD_CONTACT_EMAIL, new ContactEmail()));
        break;
      case 'name':
        dispatch(action(actionTypes.ADD_CONTACT_NAME, new ContactName()));
        break;
      case 'phone':
        dispatch(action(actionTypes.ADD_CONTACT_PHONE, new ContactPhone()));
        break;
      case 'address':
        dispatch(action(actionTypes.ADD_CONTACT_ADDRESS, new ContactAddress()));
        break;
    }
  },
  setContactNodePropValue(contactType, arrayIndex, propName, propValue) {
    //
    switch (contactType) {
      case 'email':
        dispatch(action(actionTypes.SET_CONTACT_EMAIL_PROP, { index: arrayIndex, propName, propValue }));
        break;
      case 'name':
        dispatch(action(actionTypes.SET_CONTACT_NAME_PROP, { index: arrayIndex, propName, propValue }));
        break;
      case 'phone':
        dispatch(action(actionTypes.SET_CONTACT_PHONE_PROP, { index: arrayIndex, propName, propValue }));
        break;
      case 'address':
        dispatch(action(actionTypes.SET_CONTACT_ADDRESS_PROP, { index: arrayIndex, propName, propValue }));
        break;
    }
  },
  setContactAllNodeEditModeDisable(contactTypeName) {
    //
    const contactType = constants.contactType[contactTypeName];

    dispatch(action(actionTypes.SET_CONTACT_ALL_NODES_PROP, {
      contactType: contactType,
      propName: 'editMode',
      propValue: false,
    }));
  },

  setContactNodePropEditMode(contactTypeName, index, propName, editMode) {
    //
    const contactType = constants.contactType[contactTypeName];

    dispatch(action(actionTypes.SET_CONTACT_NODE_PROP, {
      contactType: contactType,
      index: index,
      propsToTarget: [propName],
      propName: 'editMode',
      propValue: editMode,
    }));
  },

  setOpened(opened) {
    //
    dispatch(action(actionTypes.SET_OPENED, opened));
  }
};


const initialState = {
  //
  castellan: null,
  enrollments: [],

  opened: false,
};

function reducer(state = initialState, { type, payload }) {
  //
  switch (type) {
    case actionTypes.SET_CASTELLAN:
      return { ...state,
        castellan: payload,
      };
    case actionTypes.CLEAR_CASTELLAN:
      return { ...state,
        castellan: initialState.castellan,
      };

    case actionTypes.ADD_CONTACT_EMAIL:
      return { ...state,
        castellan: { ...addContact(state.castellan, payload, 'emails', 'emails') }
      };
    case actionTypes.ADD_CONTACT_NAME:
      return { ...state,
        castellan: { ...addContact(state.castellan, payload, 'names', 'names') }
      };
    case actionTypes.ADD_CONTACT_PHONE:
      return { ...state,
        castellan: { ...addContact(state.castellan, payload, 'phones', 'phones') }
      };
    case actionTypes.ADD_CONTACT_ADDRESS:
      return { ...state,
        castellan: { ...addContact(state.castellan, payload, 'addresses', 'addresses') }
      };

    case actionTypes.SET_CONTACT_EMAIL_PROP:
      return { ...state,
        castellan: { ...setPropertyInArray(state.castellan, payload.index, payload.propName, payload.propValue, 'contact', 'emails')},
      };
    case actionTypes.SET_CONTACT_NAME_PROP:
      return { ...state,
        castellan: { ...setPropertyInArray(state.castellan, payload.index, payload.propName, payload.propValue, 'contact', 'names')},
      };
    case actionTypes.SET_CONTACT_PHONE_PROP:
      return { ...state,
        castellan: { ...setPropertyInArray(state.castellan, payload.index, payload.propName, payload.propValue, 'contact', 'phones')},
      };
    case actionTypes.SET_CONTACT_ADDRESS_PROP:
      return { ...state,
        castellan: { ...setPropertyInArray(state.castellan, payload.index, payload.propName, payload.propValue, 'contact', 'address')},
      };

    case actionTypes.SET_CONTACT_ALL_NODES_PROP:
      return { ...state,
        castellan: { ...setPropertyAllNodes(state.castellan, payload.propName, payload.propValue, 'contact', payload.contactType.propName)},
      };

    case actionTypes.SET_CONTACT_NODE_PROP:
      return { ...state,
        castellan: {
          ...setPropertyInArrayNode(
            state.castellan,
            ['contact', payload.contactType.propName],
            payload.index,
            payload.propsToTarget,
            payload.propName,
            payload.propValue
          )
        }
      };

    case actionTypes.SET_ENROLLMENTS:
      return { ...state,
        enrollments: payload,
      };

    case actionTypes.SET_OPENED:
      return { ...state,
        opened: payload,
      };

    default:
      return state;
  }
}

function addContact(state, contactNode, ...props) {
  //
  const nextState = { ...state };
  let willReplace = nextState.contact;

  props.forEach((prop) =>
    willReplace = willReplace[prop]
  );
  willReplace.push(contactNode);
  return nextState;
}

function setPropertyInArray(prevState, index, propName, value, ...propsToArray) {
  //
  const nextState = { ...prevState };
  let targetArray = nextState;

  propsToArray.forEach((prop) =>
    targetArray = targetArray[prop]
  );
  targetArray[index][propName] = value;

  return nextState;
}

function setPropertyInArrayNode(prevState, propsToArray, index, propsToTargetProp, propName, value) {
  //
  const nextState = { ...prevState };
  let targetArray = nextState;

  propsToArray.forEach((prop) =>
    targetArray = targetArray[prop]
  );

  let targetProp = targetArray[index];

  propsToTargetProp.forEach((prop) =>
    targetProp = targetProp[prop]
  );

  targetProp[propName] = value;

  return nextState;
}

function setPropertyAllNodes(prevState, propName, value, ...propsToArray) {
  //
  const nextState = { ...prevState };
  let targetArray = nextState;

  propsToArray.forEach((prop) =>
    targetArray = targetArray[prop]
  );
  targetArray.forEach((node) => node[propName] = value);

  return nextState;
}



export default reducer;
export { castellanActions };

