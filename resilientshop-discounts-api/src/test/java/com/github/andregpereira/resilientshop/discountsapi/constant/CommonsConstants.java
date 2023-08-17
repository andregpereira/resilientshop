package com.github.andregpereira.resilientshop.discountsapi.constant;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class CommonsConstants {

    public static final PageRequest PAGEABLE_ID = PageRequest.of(0, 10, Sort.Direction.ASC, "id");

}
