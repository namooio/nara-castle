
import React, { Component } from 'react';
import autoBind from 'react-autobind';
import { Col, Button, ButtonToolbar, Table, Row, FormGroup, FormControl } from 'react-bootstrap';

import { NaraAutoSuggest } from 'nara-react';


class CastellanItem extends Component {
  //
  constructor(props) {
    super(props);
    autoBind(this);
  }

  onEnableNodeEditMode(index, editMode, e) {
    //
    if (editMode) {
      return;
    }

    console.log('start enable', e.target);
    setTimeout(() => {
      console.log('execute enable');
      this.props.onClickNode(index);
    }, 200);
  }
  onDisableNodeEditMode(e, index) {
    //
    // console.log('disable', e.target);
    // console.dir(e.target);


    // console.log(document.activeElement);
    // console.log(e.target);
    // console.log(e.currentTarget);

    // console.log('start----------------------------------------');
    // console.log('focused', this.focused);

    /*
    if (this.focused === index) {
      this.focused = null;
    }
    else {
      this.focused = null;
      this.props.onDisableNodeEditMode();
    }
    */

    // if (e.target.tabIndex !== index && e.nodeIndex !== index) {

    console.log('start disable', e.target);
    setTimeout(() => { console.log('execute disable'); this.props.onDisableNodeEditMode() }, 200);
      // this.props.onDisableNodeEditMode();
    // }


    // console.log('focused', this.focused);
    // console.log('end ----------------------------------------');
  }


  render() {
    //
    const { styles, schema, item, onClickNode } = this.props;

    return (
      <div className="panel mb0 b0 radius-clear">

        <div className="panel-body p0 bt bb">
          <div className="fixed-table-container" style={styles.fixedTable}>
            <div className="header-bg"></div>
            <div className="table-wrapper">
              <div className="table-responsive">
                <div className="form-inline">

                  <Table className="table-hover table-striped mb-mails" >
                    <thead>
                      <tr>
                        { schema.properties.map((prop) =>
                          <th width={prop.width} className="text-center">
                            <div className="th-text">{prop.title}</div>
                          </th>
                        )}
                      </tr>
                    </thead>
                    <tbody>
                    { item.map((item, index) =>
                      <tr className="text-center" key={index}
                          tabIndex={index}
                          // onFocusCapture={(e) => { console.log('focus tr'); this.focused = index; onClickNode(index, item.editMode)}}
                          //  onClickCapture={(e) => { console.log('click'); e.currentTarget.focus(); onClickNode(index, item.editMode)}}
                          // onBlurCapture={(e) => this.onDisableNodeEditMode(e, index)}

                      >
                        { schema.properties.map((propSchema, tdIndex) => {

                          const propValue = propSchema.value(item[propSchema.name]);
                          let element;

                          if (item.editMode === true) {
                            // element = typeof propSchema.onEdit === 'function' && propSchema.onEdit(index, propValue);
                            element = <input  type="text" value={propValue}
                                              tabIndex={index + '_' + tdIndex}
                                              //onFocus={() => console.log('focus input')}
                                              //onBlurCapture={() => console.log('blur input')}
                                              //onFocusCapture={() => console.log('focus input')}

                                              onFocus={(e) => this.onEnableNodeEditMode(index, item.editMode, e)}
                                              onBlurCapture={(e) => this.onDisableNodeEditMode(e)}

                                             // onClick={() => onClickNode(index, item.editMode)}
                                             // onFocusCapture={(e) => { console.log('focus tr');  onClickNode(index, item.editMode)}}
                                             //   onBlurCapture={(e) => this.onDisableNodeEditMode(e, index)}
                            />
                          }
                          else {
                            element = propValue//<p
                              // tabIndex={index + '_' + tdIndex}
                              // onClick={() => onClickNode(index, item.editMode)}
                              // onFocusCapture={(e) => { console.log('focus tr');  onClickNode(index, item.editMode)}}
                              // onBlurCapture={(e) => this.onDisableNodeEditMode(e, index)}
                            //>{propValue}</p>;
                          }
                          return <td
                            //tabIndex={index}
                            key={index + '_' + tdIndex}
                            tabIndex={index}
                            // onFocusCapture={(e) => { console.log('focus tr');  onClickNode(index, item.editMode)}}
                            // onBlurCapture={(e) => this.onDisableNodeEditMode(e, index)}
                            onFocusCapture={(e) => this.onEnableNodeEditMode(index, item.editMode, e)}
                            onBlurCapture={(e) => this.onDisableNodeEditMode(e)}
                            //onFocusCapture={() => console.log('focus td')}
                            //onBlurCapture={() => console.log('blur td')}
                          >{element}</td>
                        })}
                      </tr>
                    )}
                    { item.length === 0 &&
                      <tr className="text-center">
                        <td colSpan={schema.properties.length} >등록 된 정보가 없습니다.</td>
                      </tr>
                    }
                    </tbody>
                  </Table>

                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default CastellanItem;
