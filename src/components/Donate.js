import React, { useState } from "react";
import { Link } from "react-router-dom";

const Donate = () => {
  const [input, setInput] = useState("");
  const [date, setDate] = useState("");
  return (
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
          value={input}
          onChange={(e) => {
            setDate(e.target.value);
          }}
        />
      </div>
      <Link to="/after-login">
        <div>
          <button className="hover:bg-blue-400 hover:text-whitepx-4 p-2 rounded-xl">
            Submit
          </button>
        </div>
      </Link>
    </div>
  );
};

export default Donate;
