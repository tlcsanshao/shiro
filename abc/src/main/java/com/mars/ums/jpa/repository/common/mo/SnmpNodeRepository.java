package com.mars.ums.jpa.repository.common.mo;

import com.mars.ums.jpa.bean.common.mo.SnmpNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface SnmpNodeRepository extends JpaRepository<SnmpNode,Long>{
}
