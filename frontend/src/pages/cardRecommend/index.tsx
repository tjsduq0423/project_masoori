import React, { useEffect } from "react";
import "swiper/swiper-bundle.min.css"; // Import Swiper CSS from the correct path
import Swiper, { Navigation, Pagination } from "swiper"; // Import Swiper and necessary modules
import "./style.css";

const CardRecommend = () => {
  Swiper.use([Navigation, Pagination]); // Initialize Swiper with necessary modules

  useEffect(() => {
    const swiper = new Swiper(".swiper", {
      effect: "coverflow",
      grabCursor: true,
      spaceBetween: 30,
      centeredSlides: false,
      coverflowEffect: {
        rotate: 0,
        stretch: 0,
        depth: 0,
        modifier: 1,
        slideShadows: false,
      },
      loop: true,
      pagination: {
        el: ".swiper-pagination",
        clickable: true,
      },
      keyboard: {
        enabled: true,
      },
      mousewheel: {
        thresholdDelta: 70,
      },
      breakpoints: {
        460: {
          slidesPerView: 3,
        },
        768: {
          slidesPerView: 3,
        },
        1024: {
          slidesPerView: 3,
        },
        1600: {
          slidesPerView: 3.6,
        },
      },
    });
  }, []);

  return (
    <main>
      <div className="content">
        <h2>Welcome to Online Book Store</h2>
        <p>
          Lorem, ipsum dolor sit amet consectetur adipisicing elit. Repudiandae
          quam magnam obcaecati error consequatur repellat fugiat, deleniti nisi
          eum voluptates.
        </p>
        <ul className="counter">
          <li>
            <h3>
              <i className="fa-solid fa-book"></i>68+k
            </h3>
            <span>book collections</span>
          </li>
          <li>
            <h3>
              <i className="fa-solid fa-user"></i>25,634
            </h3>
            <span>customers</span>
          </li>
        </ul>
        <button className="btn">
          Go to Collections <i className="fa-solid fa-arrow-right"></i>
        </button>
      </div>

      <div className="swiper-container">
        <div className="swiper">
          <div className="swiper-wrapper">
            <div className="swiper-slide swiper-slide--one">
              <span>bestseller</span>
              <div className="slide-content">
                <h3>The Raven</h3>
              </div>
            </div>
            <div className="swiper-slide swiper-slide--two">
              <span>bestseller</span>
              <div className="slide-content">
                <h3>Mademoiselle</h3>
                <p>epic drama</p>
              </div>
            </div>
            <div className="swiper-slide swiper-slide--three">
              <span>bestseller</span>
            </div>
            <div className="swiper-slide swiper-slide--four">
              <span>bestseller</span>
            </div>
            <div className="swiper-slide swiper-slide--five">
              <span>bestseller</span>
            </div>
            <div className="swiper-slide swiper-slide--six">
              <span>bestseller</span>
              <div className="slide-content">
                <h3>Woman in the dark</h3>
              </div>
            </div>
          </div>
        </div>
        <div className="swiper-pagination"></div>
      </div>
      <div className="circle"></div>
    </main>
  );
};

export default CardRecommend;
