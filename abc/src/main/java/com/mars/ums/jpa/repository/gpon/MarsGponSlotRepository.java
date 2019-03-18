package com.mars.ums.jpa.repository.gpon;

import com.mars.ums.jpa.bean.gpon.MarsGponSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarsGponSlotRepository extends JpaRepository<MarsGponSlot, Long> {

    List<MarsGponSlot> findByParentName(String parentName);
}
