import "./App.css";
import { BrowserRouter as Router } from "react-router-dom";
import RenderRoutes from "@/router/routes";

function App() {
  return (
    <div className="App h-screen">
      <Router>
        <RenderRoutes />
      </Router>
    </div>
  );
}

export default App;
