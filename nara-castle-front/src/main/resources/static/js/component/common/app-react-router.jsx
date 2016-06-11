/**
 * Created by hkkang on 2016-04-05.
 */

import React from 'react';
import ReactDOM from 'react-dom';
import { Router, Route, hashHistory, Link } from 'react-router';

import { Dom as CastleDom, Const as Constant } from 'app/common/castle-common';

// Routes
import MainComponent from 'app/component/common/main.jsx';
import NotFound from 'app/component/common/error.jsx';
import Castles from 'app/component/list.jsx';

import DetailTab from 'app/component/detail-tab.jsx';
import Castle from 'app/component/basic.jsx';
import NameBook from 'app/component/contact/name-book.jsx';
import PhoneBook from 'app/component/contact/phone-book.jsx';
import EmailBook from 'app/component/contact/email-book.jsx';
import AddressBook from 'app/component/contact/address-book.jsx';
import AccountBook from 'app/component/history/account-book.jsx';
import StateBook from 'app/component/history/state-book.jsx';
import MetroBook from 'app/component/history/metro-book.jsx';


'use strict';

let CTX = Constant.PAV_CTX_HASH,
    appRootPath = CTX ?  CTX : '/';


ReactDOM.render((
    <Router history={hashHistory} >
        <Route path={`${appRootPath}`} component={MainComponent}>
            <Route path="castles" component={Castles}/>
            <Route path="castle/:castleId" component={DetailTab}>
                <Route path="basic" component={Castle}/>
                <Route path="name-book" component={NameBook}/>
                <Route path="phone-book" component={PhoneBook}/>
                <Route path="email-book" component={EmailBook}/>
                <Route path="address-book" component={AddressBook}/>
                <Route path="account-book" component={AccountBook}/>
                <Route path="state-book" component={StateBook}/>
                <Route path="metro-book" component={MetroBook}/>
            </Route>
            <Route path="*" component={NotFound}/>
        </Route>
    </Router>
), CastleDom.getCastleMainDom());
