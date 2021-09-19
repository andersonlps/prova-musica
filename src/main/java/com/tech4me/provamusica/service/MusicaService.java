package com.tech4me.provamusica.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.tech4me.provamusica.shared.MusicaDTO;

@Service
public interface MusicaService {

    List<MusicaDTO> obterTodos();

    Optional<MusicaDTO> obterPorId(String idMusica);

    MusicaDTO adicionar(MusicaDTO musicaDto);

    MusicaDTO atualizar(String idMusica, MusicaDTO musica);

    void delete(String idMusica);

}
