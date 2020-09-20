package com.luizfrra.stockSim.Services.Commons;

import org.hibernate.exception.ConstraintViolationException;

import java.util.List;
import java.util.Optional;

public interface IBaseService<C, T> {

    Optional<C> findById(T id);

    C save(C data) throws ConstraintViolationException;

    List<C> findAll();

}
