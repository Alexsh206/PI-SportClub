const API_URL = "http://localhost:8080/api";



async function request(path, method = "GET", body = null) {
    const options = { method, headers: {} };

    if (body) {
        options.headers["Content-Type"] = "application/json";
        options.body = JSON.stringify(body);
    }

    const response = await fetch(API_URL + path, options);

    if (!response.ok) {
        const errorText = await response.text();
        throw new Error(`API error: ${response.status} - ${errorText}`);
    }

    if (response.status === 204) return null; // no content
    return response.json();
}

export async function login(credentials) {
    const res = await fetch(`${API_URL}/auth/login`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(credentials),
    });

    const data = await res.json();

    return {
        status: res.status,
        data,
    };
}

export function getAllEvents() {
    return request("/events/all");
}


export function getEventById(id) {
    return request(`/events/${id}`);
}

export function createEvent(eventData) {
    return request("/events/create", "POST", eventData);
}

export function updateEvent(id, eventData) {
    return request(`/events/update/${id}`, "PUT", eventData);
}

export function deleteEvent(id) {
    return request(`/events/${id}`, "DELETE");
}

