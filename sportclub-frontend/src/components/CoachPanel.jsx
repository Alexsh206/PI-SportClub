import React, { useState } from 'react';
import './Navbar'
import { useNavigate } from 'react-router-dom';

const CoachProfileCard = () => {
  const navigate = useNavigate();

  const Navbar = (
    <nav className="navbar">
      <div className="navbar-left">
        <img
          src="/logo.png"
          alt="Sport Arena Logo"
          className="logo"
        />

        <button className="nav-btn">Events</button>
        <button className="nav-btn">Search</button>
        <button className="nav-btn">Watch online</button>
        <button className="nav-btn">Sports</button>
      </div>

      <div className="navbar-right">
        <button className="nav-btn" onClick={() => navigate("/login")}>Log in</button>
        <button className="nav-btn">Sign in</button>
      </div>
    </nav>
  );

  const fields = ['Name', 'Sport', 'Country', 'City', 'Team', 'Experience', 'Titles', 'Availability'];

  const [formData, setFormData] = useState(
    fields.reduce((acc, field) => ({ ...acc, [field.toLowerCase()]: '' }), {})
  );

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const cardStyle = {
    width: '100%',
    maxWidth: '450px',
    padding: '24px',
    borderRadius: '12px',
    boxShadow: '0 4px 12px rgba(0, 0, 0, 0.2)',
    backgroundColor: '#6ED0BD',
    margin: '20px',
  };

  const headerStyle = {
    fontSize: '24px',
    fontWeight: 'bold',
    textAlign: 'center',
    marginBottom: '20px',
    color: '#333',
  };

  const rowStyle = {
    display: 'flex',
    alignItems: 'center',
    marginBottom: '16px',
  };

  const labelStyle = {
    width: '35%',
    fontSize: '16px',
    fontWeight: '500',
    color: '#333',
    whiteSpace: 'nowrap',
  };

  const inputStyle = {
    width: '65%',
    height: '32px',
    padding: '0 15px',
    backgroundColor: 'white',
    border: 'none',
    borderRadius: '16px',
    boxShadow: 'inset 0 1px 3px rgba(0, 0, 0, 0.15)',
    fontSize: '16px',
    color: '#333',
    outline: 'none',
  };

  const buttonStyle = {
    width: '100%',
    padding: '10px 0',
    marginTop: '25px',
    fontSize: '18px',
    fontWeight: '600',
    color: 'white',
    backgroundColor: '#C84F4F',
    borderRadius: '9999px',
    border: 'none',
    cursor: 'pointer',
    transition: 'background-color 0.2s',
    boxShadow: '0 4px 6px rgba(0, 0, 0, 0.1)',
  };


  return (
    <div style={{ backgroundColor: '#CEFFF5', minHeight: '100vh', display: 'flex', flexDirection: 'column' }}>


      {Navbar}


      <div style={{ flexGrow: 1, display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
        <div style={cardStyle}>
          <h1 style={headerStyle}>
            Coach
          </h1>

          <div>
            {fields.map((label) => {
              const fieldName = label.toLowerCase();
              return (
                <div key={label} style={rowStyle}>
                  <label style={labelStyle} htmlFor={fieldName}>
                    {label}
                  </label>

                  <input
                    type="text"
                    id={fieldName}
                    name={fieldName}
                    style={inputStyle}
                    value={formData[fieldName]}
                    onChange={handleChange}
                  />
                </div>
              );
            })}
          </div>

          <button
            style={buttonStyle}
            onMouseOver={(e) => e.currentTarget.style.backgroundColor = '#B34545'}
            onMouseOut={(e) => e.currentTarget.style.backgroundColor = '#C84F4F'}
          >
            View
          </button>
        </div>
      </div>
    </div>
  );
};

export default CoachProfileCard;