import React, { useState } from "react";

export default function ReportPanel() {
    const [dateFrom, setDateFrom] = useState("");
    const [dateTo, setDateTo] = useState("");
    const [reportType, setReportType] = useState("");
    const [progress, setProgress] = useState(null);

    const reportOptions = [
        { id: "match", label: "Match Summary Report" },
        { id: "team", label: "Team Perform. Report" },
        { id: "finance", label: "Financial Report" },
        { id: "attendance", label: "Attendance Report" },
        { id: "discipline", label: "Disciplinary Report" },
    ];

    const generateReport = () => {
        if (!dateFrom || !dateTo || !reportType) {
            alert("Please select date range and report type.");
            return;
        }

        setProgress(0);

        // Імітація генерації
        const interval = setInterval(() => {
            setProgress((prev) => {
                if (prev >= 100) {
                    clearInterval(interval);
                    return 100;
                }
                return prev + 5;
            });
        }, 150);
    };

    return (
        <div className="report-panel">
            <h1>Report</h1>

            {/* Period */}
            <div className="report-section">
                <h2>Period</h2>
                <div className="report-input-row">
                    <label>Date (From — To)</label>
                    <input
                        type="date"
                        value={dateFrom}
                        onChange={(e) => setDateFrom(e.target.value)}
                        className="report-input"
                    />
                    <input
                        type="date"
                        value={dateTo}
                        onChange={(e) => setDateTo(e.target.value)}
                        className="report-input"
                    />
                </div>
            </div>

            {/* Report Type */}
            <div className="report-section">
                <h2>Report Type</h2>

                <div className="report-list">
                    {reportOptions.map((opt) => (
                        <div key={opt.id} className="report-option">
                            <span>{opt.label}</span>
                            <input
                                type="radio"
                                name="reportType"
                                checked={reportType === opt.id}
                                onChange={() => setReportType(opt.id)}
                            />
                        </div>
                    ))}
                </div>
            </div>

            {/* Generate Button */}
            <button className="report-generate-btn" onClick={generateReport}>
                Generate
            </button>

            {/* Right Panel: Progress */}
            {progress !== null && (
                <div className="report-progress-box">
                    <h2>Report Generation</h2>
                    <p>Generating... {progress}%</p>

                    {progress === 100 && (
                        <div className="report-actions">
                            <button className="report-download-btn">
                                Download
                            </button>
                            <button className="report-open-btn">
                                Open in new tab
                            </button>
                        </div>
                    )}
                </div>
            )}
        </div>
    );
}
