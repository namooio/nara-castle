
import React, { Component } from 'react';
import { Col, Button, ButtonToolbar, Table, Row, FormGroup, FormControl, Tabs, Tab } from 'react-bootstrap';


function FormText({ index, propName, propValue, contactType, placeholder='', onChangeContactNodeProp }) {
  //
  return (
    <form className="form-inline">
      <FormGroup>
        <input type="text"className="form-control input-md" placeholder={placeholder} value={propValue}
               onChange={(e) => onChangeContactNodeProp(contactType, index, propName, e.target.value)}
               onFocus={() => console.log('focus input')}
               onBlur={() => console.log('blur input')}
        /> 
        {/*<FormControl type="text" placeholder={placeholder}*/}
                     {/*className="form-control input-md"*/}
                     {/*value={propValue}*/}
                     {/*onChange={(e) => onChangeContactNodeProp(contactType, index, propName, e.target.value)}*/}
                     {/**/}
        {/*/>*/}
      </FormGroup>
    </form>
  );
}

export {
  FormText
};
