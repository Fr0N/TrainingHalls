import React, { Component } from 'react';
import Input from '../common/Input';
import { login } from '../../api/remote';
import { withRouter } from 'react-router-dom';

class LoginPage extends Component {
    constructor(props) {
        super(props);

        this.state = {
            username: false,
            password: false
        };

        this.onChangeHandler = this.onChangeHandler.bind(this);
        this.onSubmitHandler = this.onSubmitHandler.bind(this);
    }

    onChangeHandler(e) {
        this.setState({ [e.target.name]: e.target.value });
    }

    async onSubmitHandler(e) {
        e.preventDefault();
        if (!this.state.username) {
            this.setState({
                errors: ["Username cannot be empty!"]
            });

            return;
        }
        if (!this.state.password) {
            this.setState({
                errors: ["Password cannot be empty!"]
            });

            return;
        }
        const res = await login(this.state.username, this.state.password);
        
        let body = await res.body;

        if (res.status != 200) {
            if(!body){
                this.setState({
                    errors: ["An error occured while trying to login. Make shure username and password are correct!"]
                });
            } else {
                this.setState({
                    errors: [body]
                });
            }
            
            return;
        }

        localStorage.setItem('authToken', body.Authorization);
        this.props.history.push('/');      
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
                <h1>Login</h1>
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
                    <input type="submit" className="btn btn-primary" value="Login" />
                </form>
            </div>
        );
    }
}

export default withRouter(LoginPage);