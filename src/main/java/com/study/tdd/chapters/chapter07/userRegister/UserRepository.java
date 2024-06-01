package com.study.tdd.chapters.chapter07.userRegister;

public interface UserRepository {
    void save(User user);
    User findById(String id);
}
