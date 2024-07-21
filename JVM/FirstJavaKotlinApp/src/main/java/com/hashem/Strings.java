package com.hashem;

public class Strings {
    public static void main(String[] args) {
        // System.out.print("hasUniqueChars: " + hasUniqueChars("Wording"));

        // ------------------------------//
        // char[] normalChars = "a!!!b.c.d,e'f,ghi".toCharArray();
        // System.out.println(normalChars);
        // reverse(normalChars);
        // System.out.println(normalChars);
        /*----------------------------------*/
        // System.out.println(isAnagram("word", "wrdo"));
        /* ========================== */
        permutation("asd");
    }

    private static boolean hasUniqueChars(String word) {
        if (word == null)
            return false;

        char[] charArray = word.toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            char tmp = charArray[i];
            for (int j = i + 1; j < charArray.length; j++)
                if (charArray[j] == tmp)
                    return false;
        }
        return true;
    }

    private static void reverse(char str[]) {
        // Initialize left and right pointers
        int r = str.length - 1, l = 0;

        // Traverse string from both ends until
        // 'l' and 'r'
        while (l < r) {
            // Ignore special characters
            if (!isAlphabet(str[l]))
                l++;
            else if (!isAlphabet(str[r]))
                r--;
            else // Both str[l] and str[r] are not spacial
            {
                char temp = str[l];
                str[l] = str[r];
                str[r] = temp;
                l++;
                r--;
            }
        }
    }

    static boolean isAlphabet(char x) {
        return ((x >= 'A' && x <= 'Z') || (x >= 'a' && x <= 'z'));
    }

    public static boolean isAnagram(String word, String anagram) {
        if (word.length() != anagram.length()) {
            return false;
        }

        char[] wordChars = word.toCharArray();
        for (char c : wordChars) {
            int index = anagram.indexOf(c);
            if (index != -1) {
                System.out.println(anagram.substring(0, index));
                System.out.println(anagram.substring(index + 1, anagram.length()));
                anagram = anagram.substring(0, index) + anagram.substring(index + 1, anagram.length());
            } else {
                return false;
            }
        }
        return anagram.isEmpty();
    }

    /* A method exposed to client to calculate permutation of String in Java. */
    public static void permutation(String input) {
        permutation("", input);
    }

    /*
     * Recursive method which actually prints all permutations of given String, but
     * since we are passing an empty String as current permutation to start with, I
     * have made this method private and didn't exposed it to client.
     */
    private static void permutation(String perm, String word) {
        if (word.isEmpty()) {
            System.err.println(perm + word);
        } else {
            for (int i = 0; i < word.length(); i++) {
                System.err.println(word);
                permutation(perm + word.charAt(i), word.substring(0, i) + word.substring(i + 1, word.length()));
            }
        }
    }

}