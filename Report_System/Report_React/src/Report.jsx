import axios from "axios";
import React, { useState } from "react";
import { name } from "./Utils/Auth";

function ViewReport() {
  const [description, setDescription] = useState("");
  const [evidence, setEvidence] = useState(null);
  //const [successMsg, setSuccessMsg] = useState("");

  const username=name();

  const handleSubmit = async (e) => {
    
  const form=new FormData();
  form.append("file",evidence);
  form.append("description",description);
  form.append("username",username);
     e.preventDefault();

    if(description&&evidence){
    axios.post("http://localhost:8081/reports/addreport",form,
        {
            headers:{
                Authorization:`Bearer ${localStorage.getItem("token")}`
            }
        }
     )
     .then(r=>
        {
            alert("Reported");
        }
     )
     .catch((e)=>{
        alert("Report Submission Unsuccessful");
     })
    }
    else{
        alert("Fill the fields");
    }
     
  
  }

  return (
<>
     <div className="max-w-xl mx-auto p-8 mt-10 bg-white dark:bg-gray-900 shadow-lg rounded-xl border border-gray-200 dark:border-gray-700">
  <h2 className="text-3xl font-semibold text-gray-800 dark:text-white mb-6 text-center">Submit Report</h2>
  <form onSubmit={handleSubmit} className="space-y-6">

    {/* File Input */}
    <div className="relative">
      <input
        type="file"
        id="evidence"
        className="peer w-full pt-6 pb-2 px-4 text-sm text-gray-900 dark:text-white bg-transparent border border-gray-300 dark:border-gray-600 rounded-lg appearance-none focus:outline-none focus:ring-2 focus:ring-blue-500"
        onChange={(e) => setEvidence(e.target.files[0])}
      />
      <label
        htmlFor="evidence"
        className="absolute left-4 top-2 text-sm text-gray-500 dark:text-gray-400 transition-all peer-placeholder-shown:top-4 peer-placeholder-shown:text-base peer-placeholder-shown:text-gray-400 dark:peer-placeholder-shown:text-gray-500 peer-focus:top-2 peer-focus:text-sm peer-focus:text-blue-600 dark:peer-focus:text-blue-400"
      >
        Upload Evidence
      </label>
    </div>

    {/* Description Textarea */}
    <div className="relative">
      <textarea
        id="description"
        rows="4"
        placeholder=" "
        value={description}
        onChange={(e) => setDescription(e.target.value)}
        className="peer w-full pt-6 pb-2 px-4 text-sm text-gray-900 dark:text-white bg-transparent border border-gray-300 dark:border-gray-600 rounded-lg resize-none focus:outline-none focus:ring-2 focus:ring-blue-500"
        required
      ></textarea>
      <label
        htmlFor="description"
        className="absolute left-4 top-2 text-sm text-gray-500 dark:text-gray-400 transition-all peer-placeholder-shown:top-4 peer-placeholder-shown:text-base peer-placeholder-shown:text-gray-400 dark:peer-placeholder-shown:text-gray-500 peer-focus:top-2 peer-focus:text-sm peer-focus:text-blue-600 dark:peer-focus:text-blue-400"
      >
        Description
      </label>
    </div>

    {/* Submit Button */}
    <button
      type="submit"
      className="w-full bg-blue-600 text-white py-3 rounded-lg hover:bg-blue-700 transition duration-200 shadow-md"
    >
      Submit Report
    </button>
  </form>
</div>
</>
  );
};

export default ViewReport;
