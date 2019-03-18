package com.mars.ums.jpa.repository.gpon.performance;

import com.mars.ums.jpa.bean.gpon.performance.MarsGponPMUpLink;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 性能管理 -- 上联口性能统计 持久层
 * Created  by sunam
 * 2019/2/20 16:00
 */
public interface MarsGponPMUlkRepository extends JpaRepository<MarsGponPMUpLink,Long>{
}
