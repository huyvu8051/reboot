import React from 'react'
import {Link} from 'react-router-dom'

const PageNotFound = () => {

    return <div style={{textAlign: 'center', height: '100vh'}}>
        <h1 style={{fontSize: '72px'}}>😔</h1>
        <h2>Oops! This page isn't available.</h2>
        <p>The link you followed may be broken, or the page may have been removed.</p>
        <Link to='/' style={{fontSize: '20px'}}>Back to Home</Link>
    </div>
}

export default PageNotFound
