package com.BYP.DAO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.BYP.model.Station;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Optional;
import java.util.List;
import java.util.Objects;

@Repository
public class StationRepository implements daoInterface<Station>{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Optional<Station> getByID(int id) {
		return Optional.ofNullable(entityManager.find(Station.class, id));
	}

	@Override
	public List<Station> getAll() {
		return entityManager.createQuery("SELECT s FROM Station s", Station.class).getResultList();
	}

	@Override
	@Transactional
	public void save(Station station) {
		entityManager.persist(station);
	}

	@Override
	@Transactional
	public void update(Station station, String[] params) {
		station.setLocation(Objects.requireNonNull(params[0], "Location cannot be null"));
		entityManager.merge(station);
	}

	@Override
	@Transactional
	public void delete(Station station) {
		entityManager.remove(station);
	}
}
