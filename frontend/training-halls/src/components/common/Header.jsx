import React, { Component } from 'react';
import { NavLink } from 'react-router-dom';

export default class Header extends Component {

    render() {
        const { loggedIn, onLogout } = this.props;

        return (
            <header>
                <nav className="navbar navbar-default">
                    <div className="container-fluid">
                        <ul className="nav navbar-nav">
                            <li><NavLink className="nav-link" activeClassName="active" exact to="/">Home</NavLink></li>
                        </ul>
                        <ul className="nav navbar-nav navbar-right">
                            <li>{!loggedIn && <NavLink className="nav-link" activeClassName="active" to="/login">Login</NavLink>}</li>
                            <li>{!loggedIn && <NavLink className="nav-link" activeClassName="active" to="/register">Register</NavLink>}</li>
                            <li>{loggedIn && <a className="nav-link" href="javascript:void(0)" onClick={onLogout}>Logout</a>}</li>
                        </ul>
                    </div>
                </nav>
            </header>
        )
    }
}