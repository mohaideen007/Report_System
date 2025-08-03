import Report from "./Report";
import { Auth } from "./Utils/Auth";
import ViewReport from "./ViewReport";

function Home(){
    const role=Auth();
    return (
    <>
      {role === "USER" ? <Report /> : <ViewReport />}
    </>
  );
}

export default Home;