package com.fiap.onescjr.creditcardstudentweb.mapper;

public interface Mapper <D, E>{
    E convertDTOToEntity(D dto);
    D convertEntityToDTO(E entity);

}
