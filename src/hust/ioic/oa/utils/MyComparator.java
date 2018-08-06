package hust.ioic.oa.utils;

import java.util.Comparator;

import hust.ioic.oa.domain.Function;

public class MyComparator implements Comparator<Function> {

    public int compare(Function o1, Function o2) {
        
        return o1.getId()-o2.getId();
    }

}
