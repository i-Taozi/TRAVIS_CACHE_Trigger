/**
 * Copyright (C) 2012-2017 Last.fm & The "mogilefs-moji" committers
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
package fm.last.moji.impl;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import fm.last.moji.tracker.Tracker;
import fm.last.moji.tracker.UnknownKeyException;

class ExistsCommand implements MojiCommand {

  final String key;
  final String domain;
  private boolean exists;

  ExistsCommand(String key, String domain) {
    this.key = key;
    this.domain = domain;
  }

  @Override
  public void executeWithTracker(Tracker tracker) throws IOException {
    List<URL> paths = null;
    try {
      paths = tracker.getPaths(key, domain);
      exists = !paths.isEmpty();
    } catch (UnknownKeyException e) {
    }
  }

  boolean getExists() {
    return exists;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("ExistsCommand [domain=");
    builder.append(domain);
    builder.append(", key=");
    builder.append(key);
    builder.append(", exists=");
    builder.append(exists);
    builder.append("]");
    return builder.toString();
  }

}
