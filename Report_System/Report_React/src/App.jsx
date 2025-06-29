import { useState } from 'react'
import './App.css'
import axios from 'axios';
import { Link,Navigate, useNavigate } from 'react-router-dom';
function App() {
  const [username, setUsername] = useState("");
  const[password,setPassword]=useState("");
  const[file,setFile]=useState(null);
  const[email,setEmail]=useState("");
const nav=useNavigate();
  function submit(e){
    e.preventDefault();
    
    const form=new FormData();
    form.append("file",file);
    form.append("username",username);
    form.append("password",password);

  if (username.trim() !== "" && password.trim() !== "") {
    axios.post("http://localhost:8080/users/adduser",form,
  )
    .then((response) => {
     
      alert("Registration was successful");
      axios.post(`http://localhost:8080/users/sendmail/${email}`)
      nav("/");
    
    })
    .catch((error) => {
      console.error("Login failed:", error);
      alert("Login failed. Please check your credentials.");
    });
  }
     else {
    alert("Please fill in all fields.");
  }
}
  

  return (
    <>
   <div className="min-h-screen flex flex-col items-center justify-center bg-gradient-to-br from-sky-100 to-white px-4">
  {/* Welcome Header */}
  <div className="bg-sky-400 rounded-xl shadow-md px-8 py-6 text-center w-full max-w-2xl mb-10">
    <h2 className="text-2xl font-bold text-white tracking-wide">
      WELCOME TO THE REGISTRATION PAGE
    </h2>
  </div>

  {/* Registration Form */}
  <div className="bg-white border-2 border-sky-500 rounded-2xl shadow-xl w-full max-w-md p-8">
    <form onSubmit={submit}>
      <div className="text-2xl font-bold text-center text-sky-600 mb-8">
        Register Form
      </div>

      {/* Username */}
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
          type="email"
          placeholder="Enter Email-ID"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          className="w-full px-4 py-3 border-2 border-sky-400 rounded-lg focus:outline-none focus:ring-2 focus:ring-sky-300 transition"
        />
      </div>

      {/* Password */}
      <div className="mb-5">
        <input
          type="password"
          placeholder="Enter Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          className="w-full px-4 py-3 border-2 border-sky-400 rounded-lg focus:outline-none focus:ring-2 focus:ring-sky-300 transition"
        />
      </div>

      {/* File Upload */}
      <div className="mb-5">
        <input
          type="file"
          onChange={(e) => setFile(e.target.files[0])}
          className="w-full px-4 py-2 border-2 border-sky-400 rounded-lg bg-sky-50 cursor-pointer text-gray-700 file:mr-4 file:py-2 file:px-4 file:rounded-full file:border-0 file:text-sm file:font-semibold file:bg-sky-500 file:text-white hover:file:bg-sky-600"
        />
      </div>

      {/* Submit Button */}
      <div className="flex justify-center gap-4 mt-6">
        <button
          type="submit"
          className="bg-sky-500 text-white font-semibold px-6 py-2 rounded-full hover:bg-sky-600 transition shadow-md"
        >
          Register
        </button>

        <Link to="/">
          <button
            type="button"
            className="bg-gray-200 text-gray-700 font-semibold px-6 py-2 rounded-full hover:bg-gray-300 transition shadow-md"
          >
            Login
          </button>
        </Link>
      </div>
    </form>
  </div>
</div>

    </>

  )

}
export default App;
