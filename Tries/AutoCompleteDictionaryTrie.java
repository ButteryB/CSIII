import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements Dictionary, AutoComplete {

  private TrieNode root;
  private int size;

  public AutoCompleteDictionaryTrie() {
    root = new TrieNode();
  }

  /** Insert a word into the trie.
   * You should ignore the word's case.
   * That is, you should convert the string to all lower case as you insert it. */
  public boolean addWord(String word) {
    word = word.toLowerCase();
    size++;
    TrieNode node = root;
    for (int i = 0; i < word.length(); i++) {
      char c = word.charAt(i);
      if (node.getChild(c) == null) {
        node = node.insert(c);
      } else {
        node = node.getChild(c);
      }
    }
    if (node.endsWord()) {
      size--;
      return false;
    }
    node.setEndsWord(true);
    return true;
  }

  /**
   * Return the number of words in the dictionary.  This is NOT necessarily the same
   * as the number of TrieNodes in the trie.
   */
  public int size() {
    return size;
  }

  /** Returns whether the string is a word in the trie */
  @Override
  public boolean isWord(String s) {
    s = s.toLowerCase();
    TrieNode node = root;
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (node.getChild(c) == null) {
        return false;
      } else {
        node = node.getChild(c);
      }
    }
    return node.endsWord();
  }

  /**
   *  * Returns up to the n "best" predictions, including the word itself,
   * in terms of length
   * If this string is not in the trie, it returns null.
   * @param text The text to use at the word stem
   * @param n The maximum number of predictions desired.
   * @return A list containing the up to n best predictions
   */@Override
  public List<String> predictCompletions(String prefix, int numCompletions) {
    // TODO: Implement this method
    // This method should implement the following algorithm:
    // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    //    empty list
    // 2. Once the stem is found, perform a breadth first search to generate completions
    //    using the following algorithm:
    //    Create a queue (LinkedList) and add the node that completes the stem to the back
    //       of the list.
    //    Create a list of completions to return (initially empty)
    //    While the queue is not empty and you don't have enough completions:
    //       remove the first Node from the queue
    //       If it is a word, add it to the completions list
    //       Add all of its child nodes to the back of the queue
    // Return the list of completions
    prefix = prefix.toLowerCase();
    TrieNode node = root;
    for (int i = 0; i < prefix.length(); i++) {
      char c = prefix.charAt(i);
      if (node.getChild(c) == null) {
        return new ArrayList<String>();
      } else {
        node = node.getChild(c);
      }
    }
    Queue<TrieNode> queue = new LinkedList<TrieNode>();
    List<String> completions = new ArrayList<String>();
    queue.add(node);
    while (!queue.isEmpty() && completions.size() < numCompletions) {
      TrieNode curr = queue.remove();
      if (curr.endsWord()) {
        completions.add(curr.getText());
      }
      Set<Character> children = curr.getValidNextCharacters();
      for (Character c : children) {
        queue.add(curr.getChild(c));
      }
    }
    return completions;
  }

  // For debugging
  public void printTree() {
    printNode(root);
  }

  /** Do a pre-order traversal from this node down */
  public void printNode(TrieNode curr) {
    if (curr == null) return;

    System.out.println(curr.getText());

    TrieNode next = null;
    for (Character c : curr.getValidNextCharacters()) {
      next = curr.getChild(c);
      printNode(next);
    }
  }
}
