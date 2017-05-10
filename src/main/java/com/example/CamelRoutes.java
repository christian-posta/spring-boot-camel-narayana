/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;


/**
 * Created by ceposta 
 * <a href="http://christianposta.com/blog>http://christianposta.com/blog</a>.
 */
@Component
public class CamelRoutes extends RouteBuilder{

    public void configure() throws Exception {
        from("jms:inbound?transacted=true")
                .transacted("PROPAGATION_REQUIRED")
                .log("Processing message: ${body}")
                .setHeader("message", body())
                .to("sql:insert into audit_log (message) values (:#message)")
                .to("jms:outbound") // this send is transacted, so the message should not be sent
                .to("mock:out");
    }

}
