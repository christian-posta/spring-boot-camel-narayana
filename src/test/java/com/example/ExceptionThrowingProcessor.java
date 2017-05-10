/*
 * Copyright (C) Scott Cranton, Jakub Korab, and Christian Posta
 * https://github.com/CamelCookbook
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Processor that throws an exception whenever called.
 */
public class ExceptionThrowingProcessor implements Processor {
    private final String message;

    public ExceptionThrowingProcessor() {
        this("boom!");
    }

    public ExceptionThrowingProcessor(String message) {
        this.message = message;
    }

    public void process(Exchange exchange) throws Exception {
        throw new IllegalStateException(message);
    }
}
