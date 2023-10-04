package com.wms;

import com.wms.entity.Power;
import com.wms.entity.User;
import com.wms.entity.UserPower;
import com.wms.mapper.UserMapper;
import com.wms.mapper.UserPowerMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootTest
class WarehouseManagementApplicationTests {

    @Test
    void contextLoads() throws IOException {
    }


}
