import React, { useState } from "react";
import signup from "../assets/Sign up-amico.svg";
import { Link } from "react-router-dom";
import axios from "axios";

const SignUpPage = () => {
  const [email, setEmail] = useState("");
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const integrate = () => {
    let data = new FormData();
    data.append("email", email);
    data.append("username", username);
    data.append("password", password);
    let config = {
      method: "POST",
      url: "http://shrutiprasad.pythonanywhere.com/auth/register/",
      headers: { "Content-Type": " application/json" },
      data: data,
    };
    axios(config)
      .then((response) => {
        console.log(JSON.stringify(response.data));
      })
      .catch((error) => {
        console.log(error);
      });
    setEmail("");
    setPassword("");
    setUsername("");
  };

  /* 
async function integrate() {
  console.log("hello");
  try {
    let result = await fetch(
      "http://shrutiprasad.pythonanywhere.com/auth/register/",
      {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
          email: email,
          password:password,
          username: username,
        }),
       
        
      }
    );
    result = await result;
    console.log(result);
    
    
  } catch (error) {
    console.log("Error" + error);
    
  }
} */

  return (
    <div className="w-full h-screen flex">
      <div className="grid grid-cols-1 md:grid-cols-2 m-auto h-[550px] shadow-lg shadow-gray-400 sm:max-w-[900px] rounded-2xl">
        <div className="w-full h-[550px] hidden md:block border rounded-tl-2xl rounded-bl-2xl border-rose-500">
          <img src={signup} className="w-full h-full" alt="/" />
        </div>
        <div className="p-4 flex flex-col justify-center border border-rose-500 lg:rounded-tr-2xl  rounded-br-2xl">
          <div>
            <h2 className="text-4xl font-bold text-center mb-12">Register</h2>
            <div>
              <input
                className="border p-2 mr-2 w-full my-2 hover:border-rose-400 rounded-lg"
                type="email"
                placeholder="Email"
                required
                value={email}
                onChange={(e) => setEmail(e.target.value)}
              />
              <input
                className="border p-2 my-2 flex w-full hover:border-rose-400 rounded-lg"
                type="text"
                placeholder="NGO Name"
                required
                value={username}
                onChange={(e) => setUsername(e.target.value)}
              />
              <input
                className="border p-2 w-full my-2 hover:border-rose-400 rounded-lg"
                type="password"
                placeholder="Password"
                required
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />
            </div>
            <button
              className="w-full py-2 my-4 bg-rose-600 hover:bg-rose-500 text-white

           font-semibold"
              onClick={integrate}
            >
              Sign Up
            </button>
            <p
              className="text-center font-semibold text-lg
            "
            >
              Already a member?
            </p>
            <Link to="/login">
              <p className="hover:text-rose-600 font-semibold text-center text-base text-black">
                Login
              </p>
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
};

export default SignUpPage;
