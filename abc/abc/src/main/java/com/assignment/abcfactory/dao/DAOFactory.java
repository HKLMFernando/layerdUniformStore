package com.assignment.abcfactory.dao;

import com.assignment.abcfactory.bo.custom.impl.*;
import com.assignment.abcfactory.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory() {
    }
    public static DAOFactory getInstance() {
        return daoFactory==null?daoFactory=new DAOFactory():daoFactory;
    }
    public enum DAOType {
        CUSTOMER,FEEDBACK,ITEM,MANU,ORDER,ORDERDETAILS,PAYMENT,USER;
    }
    public SuperDAO getDAO(DAOType type) {
        switch (type) {
            case CUSTOMER:
                return new CustomerDAOImpl();
                case FEEDBACK:
                    return new FeedBackModelDAOImpl();
                    case ITEM:
                        return new ItemDAOImpl();
                        case ORDER:
                            return new OrderDAOImpl();
                            case ORDERDETAILS:
                                return new OrderDetailsDAOImpl();
                                case PAYMENT:
                                    return new PaymentDAOImpl();
                                case USER:
                                    return new UserDAOImpl();

            default:
                return null;
        }
    }
}
