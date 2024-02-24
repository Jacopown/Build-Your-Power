package com.BYP.repository;
import org.springframework.data.repository.CrudRepository;

import com.BYP.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {}
