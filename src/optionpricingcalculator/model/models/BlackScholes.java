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
package optionpricingcalculator.model.models;

import java.util.Date;
import optionpricingcalculator.model.Util;

/**
 * Black-Scholes model algorithms
 * 
 * @author victor
 */
public class BlackScholes {
        
    /**
     * Based on the 73s formula.
     * For more information, consult: http://www.jstor.org/stable/1831029
     * 
     * @param putCallParity true if the price of a corresponding put option is 
     * based on a put-call parity
     * @param stockPrice spot price of the underlying asset
     * @param strikePrice strike price
     * @param yearsToMaturity difference in years to maturity 
     * (Maturity year - current year)
     * @param riskFreeRate annual rate, expressed in terms of continuous 
     * compounding
     * @param volatility volatility of returns of the underlying asset
     * @return call value
     */
    public static double calculate(boolean putCallParity, double stockPrice, 
            double strikePrice, double yearsToMaturity, double riskFreeRate, 
            double volatility) {
        double d1, d2;
        
        d1 = (Math.log(stockPrice / strikePrice) + 
                (riskFreeRate + volatility * volatility / 2) * 
                yearsToMaturity) / (volatility * Math.sqrt(yearsToMaturity));
        
        d2 = d1 - volatility * Math.sqrt(yearsToMaturity);
        
        if(putCallParity) 
            return strikePrice * Math.exp(-riskFreeRate * yearsToMaturity) * 
                    Util.cumulativeNormalDistribution(-d2) - stockPrice * 
                    Util.cumulativeNormalDistribution(-d1);
        else
            return stockPrice * Util.cumulativeNormalDistribution(d1) - 
                    strikePrice * Math.exp(-riskFreeRate * yearsToMaturity) * 
                    Util.cumulativeNormalDistribution(d2);        
    }
    
    /**
     * Based on the 73s formula.
     * For more information, consult: http://www.jstor.org/stable/1831029
     * 
     * @param putCallParity true if the price of a corresponding put option is 
     * based on a put-call parity
     * @param stockPrice spot price of the underlying asset
     * @param strikePrice strike price
     * @param expirationDate Expiration date in java.util.Date format
     * @param riskFreeRate annual rate, expressed in terms of continuous 
     * compounding
     * @param volatility volatility of returns of the underlying asset
     * @return call value
     */
    public static double calculate(boolean putCallParity, double stockPrice, 
            double strikePrice, Date expirationDate, double riskFreeRate, 
            double volatility) {
        final long differenceInDays = (expirationDate.getTime() - 
                new Date().getTime()) / (1000 * 60 * 60 * 24);
        final double yearsToMaturity = differenceInDays / 365;
        
        return calculate(putCallParity, stockPrice, strikePrice, 
                yearsToMaturity, riskFreeRate, volatility);
    }

}
