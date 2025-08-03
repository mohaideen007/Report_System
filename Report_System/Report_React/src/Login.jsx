import axios from "axios";
import { useState } from "react";
import { Link,useNavigate } from "react-router-dom";

function Login(){
      const [username, setUsername] = useState("");
      const[password,setPassword]=useState("");
      const navigate= useNavigate();

      
   function submit(e) {
  e.preventDefault();
  

  if (username.trim() !== "" && password.trim() !== "") {
    axios.post("http://localhost:8080/users/login", {
      username: username,
      password: password
    })
    .then(response => {
       const token = response.data.token || response.data;
      if (token) {
        localStorage.setItem("token", token);
        console.log("Token stored:", token);
        alert("Login successful!");
        navigate("/home");

      } else {
        alert("Login failed: No token received");
      }
    })
    .catch((error) => {
      console.error("Login failed:", error);
      alert("Login failed. Please check your credentials.");
    });
  } else {
    alert("Please fill in all fields.");
  }
}

    return(
        <>
       <div className="min-h-screen flex flex-col items-center justify-center bg-gradient-to-br from-sky-100 to-white px-4">
  
  <div className="bg-sky-400 rounded-xl shadow-md px-8 py-6 text-center w-full max-w-2xl mb-10">
    <h2 className="text-2xl font-bold text-white tracking-wide">
      WELCOME TO THE LOGIN PAGE
    </h2>
  </div>

 
  <div className="bg-white border-2 border-sky-500 rounded-2xl shadow-xl w-full max-w-md p-8">
    <form onSubmit={submit}>
      <div className="text-2xl font-bold text-center text-sky-600 mb-8">
        Login Form
      </div>

      
      <div className="mb-5">
        <input
          type="text"
          placeholder="Enter Username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          className="w-full px-4 py-3 border-2 border-sky-400 rounded-lg focus:outline-none focus:ring-2 focus:ring-sky-300 transition"
        />
      </div>

      
      <div className="mb-5">
        <input
          type="password"
          placeholder="Enter Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          className="w-full px-4 py-3 border-2 border-sky-400 rounded-lg focus:outline-none focus:ring-2 focus:ring-sky-300 transition"
        />
      </div>
      <div className="flex justify-center gap-4 mt-6">
      <button
          type="submit"
          className="bg-sky-500 text-white font-semibold px-6 py-2 rounded-full hover:bg-sky-600 transition shadow-md"
        >
          Login
        </button>

        <Link to="/register">
          <button
            type="button"
            className="bg-gray-200 text-gray-700 font-semibold px-6 py-2 rounded-full hover:bg-gray-300 transition shadow-md"
          >
            Register
          </button>
        </Link>
      </div>
    </form>
  </div>
</div>

        </>
    )
}

export default Login;