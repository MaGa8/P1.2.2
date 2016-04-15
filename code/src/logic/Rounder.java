package logic;

/**
 * class performing rounding to a specified accuracy. 
 * Numbers input will always be converted to double type
 * @author martin
 */
public class Rounder
{
	/**
	 * @param power an integer
	 * @return 10 ^ power
	 */
	public static double shift (int power)
	{
		return Math.pow(10, power);
	}
	
	/**
	 * @param number number to round
	 * @param shift factor by which to multiply before rounding and by which to
	 * divide after
	 * @return rounded number
	 */
	public static double round (double number, double shift)
	{
		return (Math.round (number * shift) / shift);
	}
	
	/**
	 * @param precision number of decimals relevant for rounding
	 */
	public Rounder (int precision)
	{
		assert (precision >= 0);
		mPrecision = precision;
	}
	
	/**
	 * @param num number to round
	 * @return num rounded to specified decimal places
	 */
	public double roundDigits (Number num)
	{
		return round (num.doubleValue(), shift (mPrecision));
	}
	
	/**
	 * @param num number to round
	 * @return num rounded to specified significant figures
	 */
	public double roundSignificant (Number num)
	{
		double firstFigure = Math.log10 (num.doubleValue());
		firstFigure = Math.ceil (firstFigure) - mPrecision;
		
		double shift = shift ((int)-firstFigure);
		return (round (num.doubleValue(), shift));
	}
	
	/**
	 * @param n1 a number
	 * @param n2 another number
	 * @return true if n1 ~= n2 for the specified precision/number of digits
	 */
	public boolean epsilonEquals (Number n1, Number n2)
	{
		double eps = Math.pow (10, -mPrecision + 1);
		
		double dn1 = n1.doubleValue(), dn2 = n2.doubleValue();
		
		return (dn1 - eps <= dn2 && dn1 + eps >= dn2);
	}
	
	/**
	 * @param n1 a number
	 * @param n2 another number
	 * @return true if n1, n2 rounded to specified significant figures are equal
	 */
	public boolean significanceEquals (Number n1, Number n2)
	{
		return (roundSignificant (n1) == roundSignificant (n2));
	}
	
	private int mPrecision;
}
