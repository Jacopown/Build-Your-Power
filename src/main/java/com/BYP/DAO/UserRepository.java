package com.BYP.DAO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Objects;

import com.BYP.entity.User;

import jakarta.persistence.PersistenceContext;
import jakarta.persistence.EntityManager;
import java.util.Optional;
import java.util.List;


@Repository
public class UserRepository implements daoInterface<User>{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Optional<User> getByID(int id) {
		return Optional.ofNullable(entityManager.find(User.class, id));
	}

	@Override
	public List<User> getAll() {
		return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
	}

	@Override
	@Transactional
	public void save(User user) {
		entityManager.persist(user);
	}

	@Override
	@Transactional
	public void update(User user, String[] params) {
		user.setUsername(Objects.requireNonNull(params[0], "Username cannot be null"));
		user.setEmail(Objects.requireNonNull(params[1], "Email cannot be null"));
		user.setIsSuperUser(Objects.requireNonNull(Boolean.parseBoolean(params[2])));
		entityManager.merge(user);
	}

	@Override
	@Transactional
	public void delete(User user) {
		entityManager.remove(user);
	}
}
