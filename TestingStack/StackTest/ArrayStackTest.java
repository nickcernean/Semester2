import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utility.collection.ArrayStack;
import utility.collection.StackADT;

import static org.junit.jupiter.api.Assertions.*;

class ArrayStackTest
{
  private StackADT<String> stackADT;

  @BeforeEach void setUp()
  {
    this.stackADT = new ArrayStack<>(5);
  }

  //Test push() method
  @Test void pushZero()
  {
    stackADT.push("");
    assertEquals(stackADT.pop(), "");

    stackADT.push(null);
    assertNull(stackADT.pop());
  }

  @Test void pushOne()
  {
    stackADT.push("A");
    assertEquals(stackADT.indexOf("A"), 0);

  }

  @Test void pushMultiple()
  {
    stackADT.push("A");
    stackADT.push("B");
    stackADT.push("C");

    assertEquals(stackADT.indexOf("A"), 2);
    assertEquals(stackADT.indexOf("B"), 1);
    assertEquals(stackADT.indexOf("C"), 0);

  }

  @Test void pushBoundaries()
  {
    for (int i = 0; i <= 11; i++)
    {
      stackADT.push("A" + i);
    }
    assertEquals("{A11, A10, A9, A8, A7, A6, A5, A4, A3, A2, A1, A0}",
        stackADT.toString());
  }

  @Test void pushExceptions()
  {

  }
  //Testing pop() method

  @Test void popZero()
  {
    assertThrows(IllegalStateException.class, () -> stackADT.pop());
  }

  @Test void popOne()
  {
    stackADT.push("A");
    assertEquals("A", stackADT.pop());
  }

  @Test void popMultiple()
  {
    stackADT.push("A");
    stackADT.push("B");
    stackADT.push("C");
    stackADT.push("D");
    assertEquals("D", stackADT.pop());
    stackADT.push("E");
    stackADT.push("F");
    stackADT.push("G");
    stackADT.push("H");
    stackADT.push("I");
    assertEquals("I", stackADT.pop());
  }

  @Test void popBoundary()
  {
    assertThrows(IllegalStateException.class, () -> stackADT.pop());
    stackADT.push("A");
    assertEquals("A", stackADT.pop());
  }

  @Test void popExceptions()
  {
    // No additional Exceptions
  }

  //Testing indexof() method
  @Test void indexOfZero()
  {
    assertEquals(-1, stackADT.indexOf(null));
    assertEquals(-1, stackADT.indexOf(""));
  }

  @Test void indexOfOne()
  {
    stackADT.push("A");
    assertEquals(0, stackADT.indexOf("A"));
    assertThrows(NullPointerException.class,()->stackADT.indexOf(null));
  }

  @Test void indexOfMultiple()
  {
    stackADT.push("A");
    stackADT.push("B");
    stackADT.push("C");
    stackADT.push("D");
    stackADT.push("E");
    assertEquals(1, stackADT.indexOf("D"));
    assertEquals(-1, stackADT.indexOf("T"));
    assertThrows(NullPointerException.class,()->stackADT.indexOf(null));
  }

  @Test void indexOfBoundary()
  {
    stackADT.push("A");
    assertEquals(0, stackADT.indexOf("A"));
    assertEquals(-1, stackADT.indexOf(""));
  }

  @Test void indexOfExceptions()
  {stackADT.push("A");
    assertThrows(NullPointerException.class,()->stackADT.indexOf(null));
  }
  // Testing peek() method

  @Test void peekZero()
  {
    assertThrows(IllegalStateException.class, () -> stackADT.peek());
  }

  @Test void peekOne()
  {
    stackADT.push("A");
    assertEquals("A", stackADT.peek());
  }

  @Test void peekMultiple()
  {
    stackADT.push("A");
    stackADT.push("B");
    stackADT.push("C");
    stackADT.push("D");
    stackADT.push("E");
    assertEquals("E", stackADT.peek());
    assertEquals("E", stackADT.peek());

  }

  @Test void peekBoundaries()
  {
    assertThrows(IllegalStateException.class, () -> stackADT.peek());
    stackADT.push("A");
    stackADT.push("B");
    stackADT.push("C");
    assertEquals("C", stackADT.peek());

  }

  @Test void peekExceptions()
  {
    //NO other exceptions
  }

  // Testing isFull() method
  @Test void isFullZero()
  {
    assertFalse(stackADT.isFull());

  }

  @Test void isFullOne()
  {
    stackADT.push("B");
    assertFalse(stackADT.isFull());
  }

  @Test void isFullMultiple()
  {
    stackADT.push("B");
    stackADT.push("B");
    stackADT.push("B");
    stackADT.push("B");
    stackADT.push("B");
    stackADT.push("B");
    stackADT.push("B");
    assertFalse(stackADT.isFull());
  }

  @Test void isFullBoundaries()
  {
    assertFalse(stackADT.isFull());
    stackADT.push("B");
    stackADT.push("B");
    stackADT.push("B");
    stackADT.push("B");
    stackADT.push("B");
    stackADT.push("B");
    stackADT.push("B");
    assertFalse(stackADT.isFull());
  }

  @Test void isFullExceptions()
  {
    // No other exceptions
  }

  // Testing isEmpty() method
  @Test void isEmptyZero()
  {
    assertTrue(stackADT.isEmpty());
  }

  @Test void isEmptyOne()
  {
    stackADT.push("A");
    assertFalse(stackADT.isEmpty());
  }

  @Test void isEmptyMultiple()
  {
    stackADT.push("A");
    stackADT.push("A");
    stackADT.push("A");
    stackADT.push("A");
    assertFalse(stackADT.isEmpty());
  }

  @Test void isEmptyBoundaries()
  {
    assertTrue(stackADT.isEmpty());
    stackADT.push("A");
    stackADT.push("A");
    stackADT.push("A");
    stackADT.push("A");
    assertFalse(stackADT.isEmpty());
  }

  @Test void isEmptyExceptions()
  {
    // No exceptions
  }

  // Testing size() method
  @Test void sizeZero()
  {
    assertEquals(0, stackADT.size());
  }

  @Test void sizeOne()
  {
    stackADT.push("A");
    assertEquals(1, stackADT.size());
  }

  @Test void sizeMultiple()
  {
    stackADT.push("A");
    stackADT.push("B");
    stackADT.push("C");
    stackADT.push("D");
    stackADT.push("E");
    assertEquals(5, stackADT.size());

  }

  @Test void sizeBoundaries()
  {assertEquals(0, stackADT.size());
    stackADT.push("A");
    stackADT.push("B");
    stackADT.push("C");
    stackADT.push("D");
    stackADT.push("E");
    assertEquals(5, stackADT.size());
  }

  @Test void sizeExceptions()
  {

  }

}

