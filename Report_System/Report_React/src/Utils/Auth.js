import { jwtDecode } from "jwt-decode";

export function Auth(){
    
    const decoded=jwtDecode(localStorage.getItem("token"));
    const role=decoded.role;
    return role;
}


export function name(){
    const decoded=jwtDecode(localStorage.getItem("token"));
    const name=decoded.name||decoded.username||decoded.sub;
    return name;
}
