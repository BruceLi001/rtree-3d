package com.github.davidmoten.rtree3d;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import com.github.davidmoten.rtree3d.Context;
import com.github.davidmoten.rtree3d.Entry;
import com.github.davidmoten.rtree3d.Leaf;
import com.github.davidmoten.rtree3d.SelectorMinimalVolumeIncrease;
import com.github.davidmoten.rtree3d.SplitterQuadratic;
import com.github.davidmoten.rtree3d.geometry.Box;
import com.github.davidmoten.rtree3d.geometry.Geometries;

public class LeafTest {

    private static Context context = new Context(2, 4, new SelectorMinimalVolumeIncrease(),
            new SplitterQuadratic());

    @Test(expected = IllegalArgumentException.class)
    public void testCannotHaveZeroChildren() {
        new Leaf<Object, Box>(new ArrayList<Entry<Object, Box>>(), context);
    }

    @Test
    public void testMbr() {
        Box r1 = Geometries.box(0, 1, 0, 3, 5, 1);
        Box r2 = Geometries.box(1, 2, 0, 4, 6, 1);
        @SuppressWarnings("unchecked")
        Box r = new Leaf<Object, Box>(Arrays.asList(Entry.entry(new Object(), r1),
                Entry.entry(new Object(), r2)), context).geometry().mbb();
        assertEquals(r1.add(r2), r);
    }
}
