package com.spring5.transaction;

import org.springframework.lang.Nullable;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

/**
 * @ClassName TransactionTest
 * @Author bin
 * @Date 2021/4/1 下午4:28
 * @Decr TODO
 * @Link TODO
 **/
public class TransactionTest implements TransactionCallback {
    @Nullable
    @Override
    public Object doInTransaction(TransactionStatus transactionStatus) {
        try {
            System.out.println("加载数据库信息");
        } catch (Exception e) {
            //事务会滚
            transactionStatus.setRollbackOnly();
        }
        return null;
    }
}
