package com.BYP.DAO;

import com.BYP.entity.Battery;
import com.BYP.entity.Battery.BatteryStatus;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

import com.BYP.entity.Station;
import com.BYP.entity.User;

@Repository
public class BatteryRepository implements daoInterface<Battery>{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Optional<Battery> getByID(int id) {
		return Optional.ofNullable(entityManager.find(Battery.class, id));
	}

	@Override
	public List<Battery> getAll() {
		return entityManager.createQuery("SELECT b FROM Battery b", Battery.class).getResultList();
	}

	@Override
	@Transactional
	public void save(Battery battery) {
		entityManager.persist(battery);
	}

	@Override
	@Transactional
	public void update(Battery battery, String[] params) {
		battery.setStatus(Objects.requireNonNull(BatteryStatus.valueOf(params[0]), "Status cannot be null"));
		//TODO maybe add more fields to update
		entityManager.merge(battery);
	}

	@Override
	@Transactional
	public void delete(Battery battery) {
		entityManager.remove(battery);
	}

	//switching between station and user
	//battery assignment
	@Transactional
	public void switchAssignment(Battery battery, User user) {
		battery.updateAssign(user);
		entityManager.merge(battery);
	}

	//station assignment
	@Transactional
	public void switchAssignment(Battery battery, Station station) {
		battery.updateAssign(station);
		entityManager.merge(battery);
	}
}
