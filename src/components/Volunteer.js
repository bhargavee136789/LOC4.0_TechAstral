import React, { useState } from "react";
import { Link } from "react-router-dom";

const Volunteer = () => {
  const [input, setInput] = useState("");
  const [date, setDate] = useState("");
  return (
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
            value={input}
            onChange={(e) => {
              setInput(e.target.value);
            }}
          />
          <label htmlFor="amt" className="text-2xl">
            Registration Deadline
          </label>
          <input
            type="text"
            id="amt"
            className="border p-2 my-4 rounded-lg"
            value={date}
            onChange={(e) => {
              setDate(e.target.value);
            }}
          />
        </div>
      </div>
      <Link to="/after-login">
        <div>
          <button className="hover:bg-blue-400 hover:text-white p-2 rounded-xl">
            Submit
          </button>
        </div>
      </Link>
    </div>
  );
};

export default Volunteer;
