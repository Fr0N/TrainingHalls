const host = 'http://localhost:8000/';

async function register(username, password, confirmPassword) {
    const res = await fetch(host + 'api/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            username,
            password,
            confirmPassword
        })
    });

    return await {"status" : res.status, "body" : res.json()};
}

async function login(username, password) {
    const res = await fetch(host + 'login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            username,
            password
        })
    });
    
    return await {"status" : res.status, "body" : res.json()};
    
}

async function getHalls() {
    const res = await fetch(host + 'api/halls/all', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    });
    
    return await res.json();
}

async function getHallWithReservations(id) {
    const res = await fetch(host + 'api/halls/' + id, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    });
    
    return await res.json();
}

export { register, login, getHalls, getHallWithReservations};