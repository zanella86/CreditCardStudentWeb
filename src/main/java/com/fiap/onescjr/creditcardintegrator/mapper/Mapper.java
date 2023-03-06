package com.fiap.onescjr.creditcardintegrator.mapper;

public interface Mapper <D, E>{
    E convertDTOToEntity(D dto);
    D convertEntityToDTO(E entity);

}
