import React, { useState } from "react";
import "./AddEvent.css";
import { Link } from "react-router-dom";
import axios from "axios";
const AddEvent = () => {
  const [ename, setEname] = useState("");
  const [date, setDate] = useState("");
  const [loc, setLoc] = useState("");
  const [type, setType] = useState("");
  const [description, setDescription] = useState("");
  const [time, setTime] = useState("");
  const [input, setInput] = useState(""); // donate wale ki liye
  const [endDate, setEndDate] = useState("");
  const [donate, setDonate] = useState(false);
  const [volun, setVolun] = useState(false);
  const [showBtn, setShowBtn] = useState(false);
  const [input1, setInput1] = useState(""); // volunteers ka liye
  const [DeadLine, setDeadLine] = useState("");
  const donatecrowdfund = () => {
    var axios = require("axios");
    var FormData = require("form-data");
    var data = new FormData();
    data.append("name", ename);
    data.append("date", date);
    data.append("starttime", time);
    data.append("location", loc);
    data.append("type1", type);
    data.append("desc", description);
    data.append("endtime", endDate);
    data.append("reqdamt", input);

    var config = {
      method: "post",
      url: "http://shrutiprasad.pythonanywhere.com/auth/CrowdFundingDetails/",
      headers: {},
      data: data,
    };

    axios(config)
      .then(function (response) {
        console.log(JSON.stringify(response.data));
      })
      .catch(function (error) {
        console.log(error);
      });
  };
  const volunterreq = () => {
    var axios = require("axios");
    var FormData = require("form-data");
    var data = new FormData();
    data.append("name", ename);
    data.append("date", date);
    data.append("time", time);
    data.append("location", loc);
    data.append("type1", type);
    data.append("desc", description);
    data.append("reqdppl", input1);
    data.append("deadline", DeadLine);

    var config = {
      method: "post",
      url: "http://shrutiprasad.pythonanywhere.com/auth/EventDetails/",
      headers: {},
      data: data,
    };

    axios(config)
      .then(function (response) {
        console.log(JSON.stringify(response.data));
      })
      .catch(function (error) {
        console.log(error);
      });
  };

  return (
    <div className="addpage">
      <div className="flex flex-col justify-center px-56 rounded-2xl font-body ">
        <div className="w-full bg-white mt-20 py-4 shadow-lg rounded-lg">
          <div className="px-12 ">
            <Link to="/after-login">
              <button className=" text-center text-3xl hover:bg-blue-600 rounded-xl hover:text-white px-6">
                Home
              </button>
            </Link>

            <h2 className="text-center text-3xl py-6">Add Event</h2>
          </div>
          <div className="grid grid-cols-3">
            <div className="flex flex-col items-center justify-center py-2">
              <label htmlFor="EventName" className="text-center text-2xl">
                Event Name
              </label>
              <input
                value={ename}
                type="text"
                className="border p-2 my-4 rounded-lg"
                id="EventName"
                onChange={(e) => setEname(e.target.value)}
              />
            </div>{" "}
            <div className="flex flex-col items-center justify-center py-2">
              <label htmlFor="date" className="text-center text-2xl ">
                Date
              </label>
              <input
                value={date}
                type="text"
                className="border p-2 my-4 rounded-lg"
                id="date"
                required
                onChange={(e) => setDate(e.target.value)}
              />
            </div>
            <div className="flex flex-col items-center justify-center py-2">
              <label htmlFor="location" className="text-center text-2xl ">
                Location
              </label>
              <input
                type="text"
                className="border p-2 my-4 rounded-lg"
                id="location"
                required
                value={loc}
                onChange={(e) => setLoc(e.target.value)}
              />
            </div>
            <div className="flex flex-col items-center justify-center py-2">
              <label htmlFor="type" className="text-center text-2xl ">
                Type
              </label>
              <input
                type="text"
                value={type}
                id="type"
                className="border p-2 my-4 rounded-lg"
                onChange={(e) => setType(e.target.value)}
              />
            </div>
            <div className="flex flex-col items-center justify-center py-2">
              <label htmlFor="desc" className="text-center text-2xl ">
                Description
              </label>
              <textarea
                value={description}
                name=""
                id="desc"
                cols="20"
                rows="2"
                className="border p-2 my-4 rounded-lg"
                onChange={(e) => setDescription(e.target.value)}
              />
            </div>
            <div className="flex flex-col items-center justify-center py-2">
              <label htmlFor="time" className="text-center text-2xl ">
                Time
              </label>
              <input
                value={time}
                name=""
                id="time"
                type="text"
                className="border p-2 my-4 rounded-lg"
                onChange={(e) => setTime(e.target.value)}
              />
            </div>
          </div>
          <h2 className="text-center text-blue-400 text-2xl my-8">
            ASK FOR DONATIONS AND VOLUNTEER
          </h2>
          <div className="flex justify-center items-center ">
            <button
              className={
                volun
                  ? "hidden"
                  : "text-xl hover:bg-blue-500 rounded-lg p-3 mr-4 hover:text-white"
              }
              onClick={() => {
                setDonate(!donate);
                setShowBtn(!showBtn);
              }}
            >
              Donate
            </button>
            <button
              className={
                donate
                  ? "hidden"
                  : "text-xl hover:bg-blue-500 rounded-lg p-3 hover:text-white"
              }
              onClick={() => {
                setVolun(!volun);
              }}
            >
              Volunteer
            </button>
          </div>
          {donate && (
            <div className="flex flex-col justify-center items-center">
              <div className="my-8">
                <label htmlFor="amt" className="mr-8 text-2xl">
                  Amount Required in Rupees
                </label>
                <input
                  type="number"
                  id="amt"
                  className="border p-2  rounded-lg"
                  value={input}
                  onChange={(e) => {
                    setInput(e.target.value);
                  }}
                />
              </div>
              <div className="mb-4">
                <label htmlFor="amt" className="mr-8 text-2xl">
                  End Date
                </label>
                <input
                  type="text"
                  id="amt"
                  className="border p-2  rounded-lg"
                  value={endDate}
                  onChange={(e) => {
                    setEndDate(e.target.value);
                  }}
                />
              </div>
              <Link to="/after-login">
                <div>
                  <button
                    className="hover:bg-blue-400 hover:text-whitepx-4 p-2 rounded-xl"
                    onClick={donatecrowdfund}
                  >
                    Submit
                  </button>
                </div>
              </Link>
            </div>
          )}
          {volun && (
            <div className="flex flex-col justify-center items-center">
              <div className="my-8">
                <div className="flex flex-col justify-center items-center">
                  <label htmlFor="amt" className="text-center text-2xl">
                    How many volunteers required
                  </label>
                  <input
                    type="text"
                    id="amt"
                    className="border p-2 my-4 rounded-lg"
                    value={input1}
                    onChange={(e) => {
                      setInput1(e.target.value);
                    }}
                  />
                  <label htmlFor="date" className="text-2xl">
                    Registration Deadline
                  </label>
                  <input
                    type="text"
                    id="date"
                    className="border p-2 my-4 rounded-lg"
                    value={DeadLine}
                    onChange={(e) => {
                      setDeadLine(e.target.value);
                    }}
                  />
                </div>
              </div>
              <Link to="/after-login">
                <div>
                  <button
                    className="hover:bg-blue-400 hover:text-white p-2 rounded-xl"
                    onClick={volunterreq}
                  >
                    Submit
                  </button>
                </div>
              </Link>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default AddEvent;
