package dao;

import domain.Employee;

import java.sql.SQLException;

public interface DAO extends AutoCloseable {
    void create(final Employee employee) throws SQLException;
    Employee read(final int id) throws SQLException;
    void update(final Employee employee) throws SQLException;
    void delete(final int id) throws SQLException;
}
