import React, { Component } from 'react';
import Input from '../common/Input';
import { register } from '../../api/remote';
import {  Route, Switch, withRouter } from 'react-router-dom';

class RegisterPage extends Component {
    constructor(props) {
        super(props);

        this.state = {
            username: '',
            password: '',
            confirmPassword: '',
            errors: false
        };

        this.onChangeHandler = this.onChangeHandler.bind(this);
        this.onSubmitHandler = this.onSubmitHandler.bind(this);
    }

    onChangeHandler(e) {
        this.setState({ [e.target.name]: e.target.value });
    }

    async onSubmitHandler(e) {
        e.preventDefault();
        if (this.state.password !== this.state.confirmPassword) {
            this.setState({
                errors: ["Passwords don't match"]
            });

            return;
        }
        const res = await register(this.state.username, this.state.password, this.state.confirmPassword);
    
        let body = await res.body;

        if (res.status !== 200) {
            this.setState({
                errors: [body]
            });
            return;
        }

        this.props.history.push('/login');
    }

    render() {
        let errorsToVisualize = null;
        if (this.state.errors) {
            errorsToVisualize = (
                <div>
                    {Object.keys(this.state.errors).map(k => {
                        return <p key={k} className="alert alert-danger">{this.state.errors[k]}</p>;
                    })}
                </div>
            );
        }

        return (
            <div className="container">
                <h1>Register</h1>
                {errorsToVisualize}
                <form onSubmit={this.onSubmitHandler}>
                    <Input
                        name="username"
                        value={this.state.username}
                        onChange={this.onChangeHandler}
                        label="Username"
                    />
                    <Input
                        name="password"
                        type="password"
                        value={this.state.password}
                        onChange={this.onChangeHandler}
                        label="Password"
                    />
                    <Input
                        name="confirmPassword"
                        type="password"
                        value={this.state.repeat}
                        onChange={this.onChangeHandler}
                        label="Repeat password"
                    />
                    <input type="submit" className="btn btn-primary" value="Register" />
                </form>
            </div>
        );
    }
}

export default withRouter(RegisterPage);