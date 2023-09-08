const MovieList = () => {
  const videoList = [
    {
      id: 1,
      image:
        "https://api.card-gorilla.com:8080/storage/card/2418/card_img/28128/2418card.png",
    },
    {
      id: 2,
      image:
        "https://api.card-gorilla.com:8080/storage/card/700/card_img/22174/700card.png",
    },
    {
      id: 3,
      image:
        "https://api.card-gorilla.com:8080/storage/card/2259/card_img/27662/2259card.png",
    },
    {
      id: 4,
      image:
        "https://api.card-gorilla.com:8080/storage/card/2260/card_img/27660/2260card.png",
    },
    {
      id: 5,
      image:
        "https://tilt.goombastomp.com/wp-content/uploads/2021/12/Jungle-Cruise-poster-4538533-819x1024.jpg",
    },
    {
      id: 6,
      image:
        "https://api.card-gorilla.com:8080/storage/card/16/card_img/28929/16card.png",
    },
    {
      id: 7,
      image:
        "https://api.card-gorilla.com:8080/storage/card/2387/card_img/25536/2387card.png",
    },
    {
      id: 8,
      image:
        "https://api.card-gorilla.com:8080/storage/card/2461/card_img/28297/2461card.png",
    },
  ];
  return (
    <div className="mt-10 ">
      <p className=" px-5 text-white text-[20px] font-bold">New Releases</p>

      <div
        className="w-full py-5  px-5 overflow-x-scroll 
    scroll whitespace-nowrap scroll-smooth scrollbar-hide"
      >
        {/* <HiChevronLeft className='text-white text-[30px] 
        absolute  z-10 hover:scale-125 cursor-pointer  h-[250px]' /> */}
        {videoList.map((item) => (
          <div className="inline-block m-2 md:m-3" key={item.id}>
            <img
              src={item.image}
              className="
                  w-[130px] h-[200px]
                  transition-all ease-in-out
                  duration-500 rounded-2xl object-cover
                  md:w-[160px] md:h-[240px] 
                  
                  cursor-pointer
                  hover:scale-125
                  "
            />

            <div className="absolute  inset-x-[15%] z-10 bottom-5 hidden py-5 text-center text-white md:block">
              {/* <h5 class="text-xl">First slide label</h5> */}
            </div>
          </div>
        ))}
        {/* <HiChevronRight className='text-white text-[30px] right-0 
        absolute  z-10 hover:scale-125 cursor-pointer ' /> */}
      </div>
    </div>
  );
};

export default MovieList;
