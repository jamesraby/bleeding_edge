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
package com.google.dart.server;

import com.google.dart.engine.error.ErrorCode;

/**
 * The interface {@code AnalysisError} defines the behavior of objects representing an analysis
 * error.
 * 
 * @coverage dart.server
 */
public interface AnalysisError {
  /**
   * An empty array of errors used when no errors are expected.
   */
  public static final AnalysisError[] NO_ERRORS = new AnalysisError[0];

  /**
   * Return the correction to be displayed for this error, or {@code null} if there is no correction
   * information for this error. The correction should indicate how the user can fix the error.
   * 
   * @return the template used to create the correction to be displayed for this error
   */
  String getCorrection();

  /**
   * Return the error code associated with the error.
   * 
   * @return the error code associated with the error
   */
  ErrorCode getErrorCode();

  /**
   * Return the file in which the error occurred
   * 
   * @return the file in which the error occurred
   */
  String getFile();

  /**
   * Return the number of characters from the offset to the end of the source which encompasses the
   * compilation error.
   * 
   * @return the length of the error location
   */
  int getLength();

  /**
   * Return the message to be displayed for this error. The message should indicate what is wrong
   * and why it is wrong.
   * 
   * @return the message to be displayed for this error
   */
  String getMessage();

  /**
   * Return the character offset from the beginning of the source (zero based) where the error
   * occurred.
   * 
   * @return the offset to the start of the error location
   */
  int getOffset();
}
