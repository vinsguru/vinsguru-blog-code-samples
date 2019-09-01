package com.vinsguru.dtoentity;

import com.vinsguru.dto.CarDto;
import com.vinsguru.dto.EngineDto;
import com.vinsguru.entity.Car;
import com.vinsguru.entity.Engine;
import com.vinsguru.mapper.CarMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DtoEntityApplicationTests {

	@Test
	public void modelMapperTest(){
		ModelMapper modelMapper = new ModelMapper();
		TypeMap<Car, CarDto> typeMap = modelMapper.createTypeMap(Car.class, CarDto.class);
		typeMap.addMappings(mapper -> {
			mapper.map(Car::getEngine, CarDto::setEngineDto);
		});
		final Car car = getCar();
		Runnable runnable = () -> modelMapper.map(car, CarDto.class);
		performanceTest("ModelMapper", runnable);
	}

	@Test
	public void beanUtilsTest(){
		final Car car = getCar();
		Runnable runnable = () -> {
			CarDto carDto = new CarDto();
			EngineDto engineDto = new EngineDto();
			BeanUtils.copyProperties(car.getEngine(), engineDto);
			BeanUtils.copyProperties(car, carDto);
			carDto.setEngineDto(engineDto);
		};
		performanceTest("BeanUtils", runnable);
	}

	@Test
	public void mapStructTest(){
		final Car car = getCar();
		Runnable runnable = () -> CarMapper.INSTANCE.toDto(car);
		performanceTest("MapStruct", runnable);
	}

	@Test
	public void manualTest(){
		final Car car = getCar();
		Runnable runnable = () -> {
			EngineDto engineDto = new EngineDto();
			engineDto.setType(car.getEngine().getType());
			CarDto carDto = new CarDto();
			carDto.setId(car.getId());
			carDto.setMake(car.getMake());
			carDto.setNumOfSeats(car.getNumOfSeats());
			carDto.setReleaseDate(car.getReleaseDate());
			carDto.setEngineDto(engineDto);
		};
		performanceTest("Manual", runnable);
	}

	private void performanceTest(final String type, final Runnable runnable){
		System.out.println("--------------------------------------------");
		//3 iterations
		for (int j = 0; j < 3; j++) {
			long time1 = System.currentTimeMillis();
			//each iteration 10 million conversion
			for (int i = 0; i < 10_000_000; i++) {
				runnable.run();
			}
			long time2 = System.currentTimeMillis();
			System.out.println(type + " :: " + (time2 - time1) + " ms");
		}
	}

	private Car getCar(){
		Car car = new Car();
		car.setId(1);
		car.setMake("Honda");
		car.setNumOfSeats(4);
		Date date = Date.from(LocalDate.of(2010, 10, 10).atStartOfDay(ZoneId.systemDefault()).toInstant());
		car.setReleaseDate(date);
		Engine engine = new Engine();
		engine.setType("V6");
		car.setEngine(engine);
		return car;
	}

}
