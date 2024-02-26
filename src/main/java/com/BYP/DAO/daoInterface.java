package com.BYP.DAO;

import java.util.Optional;
import java.util.List;

//generating the interface for the DAO
public interface daoInterface<Entity> {
    Optional<Entity> getByID(int id);
    List<Entity> getAll();
    void save(Entity entity);
    void update(Entity entity, String[] params);
    void delete(Entity entity);
}
