package com.github.andregpereira.resilientshop.discountsapi.app.service;

public interface UpdateService<R extends Record, T extends Record> extends Service {

    R update(Long id, T t);

}
