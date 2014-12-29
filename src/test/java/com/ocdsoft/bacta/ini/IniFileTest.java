package com.ocdsoft.bacta.ini;

import com.ocdsoft.bacta.engine.conf.ini.IniFile;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by crush on 3/21/14.
 */
public class IniFileTest {
    private IniFile iniFile = new IniFile(IniFileTest.class.getResource("/test.ini").getPath());

    @Test
    public void testInclude() throws Exception {
        assertTrue(iniFile.getBoolean("TestSection/IncludeTest", "testInclude"));
    }

    @Test
    public void testGetString() throws Exception {
        assertTrue(iniFile.getString("TestSection", "testString1").equals("Test1"));
    }

    @Test
    public void testGetStringWithNullContent() throws Exception {
        assertTrue(iniFile.getString("TestSection", "testString2").equals(""));
    }

    @Test
    public void testGetStringWithIndex() throws Exception {
        assertTrue(iniFile.getString("TestSection", "testString", 1).equals("test2"));
    }

    @Test
    public void testGetStringLast() throws Exception {
        assertTrue(iniFile.getStringLast("TestSection", "testString").equals("test3"));
    }

    @Test
    public void testGetStringWithDefault() throws Exception {
        //No default value needed cases
        assertTrue(iniFile.getStringWithDefault("TestSection", "testString", "testValue").equals("test1"));

        //Default value cases
        assertTrue(iniFile.getStringWithDefault("NonexistentSection", "testString", "testValue").equals("testValue"));
        assertTrue(iniFile.getStringWithDefault("TestSection", "nonexistentProperty", "testValue").equals("testValue"));
    }

    @Test
    public void testGetStringWithDefaultWithIndex() throws Exception {
        //No default value needed cases
        assertTrue(iniFile.getStringWithDefault("TestSection", "testString", "testValue", 2).equals("test3"));

        //Default value cases
        assertTrue(iniFile.getStringWithDefault("NonexistentSection", "testString", "testValue", 2).equals("testValue"));
        assertTrue(iniFile.getStringWithDefault("TestSection", "nonexistentProperty", "testValue", 2).equals("testValue"));
        assertTrue(iniFile.getStringWithDefault("TestSection", "testString", "testValue", 129234).equals("testValue"));
    }

    @Test
    public void testGetStringLastWithDefault() throws Exception {
        assertTrue(iniFile.getStringLastWithDefault("TestSection", "testString", "testValue").equals("test3"));
        assertTrue(iniFile.getStringLastWithDefault("TestSection", "nonexistentProperty", "testValue").equals("testValue"));
        assertTrue(iniFile.getStringLastWithDefault("NonexistentSection", "testString", "testValue").equals("testValue"));
    }

    @Test
    public void testGetStringCollection() throws Exception {
        String[] expecteds = new String[]{"test1", "test2", "test3"};
        Collection<String> collection = iniFile.getStringCollection("TestSection", "testString");
        assertArrayEquals(expecteds, collection.toArray(new String[3]));
    }

    @Test
    public void testGetBoolean() throws Exception {

    }

    @Test
    public void testGetBoolean1() throws Exception {

    }

    @Test
    public void testGetBooleanLast() throws Exception {

    }

    @Test
    public void testGetBooleanWithDefault() throws Exception {

    }

    @Test
    public void testGetBooleanWithDefault1() throws Exception {

    }

    @Test
    public void testGetBooleanLastWithDefault() throws Exception {

    }

    @Test
    public void testGetBooleanCollection() throws Exception {

    }

    @Test
    public void testGetByte() throws Exception {

    }

    @Test
    public void testGetByte1() throws Exception {

    }

    @Test
    public void testGetByteLast() throws Exception {

    }

    @Test
    public void testGetByteWithDefault() throws Exception {

    }

    @Test
    public void testGetByteWithDefault1() throws Exception {

    }

    @Test
    public void testGetByteLastWithDefault() throws Exception {

    }

    @Test
    public void testGetByteCollection() throws Exception {

    }

    @Test
    public void testGetShort() throws Exception {

    }

    @Test
    public void testGetShort1() throws Exception {

    }

    @Test
    public void testGetShortLast() throws Exception {

    }

    @Test
    public void testGetShortWithDefault() throws Exception {

    }

    @Test
    public void testGetShortWithDefault1() throws Exception {

    }

    @Test
    public void testGetShortLastWithDefault() throws Exception {

    }

    @Test
    public void testGetShortCollection() throws Exception {

    }

    @Test
    public void testGetInt() throws Exception {

    }

    @Test
    public void testGetInt1() throws Exception {

    }

    @Test
    public void testGetIntLast() throws Exception {

    }

    @Test
    public void testGetIntWithDefault() throws Exception {

    }

    @Test
    public void testGetIntWithDefault1() throws Exception {

    }

    @Test
    public void testGetIntLastWithDefault() throws Exception {

    }

    @Test
    public void testGetIntCollection() throws Exception {

    }

    @Test
    public void testGetLong() throws Exception {

    }

    @Test
    public void testGetLong1() throws Exception {

    }

    @Test
    public void testGetLongLast() throws Exception {

    }

    @Test
    public void testGetLongWithDefault() throws Exception {

    }

    @Test
    public void testGetLongWithDefault1() throws Exception {

    }

    @Test
    public void testGetLongLastWithDefault() throws Exception {

    }

    @Test
    public void testGetLongCollection() throws Exception {

    }

    @Test
    public void testGetFloat() throws Exception {

    }

    @Test
    public void testGetFloat1() throws Exception {

    }

    @Test
    public void testGetFloatLast() throws Exception {

    }

    @Test
    public void testGetFloatWithDefault() throws Exception {

    }

    @Test
    public void testGetFloatWithDefault1() throws Exception {

    }

    @Test
    public void testGetFloatLastWithDefault() throws Exception {

    }

    @Test
    public void testGetFloatCollection() throws Exception {

    }

    @Test
    public void testGetDouble() throws Exception {

    }

    @Test
    public void testGetDouble1() throws Exception {

    }

    @Test
    public void testGetDoubleLast() throws Exception {

    }

    @Test
    public void testGetDoubleWithDefault() throws Exception {

    }

    @Test
    public void testGetDoubleWithDefault1() throws Exception {

    }

    @Test
    public void testGetDoubleLastWithDefault() throws Exception {

    }

    @Test
    public void testGetDoubleCollection() throws Exception {

    }
}
