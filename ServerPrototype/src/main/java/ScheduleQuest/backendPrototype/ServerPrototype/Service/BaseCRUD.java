package ScheduleQuest.backendPrototype.ServerPrototype.Service;

import java.sql.Connection;
import java.sql.SQLException;

public interface BaseCRUD<T> {

    public void create(T entity, Connection connection) throws SQLException;

    public boolean getById(int id, Connection connection) throws SQLException;

    public void delete(int id, Connection connection) throws SQLException;



}
