import "./App.css";
import { BrowserRouter as Router } from "react-router-dom";
import RenderRoutes from "@/router/routes";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
// import bgm from "@/assets/bgm/BGM.mp3";

function App() {
  return (
    <div className="App h-screen">
      <Router>
        <RenderRoutes />
      </Router>
      <ToastContainer
        position="top-center"
        autoClose={2000}
        limit={5}
        hideProgressBar
        newestOnTop
        closeOnClick
        rtl={false}
        pauseOnFocusLoss={false}
        draggable={false}
        pauseOnHover
        theme="dark"
      />
      {/* <audio src={bgm} autoPlay></audio> */}
    </div>
  );
}

export default App;
