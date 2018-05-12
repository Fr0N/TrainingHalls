import React, { Component } from 'react';
import HallCard from './HallCard';

export default class HallsList extends Component {
    render() {
        return (
            <div>
                {this.props.halls.map(h => (
                    <HallCard
                        name={h.name}
                        morningPrice={h.morningPrice}
                        daytimePrice={h.daytimePrice}
                        eveningPrice={h.eveningPrice}
                        nightPrice={h.nightPrice}
                        id={h.id} /> 
                ))}
            </div>
        );
    }
}