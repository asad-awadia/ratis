/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.ratis.datastream;

import org.apache.ratis.conf.Parameters;
import org.apache.ratis.netty.NettyConfigKeys;
import org.apache.ratis.netty.NettyUtils;
import org.apache.ratis.security.SecurityTestUtils;
import org.apache.ratis.security.TlsConf;
import org.apache.ratis.util.JavaUtils;
import org.apache.ratis.util.Slf4jUtils;
import org.junit.jupiter.api.Disabled;
import org.slf4j.event.Level;

import java.util.function.Supplier;

public class TestDataStreamSslWithRpcTypeGrpcAndDataStreamTypeNetty
    extends DataStreamAsyncClusterTests<MiniRaftClusterWithRpcTypeGrpcAndDataStreamTypeNetty> {
  {
    Slf4jUtils.setLogLevel(NettyUtils.LOG, Level.DEBUG);
  }

  Parameters newParameters() {
    final Parameters parameters1 = new Parameters();
    final TlsConf serverTlsConfig = SecurityTestUtils.newServerTlsConfig(true);
    NettyConfigKeys.DataStream.Server.setTlsConf(parameters1, serverTlsConfig);
    final TlsConf clientTlsConfig = SecurityTestUtils.newClientTlsConfig(true);
    NettyConfigKeys.DataStream.Client.setTlsConf(parameters1, clientTlsConfig);
    return parameters1;
  }

  private final Supplier<Parameters> parameters = JavaUtils.memoize(this::newParameters);

  public Parameters getParameters() {
    return parameters.get();
  }

  @Override
  public MiniRaftClusterWithRpcTypeGrpcAndDataStreamTypeNetty.Factory getFactory() {
    return new MiniRaftClusterWithRpcTypeGrpcAndDataStreamTypeNetty.Factory(getParameters());
  }

  @Disabled
  @Override
  public void testStreamWrites() {
  }

  @Disabled
  @Override
  public void testStreamWithInvalidRoutingTable() {
  }

  @Disabled
  @Override
  public void testMultipleStreamsMultipleServers() {
  }

  @Disabled
  @Override
  public void testMultipleStreamsMultipleServersStepDownLeader() {
  }
}
