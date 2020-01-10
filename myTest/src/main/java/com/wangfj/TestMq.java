package com.wangfj;

import org.apache.activemq.jndi.ActiveMQInitialContextFactory;

import javax.jms.Destination;
import javax.naming.Context;
import javax.naming.NamingException;

/**
 * @Author wangfj_tongtech
 * @DATE 2020/1/9
 */
public class TestMq {

    public static void main(String[] args) {
        ActiveMQInitialContextFactory contextFactory = new ActiveMQInitialContextFactory();
        try {
            Context context = contextFactory.getInitialContext(null);
            Destination dest = (Destination) context.lookup("order1");
            System.out.println(dest);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
