import { useState, Suspense } from "react";
import {
  Sphere,
  OrbitControls,
  Box,
  useTexture,
  Environment,
} from "@react-three/drei";
import { Canvas, useFrame } from "@react-three/fiber";
import { a as aw, useSpring as useSpringWeb } from "@react-spring/web";
import { a as a3, useSpring as useSpringThree } from "@react-spring/three";
import * as THREE from "three";
import styled from "styled-components";
import AlertModal from "@/components/alertModal";
import crystalBall from "@/assets/img/crystalBlue.png";

interface MagicMarbleMaterialProps
  extends THREE.MeshStandardMaterialParameters {
  step: number;
}

const Backdrop = styled.div<{ isOpen: boolean }>`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5); /* Semi-transparent black background */
  display: ${(props) => (props.isOpen ? "block" : "none")};
  z-index: 1;
`;

// Create a modal container
const ModalContainer = styled.div<{ isOpen: boolean }>`
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  padding: 20px;
  display: ${(props) => (props.isOpen ? "block" : "none")};
  z-index: 2;
`;

const data = {
  color: "#ff0000",
  colorName: "BLUE",
  description:
    "오늘의 행운의 색은 {color}입니다. 오늘은 ~~를 하면 좋은 일이 생길것 같습니다....",
};

interface ColorOptions {
  [key: string]: number[]; // 인덱스 시그니처 추가
}

const options: ColorOptions = {
  RED: [0, 100, 50], // 빨간색
  ORANGE: [30, 100, 50], // 주황색
  YELLOW: [60, 100, 50], // 노란색
  GREEN: [120, 100, 50], // 초록색
  BLUE: [240, 100, 50], // 파란색
  NAVY: [200, 70, 60], // 남색
  PURPLE: [270, 100, 50], // 보라색
  PINK: [330, 100, 50], // 분홍색
  BROWN: [30, 70, 25], // 갈색
  WHITE: [0, 0, 100], // 흰색
  GRAY: [0, 0, 40], // 회색
  OLIVE: [60, 100, 50], // 올리브색
  SKYBLUE: [200, 70, 60], // 하늘색
  MINT: [150, 100, 50], // 민트색
};

const CrystalBallPage = () => {
  const [step, setStep] = useState(0);
  const [isModalOpen, setIsModalOpen] = useState(false); // 모달 열림 여부 상태

  // 초기 h, s, l 값을 0, 0, 0으로 설정
  const { hsl } = useSpringWeb({
    hsl: step === 0 ? [210, 80, 50] : options[data.colorName],
    config: { tension: 50 },
  });

  const springyGradient = hsl.to(
    (h, s, l) =>
      `radial-gradient(hsl(${h}, ${s * 0.7}%, ${l}%), hsl(${h},${s * 0.4}%, ${
        l * 0.2
      }%))`
  );

  // 모달 열기 함수
  const openModal = () => {
    setIsModalOpen(true);
  };

  // 모달 닫기 함수
  const closeModal = () => {
    setIsModalOpen(false);
  };

  return (
    <aw.div
      style={{ background: springyGradient, width: "100%", height: "100%" }}
    >
      <Canvas camera={{ position: [0, 0, 2] }}>
        <Suspense fallback={null}>
          <OrbitControls
            autoRotate
            enableRotate={false}
            enablePan={false}
            enableZoom={false}
          />
          <Marble step={step} setStep={setStep} openModal={openModal} />
          <Environment preset="warehouse" />
        </Suspense>
      </Canvas>
      {/* 모달 렌더링 */}
      <Backdrop isOpen={isModalOpen} onClick={closeModal} />
      <ModalContainer isOpen={isModalOpen}>
        <AlertModal
          width="550px"
          topText={`Lucky ${data.colorName}`}
          middleText={`${data.description}`}
          bottomText="메인으로 돌아가기"
          imageUrl={crystalBall}
          topTextColor={`${data.color}`}
          middleTextColor="#5E3A66"
          bottomTextColor="#EAE2ED"
          upperSectionBackground="#EAE2ED"
          lowerSectionBackground="#5E3A66"
          topTextFontSize="28px"
          middleTextFontSize="14px"
          bottomTextFontSize="20px"
          topTextPaddingTopBottom="2px"
          middleTextPaddingTopBottom="20px"
          middleTextPaddingLeftRight="20px"
          topTextFontWeight="bold"
          middleTextFontWeight="medium"
          bottomTextFontWeight="medium"
          routerLink="/main"
        />
      </ModalContainer>
    </aw.div>
  );
};

interface MarbleProps {
  step: number;
  setStep: (step: number) => void;
  openModal: () => void; // openModal 함수가 어떤 인자도 받지 않음
}

const Marble: React.FC<MarbleProps> = ({ step, setStep, openModal }) => {
  const [hover, setHover] = useState(false);
  const [tap, setTap] = useState(false);
  const { scale } = useSpringThree({
    scale: tap && hover ? 0.95 : 1,
    config: {
      friction: 15,
      tension: 300,
    },
  });

  // 구체 클릭 이벤트 핸들러
  const handleSphereClick = () => {
    setStep(step + 1); // 스텝 증가
    setTimeout(() => {
      openModal(); // 모달 열기
    }, 1000); // 1000 밀리초(1초) 후에 함수 실행
  };

  return (
    <group>
      <a3.group
        scale={scale}
        onPointerEnter={() => setHover(true)}
        onPointerOut={() => setHover(false)}
        onClick={handleSphereClick}
      >
        <Sphere args={[1, 64, 32]}>
          <MagicMarbleMaterial step={step} roughness={0.1} />
        </Sphere>
      </a3.group>
      {/* This big invisible box is just a pointer target so we can reliably track if the mouse button is up or down */}
      <Box
        args={[100, 100, 100]}
        onPointerDown={() => setTap(true)}
        onPointerUp={() => setTap(false)}
      >
        {/* eslint-disable-next-line react/no-unknown-property */}
        <meshBasicMaterial side={THREE.BackSide} visible={false} />
      </Box>
    </group>
  );
};

const MagicMarbleMaterial: React.FC<MagicMarbleMaterialProps> = ({
  step,
  ...props
}) => {
  // Load the noise textures
  const heightMap = useTexture("noise.jpg");
  const displacementMap = useTexture("noise3D.jpg");
  heightMap.minFilter = displacementMap.minFilter = THREE.NearestFilter;
  displacementMap.wrapS = displacementMap.wrapT = THREE.RepeatWrapping;

  // Create persistent local uniforms object
  const [uniforms] = useState(() => ({
    time: { value: 0 },
    colorA: { value: new THREE.Color(0, 0, 0) },
    colorB: { value: new THREE.Color(0, 0, 0) },
    heightMap: { value: heightMap },
    displacementMap: { value: displacementMap },
    iterations: { value: 48 },
    depth: { value: 0.6 },
    smoothing: { value: 0.2 },
    displacement: { value: 0.1 },
  }));

  // This spring value allows us to "fast forward" the displacement in the marble
  const { timeOffset } = useSpringThree({
    hsl: options[data.colorName], // 스텝에 따라 옵션에서 색상 가져오기
    timeOffset: step * 0.2,
    config: { tension: 50 },
    onChange: ({ value: { hsl } }) => {
      const [h, s, l] = hsl;
      uniforms.colorB.value.setHSL(h / 360, s / 100, l / 100);
    },
  });

  // Update time uniform on each frame
  useFrame(({ clock }) => {
    uniforms.time.value = timeOffset.get() + clock.elapsedTime * 0.05;
  });

  // Add our custom bits to the MeshStandardMaterial
  const onBeforeCompile = (shader: THREE.Shader) => {
    // Wire up local uniform references
    shader.uniforms = { ...shader.uniforms, ...uniforms };

    // Add to top of vertex shader
    shader.vertexShader =
      /* glsl */ `
      varying vec3 v_pos;
      varying vec3 v_dir;
    ` + shader.vertexShader;

    // Assign values to varyings inside of main()
    shader.vertexShader = shader.vertexShader.replace(
      /void main\(\) {/,
      (match) =>
        match +
        /* glsl */ `
        v_dir = position - cameraPosition; // Points from camera to vertex
        v_pos = position;
        `
    );

    // Add to top of fragment shader
    shader.fragmentShader =
      /* glsl */ `
      #define FLIP vec2(1., -1.)
      
      uniform vec3 colorA;
      uniform vec3 colorB;
      uniform sampler2D heightMap;
      uniform sampler2D displacementMap;
      uniform int iterations;
      uniform float depth;
      uniform float smoothing;
      uniform float displacement;
      uniform float time;
      
      varying vec3 v_pos;
      varying vec3 v_dir;
    ` + shader.fragmentShader;

    // Add above fragment shader main() so we can access common.glsl.js
    shader.fragmentShader = shader.fragmentShader.replace(
      /void main\(\) {/,
      (match) =>
        /* glsl */ `
       	/**
         * @param p - Point to displace
         * @param strength - How much the map can displace the point
         * @returns Point with scrolling displacement applied
         */
        vec3 displacePoint(vec3 p, float strength) {
        	vec2 uv = equirectUv(normalize(p));
          vec2 scroll = vec2(time, 0.);
          vec3 displacementA = texture(displacementMap, uv + scroll).rgb; // Upright
					vec3 displacementB = texture(displacementMap, uv * FLIP - scroll).rgb; // Upside down
          
          // Center the range to [-0.5, 0.5], note the range of their sum is [-1, 1]
          displacementA -= 0.5;
          displacementB -= 0.5;
          
          return p + strength * (displacementA + displacementB);
        }
        
				/**
          * @param rayOrigin - Point on sphere
          * @param rayDir - Normalized ray direction
          * @returns Diffuse RGB color
          */
        vec3 marchMarble(vec3 rayOrigin, vec3 rayDir) {
          float perIteration = 1. / float(iterations);
          vec3 deltaRay = rayDir * perIteration * depth;

          // Start at point of intersection and accumulate volume
          vec3 p = rayOrigin;
          float totalVolume = 0.;

          for (int i=0; i<iterations; ++i) {
            // Read heightmap from spherical direction of displaced ray position
            vec3 displaced = displacePoint(p, displacement);
            vec2 uv = equirectUv(normalize(displaced));
            float heightMapVal = texture(heightMap, uv).r;

            // Take a slice of the heightmap
            float height = length(p); // 1 at surface, 0 at core, assuming radius = 1
            float cutoff = 1. - float(i) * perIteration;
            float slice = smoothstep(cutoff, cutoff + smoothing, heightMapVal);

            // Accumulate the volume and advance the ray forward one step
            totalVolume += slice * perIteration;
            p += deltaRay;
          }
          return mix(colorA, colorB, totalVolume);
        }
      ` + match
    );

    shader.fragmentShader = shader.fragmentShader.replace(
      /vec4 diffuseColor.*;/,
      /* glsl */ `
      vec3 rayDir = normalize(v_dir);
      vec3 rayOrigin = v_pos;
      
      vec3 rgb = marchMarble(rayOrigin, rayDir);
      vec4 diffuseColor = vec4(rgb, 1.);      
      `
    );
  };

  return (
    <meshStandardMaterial
      {...props}
      // eslint-disable-next-line react/no-unknown-property
      onBeforeCompile={onBeforeCompile}
      // eslint-disable-next-line react/no-unknown-property
      onUpdate={(m) => (m.needsUpdate = true)}
      // eslint-disable-next-line react/no-unknown-property
      customProgramCacheKey={() => onBeforeCompile.toString()}
    />
  );
};

export default CrystalBallPage;
