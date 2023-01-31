import java.util.TreeSet;

public class DictionaryBST implements Dictionary {

  private TreeSet<String> dict;

  // TODO: Implement the dictionary interface using a TreeSet.
  public DictionaryBST() {
    dict = new TreeSet<String>();
  }

  /** Add this word to the dictionary.  Convert it to lowercase first
   * for the assignment requirements.
   * @param word The word to add
   * @return true if the word was added to the dictionary
   * (it wasn't already there). */
  public boolean addWord(String word) {
    word = word.toLowerCase();
    if (dict.contains(word.toLowerCase())) {
      return false;
    } else {
      dict.add(word.toLowerCase());
      return true;
    }
  }

  /** Return the number of words in the dictionary */
  public int size() {
    return dict.size();
  }

  /** Is this a word according to this dictionary? */
  public boolean isWord(String s) {
    s = s.toLowerCase();
    return dict.contains(s);
  }
}
