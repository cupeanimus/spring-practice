package com.kyle.springpractice.practice.hashtable;

import java.util.Hashtable;
import java.util.LinkedList;

public class HashTable {
    class Node {
        String key;
        String value;

        public Node(String key, String value) {
            this.key = key;
            this.value = value;
        }

        String getValue() {
            return value;
        }

        void setValue(String value) {
            this.value = value;
        }
    }
    LinkedList<Node>[] data;
    HashTable(int size) {
        this.data = new LinkedList[size];
    }
    int getHashCode(String key) {
        int hashCode = 0;
        for(char c : key.toCharArray()) {
            hashCode += c;
        }
        return hashCode;
    }
    int convertToIndex(int hashCode) {
        return hashCode % data.length;
    }
    Node searchKey(LinkedList<Node> list, String key) {
        if (list == null) {
            return null;
        }
        for(Node node : list) {
            if (node.key.equals(key)) {
                return node;
            }
        }
        return null;
    }
    void put(String key, String value) {
        int hashCode = getHashCode(key);
        int index = convertToIndex(hashCode);
        LinkedList<Node> list = data[index];
        if (list == null) {
            list = new LinkedList<Node>();
            data[index] = list;
        }
        Node node = searchKey(list, key);
        if(node == null) {
            list.addLast(new Node(key, value));
        }else {
            node.setValue(value);
        }
    }
    String get(String key) {
        int hashCode = getHashCode(key);
        int index= convertToIndex(hashCode);
        LinkedList<Node> list = data[index];
        Node node = searchKey(list, key);
        return node == null? "Not found" : node.value;
    }
}

class Test {
    public static void main(String[] args) {
        Hashtable hashtable = new Hashtable(3);
        hashtable.put("kyle", "backend developer");
        hashtable.put("sally", "marketer");
        hashtable.put("john", "front developer");
        hashtable.put("lynn", "HR manager");

        System.out.println(hashtable.get("kyle"));
        System.out.println(hashtable.get("sally"));
        System.out.println(hashtable.get("john"));
        System.out.println(hashtable.get("lynn"));
    }
}


