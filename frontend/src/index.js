import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import { BrowserRouter } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Auth0Provider } from "@auth0/auth0-react"

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <Auth0Provider
      domain="dev-hjyor4m7jens4wru.eu.auth0.com"
      clientId="FWpXDOcJ3T6OgCLYRHhzrBWDKQKrc9IR"
      redirectUri={window.location.origin}
    >
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </Auth0Provider>
);

