/*
 * The MIT License
 *
 * Copyright 2016 victor.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package optionpricingcalculator.model;

/**
 *
 * @author victor
 */
public class Util {
    
    private final static double a1 = 0.31938153;
    private final static double a2 = -0.356563782;
    private final static double a3 = 1.781477937;
    private final static double a4 = -1.821255978;
    private final static double a5 = 1.330274429;
    
    public static double cumulativeNormalDistribution(double value) {
        double l, k, w;
        
        l = Math.abs(value);
        k = 1.0 / (1.0 + 0.2316419 * l);
        w = 1.0 - 1.0 / Math.sqrt(2.0 * Math.PI) * 
                Math.exp(-l * l / 2) * 
                (a1 * k + a2 * k *k + a3 * Math.pow(k,3) + a4 * 
                Math.pow(k,4) + a5 * Math.pow(k,5));
        
        if(value < 0.0) return 1.0 - w;
        
        return w;
    }
    
}
