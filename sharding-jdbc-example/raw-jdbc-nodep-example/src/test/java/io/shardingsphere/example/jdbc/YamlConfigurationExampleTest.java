/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.shardingsphere.example.jdbc;

import io.shardingsphere.example.config.ExampleConfiguration;
import io.shardingsphere.example.jdbc.nodep.YamlConfigurationExample;
import io.shardingsphere.example.jdbc.nodep.config.ShardingTablesConfigurationPrecise;
import io.shardingsphere.example.repository.api.service.CommonService;
import io.shardingsphere.example.repository.jdbc.repository.JDBCOrderItemRepositoryImpl;
import io.shardingsphere.example.repository.jdbc.repository.JDBCOrderRepositoryImpl;
import io.shardingsphere.example.repository.jdbc.service.RawPojoService;
import io.shardingsphere.shardingjdbc.api.yaml.YamlShardingDataSourceFactory;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class YamlConfigurationExampleTest {
    
    @Before
    public void setUp() throws SQLException {
        YamlShardingDataSourceFactory.createDataSource(new File(YamlConfigurationExample.class.getResource(result).getFile())()
        ExampleConfiguration exampleConfig = new ShardingTablesConfigurationPrecise();
        DataSource dataSource = exampleConfig.getDataSource();
        CommonService commonService = new RawPojoService(new JDBCOrderRepositoryImpl(dataSource), new JDBCOrderItemRepositoryImpl(dataSource));
        commonService.initEnvironment();
    }
    
    @Test
    public void assertTableShardingMultiThread() throws SQLException, InterruptedException {
        ExampleConfiguration exampleConfig = new ShardingTablesConfigurationPrecise();
        final DataSource dataSource = exampleConfig.getDataSource();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        final CountDownLatch latch = new CountDownLatch(100);
        final AtomicLong orderId = new AtomicLong(0);
        for (int i = 0; i < 100; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        executeQuery(dataSource, orderId.incrementAndGet());
                        latch.countDown();
                        
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        latch.await();
    }
    
    private void executeQuery(final DataSource dataSource, final Long orderId) throws SQLException {
        String sql = "select * from t_order where order_id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, orderId);
            preparedStatement.execute();
        }
    }
}
