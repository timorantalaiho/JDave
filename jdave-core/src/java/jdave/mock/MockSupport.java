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
package jdave.mock;

import java.util.ArrayList;
import java.util.List;

import org.jmock.core.InvocationMatcher;
import org.jmock.core.Verifiable;
import org.jmock.core.constraint.IsCloseTo;
import org.jmock.core.constraint.IsEqual;
import org.jmock.core.constraint.IsSame;
import org.jmock.core.matcher.InvokeAtLeastOnceMatcher;
import org.jmock.core.matcher.InvokeAtMostOnceMatcher;
import org.jmock.core.matcher.InvokeCountMatcher;
import org.jmock.core.matcher.InvokeOnceMatcher;
import org.jmock.core.matcher.TestFailureMatcher;
import org.jmock.util.Verifier;

/**
 * @author Joni Freeman
 */
public class MockSupport {
    private List<Verifiable> objectsThatRequireVerification = new ArrayList<Verifiable>();
    
    public void registerToVerify(Verifiable verifiable) {
        objectsThatRequireVerification.add(verifiable);
    }

    public void unregisterToVerify(Verifiable verifiable) {
        objectsThatRequireVerification.remove(verifiable);
    }

    public void verify() {
        for (Verifiable verifiable : objectsThatRequireVerification) {
            verifiable.verify();
        }
        Verifier.verifyObject(this);
    }

    protected <T> Mock<T> mock(Class<T> mockType) {
        Mock<T> mock = new Mock<T>(mockType);
        registerToVerify(mock);
        return mock;
    }

    public InvocationMatcher once() {
        return new InvokeOnceMatcher();
    }

    public InvocationMatcher atLeastOnce() {
        return new InvokeAtLeastOnceMatcher();
    }

    public InvocationMatcher atMostOnce() {
        return new InvokeAtMostOnceMatcher();
    }

    public InvocationMatcher exactly(int expectedCount) {
        return new InvokeCountMatcher(expectedCount);
    }

    public InvocationMatcher never() {
        return new TestFailureMatcher("not expected");
    }

    public InvocationMatcher never(String errorMessage) {
        return new TestFailureMatcher("not expected (" + errorMessage + ")");
    }

    public IsEqual eq(Object operand) {
        return new IsEqual(operand);
    }

    public IsCloseTo eq(double operand, double error) {
        return new IsCloseTo(operand, error);
    }

    public IsSame same(Object operand) {
        return new IsSame(operand);
    }
}
