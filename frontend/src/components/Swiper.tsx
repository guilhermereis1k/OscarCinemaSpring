import { Swiper, SwiperSlide } from 'swiper/react';
import { Navigation, Pagination, Autoplay } from 'swiper/modules';
import oscarCover from '../assets/oscars_cover.webp';
import oscarAea from '../assets/oscar_aea.webp';

import '../style/Swiper.css';
import 'swiper/css';
import 'swiper/css/navigation';
import 'swiper/css/pagination';
import 'swiper/css/scrollbar';

const data = [
    {id: '1', image: oscarCover},
    {id: '2', image: oscarAea}
]

export default () => {
  return (
    <Swiper
        modules={[Navigation, Pagination, Autoplay]}
        autoplay={{ delay: 4000, disableOnInteraction: false }}
        slidesPerView={1}
        navigation
        pagination={{ clickable : true }}
        className='swiper__box'
    >
        {data.map((item) => (
            <SwiperSlide key={item.id}>
                <img src={item.image} className='swiper__image'
                />
            </SwiperSlide>
        ))}
    </Swiper>
  );
};
