package com.study.tdd.chapters.chapter07.autoDebitRegister;

public interface AutoDebitInfoRepository {
    AutoDebitInfo findOne(String userId);
    void save(AutoDebitInfo newInfo);
}
