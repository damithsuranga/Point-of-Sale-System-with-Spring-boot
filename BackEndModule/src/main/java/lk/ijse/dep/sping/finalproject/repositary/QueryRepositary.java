package lk.ijse.dep.sping.finalproject.repositary;

import lk.ijse.dep.sping.finalproject.entity.CustomEntity;


import java.util.List;

public interface QueryRepositary {

    List<CustomEntity> getOrdersTotal() throws Exception;

}
