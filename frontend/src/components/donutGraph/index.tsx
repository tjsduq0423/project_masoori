import React, { useState, useEffect } from "react";
import styled from "styled-components";

interface DonutChartProps {
  value: number;
  valuelabel: string;
  size: number;
  strokewidth: number;
}

const DonutChartContainer = styled.svg<{ size: number }>`
  width: ${(props) => props.size}px;
  height: ${(props) => props.size}px;
  &.donutchart {
    margin: 0 auto;
    border-radius: 50%;
    display: block;
  }
`;

const DonutTrack = styled.circle<{ strokewidth: number }>`
  fill: transparent;
  stroke: #dae2e5;
  stroke-width: ${(props) => props.strokewidth};
`;

const DonutIndicator = styled.circle<{ strokewidth: number; dashval: string }>`
  fill: transparent;
  stroke: #7c5874;
  stroke-width: ${(props) => props.strokewidth};
  stroke-dasharray: ${(props) => props.dashval};
  transition: stroke-dasharray 0.3s ease;
`;

const DonutText = styled.text`
  fill: #fff;
`;

const DonutTextVal = styled.tspan`
  font-size: 22px;
`;

const DonutTextPercent = styled.tspan`
  font-size: 16px;
`;

const DonutTextLabel = styled.tspan`
  margin-top: 10px;
  font-size: 12px;
`;

function DonutChart({ value, valuelabel, size, strokewidth }: DonutChartProps) {
  const [dashval, setDashval] = useState<string>("0 0");

  useEffect(() => {
    const halfsize = size * 0.5;
    const radius = halfsize - strokewidth * 0.5;
    const circumference = 2 * Math.PI * radius;
    const strokeval = (value * circumference) / 100;
    const dashval = `${strokeval} ${circumference}`;
    setDashval(dashval);
  }, [value, size, strokewidth]);

  const halfsize = size * 0.5;
  const rotateval = `rotate(-90 ${halfsize},${halfsize})`;

  return (
    <DonutChartContainer
      width={size}
      height={size}
      className="donutchart"
      size={size}
    >
      <DonutTrack
        r={halfsize - strokewidth * 0.5}
        cx={halfsize}
        cy={halfsize}
        transform={rotateval}
        strokewidth={strokewidth}
      />
      <DonutIndicator
        r={halfsize - strokewidth * 0.5}
        cx={halfsize}
        cy={halfsize}
        transform={rotateval}
        strokewidth={strokewidth}
        dashval={dashval}
      />
      <DonutText x={halfsize} y={halfsize} style={{ textAnchor: "middle" }}>
        <DonutTextVal>{value}</DonutTextVal>
        <DonutTextPercent>만원</DonutTextPercent>
        <DonutTextLabel x={halfsize} y={halfsize + 17} fill="#DACBD7">
          {valuelabel}
        </DonutTextLabel>
      </DonutText>
    </DonutChartContainer>
  );
}

export default DonutChart;
