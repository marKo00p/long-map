package test.de.comparus.opensource.longmap;

import de.comparus.opensource.longmap.LongMapImpl;
import org.junit.Test;
import org.junit.Before;
import java.util.Arrays;
import static org.junit.Assert.*;

public class LongMapImplTest {
    private LongMapImpl<String> longMap;
    private static final int DEFAULT_CAPACITY = 6;
    @Before
    public void setUp(){
        longMap = new LongMapImpl<>(DEFAULT_CAPACITY);
        longMap.put(1L,"10");
        longMap.put(2L,"25");
        longMap.put(3L,"Peter");
        longMap.put(4L,"John");
    }
    @Test
    public void putToExistingKeyValuePair(){
        assertEquals("Olga",longMap.put(1L,"Olga"));
    }
    @Test
   public void getKeyValuePair(){
        assertEquals("10", longMap.get(1L));
        assertEquals("25", longMap.get(2L));
        assertEquals("Peter", longMap.get(3L));
        assertEquals("John", longMap.get(4L));
    }
    @Test
    public void removedKeyValuePair(){
        assertEquals("10", longMap.remove(1L));
        assertEquals("John",longMap.remove(4L));
    }
    @Test
    public void testIsEmpty(){
        assertEquals(false, longMap.isEmpty());
        longMap.clear();
        assertEquals(true, longMap.isEmpty());
    }
    @Test
    public void testContainsKey(){
        assertEquals(false, longMap.containsKey(5L));
        assertEquals(true, longMap.containsKey(3L));

    }
    @Test
    public void testContainsValue(){
        assertEquals(false, longMap.containsValue("20"));
        assertEquals(true, longMap.containsValue("Peter"));
    }
    @Test
    public void testKeysArray(){
        long [] key = longMap.keys();
        assertEquals(Arrays.toString(key), Arrays.toString(longMap.keys()));
    }
    @Test
    public void testValuesArray(){
        LongMapImpl<Object> map = new LongMapImpl<>(DEFAULT_CAPACITY);
        map.put(3L, "100");
        Object[] valueArray = map.values();
        assertEquals(Arrays.toString(valueArray),Arrays.toString(map.values()));
    }
    @Test
    public void testSize(){
        assertEquals(4, longMap.size());
        longMap.clear();
        assertEquals(0, longMap.size());
    }
    @Test
    public void testClear(){
        assertEquals(4, longMap.size());
        longMap.clear();
        assertEquals(0, longMap.size());
    }
}
