import { useEffect, useState } from 'react';
import axios, { AxiosResponse } from 'axios';
import { useKeenSlider } from 'keen-slider/react';
import LImage from '../../src/assets/L.svg';
import TenImage from '../../src/assets/10.svg';
import TwelveImage from '../../src/assets/12.svg';
import FourteenImage from '../../src/assets/14.svg';
import SixteenImage from '../../src/assets/16.svg';
import EighteenImage from '../../src/assets/18.svg';
import NRImage from '../../src/assets/no-rating.svg';
import 'keen-slider/keen-slider.min.css';
import '../style/MoviesList.css';
import { Link } from 'react-router-dom';

interface Movie {
  id: number;
  name: string;
  description: string;
  imageUrl: string;
  minimumAge: number;
}

const MoviesList = () => {
  const [movies, setMovies] = useState<Movie[]>([]);

  const [sliderRef] = useKeenSlider<HTMLDivElement>({
    slides: {
      origin: 'auto',
      perView: 'auto',
      spacing: 15,
    },
    mode: "free-snap",
  });
  

  const Api = axios.create({
    baseURL: 'http://localhost:8080',
    headers: {
      'Content-Type': 'application/json',
    },
  });

  const getMovies = async (): Promise<AxiosResponse<Movie[]>> => {
    return Api.get('/movie');
  };

  useEffect(() => {
    const fetchMovies = async () => {
      try {
        const response = await getMovies(); 
        setMovies(response.data); 
      } catch (error) {
        console.error('Erro ao buscar filmes:', error);
      }
    };

    fetchMovies();
  }, []);

  return (
    <div className="programacao__content">
      <h2 className="programacao__title">Filmes em cartaz</h2>
  
      <div className="programacao__box">
        {movies.length === 0 ? (
          <p>Carregando filmes...</p>
        ) : (
          <div ref={sliderRef} className="keen-slider">
            {movies.map((movie) => {
              const minImage =
                movie.minimumAge < 10 ? LImage :
                movie.minimumAge === 10 ? TenImage :
                movie.minimumAge === 12 ? TwelveImage :
                movie.minimumAge === 14 ? FourteenImage :
                movie.minimumAge === 16 ? SixteenImage :
                movie.minimumAge === 18 ? EighteenImage :
                NRImage;
  
              return (
                
                  <div key={movie.id} className="keen-slider__slide movie__slide">
                    <Link to={`/movie/${movie.id}`}>
                      <img
                        src={movie.imageUrl}
                        alt={`Cartaz do filme ${movie.name}`}
                        className="movie__image"
                      />
                      <div className="movie__info">
                        <h2 className="movie__title">{movie.name}</h2>
                        <img
                          src={minImage}
                          alt={`Classificação indicativa: ${movie.minimumAge}`}
                          className="minAgeImage"
                        />
                      </div>
                    </Link>
                  </div>
              );
            })}
          </div>
        )}
      </div>
    </div>
  );  
};

export default MoviesList;
