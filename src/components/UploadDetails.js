import axios from "axios";
import React, { useState } from "react";
import { Link } from "react-router-dom";
import "./UploadDetails.css";
const UploadDetails = () => {
  const [name, setName] = useState("");
  const [owner, setOwner] = useState("");
  const [loc, setLoc] = useState("");
  const [motto, setMotto] = useState("");
  const [desc, setDesc] = useState("");
  const [achieve, setAchieve] = useState("");
  /* const integrate = () => {
    let data = new FormData();
    data.append("name", name);
    data.append("owner", owner);
    data.append("loc", loc);
    data.append("motto", motto);
    data.append("desc", desc);
    data.append("achieve", achieve);

    let config = {
      method: "POST",
      url: "http://shrutiprasad.pythonanywhere.com/auth/NGODetails",
      headers: { accept: "application/json" },
      data: data,
    };

    axios(config)
      .then((response) => {
        console.log(JSON.stringify(response.data));
      })
      .catch((error) => {
        console.log(error + "error");
      });
  }; */
  const integrate = () => {
    var axios = require("axios");
    var FormData = require("form-data");
    var data = new FormData();
    data.append("username", name);
    data.append("owner", owner);
    data.append("location", loc);
    data.append("moto", motto);
    data.append("desc", desc);
    data.append("achievement", achieve);

    var config = {
      method: "get",
      url: "http://shrutiprasad.pythonanywhere.com/auth/NGODetails",
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
    <div className="images">
      <div className="flex flex-col justify-center items-center font-body px-56 rounded-2xl">
        <div className="w-full  bg-white mt-20 py-4 shadow-lg rounded-lg">
          <h2 className="text-center text-3xl py-6">Upload Details</h2>
          <div className="grid grid-cols-3">
            <div className="flex flex-col items-center justify-center py-2">
              <label htmlFor="ngoname" className="text-center text-2xl ">
                NGO Name
              </label>
              <input
                value={name}
                type="text"
                className="border p-2 my-4 rounded-lg"
                id="ngoname"
                required
                onChange={(e) => setName(e.target.value)}
              />
            </div>{" "}
            <div className="flex flex-col items-center justify-center py-2">
              <label htmlFor="owner" className="text-center text-2xl ">
                Owner
              </label>
              <input
                value={owner}
                type="text"
                className="border p-2 my-4 rounded-lg"
                id="owner"
                required
                onChange={(e) => setOwner(e.target.value)}
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
              <label htmlFor="motto" className="text-center text-2xl ">
                Motto
              </label>
              <input
                type="text"
                value={motto}
                id="motto"
                className="border p-2 my-4 rounded-lg"
                onChange={(e) => setMotto(e.target.value)}
              />
            </div>
            <div className="flex flex-col items-center justify-center py-2">
              <label htmlFor="desc" className="text-center text-2xl ">
                Description
              </label>
              <textarea
                value={desc}
                name=""
                id="desc"
                cols="10"
                rows="2"
                className="border p-1 my-4 rounded-lg"
                onChange={(e) => setDesc(e.target.value)}
              />
            </div>
            <div className="flex flex-col items-center justify-center py-2">
              <label htmlFor="achiev" className="text-center text-2xl ">
                Achievements
              </label>
              <textarea
                value={achieve}
                name=""
                id="achive"
                cols="10"
                rows="2"
                className="border p-1 my-4 rounded-lg"
                onChange={(e) => setAchieve(e.target.value)}
              />
            </div>
          </div>
          <div className=" flex justify-center items-center p-10 m-5">
            <Link to="/add">
              <button
                className=" text-2xl rounded-lg mr-24 hover:bg-blue-600 p-2 hover:text-white"
                onClick={integrate}
              >
                Submit
              </button>
            </Link>

            <Link to="/after-login">
              <button className=" text-2xl rounded-lg hover:bg-blue-600 p-2 hover:text-white">
                Home
              </button>
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
};

export default UploadDetails;
