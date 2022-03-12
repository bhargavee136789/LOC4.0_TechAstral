import React from "react";

const UploadDetails = () => {
  return (
    <div className="flex flex-col justify-center">
      <div className="max-w-[400px] w-full mx-auto bg-white p-4">
        <h2 className="text-center text-3xl font-mono py-6">Upload Details</h2>
        <div className="">
          <div className="flex flex-col py-2">
            <label
              htmlFor="ngoname"
              className="text-center text-2xl text-cyan-400"
            >
              NGO Name
            </label>
            <input
              type="text"
              className="border p-2 my-4 rounded-lg"
              id="ngoname"
              required
            />
          </div>{" "}
          <div className="flex flex-col py-2">
            <label
              htmlFor="owner"
              className="text-center text-2xl text-cyan-400"
            >
              Owner
            </label>
            <input
              type="text"
              className="border p-2 my-4 rounded-lg"
              id="owner"
              required
            />
          </div>{" "}
          <div className="flex flex-col py-2">
            <label
              htmlFor="location"
              className="text-center text-2xl text-cyan-400"
            >
              Location
            </label>
            <input
              type="text"
              className="border p-2 my-4 rounded-lg"
              id="location"
              required
            />
          </div>
          <div className="flex flex-col py-2">
            <label
              htmlFor="motto"
              className="text-center text-2xl text-cyan-400"
            >
              Motto
            </label>
            <textarea
              name=""
              id="motto"
              cols="2"
              rows="2"
              className="border p-2 my-4 rounded-lg"
            />
          </div>
          <div className="flex flex-col py-2">
            <label
              htmlFor="desc"
              className="text-center text-2xl text-cyan-400"
            >
              Description
            </label>
            <textarea
              name=""
              id="desc"
              cols="2"
              rows="2"
              className="border p-2 my-4 rounded-lg"
            />
          </div>
          <div className="flex flex-col py-2">
            <label
              htmlFor="achiev"
              className="text-center text-2xl text-cyan-400"
            >
              Achievements
            </label>
            <textarea
              name=""
              id="achive"
              cols="2"
              rows="2"
              className="border p-2 my-4 rounded-lg"
            />
          </div>
        </div>
      </div>
    </div>
  );
};

export default UploadDetails;
