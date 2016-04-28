/* 
 * jBrowserDriver (TM)
 * Copyright (C) 2014-2016 Machine Publishers, LLC
 * 
 * Sales and support: ops@machinepublishers.com
 * Updates: https://github.com/MachinePublishers/jBrowserDriver
 *
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
 */
package com.machinepublishers.jbrowserdriver;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

class PortGroup implements Serializable {
  static final int SIZE = 3;
  final long child;
  final long parent;
  final long parentAlt;
  private final String id;
  private final int hashCode;

  PortGroup(long child, long parent, long parentAlt) {
    this.child = child;
    this.parent = parent;
    this.parentAlt = parentAlt;
    id = new StringBuilder().append(child).append("/").append(parent).append("/").append(parentAlt).toString();
    hashCode = id.hashCode();
  }

  @Override
  public int hashCode() {
    return hashCode;
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof PortGroup && ((PortGroup) obj).id.equals(id);
  }

  boolean conflicts(PortGroup other) {
    Set<Long> allPorts = new HashSet<Long>();
    if (other != null) {
      allPorts.add(other.child);
      allPorts.add(other.parent);
      allPorts.add(other.parentAlt);
      allPorts.add(child);
      allPorts.add(parent);
      allPorts.add(parentAlt);
    }
    return allPorts.size() != SIZE * 2;
  }
}
