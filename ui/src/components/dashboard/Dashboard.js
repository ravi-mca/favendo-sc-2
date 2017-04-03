import React from "react";
import 'react-bootstrap';
import { Router, Route, Link, browserHistory, IndexRoute  } from 'react-router';

import Header from '../header/Header';
import Footer from '../footer/Footer';

export default class Dashboard extends React.Component {
    constructor() {
        super();
    }

    render() {
        return (
            <div>
                <Header/>
                    <div> {this.props.children}</div>
                <Footer/>
            </div>
        );
    }
}
