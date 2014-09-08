/**
 * 
 */
package com.rightlawyers.rightlawyersmobile.helper;

/**
 * @author james_r_bray
 *
 */
public class UtilHelper {
	
	public UtilHelper() {}
	
	/**
     * Array concatenation
     * @param arrayA
     * @param arrayB
     * @return
     */
    public String[] concat(String[] arrayA, String[] arrayB) {
    	int aLen = arrayA.length;
    	int bLen = arrayB.length;
    	String[] arrayC= new String[aLen+bLen];
    	System.arraycopy(arrayA, 0, arrayC, 0, aLen);
    	System.arraycopy(arrayB, 0, arrayC, aLen, bLen);
    	return arrayC;
    }

}
