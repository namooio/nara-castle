
import React, { Component } from 'react';
import autoBind from 'react-autobind';
import { connect } from 'react-redux';
import { Link } from 'react-router';
import { Col, Button, ButtonToolbar, Table, Row, FormGroup, FormControl, Tabs, Tab } from 'react-bootstrap';

import { dramaContext } from 'nara-core';
import { NaraAutoSuggest } from 'nara-react';
import { castellanActions } from '../module/castellan';
import { FormText } from '../component/CastellanItemFormSnippet';
import CastellanItem from '../component/CastellanItem';



class PropSchema {
  //
  constructor(title, name, width, { getValue, onEdit } = {}) {
    //
    this.title = title;
    this.name = name;
    this.width = width;


    if (typeof getValue === 'function') {
      this.value = getValue;
    }
    if (typeof onEdit === 'function') {
      this.onEdit = (index, value) => onEdit(index, this.name, value);
    }
  }
  value(value) {
    return value;
  }
  onEdit(index, value) {
    return (
      <form className="form-inline">
        <FormGroup>
          <FormControl type="text" placeholder="E-mail"
                       className="form-control input-md"
                       disabled={true}
                       value={value}
          />
        </FormGroup>
      </form>
    );
  }
}

class CastellanContainer extends Component {
  //
  static propTypes = {

  };
  // static itemSchema = {
  itemSchema = {
    email: {
      title: 'Emails',
      properties: [
        new PropSchema('Tier',      'tier',     '25%'),
        new PropSchema('Category',  'category', '25%', { onEdit: (index, propName, propValue) =>
          <FormGroup className="mb0">
            <FormControl componentClass="select" name="account"
                         className="form-control m-b">
              <option> select</option>
              <option>Primary</option>
              <option>Secondary</option>
            </FormControl>
          </FormGroup>
        }),
        new PropSchema('Email',     'email',    '50%', { onEdit: (index, propName, propValue) =>
          <FormText
            index={index}
            propName={propName}
            propValue={propValue}
            contactType={'email'}
            placeholder="E-mail"
            onChangeContactNodeProp={this.onChangeContactNodeProp}
          />
        }),
      ],
    },
    name: {
      title: 'Names',
      properties: [
        new PropSchema('Tier',        'tier',       '10%'),
        new PropSchema('Locale',      'langLocale', '15%'),
        new PropSchema('First name',  'firstName',  '25%'),
        new PropSchema('Middle name', 'middleName', '25%'),
        new PropSchema('Family name', 'familyName', '25%',),
      ],
    },
    phone: {
      title: 'Phones',
      properties: [
        new PropSchema('Tier',                'tier',               '10%'),
        new PropSchema('Category',            'category',           '5%'),
        new PropSchema('Country code',        'countryCode',        '10%'),
        new PropSchema('Carrier code',        'carriderCode',       '10%'),
        new PropSchema('Front number',        'frontNumber',        '10%'),
        new PropSchema('Back number',         'backNumber',         '10%'),
        new PropSchema('Full number',         'fullNumber',         '15%'),
        new PropSchema('Carrier full number', 'carrierFullNumber',  '15%'),
        new PropSchema('Display number',      'displayNumber',      '15%'),
      ],
    },
    address: {
      title: 'Addresses',
      properties: [
        new PropSchema('Category',      'category',     '10%'),
        new PropSchema('Tag',           'tag',          '20%'),
        new PropSchema('Country',       'country',      '10%'),
        new PropSchema('Zip code',      'zipCode',      '15%'),
        new PropSchema('Address',       'address',      '25%'),
        new PropSchema('Phone number',  'phoneNumber',  '20%'),
      ],
    },
    enrollment: {
      title: 'Enrollment',
      properties: [
        new PropSchema('Metro name',      'metroName',      '10%'),
        new PropSchema('Metro id',        'metroId',        '10%'),
        new PropSchema('Citizen id',      'citizenId',      '10%'),
        new PropSchema('Name',            'name',           '10%',  { getValue: (value) => value && value.displayName } ),
        new PropSchema('Email',           'email',          '15%',  { getValue: (value) => value && value.email }),
        new PropSchema('Withdrawn',       'withdrawn',      '10%',  { getValue: (value) => typeof value === 'boolean' && value.toString() }),
        new PropSchema('Enrolling time',  'enrollingTime',  '15%',  { getValue: (value) => value && new Date(value).toLocaleString() }),
        new PropSchema('Withdrawn time',  'withdrawnTime',  '15%',  { getValue: (value) => value && new Date(value).toLocaleString() }),
        new PropSchema('Zone',            'zone',           '5%',   { getValue: (value) => value && `${value.locale}-${value.zoneId}` }),
      ],
    },
  };

  constructor(props) {
    super(props);
    autoBind(this);
  }

  getItem(itemType) {
    //
    const { castellan, enrollments } = this.props;

    if (!castellan) {
      return [];
    }
    const  { names, emails, phones, addresses } = castellan.contact;

    let item;

    switch (itemType) {
      case 'email':
        item = emails;
        break;
      case 'name':
        item = names;
        break;
      case 'phone':
        item = phones;
        break;
      case 'address':
        item = addresses;
        break;
      case 'enrollment':
        item = enrollments;
        break;
    }
    return item;
  }

  onClickTab(tabKey) {
    //
    this.props.router.push(`castellans/${this.props.castellan.id}/${tabKey}`)
  }
  onClickClose() {
    //
    this.props.router.push(`castellans`);
  }
  onAddContact(itemType) {
    //
    castellanActions.addContact(itemType);
  }
  onClickContactNode(contactType, arrayIndex, editMode) {
    // castellanActions.setContactAllNodeEditModeDisable(contactType);

    castellanActions.setContactAllNodeEditModeDisable(contactType);
    castellanActions.setContactNodePropValue(contactType, arrayIndex, 'editMode', true);
  }
  onDisableNodeEditMode(contactType) {
    //
    castellanActions.setContactAllNodeEditModeDisable(contactType);
  }
  onChangeContactNodeProp(contactType, arrayIndex, propName, propValue) {
    //
    castellanActions.setContactNodePropValue(contactType, arrayIndex, propName, propValue);
  }


  render() {
    //
    const { styles } = this.props,
      { itemType } = this.props.params;


    return (
      <div className="panel content0 castle-panel">
        <div className="panel-body p0">

          { itemType !== 'enrollment' &&
            <div className="pull-left p">
              <Button bsStyle="primary" onClick={() => this.onAddContact(itemType)}>
                <i className="fa fa-plus mr-sm"/>
                Add
              </Button>
            </div>
          }

          <div className="pull-right p">
            <Button bsStyle="default b0" onClick={this.onClickClose}>
              <i className="fa fa-close text-muted"/>
            </Button>
          </div>

          <Tabs className="tab-style01"
                defaultActiveKey="email"
                activeKey={itemType}
                onSelect={ this.onClickTab }
          >
            { Object.keys(this.itemSchema).map((itemSchemaName) =>
              <Tab eventKey={itemSchemaName} title={itemSchemaName}>
                <CastellanItem
                  styles={styles}
                  schema={this.itemSchema[itemSchemaName]}
                  item={this.getItem(itemSchemaName)}
                  onClickNode={(arrayIndex, editModeValue) => this.onClickContactNode(itemSchemaName, arrayIndex, editModeValue)}
                  onDisableNodeEditMode={() => this.onDisableNodeEditMode(itemSchemaName)}
                />
              </Tab>
            )}
          </Tabs>

        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ castellanStore }) => {
  return {
    castellan: castellanStore.castellan,
    enrollments: castellanStore.enrollments,
  };
};

export default connect(mapStateToProps)(CastellanContainer);
