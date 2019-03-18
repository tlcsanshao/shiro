package com.mars.ums.jpa.repository.common.mo;

import com.mars.ums.jpa.bean.common.mo.ManagedObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public interface ManagedObjectRepository extends JpaRepository<ManagedObject,Long>{

    ManagedObject findByName(String name);

    List<ManagedObject> findByTypeIn(String [] strs);

    ArrayList<ManagedObject> findByParentName(String name);

    ArrayList<ManagedObject> findByParentNameOrderByMoid(String name);

    @Query(value = "select displayname from managedobject where parentname = ?1",nativeQuery = true)
    List<String> findDisplayNameByParentName(String parentName);
}
