import React, { Component } from 'react';
import { getHallWithReservations } from '../../api/remote';
import BigCalendar from 'react-big-calendar';
import moment from 'moment';
import "react-big-calendar/lib/css/react-big-calendar.css";

BigCalendar.setLocalizer(BigCalendar.momentLocalizer(moment));


export default class HallReservePage extends Component {
    // constructor(props) {
    //     super(props);

    //     this.state = {
    //         hall: false
    //     };
    // }

    // componentDidMount() {
    //     this.getData();
    // }

    // async getData() {
    //     const data = await getHallWithReservations();
    //     this.setState({ hall: data });
    // }

    state = {
        events: [
            {
                start: new Date(),
                end: new Date(moment().add(1, "days")),
                title: "Some title"
            }
        ]
    };

    render() {
        // let main = <p>Loading &hellip;</p>;
        // if (this.state.hall) {
        //     const hall = this.state.hall;
        //     main = (
        //         <div className="jumbotron text-center">
        //             <h2>{hall.name}</h2>
        //             <h5>Price:</h5>
        //             <ul className="list-group">
        //                 <li className="list-group-item">00:00 - 09:00 - {hall.morningPrice}$</li>
        //                 <li className="list-group-item">09:00 - 17:00 - {hall.daytimePrice}$</li>
        //                 <li className="list-group-item">17:00 - 22:00 - {hall.eveningPrice}$</li>
        //                 <li className="list-group-item">22:00 - 00:00 - {hall.nightPrice}$</li>
        //             </ul>
        //         </div>
        //     );
        // }

        return (
            <div className="container">
                <h1>Reserve Hall Page</h1>
                {/* {main} */}
                <BigCalendar
                    selectable
                    events={this.state.events}
                    defaultView="week"
                    scrollToTime={new Date(1970, 1, 1, 6)}
                    defaultDate={new Date()}
                    onSelectSlot={slotInfo =>
                        alert(
                            `selected slot: \n\nstart ${slotInfo.start.toLocaleString()} ` +
                            `\nend: ${slotInfo.end.toLocaleString()}` +
                            `\naction: ${slotInfo.action}`,
                            console.log(this.BigCalendar)
                        )
                    }
                    step={10}
                    style={{ height: "100vh" }}
                />
            </div>
        );
    }
}