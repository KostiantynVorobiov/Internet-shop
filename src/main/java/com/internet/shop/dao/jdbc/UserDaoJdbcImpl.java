package com.internet.shop.dao.jdbc;

import com.internet.shop.dao.UserDao;
import com.internet.shop.exeption.DataOperationException;
import com.internet.shop.lib.Dao;
import com.internet.shop.model.Role;
import com.internet.shop.model.User;
import com.internet.shop.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Dao
public class UserDaoJdbcImpl implements UserDao {

    @Override
    public Optional<User> findByLogin(String login) {
        String query = "SELECT * FROM users WHERE deleted = false AND login = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = getUserFromResultSet(resultSet);
                return Optional.of(user);
            }
        } catch (SQLException e) {
            throw new DataOperationException("Can't find user with login: " + login, e);
        }
        return Optional.empty();
    }

    @Override
    public User create(User user) {
        String query = "INSERT INTO users (name, login, password) VALUES (?, ?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                user.setId(resultSet.getLong(1));
            }
            addUserRoles(user);
            return user;
        } catch (SQLException e) {
            throw new DataOperationException("Can't create user " + user.getName(), e);
        }
    }

    @Override
    public Optional<User> getById(Long id) {
        String query = "SELECT * FROM users WHERE deleted = false AND user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = getUserFromResultSet(resultSet);
                return Optional.of(user);
            }
        } catch (SQLException e) {
            throw new DataOperationException("Can't get user by id= " + id, e);
        }
        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        String query = "SELECT * FROM users WHERE deleted = false";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> userList = new ArrayList<>();
            while (resultSet.next()) {
                userList.add(getUserFromResultSet(resultSet));
            }
            return userList;
        } catch (SQLException e) {
            throw new DataOperationException("Can't get all users", e);
        }
    }

    @Override
    public User update(User user) {
        String query = "UPDATE users SET name=?, login=?, password=? "
                + "WHERE deleted = false AND user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setLong(4, user.getId());
            preparedStatement.executeUpdate();
            return user;
        } catch (SQLException e) {
            throw new DataOperationException("Can't update user " + user.getName(), e);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        String query = "UPDATE users SET deleted = true WHERE deleted = false AND user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DataOperationException("Can't delete user with id: " + id, e);
        }
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        Long userId = resultSet.getLong("user_id");
        String name = resultSet.getString("name");
        String login = resultSet.getString("login");
        String password = resultSet.getString("password");
        Set<Role> userRoles = getUserRoleByUserId(userId);
        User user = new User(name, login, password);
        user.setId(userId);
        user.setRoles(userRoles);
        return user;
    }

    private Set<Role> getUserRoleByUserId(Long userId) {
        String query = "SELECT user_id, role_name FROM roles \n"
                + "JOIN users_roles ON roles.role_id = users_roles.role_id \n"
                + "WHERE user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Set<Role> userRoles = new HashSet<>();
            while (resultSet.next()) {
                userRoles.add(Role.of(resultSet.getString("role_name")));
            }
            return userRoles;
        } catch (SQLException e) {
            throw new DataOperationException("Can't get user role for user with id " + userId, e);
        }
    }

    private void addUserRoles(User user) {
        String query = "INSERT INTO users_roles (user_id, role_id) VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            for (Role role : user.getRoles()) {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setLong(1, user.getId());
                preparedStatement.setLong(2, getRoleId(role.getRoleName()));
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataOperationException("Can't add role to user", e);
        }
    }

    private Long getRoleId(Role.RoleName roleName) {
        String query = "SELECT role_id FROM roles WHERE role_name = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, roleName.name());
            ResultSet resultSet = preparedStatement.executeQuery();
            Long roleId = null;
            while (resultSet.next()) {
                roleId = resultSet.getLong("role_id");
            }
            return roleId;
        } catch (SQLException e) {
            throw new DataOperationException("Can't get id for role " + roleName, e);
        }
    }
}
