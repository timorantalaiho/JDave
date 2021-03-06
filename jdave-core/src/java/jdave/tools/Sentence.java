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
package jdave.tools;

/**
 * @author Joni Freeman
 */
public class Sentence {
    private final String sentence;

    private Sentence(String sentence) {
        this.sentence = sentence;        
    }
    
    public static Sentence fromCamelCase(String s) {
        StringBuilder sentence = new StringBuilder();        
        for (int pos = 0; pos < s.length(); pos++) {
            char ch = s.charAt(pos);
            if (pos > 0 && Character.isUpperCase(ch)) {
                sentence.append(" ");
                sentence.append(Character.toLowerCase(ch));
            } else {
                sentence.append(ch);
            }
        }
        return new Sentence(sentence.toString());
    }
    
    @Override
    public String toString() {
        return sentence;
    }
}
