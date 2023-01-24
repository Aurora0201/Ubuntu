package FileOperationTest;

import java.io.OutputStream;
import java.util.TreeSet;

public class StreamSummary {
    /*
    * 1.Classification of IOStream:
        * There are many ways to classify IOStream
        * 1.For RAM:
        *   1.Get into RAM called "Input" or "Read"
        *   2.Get out of RAM called "Output" or "Write"
        *
        * 2.Judge by the way reading data
        *   1.Reading bytes Stream, read a byte at a time. Everything can be read by this way.
        *   2.Reading char Stream, read a char at a time. This Stream only can read txt file.
        *
    * 2.The IOStream in Java
    *   1.Byte Read:java.io.InputStream/java.io.OutputStream
    *   2.Char Read:java.io.Reader/java.io.Writer
    * 3.The usage of Stream
    *   1.We should close() the IOStream, when we complete IO operation
    *   2.At the end of work, we should flush() the OutStream which is equal to clear the tube.
    *   if we don't use the method, maybe we would lose our data
    * */

    public static void main(String[] args) {

    }
}