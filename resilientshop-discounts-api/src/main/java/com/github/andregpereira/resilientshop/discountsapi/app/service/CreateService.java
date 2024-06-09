package com.github.andregpereira.resilientshop.discountsapi.app.service;

public interface CreateService<R extends Record, T extends Record> extends Service {

    R criar(T t);

}
