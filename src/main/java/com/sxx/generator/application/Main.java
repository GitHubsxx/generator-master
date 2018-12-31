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
                .setTableName("user")
                .setClassName("User")
                .setParentTableName("office")
                .setParentClassName("Office")
                .setForeignKey("officeId")
                .build();
        invoker.execute();
    }

    public static void single() {
        Invoker invoker = new SingleInvoker.Builder()
                .setTableName("t_bas_annual")
                .setClassName("Annual")
                .build();
        invoker.execute();
    }

}
