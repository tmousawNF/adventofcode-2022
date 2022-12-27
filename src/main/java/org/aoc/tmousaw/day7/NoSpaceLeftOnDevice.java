package org.aoc.tmousaw.day7;

import java.io.IOException;
import java.util.function.Consumer;
import org.aoc.tmousaw.common.AdventOfCodeSolver;
import org.aoc.tmousaw.day7.collections.Tree;

public class NoSpaceLeftOnDevice extends AdventOfCodeSolver {

  public static String CD_CMD = "cd";
  private final Tree<Inode> root;
  public static long sumOfDirectoriesAtMost100K = 0;
  public static long freeSpace = 70000000;
  public static long sizeOfDirectoryToDelete = 70000000;

  public NoSpaceLeftOnDevice() throws IOException {
    root = new Tree<>(new Inode("/", 0, true));
  }

  public static void main(String[] args) throws IOException {
    NoSpaceLeftOnDevice noSpaceLeftOnDevice = new NoSpaceLeftOnDevice();
    noSpaceLeftOnDevice.solve();
    noSpaceLeftOnDevice.printAnswers();
    System.out.println();
    noSpaceLeftOnDevice.printTimings();
  }


  @Override
  public void solve() {
    Tree<Inode> current = getRoot();
    for (String line : getLinesOfInput()) {
      if (line.trim().length() > 0) {
        if (line.trim().startsWith("$")) {
          // Command. Either 'cd' or 'ls'.
          String[] tokens = line.trim().substring(1).trim().split(" ");
          if (tokens.length == 0) {
            System.err.println("ERROR: Line contained no command. line=" + line);
            continue;
          }

          if (CD_CMD.equals(tokens[0])) {
            if (tokens.length == 1) {
              System.out.println("WARNING: 'cd' command with no directory. Assuming root.");
              current = getRoot();
            }

            String directory = tokens[1];
            if ("/".equals(directory)) {
              current = getRoot();
            } else if ("..".equals(directory)) {
              if (current.getParent() == null) {
                throw new RuntimeException("Attempted to traverse to parent of root.");
              }
              current = current.getParent();
            } else {
              boolean found = false;
              for (Tree<Inode> child : current.getChildren()) {
                if (child.getData().getName().equals(directory)) {
                  current = child;
                  found = true;
                  break;
                }
              }
              if (!found) {
                System.out.println("WARNING: Directory not found. dir=" + directory);
              }
            }
            // Else it is a non-cd command.
          }
        } else if (line.trim().startsWith("dir")) {
          String name = line.trim().substring(4);
          Inode data = new Inode(name, 0, true);
          current.addChild(data);
        } else {
          // Only other option is file.
          String[] tokens = line.trim().split(" ");

          if (tokens.length < 2) {
            System.err.println("ERROR: Assumed file, but less than 2 tokens found. line=" + line);
            continue;
          }

          long size = Long.parseLong(tokens[0]);
          String name = tokens[1];
          Inode data = new Inode(name, size, false);
          current.addChild(data);
        }
      }
    }

    Consumer<Tree<Inode>> directoriesAtMost100K = inode -> {
      if (inode != null) {
        if (inode.getData().isDirectory()) {
          long dirSize = calculateDirectorySize(inode);
          if (dirSize < 100000) {
            sumOfDirectoriesAtMost100K += dirSize;
          }
        }
      }
    };
    traverse(getRoot(), directoriesAtMost100K);
    addAnswer("Sum of all directories at most 100K in size", sumOfDirectoriesAtMost100K);

    freeSpace -= calculateDirectorySize(getRoot());
    Consumer<Tree<Inode>> smallestDirectoryToGet30MFreeSpace = inode -> {
      if (inode != null) {
        if (inode.getData().isDirectory()) {
          long dirSize = calculateDirectorySize(inode);
          if (dirSize >= (30000000 - freeSpace) && dirSize < sizeOfDirectoryToDelete) {
            sizeOfDirectoryToDelete = dirSize;
          }
        }
      }
    };
    traverse(getRoot(), smallestDirectoryToGet30MFreeSpace);
    addAnswer("Smallest directory to make at least 30M in free space", sizeOfDirectoryToDelete);
  }

  public static long calculateDirectorySize(Tree<Inode> directory) {
    long size = 0;
    for (Tree<Inode> childTree : directory.getChildren()) {
      Inode child = childTree.getData();
      if (!child.isDirectory()) {
        size += child.getSize();
      } else {
        // Recursively calculate the directory size.
        size += calculateDirectorySize(childTree);
      }
    }

    return size;
  }

  public Tree<Inode> getRoot() {
    return root;
  }

  public void traverse(Tree<Inode> inode, Consumer<Tree<Inode>> consumer) {
    consumer.accept(inode);
    if (inode != null) {
      for (Tree<Inode> child : inode.getChildren()) {
        traverse(child, consumer);
      }
    }
  }
}
