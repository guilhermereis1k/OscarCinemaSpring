package com.greis1.oscarcinema.config;

import com.greis1.oscarcinema.entities.Movie;
import com.greis1.oscarcinema.repositories.MovieRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
public class BestPictureMoviesSeeder {

    @Autowired
    MovieRepository movieRepository;

    @PostConstruct
    public void seedMovies() {
        movieRepository.save(new Movie("Anora",
                "https://a-static.mlcdn.com.br/1500x1500/poster-cartaz-anora-posterflix/poparteskins2/pos-03857-30x45cm/4be4e183101764c8368190333cd9797b.jpeg",
                "Uma jovem stripper se envolve com o filho de um oligarca russo, desencadeando uma série de eventos inesperados.",
                18));

        movieRepository.save(new Movie("O Brutalista",
                "https://a-static.mlcdn.com.br/1500x1500/poster-cartaz-o-brutalista-a-pop-arte-poster/poparteskins2/pos-04019-30x45cm/1b66bd9cfbaf1ec85e55140e2db9fc37.jpeg",
                "A vida de um arquiteto visionário é explorada enquanto ele enfrenta conflitos entre arte e sociedade.",
                16));

        movieRepository.save(new Movie("Um Completo Desconhecido",
                "https://a-static.mlcdn.com.br/1500x1500/poster-cartaz-um-completo-desconhecido-posterflix/poparteskins2/pos-03855-60x90cm/5edffe105a51e48c19876f882cf903ed.jpeg",
                "Um homem misterioso aparece em uma pequena cidade e muda a vida dos moradores.",
                14));

        movieRepository.save(new Movie("Conclave",
                "https://a-static.mlcdn.com.br/1500x1500/poster-cartaz-conclave-posterflix/poparteskins2/pos-03861-30x45cm/54356b5e422e5a91087d46c89dd6b542.jpeg",
                "Os bastidores da escolha do novo Papa após a morte do pontífice anterior.",
                12));

        movieRepository.save(new Movie("Duna: Parte 2",
                "https://a-static.mlcdn.com.br/1500x1500/poster-cartaz-duna-parte-2-c-pop-arte-poster/poparteskins2/pos-03531-30x45cm/d57650f6d2fb377930267ac06d1f2754.jpeg",
                "Continuação da saga de Paul Atreides enquanto ele lidera uma rebelião no planeta Arrakis.",
                14));

        movieRepository.save(new Movie("Emilia Pérez",
                "https://a-static.mlcdn.com.br/1500x1500/poster-cartaz-emilia-perez-pop-arte-poster/poparteskins2/pos-03789-30x45cm/929751db504ce8f06118a5b8c4cccc11.jpeg",
                "Um cartel mexicano é abalado quando seu líder decide se tornar uma mulher.",
                18));

        movieRepository.save(new Movie("Ainda Estou Aqui",
                "https://a-static.mlcdn.com.br/1500x1500/poster-cartaz-ainda-estou-aqui-posterflix/poparteskins2/pos-03947-30x45cm/68a05526ef1de4da251517260a6e108b.jpeg",
                "Uma história de sobrevivência e resistência em meio à ditadura militar brasileira.",
                16));

        movieRepository.save(new Movie("Nickel Boys",
                "https://a-static.mlcdn.com.br/1500x1500/poster-cartaz-nickel-boys-posterflix/poparteskins2/pos-03977-30x45cm/341c19f7ba5a8dfee2b7a63faab3172a.jpeg",
                "Adaptação do livro de Colson Whitehead sobre abusos em uma escola de reabilitação para jovens.",
                16));

        movieRepository.save(new Movie("A Substância",
                "https://image.tmdb.org/t/p/original/vWeOgzlhnP1sS23H3rzctGHB9Nb.jpg",
                "Uma substância misteriosa promete juventude eterna, mas cobra um preço terrível.",
                18));

        movieRepository.save(new Movie("Wicked",
                "https://a-static.mlcdn.com.br/1500x1500/poster-cartaz-wicked-posterflix/poparteskins2/pos-03834-60x90cm/e92bba484442009644de7d4742b2bad0.jpeg",
                "O musical clássico que conta a origem da Bruxa Má do Oeste em Oz.",
                10));
    }

}
