package aed.tries;

import java.util.Arrays;

import es.upm.aedlib.Pair;
import es.upm.aedlib.Position;
import es.upm.aedlib.tree.GeneralTree;
import es.upm.aedlib.tree.LinkedGeneralTree;
import es.upm.aedlib.positionlist.PositionList;
import es.upm.aedlib.positionlist.NodePositionList;


public class DictImpl implements Dictionary {
  // A boolean because we need to know if a word ends in a node or not
  GeneralTree<Pair<Character,Boolean>> tree;

  public DictImpl() {
    tree = new LinkedGeneralTree<>();
    tree.addRoot(new Pair<Character,Boolean>(null,false));
  }

  public void add(String word) { }
  public void delete(String word) { }
  public boolean isIncluded(String word) { return true; }
  public PositionList<String> wordsBeginningWithPrefix(String prefix) { return null; }
}
