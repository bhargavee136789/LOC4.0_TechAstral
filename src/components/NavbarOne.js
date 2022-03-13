import React from "react";
import { Link } from "react-router-dom";
const NavbarOne = () => {
  return (
    <>
      <div>
        <header className="text-black font-body">
          <div className="container mx-auto flex flex-wrap p-5 flex-col md:flex-row items-center">
            <a
              className="flex title-font font-medium items-center mb-4 md:mb-0"
              href="/"
            >
              <span className="ml-3 text-xl"></span>
            </a>
            <nav className="md:mr-auto md:ml-4 md:py-1 md:pl-4 md:border-l md:border-gray-400	flex flex-wrap items-center text-xl justify-center">
              {/* <a className="ml-12 mr-12 hover:text-rose-400" href="/">
                Home
              </a>
              <a className="mr-12 hover:text-rose-400" href="/">
                About
              </a>
              <a className="mr-12 hover:text-rose-400" href="/">
                Contacts
              </a>
              <a className="mr-12 hover:text-rose-400" href="/">
                Pricing
              </a> */}
            </nav>
            {/* <Link to="/login">
              <button className="text-xl inline-flex items-center border-0 py-1 px-4 focus:outline-none hover:bg-blue-600 hover:text-white rounded  mt-4 md:mt-0">
                Login
              </button>
            </Link>
            <Link to="/signup">
              <button className="inline-flex items-center border-0 py-1 px-4 focus:outline-none hover:bg-blue-600 hover:text-white rounded text-xl mt-4 md:mt-0">
                Register
              </button>
            </Link> */}
            <Link to="/upload">
              <button className="inline-flex items-center border-0 py-1 px-4 focus:outline-none hover:bg-blue-600 hover:text-white rounded text-xl mt-4 md:mt-0">
                Upload
              </button>
            </Link>
            <Link to="/add">
              <button className="inline-flex items-center border-0 py-1 px-4 focus:outline-none hover:bg-blue-600 hover:text-white rounded text-xl mt-4 md:mt-0">
                Add
              </button>
            </Link>
            <Link to="/volun">
              <button className="inline-flex items-center border-0 py-1 px-4 focus:outline-none hover:bg-blue-600 hover:text-white rounded text-xl mt-4 md:mt-0">
                Volunteer
              </button>
            </Link>
          </div>
        </header>
      </div>
    </>
  );
};

export default NavbarOne;
