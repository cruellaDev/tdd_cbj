package com.study.tdd.chapters.chapter07;

public class StubAutoDebitInfoRepository implements  AutoDebitInfoRepository {
    @Override
    public AutoDebitInfo findOne(String userId) {
        return null;
    }

    @Override
    public void save(AutoDebitInfo newInfo) {

    }
}
