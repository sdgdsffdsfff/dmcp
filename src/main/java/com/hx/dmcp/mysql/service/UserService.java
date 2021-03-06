package com.hx.dmcp.mysql.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hx.dmcp.mysql.dao.UserDao;
import com.hx.dmcp.mysql.entity.User;
import com.hx.dmcp.util.MD5Util;
import com.hx.dmcp.util.Pagination;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User addUser(User user) {
        userDao.addUser(user);
        return user;
    }


    public int deleteUser(long userId) {
        return userDao.deleteUser(userId);
    }


    public User updateUser(long userId, String name, String password, int status, String email) {
        User user = this.getUserById(userId);
        user.setName(name);
        user.setPassword(MD5Util.encrypt(password));
        user.setEmail(email);
        user.setStatus(status);
        userDao.updateUser(user);
        return user;
    }


    public User getUserById(long userId) {
        return userDao.getUserById(userId);
    }


    public List<User> getUserWithPage(Pagination<User> page){
    	return userDao.getUserWithPage(page.getOffsetRecords(),page.getPerPageRecords());
    }
    
    
    public int getTotalUserCounts() {
        return userDao.getTotalUserCounts();
    }

    public Pagination<User> getUserWithPage(int pageNO,int perPageRecords) {
        Pagination<User> page = new Pagination<User>();
        page.setCurrentPageSize(pageNO);
        page.setPerPageRecords(perPageRecords);
        List<User> list = this.getUserWithPage(page);
        page.setData(list);
        page.setTotalRecords(this.getTotalUserCounts());
        return page;
    }

    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    public User getUserByName(String userName) {
        return userDao.getUserByName(userName);
    }

    public User getUserByPassword(String password) {
        return userDao.getUserByPassword(password);
    }
    
    public void deleteUsers(String userId){
    	 userDao.deleteUsers(userId);
    }
    
    
}
