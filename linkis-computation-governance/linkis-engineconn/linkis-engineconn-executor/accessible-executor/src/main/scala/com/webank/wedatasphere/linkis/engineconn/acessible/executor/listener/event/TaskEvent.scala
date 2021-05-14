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

package com.webank.wedatasphere.linkis.engineconn.acessible.executor.listener.event

import com.webank.wedatasphere.linkis.engineconn.executor.listener.event.EngineConnSyncEvent
import com.webank.wedatasphere.linkis.governance.common.entity.ExecutionNodeStatus
import com.webank.wedatasphere.linkis.protocol.engine.JobProgressInfo

trait TaskEvent extends EngineConnSyncEvent {

}

case class TaskProgressUpdateEvent(taskId: String, progress: Float, progressInfo: Array[JobProgressInfo]) extends TaskEvent

case class TaskLogUpdateEvent(taskId: String, log: String) extends TaskEvent

case class TaskStatusChangedEvent(taskId: String, fromStatus: ExecutionNodeStatus, toStatus: ExecutionNodeStatus) extends TaskEvent

case class TaskResultCreateEvent(taskId: String, resStr: String, alias: String) extends TaskEvent

case class TaskResultSizeCreatedEvent(taskId: String, resultSize: Int) extends TaskEvent