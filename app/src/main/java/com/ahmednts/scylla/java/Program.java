package com.ahmednts.scylla.java;

public class Program {
  public static void main(String[] args) {

    long enterDate = 1575212400000L;
    long exitDate = 1575213600000L;

    long diff = exitDate - enterDate;
    int diffMinutes = (int) (diff / (60 * 1000));

    int count = Math.round(diffMinutes / 25);

    System.out.println(count);
  }
}
