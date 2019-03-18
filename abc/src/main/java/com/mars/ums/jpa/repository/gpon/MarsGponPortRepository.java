package com.mars.ums.jpa.repository.gpon;

import com.mars.ums.jpa.bean.gpon.MarsGponPort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarsGponPortRepository extends JpaRepository<MarsGponPort,Long>{
    List<MarsGponPort> findByParentName(String slotName);
}
