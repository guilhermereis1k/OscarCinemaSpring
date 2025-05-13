import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import LImage from '../src/assets/L.svg';
import TenImage from '../src/assets/10.svg';
import TwelveImage from '../src/assets/12.svg';
import FourteenImage from '../src/assets/14.svg';
import SixteenImage from '../src/assets/16.svg';
import EighteenImage from '../src/assets/18.svg';
import NRImage from '../src/assets/no-rating.svg';
import "./style/MoviePage.css";
import Header from "./components/UI/Header";
import SessionFilters from "./components/SessionFilters";
import SessionList from "./components/SessionList"; // <-- Mantido

interface Movie {
    id: number;
    name: string;
    description: string;
    imageUrl: string;
    minimumAge: number;
}

const MoviePage = () => {
    const [movie, setMovie] = useState<Movie | null>(null);
    const formatarData = (date: Date) => {
        return date.toISOString().split('T')[0];
    };
    const [filters, setFilters] = useState({
        date: formatarData(new Date()),
        projectorType: '2D',
        language: 'dub',
    });
    const [error, setError] = useState<string | null>(null);

    const { id } = useParams<{ id: string }>();

    useEffect(() => {
        const fetchMovie = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/movie/search/id/${id}`);
                if (response.data) {
                    setMovie(response.data);
                } else {
                    setError('Filme não encontrado.');
                }
            } catch (error) {
                setError('Erro ao carregar os dados do filme.');
                console.error('Erro ao buscar filme:', error);
            }
        };

        fetchMovie();
    }, [id]);

    const handleFilterChange = (field: string, value: string) => {
        setFilters((prevFilters) => ({
            ...prevFilters,
            [field]: value,
        }));
    };

    if (error) {
        return <p>{error}</p>;
    }

    if (!movie) {
        return <p>Carregando filme...</p>;
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
        <>
            <Header />
            <div className="moviePage__content">
                <div className="moviePage__imgBox" key={movie.id}>
                    <img
                        className="moviePage__movieCover"
                        src={movie.imageUrl}
                        alt={`Cartaz do filme ${movie.name}`}
                    />
                    <ul className="moviePage__underCover">
                        <li className="moviePage__titleBox">
                            <h2 className="moviePage__title">{movie.name}</h2>
                        </li>
                        <li>
                            <p>Classificação indicativa: </p>
                            <img className="moviePage__pg"
                                src={minImage}
                                alt={`Classificação indicativa: ${movie.minimumAge}`}
                            />
                        </li>
                    </ul>
                </div>
                <div className="moviePage__right">
                    <div className="moviePage__info">
                        <h2>Sinopse</h2>
                        <p className="moviePage__description"> {movie.description}</p>
                    </div>
                    <SessionFilters filters={filters} onFilterChange={handleFilterChange} />
                    <SessionList
                        movieId={movie.id}
                        filters={filters}
                    />
                </div>
            </div>
        </>
    );
};

export default MoviePage;
