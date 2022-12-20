package org.aoc.tmousaw.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public abstract class AdventOfCodeSolver {

  private final List<String> linesOfInput;
  private final List<Answer> answers;
  private final long startTime; // The time at which all lines were read in.

  public AdventOfCodeSolver() throws IOException {
    this("input.txt");
  }

  public AdventOfCodeSolver(String resourceFileName) throws IOException {
    linesOfInput = new ArrayList<>();
    answers = new ArrayList<>();

    String packageName = this.getClass().getPackageName();
    InputStream is = Thread.currentThread().getContextClassLoader()
        .getResourceAsStream(packageName.substring(packageName.lastIndexOf(".") + 1) + "/" + resourceFileName);
    assert is != null;
    InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8);
    BufferedReader br = new BufferedReader(reader);

    String line;

    while ((line = br.readLine()) != null) {
      linesOfInput.add(line);
    }

    startTime = System.currentTimeMillis();
  }

  public List<String> getLinesOfInput() {
    return linesOfInput;
  }

  public List<Answer> getAnswers() {
    return answers;
  }

  public void printAnswers() {
    for (int i = 0; i < answers.size(); i++) {
      Answer answer = answers.get(i);
      StringBuilder sb = new StringBuilder();
      if (StringUtils.isNotBlank(answer.getMessage())) {
        sb.append(answer.getMessage()).append(" ");
      }

      sb.append(String.format("(Part %d): %s", i + 1, answer.getValue()));
      System.out.println(sb);
    }
  }

  public void printTimings() {
    long timeToSubtract = startTime;
    for (int i = 0; i < answers.size(); i++) {
      System.out.printf("Time for part %d: %.3f seconds%n", i + 1, (float) (answers.get(i).getTime() - timeToSubtract) / 1000.0);
      timeToSubtract = answers.get(i).getTime();
    }
    System.out.printf("Total time: %.3f seconds%n", (float) (answers.get(answers.size() - 1).getTime() - startTime) / 1000.0);
  }

  public void addAnswer(String message, int value) {
    answers.add(new Answer(message, String.valueOf(value)));
  }

  public abstract void solve();
}
