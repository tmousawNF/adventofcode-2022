package org.aoc.tmousaw.fs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.NoSuchFileException;
import java.util.function.Consumer;
import org.aoc.tmousaw.fs.collections.Tree;

public class NoSpaceLeftOnDevice {

  public static String CD_CMD = "cd";
  private final Tree<Inode> root;
  public static long sumOfDirectoriesAtMost100K = 0;
  public static long freeSpace = 70000000;
  public static long sizeOfDirectoryToDelete = 70000000;

  public NoSpaceLeftOnDevice() {
    root = new Tree<>(new Inode("/", 0, true));
  }

  public static void main(String[] args) throws IOException {
    InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("input.txt");
    assert is != null;
    InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8);
    BufferedReader br = new BufferedReader(reader);

    NoSpaceLeftOnDevice device = new NoSpaceLeftOnDevice();
    Tree<Inode> current = device.getRoot();
    String line;
    while ((line = br.readLine()) != null) {
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
              current = device.getRoot();
            }

            String directory = tokens[1];
            if ("/".equals(directory)) {
              current = device.getRoot();
            } else if ("..".equals(directory)) {
              if (current.getParent() == null) {
                throw new NoSuchFileException("Attempted to traverse to parent of root.");
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
    traverse(device.getRoot(), directoriesAtMost100K);
    System.out.println("Sum of all directories at most 100K in size (Part 1):" + sumOfDirectoriesAtMost100K);
    freeSpace -= calculateDirectorySize(device.getRoot());
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
    traverse(device.getRoot(), smallestDirectoryToGet30MFreeSpace);
    System.out.println("Smallest directory to make at least 30M in free space (Part 2):" + sizeOfDirectoryToDelete);
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

  public static void traverse(Tree<Inode> inode, Consumer<Tree<Inode>> consumer) {
    consumer.accept(inode);
    if (inode != null) {
      for (Tree<Inode> child : inode.getChildren()) {
        traverse(child, consumer);
      }
    }
  }
}
