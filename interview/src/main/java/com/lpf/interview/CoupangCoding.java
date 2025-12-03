package com.lpf.interview;

import java.util.*;

/**
 * Pengfei Su (2025年11月24日，14:20)
 * Design an in-memory file system to simulate the following functions:
 * 1、ls: Given a path in string format.
 * * 1.1 If it is a file path, return a list that only contains this file's name.
 * If it is a directory path, return the list of file and directory names in this directory.
 * Your output (file and directory names together) should in lexicographic order.
 * 2、mkdir: Given a directory path that does not exist, you should make a new directory according to the path.
 * If the middle directories in the path don't exist either, you should create them as well.
 * This function has void return type.
 * addContentToFile:
 * Given a file path and file content in string format.
 * If the file doesn't exist, you need to create that file containing given content.
 * If the file already exists, you need to append given content to original content.
 * This function has void return type.
 * readContentFromFile: Given a file path, return its content in string format.
 */
public class CoupangCoding {

    public static void main(String[] args) {

        MemoryFileSystem fileSystem = new MemoryFileSystem();
        fileSystem.mkdir("/a");
        fileSystem.mkdir("/a/b/c");
        fileSystem.mkdir("/a/b/d");
        System.out.println(fileSystem.ls("/a/b"));  // [c,d]


        fileSystem.addContentToFile("/a/b/c/d.txt", "Hello ");
        System.out.println(fileSystem.readContentFromFile("/a/b/c/d.txt"));

        fileSystem.addContentToFile("/a/b/c/d.txt", "World!");
        System.out.println(fileSystem.readContentFromFile("/a/b/c/d.txt"));

    }

    static class MemoryFileSystem {

        private Directory root;

        public MemoryFileSystem() {
            this.root = new Directory(" ");
        }

        public List<String> ls(String path) {
            BaseNode current = resolvePath(path);

            if (current instanceof FileNode) {
                return Collections.singletonList(current.getName());
            } else {
                return ((Directory) current).getSortedChildList();
            }
        }

        private BaseNode resolvePath(String path) {
            String[] split = path.split("/");
            BaseNode current = root; // 当前路径

            for (String s : split) {
                if (s.isEmpty()) {
                    continue;
                }

                if (!(current instanceof Directory)) {
                    throw new RuntimeException("非目录");
                }

                current = ((Directory) current).getChild(s);
            }
            return current;
        }

        public void mkdir(String filePath) {
            String[] split = filePath.split("/");
            Directory current = root;

            for (String s : split) {
                if (s.isEmpty()) {
                    continue;
                }
                if (!current.hasChild(s)) {
                    current.addChild(new Directory(s));
                }
                current = (Directory) current.getChild(s);
            }
        }

        public void addContentToFile(String filePath, String content) {
            String[] split = filePath.split("/");
            int lastIdx = split.length - 1;
            String fileName = split[lastIdx];

            Directory parentDir = root;
            for (int i = 0; i < lastIdx; i++) {
                String dirName = split[i];
                if (dirName.isEmpty()) continue;
                if (!parentDir.hasChild(dirName)) {
                    parentDir.addChild(new Directory(dirName));
                }
                parentDir = (Directory) parentDir.getChild(dirName);
            }

            if (parentDir.hasChild(fileName)) {
                ((FileNode) parentDir.getChild(fileName)).appendFileContent(content);
            } else {
                parentDir.addChild(new FileNode(fileName, content));
            }
        }

        public String readContentFromFile(String filePath) {
            BaseNode current = resolvePath(filePath);
            if (current instanceof Directory) {
                throw new RuntimeException("not a file");
            }
            FileNode fileNode = (FileNode) current;
            return fileNode.getContent();
        }


    }


    static class BaseNode {
        private String name;

        public BaseNode(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    static class Directory extends BaseNode {

        private Map<String, BaseNode> childrenMap;

        public Directory(String name) {
            super(name);
            this.childrenMap = new HashMap<>();
        }

        public void addChild(BaseNode child) {
            this.childrenMap.put(child.getName(), child);
        }

        public boolean hasChild(String name) {
            return this.childrenMap.containsKey(name);
        }

        public BaseNode getChild(String name) {
            return this.childrenMap.get(name);
        }


        public List<String> getSortedChildList() {
            List<String> names = new ArrayList<>(this.childrenMap.keySet());
            Collections.sort(names);
            return names;
        }

    }

    static class FileNode extends BaseNode {
        private StringBuffer sb;

        public FileNode(String name, String content) {
            super(name);
            this.sb = new StringBuffer(content);
        }

        private void appendFileContent(String content) {
            this.sb.append(content);
        }

        private String getContent() {
            return this.sb.toString();
        }
    }


}
