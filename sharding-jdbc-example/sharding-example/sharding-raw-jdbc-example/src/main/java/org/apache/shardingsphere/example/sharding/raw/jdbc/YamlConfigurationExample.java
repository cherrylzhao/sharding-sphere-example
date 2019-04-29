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

package org.apache.shardingsphere.example.sharding.raw.jdbc;

import org.apache.shardingsphere.example.common.jdbc.repository.OrderItemRepositoryImpl;
import org.apache.shardingsphere.example.common.jdbc.repository.OrderRepositoryImpl;
import org.apache.shardingsphere.example.common.jdbc.service.CommonServiceImpl;
import org.apache.shardingsphere.example.common.service.CommonService;
import org.apache.shardingsphere.example.sharding.raw.jdbc.factory.YamlDataSourceFactory;
import org.apache.shardingsphere.example.type.ShardingType;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 * Please make sure master-slave data sync on MySQL is running correctly. Otherwise this example will query empty data from slave.
 */
public class YamlConfigurationExample {
    
//    private static ShardingType shardingType = ShardingType.SHARDING_DATABASES;
//    private static ShardingType shardingType = ShardingType.SHARDING_TABLES;
    private static ShardingType shardingType = ShardingType.SHARDING_DATABASES_AND_TABLES;
//    private static ShardingType shardingType = ShardingType.MASTER_SLAVE;
//    private static ShardingType shardingType = ShardingType.SHARDING_MASTER_SLAVE;
    
    public static void main(final String[] args) throws SQLException, IOException {
//        DataSource dataSource = YamlDataSourceFactory.newInstance(shardingType);
//        CommonService commonService = getCommonService(dataSource);
//        commonService.initEnvironment();
//        commonService.processSuccess();
//        commonService.cleanEnvironment();
        
//        try (Connection connection = dataSource.getConnection()) {
//            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO t_order (user_id, status) VALUES (?, ?),(?,?),(?,?)");
//            preparedStatement.setLong(1, 1);
//            preparedStatement.setString(2, "init");
//            preparedStatement.setLong(3, 2);
//            preparedStatement.setString(4, "init");
//            preparedStatement.setLong(5, 3);
//            preparedStatement.setString(6, "init");
//            preparedStatement.execute();
//        }
        
//        DataSource dataSource = YamlDataSourceFactory.newInstance(shardingType);
//        try (Connection connection = dataSource.getConnection()) {
//            PreparedStatement preparedStatement = connection.prepareStatement("delete from t_order where user_id = ? and order_id =?");
//            preparedStatement.setLong(1, 1);
//            preparedStatement.setLong(2, 1);
//            preparedStatement.execute();
//        }
    
//        try (Connection connection = dataSource.getConnection()) {
//            Statement statement = connection.createStatement();
//            statement.execute("delete from t_order where user_id = 1 and order_id =1");
//        }
    
//        try (Connection connection = dataSource.getConnection()) {
//            Statement statement = connection.createStatement();
//            statement.execute("update t_order where user_id = 1 and order_id =1");
//        }
    
//        DataSource dataSource = YamlDataSourceFactory.newInstance(shardingType);
//        try (Connection connection = dataSource.getConnection()) {
//            PreparedStatement preparedStatement = connection.prepareStatement("update t_order t set t.status='zjv3' where t.user_id =? and t.order_id =?");
//            preparedStatement.setObject(1, 1);
//            preparedStatement.setObject(2, 1);
//            preparedStatement.execute();
//        }
    
//        DataSource dataSource = YamlDataSourceFactory.newInstance(shardingType);
//        try (Connection connection = dataSource.getConnection()) {
//            PreparedStatement preparedStatement = connection.prepareStatement("update t_order set status=? where user_id =? and order_id =?");
//            preparedStatement.setObject(1, "zjv3");
//            preparedStatement.setObject(2, 1);
//            preparedStatement.setObject(3, 1);
//            preparedStatement.execute();
//        }
    
        DataSource dataSource = YamlDataSourceFactory.newInstance(shardingType);
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("update t_order set user_id=user_id+1 where user_id =? and order_id =?");
//            preparedStatement.setObject(1, "zjv3");
            preparedStatement.setObject(1, 1);
            preparedStatement.setObject(2, 1);
            preparedStatement.execute();
        }
    
//        DataSource dataSource = YamlDataSourceFactory.newInstance(ShardingType.SHARDING_DATABASES);
//        try (Connection connection = dataSource.getConnection()) {
//            Statement statement = connection.createStatement();
//            statement.execute("update account set realtimeremain=realtimeremain+1 where account_no=1000232002 and customer_no=1000666602");
//            statement.execute("update account set realtimeremain=realtimeremain-1 where account_no=1000232001 and customer_no=1000666601");
//        }
    }
    
    private static CommonService getCommonService(final DataSource dataSource) {
        return new CommonServiceImpl(new OrderRepositoryImpl(dataSource), new OrderItemRepositoryImpl(dataSource));
    }
}
