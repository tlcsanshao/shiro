package com.mars.ums.jpa.repository.common.alarm;

import com.mars.ums.jpa.bean.common.alarm.MarsActiveAlarm;
import com.mars.ums.jpa.bean.common.alarm.MarsHistoryAlarm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 历史告警持久化Jpa操作接口
 * Created  by sunam
 * 2019/1/21 15:59
 */
public interface MarsHistoryAlarmRepository extends JpaRepository<MarsHistoryAlarm,Long>{
    /**
     * 查询网元ip下的所有当前告警
     * @param ip
     * @return
     */
    List<MarsHistoryAlarm> findByIp(String ip);

    @Query(value = "SELECT * FROM activealarm ORDER BY severity DESC,createtime DESC",nativeQuery = true)
    List<MarsHistoryAlarm> findByOrderBy();




}
