// Copyright (c) 2012, the Dart project authors.  Please see the AUTHORS file
// for details. All rights reserved. Use of this source code is governed by a
// BSD-style license that can be found in the LICENSE file.

/**
 * Tool for running co19 tests. Used when updating co19.
 *
 * Currently, this tool is merely a convenience around multiple
 * invocations of test.dart. Long term, we hope to evolve this into a
 * script that can automate most of the tasks necessary when updating
 * co19.
 *
 * Usage:
 * [: ./tools/testing/bin/$OS/dart tools/testing/dart/co19_test.dart :]
 */

library co19_test;

import "dart:io";

import "test_options.dart";
import "test_progress.dart";
import "test_runner.dart";
import "test_suite.dart";
import "utils.dart" show Path;
import "../../test.dart" as test_dart;

import "../../../tests/co19/test_config.dart";

const List<String> COMMON_ARGUMENTS =
    const <String>['--report', '--progress=diff', 'co19'];

const List<List<String>> COMMAND_LINES = const <List<String>>[
    const <String>['-mrelease,debug', '-rvm', '-cnone'],
    const <String>['-mrelease,debug', '-rvm', '-cnone', '--checked'],
    const <String>['-mrelease', '-rnone', '-cdartanalyzer'],
    const <String>['-mrelease', '-rnone', '-cdart2analyzer'],
    const <String>['-mrelease', '-rvm', '-cdart2dart', '--use-sdk'],
    const <String>['-mrelease', '-rd8', '-cdart2js', '--use-sdk'],
    const <String>['-mrelease', '-rd8,jsshell', '-cdart2js', '--use-sdk',
                   '--minified'],
    const <String>['-mrelease', '-rd8,jsshell', '-cdart2js', '--use-sdk',
                   '--checked'],
    const <String>['-mrelease', '-rdartium', '-cnone', '--use-sdk',
                   '--use_browser_controller', '--write-debug-log'],
];

void main() {
  Options options = new Options();
  File scriptFile = new File(options.script);
  Path scriptPath =
      new Path(scriptFile.fullPathSync())
      .directoryPath.directoryPath.directoryPath.append('test.dart');
  TestUtils.testScriptPath = scriptPath.toNativePath();
  var optionsParser = new TestOptionsParser();
  List<Map> configurations = <Map>[];
  for (var commandLine in COMMAND_LINES) {
    List arguments = <String>[];
    arguments.addAll(COMMON_ARGUMENTS);
    arguments.addAll(options.arguments);
    arguments.addAll(commandLine);
    configurations.addAll(optionsParser.parse(arguments));
  }

  if (configurations != null || configurations.length > 0) {
    test_dart.testConfigurations(configurations);
  }
}

