package common.common.transaction;

import org.jooq.TransactionContext;
import org.jooq.TransactionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import org.springframework.transaction.TransactionDefinition;

public class SpringTransactionProvider implements TransactionProvider {

    @Autowired
    DataSourceTransactionManager txMgr;

    /**
     * 事务级别 ：http://skyuck.iteye.com/blog/1833107
     * <p>
     * PROPAGATION_REQUIRED -- 支持当前事务，如果当前没有事务，就新建一个事务。这是最常见的选择。
     * PROPAGATION_SUPPORTS -- 支持当前事务，如果当前没有事务，就以非事务方式执行。
     * PROPAGATION_MANDATORY -- 支持当前事务，如果当前没有事务，就抛出异常。
     * PROPAGATION_REQUIRES_NEW -- 新建事务，如果当前存在事务，把当前事务挂起。
     * PROPAGATION_NOT_SUPPORTED -- 以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。
     * PROPAGATION_NEVER -- 以非事务方式执行，如果当前存在事务，则抛出异常。
     * PROPAGATION_NESTED -- 如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则进行与PROPAGATION_REQUIRED类似的操作。
     * 前六个策略类似于EJB CMT，第七个（PROPAGATION_NESTED）是Spring所提供的一个特殊变量。
     * 它要求事务管理器或者使用JDBC 3.0 Savepoint API提供嵌套事务行为（如Spring的DataSourceTransactionManager）
     */
    @Override
    public void begin(TransactionContext ctx) {
        //如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则进行与PROPAGATION_REQUIRED类似的操作。
        TransactionStatus tx = txMgr.getTransaction(new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED));
        ctx.transaction(new SpringTransaction(tx));
    }

    @Override
    public void commit(TransactionContext ctx) {
        txMgr.commit(((SpringTransaction) ctx.transaction()).tx);
    }

    @Override
    public void rollback(TransactionContext ctx) {
        txMgr.rollback(((SpringTransaction) ctx.transaction()).tx);
    }
}
