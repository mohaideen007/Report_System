import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Login from './Login.jsx';
import Home from './Home.jsx';
import Report from './Report.jsx';
import ViewReport from './ViewReport.jsx';
const router=createBrowserRouter([
  {

    path:'/register',
    element:<App/>
  },
  {
    path:'/',
    element:<Login/>
  },{
    path:'/home',
    element:<Home/>
  },
  {
    path:'/submitreport',
    element:<Report/>
  },
  {
    path:'/viewreport',
    element:<ViewReport/>
  }
])

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <RouterProvider router={router}/>
  </StrictMode>,
)
