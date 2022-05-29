package com.tdtu.ecommerce.service.impl;

import com.tdtu.ecommerce.dto.RacketDto;
import com.tdtu.ecommerce.entity.Racket;
import com.tdtu.ecommerce.mapper.ProductMapper;
import com.tdtu.ecommerce.repository.RacketRepository;
import com.tdtu.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RacketService implements ProductService<RacketDto> {

    @Autowired
    RacketRepository racketRepository;

    @Autowired
    ProductMapper productMapper;

    @Override
    public List<RacketDto> getAll() {
        List<Racket> racketDtoList = racketRepository.findByActiveTrue();
        return racketDtoList.stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void save(RacketDto racketDto) {
        Racket newRacket = productMapper.toEntity(racketDto);
        racketRepository.save(newRacket);
    }

    @Override
    @Transactional
    public void saveAll(List<RacketDto> racketDtos) {
        List<Racket> racketList = racketDtos.stream()
                .map(productMapper::toEntity)
                .collect(Collectors.toList());
        racketRepository.saveAll(racketList);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if(racketRepository.existsById(id)){
            racketRepository.deleteById(id);
        }
    }
}
