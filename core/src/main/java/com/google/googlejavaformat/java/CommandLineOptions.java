/*
 * Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.googlejavaformat.java;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableRangeSet;
import com.google.common.collect.RangeSet;
import com.google.common.collect.TreeRangeSet;
import java.util.Optional;

/**
 * Command line options for google-java-format.
 *
 * <p>google-java-format doesn't depend on AutoValue, to allow AutoValue to depend on google-java-format.
 */
final class CommandLineOptions {

  private final ImmutableList<String> files;
  private final boolean inPlace;
  private final ImmutableRangeSet<Integer> lines;
  private final ImmutableList<Integer> offsets;
  private final ImmutableList<Integer> lengths;
  private final boolean aosp;
  private final int width ;
  private final boolean version;
  private final boolean help;
  private final boolean stdin;
  private final boolean fixImportsOnly;
  private final boolean sortImports;
  private final boolean removeUnusedImports;
  private final boolean dryRun;
  private final boolean setExitIfChanged;
  private final Optional<String> assumeFilename;
  private final boolean reflowLongStrings;
  private final boolean formatJavadoc;

  CommandLineOptions(
      final ImmutableList<String> files,
      final boolean inPlace,
      final ImmutableRangeSet<Integer> lines,
      final ImmutableList<Integer> offsets,
      final ImmutableList<Integer> lengths,
      final boolean aosp,
      final int width,
      final boolean version,
      final boolean help,
      final boolean stdin,
      final boolean fixImportsOnly,
      final boolean sortImports,
      final boolean removeUnusedImports,
      final boolean dryRun,
      final boolean setExitIfChanged,
      final Optional<String> assumeFilename,
      final boolean reflowLongStrings,
      final boolean formatJavadoc) {
    this.files = files;
    this.inPlace = inPlace;
    this.lines = lines;
    this.offsets = offsets;
    this.lengths = lengths;
    this.aosp = aosp;
    this.width = width;
    this.version = version;
    this.help = help;
    this.stdin = stdin;
    this.fixImportsOnly = fixImportsOnly;
    this.sortImports = sortImports;
    this.removeUnusedImports = removeUnusedImports;
    this.dryRun = dryRun;
    this.setExitIfChanged = setExitIfChanged;
    this.assumeFilename = assumeFilename;
    this.reflowLongStrings = reflowLongStrings;
    this.formatJavadoc = formatJavadoc;
  }

  /** The files to format. */
  ImmutableList<String> files() {
    return files;
  }

  /** Format files in place. */
  boolean inPlace() {
    return inPlace;
  }

  /** Line ranges to format. */
  ImmutableRangeSet<Integer> lines() {
    return lines;
  }

  /** Character offsets for partial formatting, paired with {@code lengths}. */
  ImmutableList<Integer> offsets() {
    return offsets;
  }

  /** Partial formatting region lengths, paired with {@code offsets}. */
  ImmutableList<Integer> lengths() {
    return lengths;
  }

  /** Use AOSP style instead of Google Style (4-space indentation). */
  boolean aosp() {
    return aosp;
  }

  /** Maximal line length. */
  int width() {
    return width;
  }

  /** Print the version. */
  boolean version() {
    return version;
  }

  /** Print usage information. */
  boolean help() {
    return help;
  }

  /** Format input from stdin. */
  boolean stdin() {
    return stdin;
  }

  /** Fix imports, but do no formatting. */
  boolean fixImportsOnly() {
    return fixImportsOnly;
  }

  /** Sort imports. */
  boolean sortImports() {
    return sortImports;
  }

  /** Remove unused imports. */
  boolean removeUnusedImports() {
    return removeUnusedImports;
  }

  /** Print the paths of the files whose contents would change if the formatter were run normally. */
  boolean dryRun() {
    return dryRun;
  }

  /** Return exit code 1 if there are any formatting changes. */
  boolean setExitIfChanged() {
    return setExitIfChanged;
  }

  /** Return the name to use for diagnostics when formatting standard input. */
  Optional<String> assumeFilename() {
    return assumeFilename;
  }

  boolean reflowLongStrings() {
    return reflowLongStrings;
  }

  /** Returns true if partial formatting was selected. */
  boolean isSelection() {
    return !lines().isEmpty() || !offsets().isEmpty() || !lengths().isEmpty();
  }

  boolean formatJavadoc() {
    return formatJavadoc;
  }

  static Builder builder() {
    return new Builder();
  }

  static class Builder {

    private final ImmutableList.Builder<String> files = ImmutableList.builder();
    private final RangeSet<Integer> lines = TreeRangeSet.create();
    private final ImmutableList.Builder<Integer> offsets = ImmutableList.builder();
    private final ImmutableList.Builder<Integer> lengths = ImmutableList.builder();
    private boolean inPlace = false;
    private boolean aosp = false;
    private int width = 100;
    private boolean version = false;
    private boolean help = false;
    private boolean stdin = false;
    private boolean fixImportsOnly = false;
    private boolean sortImports = true;
    private boolean removeUnusedImports = true;
    private boolean dryRun = false;
    private boolean setExitIfChanged = false;
    private Optional<String> assumeFilename = Optional.empty();
    private boolean reflowLongStrings = true;
    private boolean formatJavadoc = true;

    ImmutableList.Builder<String> filesBuilder() {
      return files;
    }

    Builder inPlace(final boolean inPlace) {
      this.inPlace = inPlace;
      return this;
    }

    RangeSet<Integer> linesBuilder() {
      return lines;
    }

    Builder addOffset(final Integer offset) {
      offsets.add(offset);
      return this;
    }

    Builder addLength(final Integer length) {
      lengths.add(length);
      return this;
    }

    Builder aosp(final boolean aosp) {
      this.aosp = aosp;
      return this;
    }

    Builder width(final int width) {
      this.width = width;
      return this;
    }

    Builder version(final boolean version) {
      this.version = version;
      return this;
    }

    Builder help(final boolean help) {
      this.help = help;
      return this;
    }

    Builder stdin(final boolean stdin) {
      this.stdin = stdin;
      return this;
    }

    Builder fixImportsOnly(final boolean fixImportsOnly) {
      this.fixImportsOnly = fixImportsOnly;
      return this;
    }

    Builder sortImports(final boolean sortImports) {
      this.sortImports = sortImports;
      return this;
    }

    Builder removeUnusedImports(final boolean removeUnusedImports) {
      this.removeUnusedImports = removeUnusedImports;
      return this;
    }

    Builder dryRun(final boolean dryRun) {
      this.dryRun = dryRun;
      return this;
    }

    Builder setExitIfChanged(final boolean setExitIfChanged) {
      this.setExitIfChanged = setExitIfChanged;
      return this;
    }

    Builder assumeFilename(final String assumeFilename) {
      this.assumeFilename = Optional.of(assumeFilename);
      return this;
    }

    Builder reflowLongStrings(final boolean reflowLongStrings) {
      this.reflowLongStrings = reflowLongStrings;
      return this;
    }

    Builder formatJavadoc(final boolean formatJavadoc) {
      this.formatJavadoc = formatJavadoc;
      return this;
    }

    CommandLineOptions build() {
      return new CommandLineOptions(
          files.build(),
          inPlace,
          ImmutableRangeSet.copyOf(lines),
          offsets.build(),
          lengths.build(),
          aosp,
          width,
          version,
          help,
          stdin,
          fixImportsOnly,
          sortImports,
          removeUnusedImports,
          dryRun,
          setExitIfChanged,
          assumeFilename,
          reflowLongStrings,
          formatJavadoc);
    }
  }
}
