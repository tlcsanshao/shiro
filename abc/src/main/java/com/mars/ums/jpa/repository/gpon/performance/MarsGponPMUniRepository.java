package com.mars.ums.jpa.repository.gpon.performance;

import com.mars.ums.jpa.bean.gpon.performance.MarsGponPmUni;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * 性能管理 ONU UNI口性能统计 持久层
 * Created  by sunam
 * 2019/2/20 15:56
 */
@Component
public interface MarsGponPMUniRepository extends JpaRepository<MarsGponPmUni,Long>{
}
