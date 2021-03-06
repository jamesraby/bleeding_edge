/*
 * Copyright (c) 2014, the Dart project authors.
 * 
 * Licensed under the Eclipse Public License v1.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.dart.server.internal.remote;

import com.google.common.base.Charsets;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * An {@link InputStream} based implementation of {@link ResponseStream}. Each line must contain
 * exactly one complete JSON object.
 * 
 * @coverage dart.server.remote
 */
public class ByteResponseStream implements ResponseStream {
  /**
   * The {@link BufferedReader} to read JSON strings from.
   */
  private final BufferedReader reader;

  /**
   * Initializes a newly created response stream.
   * 
   * @param stream the byte stream to read JSON strings from
   */
  public ByteResponseStream(InputStream stream) {
    reader = new BufferedReader(new InputStreamReader(stream, Charsets.UTF_8));
  }

  @Override
  public void lastRequestProcessed() {
  }

  @Override
  public JsonObject take() throws Exception {
    while (true) {
      String line = reader.readLine();
      if (line == null) {
        return null;
      }
//      if (line.contains("\"event\":\"server.status\"")) {
//        System.out.println(System.currentTimeMillis() + " <= " + line);
//      }
//      if (!line.startsWith("{")) {
//        continue;
//      }
      return (JsonObject) new JsonParser().parse(line);
    }
  }
}
