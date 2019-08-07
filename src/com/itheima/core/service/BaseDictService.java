package com.itheima.core.service;

import com.itheima.core.po.BaseDict;

import java.util.List;

public interface BaseDictService {
    public List<BaseDict> findBaseDictByTypeCode(String typecode);
}
