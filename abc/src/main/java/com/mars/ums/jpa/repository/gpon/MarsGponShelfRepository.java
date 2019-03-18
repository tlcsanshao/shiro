package com.mars.ums.jpa.repository.gpon;

import com.mars.ums.jpa.bean.gpon.MarsGponShelf;
import com.mars.ums.jpa.bean.gpon.MarsGponSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarsGponShelfRepository extends JpaRepository<MarsGponShelf,Long>{


    List<MarsGponShelf> findByParentName(String parentName);
}
