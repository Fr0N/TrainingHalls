import React from 'react';
import { Link } from 'react-router-dom';

export default function HallCard({ name, morningPrice, daytimePrice, eveningPrice, nightPrice, id }) {
    return (
        <div className="col-sm-6 col-md-4">
            <div className="thumbnail text-center">
                <div className="caption">
                    <h3>{name}</h3>
                    <h5>Price:</h5>
                    <ul className="list-group">
                        <li className="list-group-item">00:00 - 09:00 - {morningPrice}$</li>
                        <li className="list-group-item">09:00 - 17:00 - {daytimePrice}$</li>
                        <li className="list-group-item">17:00 - 22:00 - {eveningPrice}$</li>
                        <li className="list-group-item">22:00 - 00:00 - {nightPrice}$</li>
                    </ul>
                    <Link className="btn btn-primary" to={'/halls/details/' + id}>Reserve Hall</Link>
                </div>
            </div>
        </div>
    );
}