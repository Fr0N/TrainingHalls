import React, { Component } from 'react';
import { getHalls } from '../../api/remote';
import HallsList from './HallsList';

export default class HomePage extends Component {

    constructor(props) {
        super(props);

        this.state = {
            halls: []
        };
    }

    componentDidMount() {
        this.getData();
    }

    async getData() {
        const data = await getHalls();
        this.setState({ halls: data });
    }

    render() {

        return (
            <div className="container text-center">
                <h1>Home Page</h1>
                <p>Welcome to Training Halls.</p>
                <br/>
                <HallsList halls={this.state.halls}/>
            </div>
        );
    }
}