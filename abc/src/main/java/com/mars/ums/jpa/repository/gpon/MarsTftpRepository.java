package com.mars.ums.jpa.repository.gpon;

import com.mars.ums.jpa.bean.gpon.MarsTftp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarsTftpRepository extends JpaRepository<MarsTftp,Integer>{
    MarsTftp findByNeIp(String neIp);

    boolean deleteAllByNeIp(String neIp);

}
