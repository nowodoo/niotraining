package com.zach.nio;

import org.junit.Test;

public class ByteBufferTest {
	
	
	@Test
	public void testByteBufferRW() throws Exception{
		ByteBufferDemo.readFile("file/1.txt");
	}

}
