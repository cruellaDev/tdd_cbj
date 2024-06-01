package com.study.tdd.chapters.chapter07;

public interface AutoDebitInfoRepository {
    AutoDebitInfo findOne(String userId);
    void save(AutoDebitInfo newInfo);
}
