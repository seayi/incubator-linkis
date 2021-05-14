/*
 * Copyright 2019 WeBank
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.webank.wedatasphere.linkis.engineconn.acessible.executor.service

import java.util

import com.webank.wedatasphere.linkis.engineconn.executor.service.ManagerService
import com.webank.wedatasphere.linkis.governance.common.conf.GovernanceCommonConf
import com.webank.wedatasphere.linkis.manager.common.entity.enumeration.NodeStatus
import com.webank.wedatasphere.linkis.manager.common.protocol.engine.EngineConnReleaseRequest
import com.webank.wedatasphere.linkis.manager.common.protocol.label.LabelReportRequest
import com.webank.wedatasphere.linkis.manager.common.protocol.node.{NodeHeartbeatMsg, ResponseNodeStatus}
import com.webank.wedatasphere.linkis.manager.common.protocol.resource.ResourceUsedProtocol
import com.webank.wedatasphere.linkis.manager.label.entity.Label
import com.webank.wedatasphere.linkis.rpc.Sender

class DefaultManagerService extends ManagerService {


  protected def getManagerSender: Sender = {
    Sender.getSender(GovernanceCommonConf.MANAGER_SPRING_NAME.getValue)
  }

  protected def getEngineConnManagerSender: Sender = {
    Sender.getSender(GovernanceCommonConf.ENGINE_CONN_MANAGER_SPRING_NAME.getValue)
  }


  override def labelReport(labels: util.List[Label[_]]): Unit = {
    val labelReportRequest = LabelReportRequest(labels, Sender.getThisServiceInstance)
    getManagerSender.send(labelReportRequest)
  }


  override def statusReport(status: NodeStatus): Unit = {
    val responseNodeStatus = new ResponseNodeStatus
    responseNodeStatus.setNodeStatus(status)
    getManagerSender.send(responseNodeStatus)
  }

  override def requestReleaseEngineConn(engineConnReleaseRequest: EngineConnReleaseRequest): Unit = {
    getManagerSender.send(engineConnReleaseRequest)
  }

  override def heartbeatReport(nodeHeartbeatMsg: NodeHeartbeatMsg): Unit = {
    getManagerSender.send(nodeHeartbeatMsg)
  }

  override def reportUsedResource(resourceUsedProtocol: ResourceUsedProtocol): Unit = {
    getManagerSender.send(resourceUsedProtocol)
  }

}
