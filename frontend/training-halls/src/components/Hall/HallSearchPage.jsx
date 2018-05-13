import React, { Component } from 'react';
import Input from '../common/Input';
import { searchHalls } from '../../api/remote';
import HallsList from '../HomePage/HallsList';

export default class HallSearchPage extends Component {

    constructor(props) {
        super(props);

        this.state = {
            start: '',
            end: '',
            halls: [],
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

        const res = await searchHalls(this.state.start, this.state.end);

        let body = await res.body;

        if (res.status !== 200) {
            if (!body) {
                this.setState({
                    errors: ["An error occured, please try again!"]
                });
            } else {
                this.setState({
                    errors: [body]
                });
            }

            return;
        } else {
            this.setState({
                errors: false
            });
        }

        this.setState({ halls: body });
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
            <div className="container text-center">
                <h1>Search Page</h1>
                <p>Search by choosing the start datetime and the end datetime.</p>
                <br />
                <form onSubmit={this.onSubmitHandler}>
                    <Input
                        name="start"
                        type="datetime-local"
                        value={this.state.start}
                        onChange={this.onChangeHandler}
                        label="Start Time"
                    />
                    <Input
                        name="end"
                        type="datetime-local"
                        value={this.state.end}
                        onChange={this.onChangeHandler}
                        label="End Time"
                    />
                    <input type="submit" className="btn btn-primary" value="Search" />
                </form>
                {errorsToVisualize}
                <br />
                <HallsList halls={this.state.halls} />
            </div>
        );
    }
}