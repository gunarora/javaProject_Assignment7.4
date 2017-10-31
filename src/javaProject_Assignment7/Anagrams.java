/*Write a program that finds all anagram sets from a list of words. Anagrams are those words in which there are same characters jumbled. For example -> GOD - DOG ARE ANAGRAMS. Both words have same characters but in jumbled order.
Input: listen, pot, part, opt, trap, silent, top, this, hello, hits, what, shit ------- Output: { listen,
silent} { part, trap } { pot, opt, top }{ this, hits, shit }*/


package javaProject_Assignment7; //Package declaration is mandatory
 
//Importing packages
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
 

 
public class Anagrams {
 
    // we are only dealing with keys with chars 'a' to 'z'
    final static int ALPHABET_SIZE = 26;
     
    class TrieNode
    {
         
        ArrayList<Integer> anagramIndices;
        TrieNode[] children;
         
        TrieNode()
        {
            children = new TrieNode[ALPHABET_SIZE];
            this.anagramIndices = new ArrayList();
        }
    }
 
    TrieNode root;
     
    //Constructor
    Anagrams()
    {
        this.root = new TrieNode();
    }
 
    private int getIndex(char ch)
    {
        return ch - 'a';
    }
 
    //logic for finding anagrams
    public void insertIntoTrie(String key, int index, HashMap anagramNodes)
    {
        // null keys are not allowed
        if (key == null) return;
         
        key = key.toLowerCase();
         
        TrieNode currentNode = this.root;
        int charIndex = 0;
         
        while (charIndex < key.length())
        {
            int childIndex = getIndex(key.charAt(charIndex));
 
            if (childIndex < 0 || childIndex >= ALPHABET_SIZE)
            {
                System.out.println("Invalid Key");
                return;
            }
             
            if (currentNode.children[childIndex] == null)
            {
                currentNode.children[childIndex] = new TrieNode();
            }
             
            currentNode = currentNode.children[childIndex];
             
            charIndex  += 1;
        }
         
        if (charIndex == key.length())
        {
            currentNode.anagramIndices.add(index);
            anagramNodes.put(currentNode, currentNode.anagramIndices);
        }
         
        return;
    }
 
    //Printing anagrams
    public void printGroupedAnagrams(String[] sequence)
    {
        HashMap<TrieNode, ArrayList<Integer>> anagramNodes = new HashMap();
        for (int i = 0;  i < sequence.length; i++)
        {
            char[] charSequence = sequence[i].toCharArray(); 
            Arrays.sort(charSequence);
             
            insertIntoTrie(new String(charSequence), i, anagramNodes);
        }
 
        Iterator<ArrayList<Integer>> mapItr = anagramNodes.values().iterator();
        while (mapItr.hasNext())
        {
            ArrayList<Integer> currentAnagramList = mapItr.next();
            for (int j = 0; j < currentAnagramList.size(); j++)
            {
                int indexIntoSequence = currentAnagramList.get(j);
                System.out.print(" " + sequence[indexIntoSequence]);
            }
            System.out.println("");
        }
    }
     
     
   // main method started
    public static void main(String[] args)
    {
        Anagrams tr = new Anagrams();
         
        //sequance provided to find anagrams
        String[] sequence = {"listen", "pot", "part", "opt", "trap", "silent", "top", "this", "hello", "hits", "what", "shit"};
 
        //calling method to print anagrams
        tr.printGroupedAnagrams(sequence);
         
    }
	
	}