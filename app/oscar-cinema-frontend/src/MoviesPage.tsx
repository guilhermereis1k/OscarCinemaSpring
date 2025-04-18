// MoviesPage.tsx
import React, { useEffect, useState } from 'react';
import axios, { AxiosResponse } from 'axios';
import './MoviesPage.css'

interface Movie {
  id: number;
  name: string;
  description: string;
  imageUrl: string;
  minimumAge: number;
}

const MoviesPage = () => {
  const [movies, setMovies] = useState<Movie[]>([]);

  const Api = axios.create({
    baseURL: 'http://localhost:8080',
    headers: {
      'Content-Type': 'application/json',
    },
  });

  const getMovies = async (): Promise<AxiosResponse<Movie[]>> => {
    return Api.get('/movie/');
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
    <div>
      <h1>Lista de Filmes</h1>
      {movies.length === 0 ? (
        <p>Carregando filmes...</p>
      ) : (
        <ul className='moviesList'>
          {movies.map((movie) => (
            <li className='movieItem' key={movie.id}>
              <h2 className='movieTitle'>{movie.name}</h2>
              <img src={movie.imageUrl} alt={movie.name} />
              <p className='movieDesc'>{movie.description}</p>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default MoviesPage;
