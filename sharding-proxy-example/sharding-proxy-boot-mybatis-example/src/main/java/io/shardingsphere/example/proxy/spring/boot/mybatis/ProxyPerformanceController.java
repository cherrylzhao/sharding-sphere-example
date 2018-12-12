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

import io.shardingsphere.core.exception.ShardingException;
import io.shardingsphere.example.repository.mybatis.service.SpringPojoTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/proxy")
public final class ProxyPerformanceController {
    
    @Autowired
    @Qualifier("proxyTransactionService")
    private SpringPojoTransactionService springPojoTransactionService;
    
    @RequestMapping(value = "/init")
    public String init() {
        springPojoTransactionService.initEnvironment();
        return "ok";
    }
    
    @RequestMapping(value = "/insert")
    public String insert() {
        springPojoTransactionService.processSuccess(false);
        return "ok";
    }
    
    @RequestMapping(value = "/commit/local")
    public String localCommit() {
        springPojoTransactionService.processSuccessWithLocal();
        return "ok";
    }
    
    @RequestMapping(value = "/rollback/local")
    public String localRollback() {
        try {
            springPojoTransactionService.processFailureWithLocal();
        } catch (final ShardingException ignore) {}
        return "ok";
    }
    
    @RequestMapping(value = "/commit/xa")
    public String xaCommit() {
        springPojoTransactionService.processSuccessWithXA();
        return "ok";
    }
    
    @RequestMapping(value = "/rollback/xa")
    public String xaRollback() {
        try {
            springPojoTransactionService.processFailureWithXA();
        } catch (final ShardingException ignore) {}
        return "ok";
    }
}
