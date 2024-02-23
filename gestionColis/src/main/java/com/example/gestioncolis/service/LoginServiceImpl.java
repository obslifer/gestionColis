package com.example.gestioncolis.service;

import com.example.gestioncolis.dao.LoginDAO;

import java.sql.SQLException;

public class LoginServiceImpl implements LoginService{
    private LoginDAO loginDAO=new LoginDAO();

    public LoginServiceImpl() throws SQLException {
    }


    @Override
    public int checkCredentials(String login, String password){
        return this.loginDAO.checkCredentials(login,password);
    }


}
