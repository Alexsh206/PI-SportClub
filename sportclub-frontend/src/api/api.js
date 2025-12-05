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

export function getAllTeams() {
    return request("/teams/all");
}

export function createTeam(data) {
    return request("/teams/create", "POST", data);
}

export function updateTeam(id, data) {
    return request(`/teams/update/${id}`, "PUT", data);
}

export function deleteTeam(id) {
    return request(`/teams/${id}`, "DELETE");
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


export function getAllParticipants() {
    return request("/event-participants/all");
}

export function getAllResults() {
    return request("/event-results/all");
}

export function getParticipantsByEvent(id) {
    return request(`/event-participants/event/${id}`);
}


export function getResultsByEvent(id) {
    return request(`/event-results/event/${id}`);
}

export function getAllAthletes() {
    return request("/athletes/all");
}

export function createAthlete(data) {
    return request("/athletes/create", "POST", data);
}

export function updateAthlete(id, data) {
    return request(`/athletes/update/${id}`, "PUT", data);
}

export function deleteAthlete(id) {
    return request(`/athletes/${id}`, "DELETE");
}

export function getAllUsers() {
    return request("/spectators/all");
}

export function blockUser(id) {
    return request(`/spectators/block/${id}`, "PUT");
}

export function unblockUser(id) {
    return request(`/spectators/unblock/${id}`, "PUT");
}

export function createHallLayout(hallData) {
    return request("/halls/create", "POST", hallData);
}

export function getHallByEvent(eventId) {
    return request(`/halls/event/${eventId}`);
}

export function generateSeats({
                                  hallId,
                                  rows,
                                  seatsInRow,
                                  vipRows,
                                  standardRows,
                                  vipPrice,
                                  standardPrice,
                                  economyPrice
                              }) {
    const query =
        `?hallId=${hallId}` +
        `&rows=${rows}` +
        `&seatsInRow=${seatsInRow}` +
        `&vipRows=${vipRows}` +
        `&standardRows=${standardRows}` +
        `&vipPrice=${vipPrice}` +
        `&standardPrice=${standardPrice}` +
        `&economyPrice=${economyPrice}`;

    return request(`/seats/generate${query}`, "POST");
}

export function getSeatsByHall(hallId) {
    return request(`/seats/hall/${hallId}`);
}

export function generateSeatRanges({
                                       hallId,
                                       rows,
                                       seatsInRow,

                                       vipFrom,
                                       vipTo,

                                       standardFrom,
                                       standardTo,

                                       economyFrom,
                                       economyTo,

                                       vipPrice,
                                       standardPrice,
                                       economyPrice
                                   }) {
    const query =
        `?hallId=${hallId}` +
        `&rows=${rows}` +
        `&seatsInRow=${seatsInRow}` +
        `&vipFrom=${vipFrom}&vipTo=${vipTo}` +
        `&standardFrom=${standardFrom}&standardTo=${standardTo}` +
        `&economyFrom=${economyFrom}&economyTo=${economyTo}` +
        `&vipPrice=${vipPrice}` +
        `&standardPrice=${standardPrice}` +
        `&economyPrice=${economyPrice}`;

    return request(`/seats/generate-range${query}`, "POST");
}

export function createHallFull(params) {
    const query = new URLSearchParams(params).toString();
    return request(`/halls/create-full?${query}`, "POST");
}
export { request };
