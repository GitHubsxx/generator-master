package com.sxx.generator.application;

import com.sxx.generator.invoker.Many2ManyInvoker;
import com.sxx.generator.invoker.One2ManyInvoker;
import com.sxx.generator.invoker.SingleInvoker;
import com.sxx.generator.invoker.base.Invoker;

/**
 * Author sxx
 * Date   2018/9/5
 */
public class Main {

    public static void main(String[] args) {
        single();
        //one2many();
    }

    public static void many2many() {
        Invoker invoker = new Many2ManyInvoker.Builder()
                .setTableName("user")
                .setClassName("User")
                .setParentTableName("role")
                .setParentClassName("Role")
                .setRelationTableName("user_role")
                .setForeignKey("userId")
                .setParentForeignKey("roleId")
                .build();
        invoker.execute();
    }

    public static void one2many() {
        Invoker invoker = new One2ManyInvoker.Builder()
                .setTableName("t_enr_learner")
                .setClassName("Learner")
                .setParentTableName("t_bas_level")
                .setParentClassName("Level")
                .setForeignKey("level")
                .build();
        invoker.execute();
    }

    public static void single() {
        Invoker invoker = new SingleInvoker.Builder()
                .setTableName("T_BAS_SCHOOL")
                .setClassName("School")
                .build();
        invoker.execute();
    }

}
