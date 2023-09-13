import React from "react";
import EasyPieChart from "react-easy-pie-chart";

const CircleGraph = ({ data }) => {
  const options = {
    scaleColor: false,
    lineWidth: 20,
    lineCap: "butt",
    barColor: "#a378aa",
    trackColor: "#e7b8ef",
    size: 150,
    animate: 800,
  };

  return (
    <div>
      <EasyPieChart data={data} options={options} />
    </div>
  );
};

export default CircleGraph;
