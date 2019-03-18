package com.mars.ums.jpa.repository.common.mo;

import com.mars.ums.jpa.bean.common.mo.MarsArea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarsAreaRepository extends JpaRepository<MarsArea,Long> {
    MarsArea findByName(String name);
}
