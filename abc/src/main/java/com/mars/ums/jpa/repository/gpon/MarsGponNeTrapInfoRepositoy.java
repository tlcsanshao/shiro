package com.mars.ums.jpa.repository.gpon;

import com.mars.ums.jpa.bean.gpon.MarsGponNeTrapInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created  by sunam
 * 2018/11/23 11:54
 */
public interface MarsGponNeTrapInfoRepositoy extends JpaRepository<MarsGponNeTrapInfo,Integer>{
    MarsGponNeTrapInfo findByTrapName(String trapName);
}
