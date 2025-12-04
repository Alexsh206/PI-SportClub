import React from "react";

export default function AnalyticsPanel() {
    return (
        <div className="analytics-panel">

            <h1 className="analytics-title">
                Analytics and statistics formation
            </h1>

            <div className="analytics-layout">

                {/* LEFT FILTERS */}
                <div className="analytics-filters">
                    <h2>Filters</h2>

                    <div className="filter-row">
                        <label>Team</label>
                        <input className="filter-input" />
                    </div>

                    <div className="filter-row">
                        <label>Sport</label>
                        <input className="filter-input" />
                    </div>

                    <div className="filter-row">
                        <label>Season</label>
                        <input className="filter-input" />
                    </div>

                    <button className="find-btn">Find</button>
                </div>

                {/* RIGHT TABLE */}
                <div className="analytics-table-container">
                    <table className="analytics-table">
                        <thead>
                        <tr>
                            <th>Sport</th>
                            <th>Team</th>
                            <th>Season</th>
                            <th>Games</th>
                            <th>W</th>
                            <th>L</th>
                            <th>Pts</th>
                            <th>Gls For</th>
                            <th>Gls Agnst</th>
                        </tr>
                        </thead>

                        <tbody>
                        <tr>
                            <td>Football</td>
                            <td>Arsenal 3</td>
                            <td>UEFA</td>
                            <td>20</td>
                            <td>9</td>
                            <td>11</td>
                            <td>98</td>
                            <td>12</td>
                            <td>29</td>
                        </tr>
                        </tbody>
                    </table>
                </div>

            </div>
        </div>
    );
}
