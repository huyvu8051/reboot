import React from 'react';

const Unauthorized = () => (
    <div style={{ textAlign: 'center', height: '100vh', display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center' }}>
        <h1 style={{ fontSize: '72px' }}>🚫</h1>
        <h2 style={{ marginTop: '20px', fontSize: '36px' }}>401</h2>
        <p style={{ marginTop: '20px', fontSize: '18px', textAlign: 'center' }}>You are not authorized to access this page.</p>
        <a href="/" style={{ marginTop: '40px', fontSize: '20px', textDecoration: 'none', color: '#333' }}>Back to Home</a>
    </div>
);

export default Unauthorized;
