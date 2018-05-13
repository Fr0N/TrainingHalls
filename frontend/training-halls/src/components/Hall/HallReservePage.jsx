import React, { Component } from 'react';
import { getHallById, getReservationsForHallById } from '../../api/remote';
import BigCalendar from 'react-big-calendar';
import moment from 'moment';
import "react-big-calendar/lib/css/react-big-calendar.css";
import { reserveHall } from '../../api/remote';

BigCalendar.setLocalizer(BigCalendar.momentLocalizer(moment));


export default class HallReservePage extends Component {
    constructor(props) {
        super(props);

        this.state = {
            hall: false,
            events: [],
            startTime: "",
            endTime: ""
        };

        this.onSubmitHandler = this.onSubmitHandler.bind(this);
    }

    componentDidMount() {
        this.getData();
    }

    async getData() {
        const hallData = await getHallById(this.props.match.params.id);
        this.setState({ hall: hallData });

        const reservations = await getReservationsForHallById(this.props.match.params.id);
        let events = [];
        for(let key in reservations) {
            events.push({
                start: new Date(reservations[key].start),
                end: new Date(reservations[key].end),
                title: "Reserved!"
            })
        }

        console.log("my events " + events);

        this.setState({
            events
        })
        // this.setState({
        //     events: Object.keys(reservations).map(k => {
        //         return {
        //             start: reservations[k].start,
        //             end: reservations[k].end,
        //             title: "Reserved!"
        //         }
        //     })
        // });

        // this.setState({
        //     events: [
        //         {
        //             start: new Date(),
        //             end: new Date(moment().add(1, "days")),
        //             title: "Some title"
        //         }
        //     ]
        // });

        console.log("calendar events " + this.state.events)
    }

    async onSubmitHandler(e) {
        e.preventDefault();

        const res = await reserveHall(this.props.match.params.id, this.state.startTime, this.state.endTime);
        
        let body = await res.body;
        
        if (res.status != 200) {
            this.setState({
                errors: [body]
            });
            return;
        }
        // this.props.history.push('/');      
    }

    // calculatePrice() {
    //     if(this.state.startTime)
    // }

    render() {
        let main = <p>Loading &hellip;</p>;
        if (this.state.hall) {
            const hall = this.state.hall;
            main = (
                <div className="jumbotron text-center">
                    <div className="row">
                        <div>
                            <h2>{hall.name}</h2>
                            <h5>Price:</h5>
                            <ul className="list-group">
                                <li className="list-group-item">00:00 - 09:00 - {hall.morningPrice}$</li>
                                <li className="list-group-item">09:00 - 17:00 - {hall.daytimePrice}$</li>
                                <li className="list-group-item">17:00 - 22:00 - {hall.eveningPrice}$</li>
                                <li className="list-group-item">22:00 - 00:00 - {hall.nightPrice}$</li>
                            </ul>
                        </div>
                        <div>
                            <form>
                                <h2>Choose time period for reservation</h2>
                                <label htmlFor="startTime">Start time</label>
                                <input className="form-control" id="startTime" disabled="disabled" value={this.state.startTime} />
                                <label htmlFor="endTime">End time</label>
                                <input className="form-control" id="endTime" disabled="disabled" value={this.state.endTime} />
                                <input type="submit" className="btn btn-primary" onClick={this.onSubmitHandler} value="Reserve" />
                            </form>
                        </div>
                    </div>
                </div>
            );
        }

        return (
            <div className="container">
                <h1>Reserve Hall Page</h1>
                {main}
                <BigCalendar
                    selectable
                    events={this.state.events}
                    defaultView="week"
                    scrollToTime={new Date(1970, 1, 1, 6)}
                    defaultDate={new Date()}
                    onSelectSlot={slotInfo => {
                        this.setState({ startTime: slotInfo.start.toLocaleString() }),
                        this.setState({ endTime: slotInfo.end.toLocaleString() })
                        console.log(slotInfo.end - slotInfo.start)
                    }}
                    step={10}
                    style={{ height: "100vh" }}
                />
            </div>
        );
    }
}