package pcsladders.util;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class TransformSet2List {

    public static <T> List<T> transformSet2List(Set<T> set) {
        List<T> list = new ArrayList<T>();
        Iterator<T> it = set.iterator();
        while(it.hasNext()) {
            list.add(it.next());
        }
        return list;
    }
}
