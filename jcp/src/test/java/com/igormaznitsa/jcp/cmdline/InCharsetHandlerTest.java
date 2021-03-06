/*
 * Copyright 2002-2019 Igor Maznitsa (http://www.igormaznitsa.com)
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.igormaznitsa.jcp.cmdline;

import com.igormaznitsa.jcp.context.PreprocessorContext;
import org.mockito.Mockito;

import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

public class InCharsetHandlerTest extends AbstractCommandLineHandlerTest {

  private static final InCharsetHandler HANDLER = new InCharsetHandler();

  @Override
  public void testThatTheHandlerInTheHandlerList() {
    assertHandlerInTheHandlerList(HANDLER);
  }

  @Override
  public void testExecution() throws Exception {
    final PreprocessorContext mock = prepareMockContext();
    assertFalse(HANDLER.processCommandLineKey("/o:UUU", mock));
    assertFalse(HANDLER.processCommandLineKey("/T:", mock));
    assertFalse(HANDLER.processCommandLineKey("/t", mock));
    assertTrue(HANDLER.processCommandLineKey("/t:" + StandardCharsets.UTF_16.name(), mock));
    verify(mock).setSourceEncoding(StandardCharsets.UTF_16);

    reset(mock);

    assertTrue(HANDLER.processCommandLineKey("/T:" + StandardCharsets.UTF_16LE, mock));
    Mockito.verify(mock).setSourceEncoding(StandardCharsets.UTF_16LE);
  }

  @Override
  public void testName() {
    assertEquals("/T:", HANDLER.getKeyName());
  }

  @Override
  public void testDescription() {
    assertDescription(HANDLER);
  }
}
