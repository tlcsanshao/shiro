package com.mars.ums.jpa.repository.common.alarm;

import com.mars.ums.jpa.bean.common.alarm.MarsAlarmProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 告警模板持久化jpa操作
 * Created  by sunam
 * 2019/1/22 11:06
 */
public interface MarsAlarmProfileRepository extends JpaRepository<MarsAlarmProfile,Long> {
    /**
     * 通过告警code查询到对应的告警模板
     * @param alarmCode
     * @return
     */
    MarsAlarmProfile findByAlarmCode(int alarmCode);

    /**
     * 通过divicecode查询
     * @param deviceCodes
     * @return
     */
    List<MarsAlarmProfile> findByDeviceCodeIn(int[] deviceCodes);

    /**
     * 根据告警code查询到对应的告警类型
     * @param alarmCode
     * @return
     */
    @Query(value = "select alarmtype from alarmprofile where alarmcode = ?1",nativeQuery = true)
    int findAlarmTypeByAlarmCode(int alarmCode);

    /**
     * 通过告警code去查询到对应的告警产生的原因
     * @param alarmCode
     * @return
     */
    @Query(value = "select probablecause from alarmprofile where alarmcode = ?1",nativeQuery = true)
    String findProbableCauseByAlarmCode(int alarmCode);

    /**
     * 通过告警code去查询到对应的告警名称
     * @param alarmCode
     * @return
     */
    @Query(value = "select alarmname from alarmprofile where alarmcode = ?1",nativeQuery = true)
    String findAlarmNameByAlarmCode(int alarmCode);

}
