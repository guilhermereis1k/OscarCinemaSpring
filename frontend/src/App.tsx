import MoviesList from './components/MoviesList.tsx';
import Header from './components/UI/Header.tsx';
import Swiper from './components/Swiper.tsx';
import './style/App.css';

function App() {

  return (
    <>
      <Header />
      <Swiper />
      <MoviesList />
    </>
  )
}

export default App
