package com.lanhun.example.dao;

import com.lanhun.example.model.DataFlow;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DataFlowMapper {

    List<DataFlow> list();
}
