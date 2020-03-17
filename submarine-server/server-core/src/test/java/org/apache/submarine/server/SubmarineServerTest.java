/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.submarine.server;

import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.submarine.server.rest.JobManagerRestApiTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertFalse;

public class SubmarineServerTest extends AbstractSubmarineServerTest {
  private static final Logger LOG = LoggerFactory.getLogger(JobManagerRestApiTest.class);

  @BeforeClass
  public static void init() throws Exception {
    AbstractSubmarineServerTest.startUp(SubmarineServerTest.class.getSimpleName());
  }

  @AfterClass
  public static void destroy() throws Exception {
    AbstractSubmarineServerTest.shutDown();
  }

  @Test
  // SUBMARINE-422. Fix refreshing page returns 404 error
  public void testRefreshingURL() throws IOException {
    ArrayList<String> arrUrls = new ArrayList();
    arrUrls.add("/user/login");
    arrUrls.add("/workbench/manager/user");

    for (int i = 0; i < arrUrls.size(); i++) {
      GetMethod response = httpGet(arrUrls.get(i));
      LOG.info(response.toString());

      String requestBody = response.getResponseBodyAsString();
      LOG.info(requestBody);
      assertFalse(requestBody.contains("Error 404 Not Found"));
    }
  }
}
