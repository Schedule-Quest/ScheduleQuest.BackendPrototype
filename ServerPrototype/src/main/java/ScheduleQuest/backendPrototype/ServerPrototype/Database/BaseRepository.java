package ScheduleQuest.backendPrototype.ServerPrototype.Database;

import ScheduleQuest.backendPrototype.ServerPrototype.Model.Task;

import java.sql.Connection;
import java.sql.SQLException;

public interface BaseRepository<T> {
    public void create(T entity, Connection connection) throws SQLException;

    public T getById(int id, Connection connection) throws SQLException;

    public void update(int id, Connection connection)throws SQLException;

    public void delete(int id, Connection connection) throws SQLException;

}
