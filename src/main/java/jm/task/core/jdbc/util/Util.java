package jm.task.core.jdbc.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;


public class Util {
    // реализуйте настройку соеденения с БД
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/testdbkata";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";

    public static Connection getConnectionDB(){
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);

        } catch (SQLException|ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnectionDB() {
        try{
            getConnectionDB().close();
        }catch (SQLException err){
            err.printStackTrace();
        }
    }


    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Properties properties = new Properties();
                properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                properties.put(Environment.URL, "jdbc:mysql://localhost:3306/testdbkata");
                properties.put(Environment.USER, "root");
                properties.put(Environment.PASS, "root");

                properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                properties.put(Environment.SHOW_SQL, "true");
                properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                properties.put(Environment.HBM2DDL_AUTO, "");


                Configuration configuration = new Configuration()
                        .setProperties(properties)
                        .addAnnotatedClass(User.class);

                StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(standardServiceRegistry);
                System.out.println("SessionFactory успешно создана");

            } catch (HibernateException he) {
                System.out.println("Ошибка создания SessionFactory");
                he.printStackTrace();
            }
        } else {
            System.out.println("Используется ранее созданная SessionFactory");
        }
        return sessionFactory;
    }

    public static Session openSession() {
        return sessionFactory.getCurrentSession();
    }

}
