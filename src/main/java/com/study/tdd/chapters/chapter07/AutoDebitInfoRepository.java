package com.study.tdd.chapters.chapter07;

public interface AutoDebitInfoRepository {
    public AutoDebitInfo findOne(String userId);
    public void save(AutoDebitInfo newInfo);
}
