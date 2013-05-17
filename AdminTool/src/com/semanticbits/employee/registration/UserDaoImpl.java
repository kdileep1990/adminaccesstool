package com.semanticbits.employee.registration;

import com.semanticbits.employee.Employee;
import com.semanticbits.employee.Roles;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl {

    @Resource
    private SessionFactory sessionFactory;
    private Session session;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;

    }

    public boolean saveUserInfo(Employee user) {
        boolean flag = false;
        try {
            session = sessionFactory.openSession();
            session.getTransaction().begin();
            session.save(user);
            session.getTransaction().commit();
            System.out.println("inserted");
            return true;
        } catch (org.hibernate.exception.ConstraintViolationException e) {
            return false;
        }
    }

    public boolean isAuthenticated(Employee user) {
        boolean flag = true;
        System.out.println(user.getEmailid());

        session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Employee.class);

        // Query query=session.createQuery("from Employee a");
        Criterion email = Restrictions.eq("emailid", user.getEmailid());
        Criterion password = Restrictions.eq("password", user.getPassword());
        LogicalExpression andExp = Restrictions.and(email, password);

        criteria.add(andExp);
        List<Employee> list = criteria.list();
//        System.out.println(list.get(0).getRoleses().size() + "hihihiihihih");
        System.out.println(user);
        if (list.isEmpty()) {
            flag = false;
        } else {
            Set rolesSet = list.get(0).getRoleses();
            Iterator iterator = rolesSet.iterator();
            flag = false;
            while (iterator.hasNext()) {
                Roles roles = (Roles) iterator.next();
                if (roles.getRole().equals("User Admin")) {
                    flag = true;
                }
            }
        }
        return flag;
    }

    public List<Roles> getRoles(String firstName) {
        session = sessionFactory.openSession();
        Query query = session.createQuery("select distinct a from UserRole a");
        List<Roles> rolesList = query.list();

        return rolesList;
    }

    public boolean assignRoles(String[] role, String emailid) {


        session = sessionFactory.openSession();
        Employee user = (Employee) session.get(Employee.class, emailid);
        List roles = new ArrayList();
        for (int i = 0; i < role.length; i++) {
            roles.add(role[i]);
        }
        String[] roleid = new String[role.length];

        session.getTransaction().begin();
        String hql = "delete from Userroles U where U.id.emailid='" + emailid + "'";
        System.out.println(hql);
        Query query = session.createQuery(hql);
        int count1 = query.executeUpdate();
        session.getTransaction().commit();
        if (roles.size() > 0) {
            hql = "from Roles R";
            System.out.println(hql);
            query = session.createQuery(hql);
            List rolesList = query.list();
            Set rolesSet = new HashSet();
            System.out.println(roles);
            for (int i = 0; i < rolesList.size(); i++) {
                Roles currentRole = (Roles) rolesList.get(i);
                if (roles.contains(currentRole.getRole())) {
                    System.out.println("hi");
                    rolesSet.add(currentRole);
                }
            }
            session.getTransaction().begin();
            user.setRoleses(rolesSet);
//            user.setFirstname("di");
//            System.out.println(user.getRoleses().size());
            session.saveOrUpdate(user);
            session.getTransaction().commit();
//            List<Roles> availableRoleList = getRoles(firstName);//available roles
//
//            List<Integer> availRoles = new ArrayList<Integer>();
//            Iterator<Roles> iterator = availableRoleList.iterator();
//            while (iterator.hasNext()) {
//                Roles userRole2 = (Roles) iterator.next();
//                availRoles.add(userRole2.getRoleid());
//            }
//
//            for (int availRoleCount = 0; availRoleCount < availRoles.size(); availRoleCount++) {
//                boolean flag = false;
//                int availableRole = 0;
//                Session session2 = sessionFactory.openSession();
//
//                for (int assignedRoleCount = 0; assignedRoleCount < role.length; assignedRoleCount++) {
//                    availableRole = availRoles.get(availRoleCount);
//
//                    if (availRoles.get(availRoleCount) == Integer.parseInt(role[assignedRoleCount])) {
//
//                        session2.getTransaction().begin();
//                        SQLQuery query2 = session2.createSQLQuery("insert into USERROLES(emailid,roleid) values('" + email + "','" + role[assignedRoleCount] + "')");
//                        int count1 = query2.executeUpdate();
//                        session2.getTransaction().commit();
//                        flag = true;
//                        break;
//
//                    }
//                }
//                if (flag == false) {
//                    session2.getTransaction().begin();
//                    SQLQuery query2 = session2.createSQLQuery("delete from USERROLES where roleid='" + availableRole + "'");
//                    int count1 = query2.executeUpdate();
//                    session2.getTransaction().commit();
//                }
//
//            }
        }



        return true;
    }

    public List getAssignedRoles(String firstName) {
        session = sessionFactory.openSession();
        Employee user = (Employee) session.get(Employee.class, firstName);
        System.out.println(firstName);
        String email = user.getEmailid();
        List roles = new ArrayList();
        Iterator iterator = user.getRoleses().iterator();
        while (iterator.hasNext()) {
            Roles role = (Roles) iterator.next();
            roles.add(role.getRole());
        }
        return roles;
    }
}
