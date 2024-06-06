package com.learning;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.SystemPropertyKeyConst;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.common.utils.ThreadUtils;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@SpringBootTest
public class NamingTest {

    @Test
    public void testServiceList() throws Exception {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, "127.0.0.1:8848");
        properties.put(PropertyKeyConst.USERNAME, "nacos");
        properties.put(PropertyKeyConst.PASSWORD, "nacos");

        Instance instance = new Instance();
        instance.setIp("1.1.1.1");
        instance.setPort(8080);
        instance.setWeight(2);
        Map<String, String> map = new HashMap<>();
        map.put("netType", "external");
        map.put("version", "2.0");
        instance.setMetadata(map);

        NamingService namingService = NacosFactory.createNamingService(properties);
        namingService.registerInstance("nacos.test.1", instance);

        ThreadUtils.sleep(5000L);

        List<Instance> list = namingService.getAllInstances("nacos.test.1");

        System.out.println(list);

        ThreadUtils.sleep(30000L);

    }
}
