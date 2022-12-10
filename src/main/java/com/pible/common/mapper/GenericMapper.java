package com.pible.common.mapper;

public interface GenericMapper<T, A> {

    A entityToDto(T entity);
    T dtoToEntity(A dto);
}
