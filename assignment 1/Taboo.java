
/*
 HW1 Taboo problem class.
 Taboo encapsulates some rules about what objects
 may not follow other objects.
 (See handout).
*/

import java.util.*;

public class Taboo<T> {
	private List<T> rules;
	private HashMap<T,HashSet<T>> taboos;
	/**
	 * Constructs a new Taboo using the given rules (see handout.)
	 * @param rules rules for new Taboo
	 */
	public Taboo(List<T> rules) {
		this.taboos = new HashMap<>();
		this.rules = rules;

		T curr = null;
		for(T val : rules){

			if(curr != null) {
				if (!taboos.containsKey(curr)) {
					taboos.put(curr, new HashSet<>());
				}
				taboos.get(curr).add(val);
			}
			curr = val;
		}
	}
	
	/**
	 * Returns the set of elements which should not follow
	 * the given element.
	 * @param elem
	 * @return elements which should not follow the given element
	 */
	public Set<T> noFollow(T elem) {
		if(taboos.containsKey(elem)) return taboos.get(elem);
		return (new HashSet<>());
	}
	
	/**
	 * Removes elements from the given list that
	 * violate the rules (see handout).
	 * @param list collection to reduce
	 */
	public void reduce(List<T> list) {

		T curr = null;
		for(Iterator<T> it = list.iterator(); it.hasNext();){
			T val = it.next();
			if(curr != null && val != null){
				if(noFollow(curr).contains(val)){
					it.remove();
				}else{
					curr = val;
				}
			}else {
				curr = val;
			}
		}

	}
}
