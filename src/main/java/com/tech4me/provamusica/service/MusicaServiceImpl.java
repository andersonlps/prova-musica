package com.tech4me.provamusica.service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tech4me.provamusica.model.Musica;
import com.tech4me.provamusica.repository.MusicaRepository;
import com.tech4me.provamusica.shared.MusicaDTO;

@Service
public class MusicaServiceImpl implements MusicaService {

    @Autowired
    MusicaRepository repositorioMusica;

    @Override
    public List<MusicaDTO> obterTodos() {
        List<Musica> musicas = repositorioMusica.findAll();
        ModelMapper mapper = new ModelMapper();
        return musicas.stream()
        .map(musica -> mapper.map(musica, MusicaDTO.class))
        .collect(Collectors.toList());
    }

    @Override
    public Optional<MusicaDTO> obterPorId(String idMusica) {
        Optional<Musica> optionalMusica = repositorioMusica.findById(idMusica);
        if(optionalMusica.isEmpty()){
            throw new InputMismatchException("Não foi possível encontrar nenhuma música com este id:" + idMusica);   
        }
        MusicaDTO musicaDto = new ModelMapper().map(optionalMusica.get(), MusicaDTO.class);
        return Optional.of(musicaDto);    
    }

    @Override
    public MusicaDTO adicionar(MusicaDTO musicaDto) {
        ModelMapper mapper = new ModelMapper();
        Musica musica = mapper.map(musicaDto, Musica.class);
        musica.setId(null);
        musica = repositorioMusica.save(musica);
        return mapper.map(musica, MusicaDTO.class);
    }

    @Override
    public void delete(String idMusica) {
        repositorioMusica.deleteById(idMusica);
    }

    @Override
    public MusicaDTO atualizar(String idMusica, MusicaDTO musicaDto) {
        musicaDto.setId(idMusica);
        ModelMapper mapper = new ModelMapper();
        Musica musica = mapper.map(musicaDto, Musica.class);
        musica = repositorioMusica.save(musica);
        return mapper.map(musica, MusicaDTO.class);
    }  
}
