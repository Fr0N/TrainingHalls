import React, { Component } from 'react';


export default class Input extends Component {
    render() {
        const { name, type = 'text', value, onChange, label } = this.props;
        return (
            <div className="form-group">
                <label htmlFor={name}>{label}</label>
                <input
                    className="form-control"
                    onChange={onChange}
                    name={name}
                    id={name}
                    type={type}
                    value={value} />
            </div>
        );
    }
}