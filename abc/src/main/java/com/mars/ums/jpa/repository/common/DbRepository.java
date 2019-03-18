package com.mars.ums.jpa.repository.common;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface DbRepository {



    @Transactional
    @Modifying
    @Query(value="truncate table tftp")
    public void truncateTable();




}
