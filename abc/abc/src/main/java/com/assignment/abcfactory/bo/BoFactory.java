package com.assignment.abcfactory.bo;

import com.assignment.abcfactory.bo.custom.impl.*;

public class BoFactory {
    private static BoFactory boFactory ;
    private BoFactory(){
    }
    public static BoFactory getInstance(){
        return boFactory == null ? (boFactory = new BoFactory()) : boFactory;
    }
    public enum BOTYPE{
        CUSTOMER,FEEDBACK,ITEM,MANU,ORDER,ORDERDETAILS,PAYMENT,USER;
    }
    public SuperBo getBo(BOTYPE type){
        switch (type){
            case CUSTOMER:
                return new CustomerBoImpl();
                case FEEDBACK:
                    return new FeedBackBoImpl();
                    case ITEM:
                        return new ItemBoImpl();
                        case MANU:
                            return new ManuBoImpl();
                            case ORDER:
                                return new OrderBoImpl();
                                case ORDERDETAILS:
                                   return new OrderDetailsBoImpl();
                                   case PAYMENT:
                                       return new PaymentBoImpl();
                                       case USER:
                                           return new UserBoImpl();
         default:
             return null;
        }
    }
}
