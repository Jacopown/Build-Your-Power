package com.BYP.repository;
import org.springframework.data.repository.CrudRepository;

import com.BYP.entity.Battery;

public interface BatteryRepository extends CrudRepository<Battery, Integer> {}
