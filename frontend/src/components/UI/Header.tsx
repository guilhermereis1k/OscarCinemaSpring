import '../../style/Header.css';
import LogoSVG from '../../assets/svg/LogoSVG.tsx';
import MoviePage from '../../MoviePage.tsx';
import 'swiper/swiper-bundle.css'
import { Link } from 'react-router-dom';

const Header = () => {
    return (
        <header>
            <nav className='menu'>
                <Link to={"/"}>
                    <div className='menu__logo'>
                        <LogoSVG />
                        <h3 className='logo'>OscarCinema</h3>
                    </div>
                </Link>
                <ul className='menu__list'>
                    <li>Programação</li>
                    <li>Encontre um filme</li>
                    <li>Sobre nós</li>
                </ul>
            </nav>
        </header>
        )
}

export default Header;