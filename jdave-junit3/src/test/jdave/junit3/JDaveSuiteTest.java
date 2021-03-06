/*
 * Copyright 2006 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jdave.junit3;

import jdave.Specification;
import junit.framework.TestCase;

/**
 * @author Joni Freeman
 */
public class JDaveSuiteTest extends TestCase {
    public void testShouldAddTestSuiteForEachContextInSpecification() throws Exception {
        JDaveSuite suite = new JDaveSuite(TestSpec.class);
        assertEquals(2, suite.testCount());
    }
    
    public class TestSpec extends Specification<Object> {
        public class C1 {
            public void specMethod() {
            }            
        }
        public class C2 {
            public void specMethod() {
            }                        
        }
    }
}
