-- =========================================
--  Table: spectators
-- =========================================
CREATE TABLE spectators (
                            spectator_id SERIAL PRIMARY KEY,
                            full_name VARCHAR(255) NOT NULL,
                            email VARCHAR(255) UNIQUE NOT NULL,
                            password VARCHAR(255) NOT NULL,
                            phone VARCHAR(50),
                            status BOOLEAN NOT NULL
);

-- =========================================
--  Table: coaches
-- =========================================
CREATE TABLE coaches (
                         coach_id SERIAL PRIMARY KEY,
                         full_name VARCHAR(255) NOT NULL,
                         email VARCHAR(255) UNIQUE NOT NULL,
                         password VARCHAR(255) NOT NULL,
                         phone VARCHAR(50),
                         team VARCHAR(255)
);

-- =========================================
--  Table: athletes
-- =========================================
CREATE TABLE athletes (
                          athlete_id SERIAL PRIMARY KEY,
                          full_name VARCHAR(255) NOT NULL,
                          email VARCHAR(255) UNIQUE NOT NULL,
                          password VARCHAR(255) NOT NULL,
                          phone VARCHAR(50),
                          birth_date DATE,
                          sport_type VARCHAR(255)
);

-- =========================================
--  Table: teams
-- =========================================
CREATE TABLE teams (
                       team_id SERIAL PRIMARY KEY,
                       team_name VARCHAR(255) UNIQUE NOT NULL,
                       coach_id INTEGER REFERENCES coaches(coach_id)
);

-- =========================================
--  Table: team_members (many-to-many)
-- =========================================
CREATE TABLE team_members (
                              team_id INTEGER REFERENCES teams(team_id),
                              athlete_id INTEGER REFERENCES athletes(athlete_id),
                              joined_at DATE,
                              PRIMARY KEY (team_id, athlete_id)
);

-- =========================================
--  Table: events
-- =========================================
CREATE TABLE events (
                        event_id SERIAL PRIMARY KEY,
                        title VARCHAR(255) NOT NULL,
                        sport_type VARCHAR(255) NOT NULL,
                        tournament_name VARCHAR(255),
                        event_date DATE NOT NULL,
                        event_time TIME NOT NULL,
                        location VARCHAR(255) NOT NULL,
                        status VARCHAR(50) NOT NULL
);

-- =========================================
--  Table: event_participants
-- =========================================
CREATE TABLE event_participants (
                                    participant_id SERIAL PRIMARY KEY,
                                    event_id INTEGER NOT NULL REFERENCES events(event_id),
                                    participant_type VARCHAR(50) NOT NULL,
                                    team_id INTEGER REFERENCES teams(team_id),
                                    athlete_id INTEGER REFERENCES athletes(athlete_id)
);

-- =========================================
--  Table: event_results
-- =========================================
CREATE TABLE event_results (
                               result_id SERIAL PRIMARY KEY,
                               event_id INTEGER NOT NULL REFERENCES events(event_id),
                               participant_id INTEGER NOT NULL REFERENCES event_participants(participant_id),
                               score INTEGER NOT NULL,
                               place INTEGER
);

-- =========================================
--  Table: halls (1:1 with event)
-- =========================================
CREATE TABLE halls (
                       hall_id SERIAL PRIMARY KEY,
                       event_id INTEGER UNIQUE NOT NULL REFERENCES events(event_id),
                       rows_count INTEGER NOT NULL,
                       seats_in_row INTEGER NOT NULL
);

-- =========================================
--  Table: seats
-- =========================================
CREATE TABLE seats (
                       seat_id SERIAL PRIMARY KEY,
                       hall_id INTEGER NOT NULL REFERENCES halls(hall_id),
                       row_number INTEGER NOT NULL,
                       seat_number INTEGER NOT NULL,
                       seat_type VARCHAR(50) NOT NULL,
                       price NUMERIC(10,2) NOT NULL
);

-- =========================================
--  Table: tickets
-- =========================================
CREATE TABLE tickets (
                         ticket_id SERIAL PRIMARY KEY,
                         seat_id INTEGER UNIQUE NOT NULL REFERENCES seats(seat_id),
                         event_id INTEGER NOT NULL REFERENCES events(event_id),
                         spectator_id INTEGER REFERENCES spectators(spectator_id),
                         status VARCHAR(50) NOT NULL,
                         purchase_date TIMESTAMP,
                         return_date TIMESTAMP
);

-- =========================================
--  Table: online_access
-- =========================================
CREATE TABLE online_access (
                               access_id SERIAL PRIMARY KEY,
                               spectator_id INTEGER NOT NULL REFERENCES spectators(spectator_id),
                               event_id INTEGER NOT NULL REFERENCES events(event_id),
                               activated_at TIMESTAMP NOT NULL,
                               is_active BOOLEAN NOT NULL
);
