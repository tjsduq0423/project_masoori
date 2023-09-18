import React, { useEffect, useRef } from "react";
import * as THREE from "three";
import Flickity from "flickity";
import "flickity/css/flickity.css"; // Import Flickity CSS
import "./style.css";

import masuriImg from "../../assets/img/masuri.png";
import book from "../../assets/img/book.png";
import cardhand from "../../assets/img/handcard.png";
import money from "../../assets/img/money.png";

const MainPage = () => {
  const canvasRef = useRef(null); // Ref to store the canvas element

  useEffect(() => {
    let body,
      mainContainer,
      scene,
      renderer,
      camera,
      windowWidth,
      windowHeight,
      windowHalfWidth,
      windowHalfHeight,
      mouseX = 0,
      mouseY = 0,
      graphics,
      currentGraphic = 0,
      graphicCanvas,
      gctx,
      graphicPixels;

    const cameraLookAt = new THREE.Vector3(0, 0, 0),
      cameraTarget = new THREE.Vector3(0, 0, 800),
      colors = ["#F9DFAD", "#FFC56E", "#F96893", "#FFFFFF", "#FFC56E"],
      canvasWidth = 260,
      canvasHeight = 260,
      particles = [],
      graphicOffsetX = canvasWidth / 2,
      graphicOffsetY = canvasHeight / 4;

    const initStage = () => {
      body = document.querySelector(".body");
      mainContainer = document.querySelector("#main");
      setWindowSize();
      window.addEventListener("resize", onWindowResize, false);
      window.addEventListener("mousemove", onMouseMove, false);
    };

    const initScene = () => {
      scene = new THREE.Scene();
      renderer = new THREE.WebGLRenderer({
        alpha: true,
        antialias: true,
        canvas: canvasRef.current, // Use the canvas element from the ref
      });
      renderer.setPixelRatio(window.devicePixelRatio);
      renderer.setSize(windowWidth, windowHeight);
      mainContainer.appendChild(renderer.domElement);
      scene.background = new THREE.Color(0x4d3548);
    };

    const initCamera = () => {
      const fieldOfView = 75;
      const aspectRatio = windowWidth / windowHeight;
      const nearPlane = 1;
      const farPlane = 3000;
      camera = new THREE.PerspectiveCamera(
        fieldOfView,
        aspectRatio,
        nearPlane,
        farPlane
      );
      camera.position.z = 800;
    };

    const initCanvas = () => {
      graphicCanvas = document.createElement("canvas");
      graphicCanvas.width = canvasWidth;
      graphicCanvas.height = canvasHeight;
      gctx = graphicCanvas.getContext("2d");
      graphics = document.querySelectorAll(".intro-cell > img");
    };

    function Particle(graphicPixels) {
      this.graphicPixels = graphicPixels; // Store graphicPixels array
      this.vx = Math.random() * 0.05;
      this.vy = Math.random() * 0.05;
    }

    Particle.prototype.init = function (i) {
      const particle = new THREE.Object3D();
      const geometryCore = new THREE.SphereGeometry(2, 4, 4);
      const materialCore = new THREE.MeshBasicMaterial({
        color: colors[i % colors.length],
      });

      const box = new THREE.Mesh(geometryCore, materialCore);

      const pos = getGraphicPos(graphicPixels[i]);
      particle.targetPosition = new THREE.Vector3(pos.x, pos.y, pos.z);

      particle.position.set(
        windowWidth * 0.5,
        windowHeight * 0.5,
        -10 * Math.random() + 20
      );
      randomPos(particle.position);

      // Modifying vertices for BufferGeometry
      const positions = box.geometry.getAttribute("position").array;
      for (let i = 0; i < positions.length; i += 3) {
        positions[i] += -2 + Math.random() * 4; // x
        positions[i + 1] += -2 + Math.random() * 4; // y
        positions[i + 2] += -2 + Math.random() * 4; // z
      }
      box.geometry.getAttribute("position").needsUpdate = true;

      particle.add(box);
      this.particle = particle;
    };

    Particle.prototype.updateRotation = function () {
      this.particle.rotation.x += this.vx;
      this.particle.rotation.y += this.vy;
    };

    Particle.prototype.updatePosition = function () {
      this.particle.position.lerp(this.particle.targetPosition, 0.1);
    };

    const updateParticles = () => {
      for (let i = 0, l = particles.length; i < l; i++) {
        particles[i].updateRotation();
        particles[i].updatePosition();
      }
    };

    const getGraphicPos = (pixel) => {
      const posX = (pixel.x - graphicOffsetX - Math.random() * 4 - 2) * 3;
      const posY = (pixel.y - graphicOffsetY - Math.random() * 4 - 2) * 3;
      const posZ = -20 * Math.random() + 40;
      return { x: posX, y: posY, z: posZ };
    };

    const setParticles = () => {
      for (let i = 0; i < graphicPixels.length; i++) {
        if (particles[i]) {
          const pos = getGraphicPos(graphicPixels[i]);
          particles[i].particle.targetPosition.x = pos.x;
          particles[i].particle.targetPosition.y = pos.y;
          particles[i].particle.targetPosition.z = pos.z;
        } else {
          const p = new Particle();
          p.init(i);
          scene.add(p.particle);
          particles[i] = p;
        }
      }

      for (let i = graphicPixels.length; i < particles.length; i++) {
        randomPos(particles[i].particle.targetPosition, true);
      }

      console.log("Total Particles: " + particles.length);
    };

    const randomPos = (vector, outFrame = false) => {
      const radius = outFrame ? windowWidth * 2 : windowWidth * -2;
      const centerX = 0;
      const centerY = 0;
      const r = windowWidth + radius * Math.random();
      const angle = Math.random() * Math.PI * 2;
      vector.x = centerX + r * Math.cos(angle);
      vector.y = centerY + r * Math.sin(angle);
      vector.z = Math.random() * windowWidth;
    };

    const updateGraphic = () => {
      const img = graphics[currentGraphic];
      gctx.drawImage(img, 0, 0, canvasWidth, canvasHeight);

      const gData = gctx.getImageData(0, 0, canvasWidth, canvasHeight).data;
      graphicPixels = [];

      for (let i = gData.length; i >= 0; i -= 4) {
        if (gData[i] === 0) {
          const x = (i / 4) % canvasWidth;
          const y = canvasHeight - Math.floor(Math.floor(i / canvasWidth) / 4);

          if (x && x % 2 === 0 && y && y % 2 === 0) {
            graphicPixels.push({
              x: x,
              y: y,
            });
          }
        }
      }

      for (let i = 0; i < particles.length; i++) {
        randomPos(particles[i].particle.targetPosition);
      }

      setTimeout(() => {
        setParticles();
      }, 500);
    };

    const initBgObjects = () => {
      for (let i = 0; i < 40; i++) {
        createBgObject(i);
      }
    };

    const createBgObject = (i) => {
      const geometry = new THREE.SphereGeometry(10, 6, 6);
      const material = new THREE.MeshBasicMaterial({ color: 0xffffff });
      const sphere = new THREE.Mesh(geometry, material);
      scene.add(sphere);
      const x = Math.random() * windowWidth * 2 - windowWidth;
      const y = Math.random() * windowHeight * 2 - windowHeight;
      const z = Math.random() * -2000 - 200;
      sphere.position.set(x, y, z);
    };

    const initSlider = () => {
      const elem = document.querySelector(".intro-carousel");

      const flkty = new Flickity(elem, {
        cellAlign: "center",
        contain: true,
        pageDots: false,
        wrapAround: true,
        resize: true,
        arrowShape: {
          x0: 10,
          x1: 60,
          y1: 50,
          x2: 70,
          y2: 40,
          x3: 30,
        },
      });

      const listener = () => {
        currentGraphic = flkty.selectedIndex;
        updateGraphic();
        console.log(flkty.selectedIndex);
      };

      flkty.on("select", listener);
    };

    const onMouseMove = (event) => {
      mouseX = event.clientX - windowHalfWidth;
      mouseY = event.clientY - windowHalfHeight;
      cameraTarget.x = Number(-mouseX) / 2;
      cameraTarget.y = mouseY / 2;
    };

    const onWindowResize = () => {
      setWindowSize();
      camera.aspect = windowWidth / windowHeight;
      camera.updateProjectionMatrix();
      renderer.setSize(windowWidth, windowHeight);
    };

    const setWindowSize = () => {
      windowWidth = window.innerWidth;
      windowHeight = window.innerHeight;
      windowHalfWidth = windowWidth / 3;
      windowHalfHeight = windowHeight / 2;
    };

    const animate = () => {
      requestAnimationFrame(animate);
      updateParticles();
      camera.position.lerp(cameraTarget, 0.2);
      camera.lookAt(cameraLookAt);
      render();
    };

    const render = () => {
      renderer.render(scene, camera);
    };

    initStage();
    initScene();
    initCanvas();
    initCamera();
    initSlider();
    initBgObjects();
    updateGraphic();
    animate();

    const areAllImagesLoaded = () => {
      const images = document.querySelectorAll(".intro-cell > img");
      for (const img of images) {
        if (!img.complete) {
          return false;
        }
      }
      return true;
    };

    const initAll = () => {
      initStage();
      initScene();
      initCanvas();
      initCamera();
      initSlider();
      initBgObjects();
      updateGraphic();
      animate();

      window.addEventListener("resize", onWindowResize, false);
      window.addEventListener("mousemove", onMouseMove, false);
    };

    if (areAllImagesLoaded()) {
      initAll();
    } else {
      const images = document.querySelectorAll(".intro-cell > img");
      let loadedImagesCount = 0;

      images.forEach((img) => {
        img.addEventListener("load", () => {
          loadedImagesCount++;
          if (loadedImagesCount === images.length) {
            initAll();
          }
        });
      });
    }

    // Cleanup function
    return () => {
      window.removeEventListener("resize", onWindowResize);
      window.removeEventListener("mousemove", onMouseMove);
    };
  }, []);

  return (
    <div className="body">
      <main role="main" id="main">
        <div className="feature">
          <canvas ref={canvasRef} />
          <div className="intro-carousel">
            <div className="intro-cell">
              <img src={masuriImg} className="intro-graphic" />
              <div className="intro-text">
                <h2>첫 번째 페이지</h2>
                <h1>INTRO</h1>
                <p>
                  당신의 소비내역으로 만드는 마수리만의 특별한 타로카드.
                  <br />
                  마수리와 함께 새로운 시작, 새로운 습관을 만들어보세요.
                </p>
                <a>ENTER</a>
              </div>
            </div>
            <div className="intro-cell">
              <img src={cardhand} className="intro-graphic" />
              <div className="intro-text">
                <h2>두번 째 페이지</h2>
                <h1>오늘의 금전운</h1>
                <p>
                  마수리와 함께 오늘의 금전운과 행운의 색깔을 확인하세요!
                  <br /> 당신의 하루를 더 행복하고 특별하게 만들어 줄 조언들이
                  기다리고 있습니다.
                </p>
                <a>ENTER</a>
              </div>
            </div>
            <div className="intro-cell">
              <img src={book} className="intro-graphic" />
              <div className="intro-text">
                <h2>세번 째 페이지 </h2>
                <h1>소비패턴 타로</h1>
                <p>
                  당신의 주간 소비 패턴으로 탄생하는 단 하나의 타로카드
                  <br /> 당신만을 위한 특별한 메시지를 받아보세요.
                </p>
                <a>ENTER</a>
              </div>
            </div>
            <div className="intro-cell">
              <img src={money} className="intro-graphic" />
              <div className="intro-text">
                <h2>네번 째 페이지</h2>
                <h1>도감 및 카드추천</h1>
                <p>
                  당신만의 타로카드를 한 눈에 확인하세요. <br />
                  더불어, 마수리의 맞춤형 카드 추천 서비스를 통해 똑똑한 소비를
                  경험해보세요!
                </p>
                <a>ENTER</a>
              </div>
            </div>
          </div>
        </div>
      </main>
    </div>
  );
};

export default MainPage;
