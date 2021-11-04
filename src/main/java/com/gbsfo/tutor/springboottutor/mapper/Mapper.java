package com.gbsfo.tutor.springboottutor.mapper;

import org.springframework.stereotype.Component;

@Component
public interface Mapper<E,D> {
    D mapToDto(E entity);
    E mapToEntity(D dto);
}
