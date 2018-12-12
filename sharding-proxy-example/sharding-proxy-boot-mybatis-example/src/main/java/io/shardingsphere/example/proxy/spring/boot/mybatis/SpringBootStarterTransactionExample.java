/*
 * Copyright 2016-2018 shardingsphere.io.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package io.shardingsphere.example.proxy.spring.boot.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@ComponentScans({
    @ComponentScan("io.shardingsphere.example.repository.mybatis"),
    @ComponentScan("io.shardingsphere.transaction.aspect")
})
@MapperScan(basePackages = "io.shardingsphere.example.repository.mybatis.repository")
@SpringBootApplication(exclude = JtaAutoConfiguration.class)
public class SpringBootStarterTransactionExample {
    
    public static void main(final String[] args) {
        SpringApplication.run(SpringBootStarterTransactionExample.class, args);
    }
}
