package com.luizfrra.stockSim.Services.Commons;

import java.util.Optional;

public interface IBaseService<C, T> {

    Optional<C> findById(T id);

    C save(C data);

}
