package org.aoc.tmousaw.fs;

public class Inode {
  // It's not really an inode, but it's a subset of an inode.
  private final String name;
  private final long size;
  private final boolean isDirectory;

  public Inode(String name, long size, boolean isDirectory) {
    this.name = name;
    this.size = size;
    this.isDirectory = isDirectory;
  }

  public String getName() {
    return name;
  }

  public long getSize() {
    return size;
  }

  public boolean isDirectory() {
    return isDirectory;
  }
}
