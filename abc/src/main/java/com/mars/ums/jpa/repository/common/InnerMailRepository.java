package com.mars.ums.jpa.repository.common;

import com.mars.ums.jpa.bean.common.InnerMail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * 邮箱服务对应持久化jpa操作
 * Created  by sunam
 * 2019/2/15 10:41
 */
@Component
public interface InnerMailRepository extends JpaRepository<InnerMail,Integer>{
    InnerMail findById(int id);
}
