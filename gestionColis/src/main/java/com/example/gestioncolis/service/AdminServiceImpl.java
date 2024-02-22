package com.example.gestioncolis.service;

import com.example.gestioncolis.dao.AdminDAO;
import com.example.gestioncolis.entities.Admin;

import java.sql.SQLException;
import java.util.List;

public class AdminServiceImpl implements AdminService {
    private AdminDAO adminDAO;

    public AdminServiceImpl() throws SQLException {
        this.adminDAO = new AdminDAO();
    }

    @Override
    public void create(Admin admin) {
        try {
            adminDAO.create(admin);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Admin admin) {
        try {
            adminDAO.update(admin);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            adminDAO.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
    }

    @Override
    public Admin getById(int id) {
        try {
            return adminDAO.getById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        return null;
    }

    @Override
    public List<Admin> getAll() {
        try {
            return adminDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        return null;
    }
}
