package com.mars.ums.jpa.repository.common.log;

import com.mars.ums.jpa.bean.common.log.SysLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 系统日志JPA操作接口
 * Created  by sunam
 * 2019/1/16 14:29
 */
public interface SysLogRepository extends JpaRepository<SysLog,Long> {
}
