import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import "./Volun.css";
const Volun = () => {
  const [event, setEvent] = useState("");
  const [date, setDate] = useState("");
  const [loc, setLoc] = useState("");
  const [req, setReq] = useState("");
  const [got, setGot] = useState("");

  useEffect(() => {
    setEvent("Help Old");
    setDate("2022-03-13");
    setLoc("Mumbai-400057");
    setReq("1000");
    setGot("50");
  }, []);
  return (
    <div className="image">
      <div className=" w-full flex flex-col justify-center items-center font-body  ">
        <div className="pb-5">
          <div className="mt-12 ">
            <h2 className="font-bold text-3xl">Crowd funding details</h2>
          </div>
        </div>
        <div className="border shadow-lg bg-white">
          <div className="h-30 flex flex-col justify-center items-center">
            <div className="grid grid-cols-3 px-10 mt-10 space-x-5">
              <div className="my-2">Event: {event}</div>
              <div className="my-2">Date: {date}</div>
              <div className="my-2">Location: {loc}</div>
            </div>
            <div className="flex flex-row justify-between mx-8">
              <div className="mr-4">
                Number of volunteers required: <span>{req}</span>
              </div>
              <div className="...">
                Number of volunteers that came: <span>{got}</span>
              </div>
            </div>
          </div>
          <div className="flex justify-evenly space-x-4 items-center">
            <button className="text-center my-8 p-2 rounded-lg hover:bg-blue-600 hover:text-white">
              Stop Registrations
            </button>
            <Link to="/after-login">
              <button className="text-center my-8 p-2 rounded-lg hover:bg-blue-600 hover:text-white">
                Home
              </button>
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Volun;
