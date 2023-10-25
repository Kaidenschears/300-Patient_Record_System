/////////////////////////////// PatientRecordTreeTester ////////////////////////////////
//
// Title: Patient Record Tree Tester
// Files: PatientRecord.java, PatientRecoardTreeTester.java, PatientRecordNode.java,
//      PatientRecord.java
// Course: CS 300 Spring 2020
//
// Author: Kaiden Schears
// Email: kgschears@wisc.edu
// Lecturer's Name: Mouna Kacem
//
//////////// PAIR PROGRAMMING (MAY SKIP WHEN WORKING INDIVIDUALLY) ////////////
//
// Partner Name:
// Partner Email:
// Partner Lecturer's Name:
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// __ Write-up states that pair programming is allowed for this assignment.
// __ We have both read and understood the course Pair Programming Policy.
// __ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Students who get help from sources other than their partner and the course
// staff must fully acknowledge and credit those sources here. If you did not
// receive any help of any kind from outside sources, explicitly indicate NONE
// next to each of the labels below.
//
// Persons: none
// Online Sources: PatientRecord.java, PatientRecoardTreeTester.java, PatientRecordNode.java,
//      PatientRecord.java
//////////////////////////////////////////////////////////////////////////////////////////////
import java.util.NoSuchElementException;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
/**
 * This class checks the correctness of the implementation of the methods defined in the class
 * PatientRecordTree.
 *
 */

public class PatientRecordTreeTester {

  /**
   * Checks the correctness of the implementation of both addPatientRecord() and toString() methods
   * implemented in the PatientRecordTree class. This unit test considers at least the following
   * scenarios. (1) Create a new empty PatientRecordTree, and check that its size is 0, it is empty,
   * and that its string representation is an empty string "". (2) try adding one patient record and
   * then check that the addPatientRecord() method call returns true, the tree is not empty, its
   * size is 1, and the .toString() called on the tree returns the expected output. (3) Try adding
   * another patientRecord which is older that the one at the root, (4) Try adding a third patient
   * Record which is younger than the one at the root, (5) Try adding at least two further patient
   * records such that one must be added at the left subtree, and the other at the right subtree.
   * For all the above scenarios, and more, double check each time that size() method returns the
   * expected value, the add method call returns true, and that the .toString() method returns the
   * expected string representation of the contents of the binary search tree in an ascendant order
   * from the oldest patient to the youngest one. (6) Try adding a patient whose date of birth was
   * used as a key for a patient record already stored in the tree. Make sure that the
   * addPatientRecord() method call returned false, and that the size of the tree did not change.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  @Test
  public void testAddPatientRecordToStringSize() {
    PatientRecordTree test = new PatientRecordTree();
    assertTrue(test.size() == 0);// scenario 1
     
    assertTrue(test.addPatientRecord(new PatientRecord("Adam", "8/12/1972")));// scenario 2
    
    assertTrue(test.size() == 1);
     
    assertTrue(test.toString().contains("Adam(8/12/1972)"));
     

    String result = "George(5/27/1943)" + "\n" + "Adam(8/12/1972)" + "\n";
    assertTrue(test.addPatientRecord(new PatientRecord("George", "5/27/1943")));// scenario 3
    assertTrue(test.toString().compareTo(result) == 0);
     
    result += "Nancy(9/12/2003)" + "\n";
    assertTrue(test.addPatientRecord(new PatientRecord("Nancy", "9/12/2003"))); // scenario 4
   
    assertTrue(test.toString().compareTo(result) == 0);
     
    assertTrue(test.addPatientRecord(new PatientRecord("William", "6/4/1998")));// scenario 5
     
    result = "George(5/27/1943)" + "\n" + "Adam(8/12/1972)" + "\n" + "William(6/4/1998)" + "\n"
        + "Nancy(9/12/2003)" + "\n";

    assertTrue(test.toString().compareTo(result) == 0);
    
    assertTrue(test.addPatientRecord(new PatientRecord("Sarah", "1/2/1935")));// scenario 5
   
    result = "Sarah(1/2/1935)" + "\n" + "George(5/27/1943)" + "\n" + "Adam(8/12/1972)" + "\n"
        + "William(6/4/1998)" + "\n" + "Nancy(9/12/2003)" + "\n";

    assertTrue(test.toString().compareTo(result) == 0);

    assertTrue(!test.addPatientRecord(new PatientRecord("Baker", "1/2/1935")));// scenario 6
    assertTrue(test.size() == 5);
 
  }

  /**
   * This method checks mainly for the correctness of the PatientRecordTree.lookup() method. It must
   * consider at least the following test scenarios. (1) Create a new PatientRecordTree. Then, check
   * that calling the lookup() method with any valid date must throw a NoSuchElementException. (2)
   * Consider a PatientRecordTree of height 3 which consists of at least 5 PatientRecordNodes. Then,
   * try to call lookup() method to search for the patient record at the root of the tree, then a
   * patient records at the right and left subtrees at different levels. Make sure that the lookup()
   * method returns the expected output for every method call. (3) Consider calling .lookup() method
   * on a non-empty PatientRecordTree with a date of birth not stored in the tree, and ensure that
   * the method call throws a NoSuchElementException.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  @Test
  public void testAddPatientRecordAndLookup() {
    PatientRecordTree test = new PatientRecordTree();
     assertThrows(NoSuchElementException.class, () -> {
        test.lookup("3/4/2000");
    });
      PatientRecord a =new PatientRecord("Al", "4/3/1992");
      PatientRecord b= new PatientRecord("Tal", "4/3/1973");
      PatientRecord c= new PatientRecord("Baker", "4/3/2000");
      PatientRecord d= new PatientRecord("Gail", "4/3/1993");
      PatientRecord e= new PatientRecord("Mave", "1/5/1963");

      test.addPatientRecord(a);// Scenario 2
      test.addPatientRecord(b);
      test.addPatientRecord(c);
      test.addPatientRecord(d);
      test.addPatientRecord(e);
      assertTrue(test.lookup("4/3/1992").equals(a));
       
      assertTrue(test.lookup("4/3/1993").equals(d));
       
      assertTrue(test.lookup("4/3/1973").equals(b));
        
      assertTrue(test.lookup("4/3/2000").equals(c));
       
      assertTrue(test.lookup("1/5/1963").equals(e));
     
     assertThrows(NoSuchElementException.class, () -> {
        test.lookup("1/6/1993");
       });
     
  }

  /**
   * Checks for the correctness of PatientRecordTree.height() method. This test must consider
   * several scenarios such as, (1) ensures that the height of an empty patient record tree is zero.
   * (2) ensures that the height of a tree which consists of only one node is 1. (3) ensures that
   * the height of a PatientRecordTree with the following structure for instance, is 4. (*) / \ (*)
   * (*) \ / \ (*) (*) (*) / (*)
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  @Test
  public void testHeight() {
    PatientRecordTree tester = new PatientRecordTree();// scenario 1
    assertTrue(tester.height() == 0);
    
    tester.addPatientRecord(new PatientRecord("Adam", "8/12/1972"));// scenario 2
    assertTrue(tester.height() == 1);
  
    tester.addPatientRecord(new PatientRecord("George", "5/27/1943"));
    assertTrue(tester.height() == 2);
    tester.addPatientRecord(new PatientRecord("", "4/3/1953"));
    assertTrue(tester.height() == 3);

    tester.addPatientRecord(new PatientRecord("", "4/3/1992"));// scenario 3
    tester.addPatientRecord(new PatientRecord("", "4/3/1973"));
    tester.addPatientRecord(new PatientRecord("", "4/3/2000"));
    tester.addPatientRecord(new PatientRecord("", "4/3/1993"));

    assertTrue(tester.height() == 4);
  }

  /**
   * Checks for the correctness of PatientRecordTree.getRecordOfYoungestPatient() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  @Test
  public void testGetRecordOfYoungestPatient() {
    PatientRecordTree test = new PatientRecordTree();
    test.addPatientRecord(new PatientRecord("Al", "4/3/1992"));
    test.addPatientRecord(new PatientRecord("Tal", "4/3/1973"));
    test.addPatientRecord(new PatientRecord("Baker", "4/3/2000"));
    test.addPatientRecord(new PatientRecord("Gail", "4/3/1993"));
    test.addPatientRecord(new PatientRecord("Mave", "1/5/1963"));
    assertTrue(test.getRecordOfYoungestPatient().compareTo(new PatientRecord("Baker", "4/3/2000")) == 0);
      
  }

  /**
   * Checks for the correctness of PatientRecordTree.getRecordOfOldestPatient() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  @Test
  public void testGetRecordOfOldestPatient() {
    PatientRecordTree test = new PatientRecordTree();
    test.addPatientRecord(new PatientRecord("Al", "4/3/1992"));
    test.addPatientRecord(new PatientRecord("Tal", "4/3/1973"));
    test.addPatientRecord(new PatientRecord("Baker", "4/3/2000"));
    test.addPatientRecord(new PatientRecord("Gail", "4/3/1993"));
    test.addPatientRecord(new PatientRecord("Mave", "1/5/1963"));
    
    assertTrue(test.getRecordOfOldestPatient().compareTo(new PatientRecord("Mave", "1/5/1963")) == 0);
  
  }

  

}
