import React, { Component } from 'react';
import { Route, Switch, withRouter } from 'react-router-dom';
// import logo from './logo.svg';
// import './App.css';
import RegisterPage from './components/auth/RegisterPage';
import LoginPage from './components/auth/LoginPage';
import HomePage from './components/HomePage/HomePage';
import Header from './components/common/Header';

class App extends Component {
    constructor(props) {
        super(props);

        this.onLogout = this.onLogout.bind(this);
    }

    onLogout() {
        localStorage.clear();
        this.props.history.push('/');
    }

render() {
  return (
      <div className="App">
          <Header loggedIn={localStorage.getItem('authToken') != null} onLogout={this.onLogout} />
          <Switch>
              <Route exact path="/" component={HomePage} />
              <Route path="/login" component={LoginPage} />
              <Route path="/register" component={RegisterPage} />
          </Switch>
      </div>
  );
}
}

export default withRouter(App);
