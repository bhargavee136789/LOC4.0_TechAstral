import React from "react";
import ReactDOM from "react-dom";
import "./index.css";
import App from "./App";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import LoginPage from "./routes/LoginPage";
import SignUpPage from "./routes/SignUpPage";
import UploadDetails from "./components/UploadDetails";

ReactDOM.render(
  <BrowserRouter>
    <Routes>
      <Route path="/" element={<App />} />
      <Route path="/login" element={<LoginPage />} />
      <Route path="/signup" element={<SignUpPage />} />
      <Route path="/upload" element={<UploadDetails />} />
    </Routes>
  </BrowserRouter>,
  document.getElementById("root")
);
