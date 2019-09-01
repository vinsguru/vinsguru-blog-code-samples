package com.vinsguru.mapper;

import com.vinsguru.dto.CarDto;
import com.vinsguru.dto.EngineDto;
import com.vinsguru.entity.Car;
import com.vinsguru.entity.Engine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper( CarMapper.class );

    @Mapping(source = "engine", target = "engineDto")
    CarDto toDto(Car car);

    EngineDto toDto(Engine engine);

}
