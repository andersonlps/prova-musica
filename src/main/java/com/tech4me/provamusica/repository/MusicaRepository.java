package com.tech4me.provamusica.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.tech4me.provamusica.model.Musica;

@Repository
public interface MusicaRepository extends MongoRepository<Musica, String> {}
