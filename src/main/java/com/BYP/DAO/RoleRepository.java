package com.BYP.DAO;


import com.BYP.Role;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Optional;
import java.util.List;

@Repository
public class RoleRepository implements daoInterface<Role>{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Optional<Role> getByID(int id) {
		return Optional.ofNullable(entityManager.find(Role.class, id));
	}

	@Override
	public List<Role> getAll() {
		return entityManager.createQuery("SELECT u FROM Role u", Role.class).getResultList();
	}

	@Override
	@Transactional
	public void save(Role role) {
		entityManager.persist(role);
	}

	@Override
	@Transactional
	public void update(Role role, String[] params) {
	}

	@Override
	@Transactional
	public void delete(Role role) {
		entityManager.remove(role);
	}

  public Optional<Role> findByName(String name) {
      return Optional.ofNullable((Role)entityManager.createQuery("SELECT u FROM Role u WHERE u.name = :name").setParameter("name", name).getSingleResult());
  }

  public boolean existsByName(String name) {
    Object result = entityManager.createQuery("SELECT COUNT(u) FROM Role u WHERE u.name = :name")
      .setParameter("name", name)
      .getSingleResult();
    // Check if the result is indeed a Long (recommended for more general cases)
    if (result instanceof Long) {
      return (Long) result != 0L;
    } else {
      // Handle unexpected result type (unlikely in this case, but good practice)
      throw new IllegalStateException("Unexpected result type from query: " + result.getClass());
    }
  }
}
