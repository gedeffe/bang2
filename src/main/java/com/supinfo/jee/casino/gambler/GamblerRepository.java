package com.supinfo.jee.casino.gambler;

import org.springframework.data.repository.CrudRepository;

public interface GamblerRepository extends CrudRepository<Gambler, Long> {
    boolean existsByPseudo(String pseudo);

    Gambler findByPseudo(String pseudo);
}
