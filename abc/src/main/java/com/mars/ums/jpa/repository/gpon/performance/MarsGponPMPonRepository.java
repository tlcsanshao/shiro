package com.mars.ums.jpa.repository.gpon.performance;

import com.mars.ums.jpa.bean.gpon.performance.MarsGponPMPon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

/**
 * 性能管理
 * MarsGponPMPonRepository
 * Created By qianhao
 * 2019/1/28 10:09
 */


public interface MarsGponPMPonRepository extends JpaRepository<MarsGponPMPon,Integer> {

    /**
     *
     * @param ip
     * @param shelfId
     * @param slotId
     * @param portId
     * @param startTime
     * @param endTime
     * @return
     */
    ArrayList<MarsGponPMPon> findByIpAndShelfIdAndSlotIdAndPortIndexAndStartTimeBetween(String ip, int shelfId, int slotId, int portId, long startTime, long endTime);
}
