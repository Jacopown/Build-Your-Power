package com.BYP.DAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Objects;

import com.BYP.model.User;

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

  public Optional<User> findByEmail(String email) {
      return Optional.ofNullable((User)entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email").setParameter("email", email).getSingleResult());
    }

	@Override
	@Transactional
	public void save(User user) {
		entityManager.persist(user);
	}

	@Override
	@Transactional
	public void update(User user, String[] params) {
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
