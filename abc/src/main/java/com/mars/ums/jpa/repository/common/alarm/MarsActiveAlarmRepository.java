package com.mars.ums.jpa.repository.common.alarm;

import com.mars.ums.jpa.bean.common.alarm.MarsActiveAlarm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 当前告警持久化Jpa操作接口
 * Created  by sunam
 * 2019/1/21 15:59
 */
public interface MarsActiveAlarmRepository extends JpaRepository<MarsActiveAlarm,Long>{
    /**
     * 查询网元ip下的所有当前告警
     * @param ip
     * @return
     */
    List<MarsActiveAlarm> findByIp(String ip);

    List<MarsActiveAlarm> findBySeverity(int severity);

    @Query(value = "select severity from activealarm where ip = ?1",nativeQuery = true)
    List<Integer> findSeverityByIp(String ip);

    @Query(value = "SELECT * FROM activealarm ORDER BY acknowledgestatus Asc,  severity DESC,createtime DESC",nativeQuery = true)
    List<MarsActiveAlarm> findByOrderBy();

    @Query(value = "select MAX(severity) from activealarm where ip = ?1",nativeQuery = true)
    Integer findMAXSeverityByIp(String ip);


}
