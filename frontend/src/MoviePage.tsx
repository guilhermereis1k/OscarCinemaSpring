import axios, { AxiosResponse } from "axios";
import { useKeenSlider } from "keen-slider/react";
import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import LImage from '../src/assets/L.svg';
import TenImage from '../src/assets/10.svg';
import TwelveImage from '../src/assets/12.svg';
import FourteenImage from '../src/assets/14.svg';
import SixteenImage from '../src/assets/16.svg';
import EighteenImage from '../src/assets/18.svg';
import NRImage from '../src/assets/no-rating.svg';


interface Movie {
    id: number;
    name: string;
    description: string;
    imageUrl: string;
    minimumAge: number;
  }

const MoviePage = () => {
    const [movie, setMovie] = useState<Movie | null>(null);

    const { id } = useParams<{ id: string }>()
  
    const Api = axios.create({
        baseURL: 'http://localhost:8080',
        headers: {
            'Content-Type': 'application/json',
        },
    });

    const getMovie = async (): Promise<AxiosResponse<Movie>> => {
        return Api.get(`/movie/search/id/${id}`);
    };

    useEffect(() => {
        const fetchMovie = async () => {
            try {
                const response = await getMovie();
                setMovie(response.data); // Set the movie data when fetched
            } catch (error) {
                console.error('Erro ao buscar filme:', error);
            }
        };

        fetchMovie();
    }, [id]);

    if (!movie) {
        return <p>Carregando filme...</p>; // Show loading text while movie is fetched
    }

    const minImage =
        movie.minimumAge < 10 ? LImage :
        movie.minimumAge === 10 ? TenImage :
        movie.minimumAge === 12 ? TwelveImage :
        movie.minimumAge === 14 ? FourteenImage :
        movie.minimumAge === 16 ? SixteenImage :
        movie.minimumAge === 18 ? EighteenImage :
        NRImage;

    return (
        <div>
            <div key={movie.id}>
                <img
                    src={movie.imageUrl}
                    alt={`Cartaz do filme ${movie.name}`}
                />
                <div>
                    <h2>{movie.name}</h2>
                    <img
                        src={minImage}
                        alt={`Classificação indicativa: ${movie.minimumAge}`}
                    />
                </div>
                <div>
                    <p>{movie.description}</p>
                </div>
            </div>
        </div>
    );
}

export default MoviePage;