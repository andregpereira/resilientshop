package com.github.andregpereira.resilientshop.discountsapi.app.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindAllService<R> extends Service {

    Page<R> findAll(Pageable pageable);

}
