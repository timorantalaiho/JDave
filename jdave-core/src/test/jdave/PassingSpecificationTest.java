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
package jdave;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import jdave.runner.SpecRunner;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * @author Joni Freeman
 */
public class PassingSpecificationTest {
    private SpecRunner runner = new SpecRunner();
    private List<Method> actualMethods = new ArrayList<Method>();
    
    @Test
    public void testShouldPassExpectation() throws Exception {
        runner.run(IntegerSpecification.class, new SpecVisitorAdapter(new ResultsAdapter() {
            @Override
            public void expected(Method method) {
                actualMethods.add(method);
            }
        }));
        Collections.sort(actualMethods, new ByNameComparator());
        assertEquals("canBeConvertedToDouble", actualMethods.get(0).getName());
        assertEquals("isPositive", actualMethods.get(1).getName());
        assertEquals("isZero", actualMethods.get(2).getName());
    }
    
    public static class IntegerSpecification extends Specification<Integer> {
        public class Zero {
            private Integer integer;

            public Integer create() {
                integer = 0;
                return integer;
            }
            
            public void isPositive() {
                specify(context, should.be > -1);
            }

            public void toStringIsZero() {
                specify(integer.toString(), should.equal("0"));
            }

            public void isZero() {
                specify(context, should.not().be != 0);
            }
            
            public void canBeConvertedToDouble() {
                specify(integer.doubleValue(), 0.0);
            }
        }
    }
}
