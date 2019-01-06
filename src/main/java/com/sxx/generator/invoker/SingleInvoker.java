package com.sxx.generator.invoker;

import com.sxx.generator.entity.ColumnInfo;
import com.sxx.generator.invoker.base.BaseBuilder;
import com.sxx.generator.invoker.base.BaseInvoker;
import com.sxx.generator.utils.GeneratorUtil;
import com.sxx.generator.utils.StringUtil;
import com.sxx.generator.task.*;

import java.sql.SQLException;

/**
 * Author sxx
 * Date   2018/9/5
 */
public class SingleInvoker extends BaseInvoker {

    @Override
    protected void getTableInfos() throws SQLException {
        tableInfos = connectionUtil.getMetaData(tableName);
    }

    @Override
    protected void initTasks() {
        taskQueue.add(new DaoTask(className));
        taskQueue.add(new DaoImplTask(className));
        taskQueue.add(new ServiceTask(className));
        taskQueue.add(new ServiceImplTask(className));
        taskQueue.add(new ControllerTask(className));
        //taskQueue.add(new EntityTask(className, tableInfos));
        taskQueue.add(new EntityTask(className,parentClassName,foreignKey,tableInfos));
        taskQueue.add(new MapperTask(className, tableName, tableInfos));
        //taskQueue.add(new HibernateTask(className, tableName, tableInfos));
        taskQueue.add(new HibernateTask(tableName,className,parentTableName,parentClassName,foreignKey,tableInfos,parentTableInfos));
        taskQueue.add(new IndexTask(className, tableInfos));
        taskQueue.add(new InputTask(className, tableInfos));
        taskQueue.add(new ListTask(className, tableInfos));

    }

    public static class Builder extends BaseBuilder {
        private SingleInvoker invoker = new SingleInvoker();

        public Builder setTableName(String tableName) {
            invoker.setTableName(tableName);
            return this;
        }

        public Builder setClassName(String className) {
            invoker.setClassName(className);
            return this;
        }

        @Override
        public BaseInvoker build(){
            if (!isParamtersValid()) {
                return null;
            }
            return invoker;
        }

        @Override
        public void checkBeforeBuild() throws Exception {
            if (StringUtil.isBlank(invoker.getTableName())) {
                throw new Exception("Expect table's name, but get a blank String.");
            }
            if (StringUtil.isBlank(invoker.getClassName())) {
                invoker.setClassName(GeneratorUtil.generateClassName(invoker.getTableName()));
            }
        }
    }

}
