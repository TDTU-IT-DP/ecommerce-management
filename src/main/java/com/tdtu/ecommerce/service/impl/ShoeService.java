package com.tdtu.ecommerce.service.impl;

import com.tdtu.ecommerce.dto.ShoeDto;
import com.tdtu.ecommerce.entity.Shoe;
import com.tdtu.ecommerce.mapper.ProductMapper;
import com.tdtu.ecommerce.repository.ShoeRepository;
import com.tdtu.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoeService implements ProductService<ShoeDto> {

    @Autowired
    ProductMapper productMapper;

    @Autowired
    ShoeRepository shoeRepository;

    @Override
    public List<ShoeDto> getAll() {
        List<Shoe> shoeList = shoeRepository.findByActiveIsTrue();
        return null;
    }

    @Override
    public void save(ShoeDto shoeDto) {

    }

    @Override
    public void saveAll(List<ShoeDto> shoeDtos) {

    }

    @Override
    public void delete(Long id) {

    }
}
