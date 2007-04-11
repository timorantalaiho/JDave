/*
 * Copyright 2007 the original author or authors.
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
package jdave.runner;

import jdave.ILifecycleListener;
import jdave.SpecVisitorAdapter;
import jdave.Specification;

import org.jmock.Mock;
import org.jmock.MockObjectTestCase;

/**
 * @author Joni Freeman
 */
public class LifecycleListenerTest extends MockObjectTestCase {
    private static Mock listener;
    
    public void testCallsListenerForEachContext() throws Exception {
        listener = mock(ILifecycleListener.class);
        listener.expects(exactly(2)).method("afterContextInstantiation");
        listener.expects(exactly(2)).method("afterContextCreation");
        new SpecRunner().run(TestSpec.class, new SpecVisitorAdapter(new DummyBehaviorResults()));
        verify();
    }
    
    public static class TestSpec extends Specification<Void> {
        public TestSpec() {
            addListener((ILifecycleListener) listener.proxy());
        }
        
        public class Context {
            public Void create() {
                return null;
            }
            
            public void behaviour1() {
            }
            
            public void behaviour2() {
            }
        }
    }
}