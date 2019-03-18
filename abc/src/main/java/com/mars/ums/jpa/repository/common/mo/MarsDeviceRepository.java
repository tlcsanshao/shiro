package com.mars.ums.jpa.repository.common.mo;

import com.mars.ums.jpa.bean.common.mo.MarsDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MarsDeviceRepository extends JpaRepository<MarsDevice,Long>{

    List<MarsDevice>  findByParentName(String areaName);

    MarsDevice findByIpaddress(String ip);

    MarsDevice  findByMoid(long moid);

    int countByStatus(int status);
}
