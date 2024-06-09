package com.github.andregpereira.resilientshop.discountsapi.app.service.factory;

import com.github.andregpereira.resilientshop.discountsapi.app.service.Service;

public interface ServiceFactory {

    <S extends Service> S getService(Class<S> type);

}
