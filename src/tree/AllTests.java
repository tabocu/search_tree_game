package tree;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import tree.algorithm.FacadeTest;

@RunWith(Suite.class)
@SuiteClasses({ FacadeTest.class, TreeTest.class })
public class AllTests {

}
