import React from "react";
import ReactDOM from "react-dom";
import "./index.css";
import App from "./App";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import LoginPage from "./routes/LoginPage";
import SignUpPage from "./routes/SignUpPage";
import UploadDetails from "./components/UploadDetails";
import AddEvent from "./components/AddEvent";
import Volun from "./components/Volun";
import AppOne from "./components/AppOne";
ReactDOM.render(
  <BrowserRouter>
    <Routes>
      <Route path="/login" exact element={<LoginPage />} />
      <Route path="/" exact element={<App />} />
      <Route path="/signup" exact element={<SignUpPage />} />
      <Route path="/upload" exact element={<UploadDetails />} />
      <Route path="/home" exact element={<App />} />
      <Route path="/add" exact element={<AddEvent />} />
      <Route path="/volun" exact element={<Volun />} />
      <Route path="/after-login" exact element={<AppOne />} />
    </Routes>
  </BrowserRouter>,
  document.getElementById("root")
);
