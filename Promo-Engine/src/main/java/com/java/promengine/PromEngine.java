package com.java.promengine;

/**
 * Implementation of a simple promotion engine for a checkout process
 * 
 * @author Ashish
 *
 */
public class PromEngine {

	private int skuIdAcount = 0;
	private int skuIdBcount = 0;
	private int skuIdCcount = 0;
	private int skuIdDcount = 0;
	private int totaUnitSkuIdA = 0;
	private int totaUnitSkuIdB = 0;
	private int skuIdCAndDactivePromotionsPrice = 0;
	private int totaUnitSkuIdCPrice = 0;
	private int totaUnitSkuIdDPrice = 0;
	private int totalPriceskuIdCandD = 0;
	private int totalPriceskuIdA = 0;
	private int totalPriceskuIdB = 0;
	private boolean skuIdCGreaterThenD = false;
	private boolean skuIdDGreaterThenC = false;
	private boolean skuIdCEqualToD = false;

	// Private Static variable
	private static char skuIdA = 'A';
	private static char skuIdB = 'B';
	private static char skuIdC = 'C';
	private static char skuIdD = 'D';

	/**
	 * SKU ids (A, B, C. ..) over which the promotion engine will need to run.
	 * 
	 * @param skuIds
	 * @return
	 */
	public int getCartCheckoutAmount(char[] skuIds) {

		for (int i = 0; i < skuIds.length; i++) {
			char skuIdsChar = skuIds[i];
			if (skuIdsChar == skuIdA) {
				skuIdAcount++;
			}
			if (skuIdsChar == skuIdB) {
				skuIdBcount++;
			}
			if (skuIdsChar == skuIdC) {
				skuIdCcount++;
			}
			if (skuIdsChar == skuIdD) {
				skuIdDcount++;
			}
		}

		// Calculating A price - buy 'A' items of a SKU for a fixed price (3 A's for
		// 130)
		getTotalPriceForUnitA(skuIdAcount);

		// Calculating B price - buy 'B' items of a SKU for a fixed price (2 B's for 45)
		getTotalPriceForUnitB(skuIdBcount);

		// find c and d count
		if (skuIdCcount > skuIdDcount) {
			skuIdCGreaterThenD = true;
		}
		if (skuIdCcount < skuIdDcount) {
			skuIdDGreaterThenC = true;
		}
		if (skuIdCcount == skuIdDcount) {
			skuIdCEqualToD = true;
		}

		// Buy SKU 1 & SKU 2 for a fixed price ( C + D = 30 ) if C count greater then D
		getTotalUnitPriceIfskuIdCGreaterThenD(skuIdCGreaterThenD, skuIdDcount);

		// Buy SKU 1 & SKU 2 for a fixed price ( C + D = 30 ) if D count greater then C
		getTotalUnitPriceIfskuIdDGreaterThenC(skuIdDGreaterThenC, skuIdCcount);

		// Buy SKU 1 & SKU 2 for a fixed price ( C + D = 30 ) if C equal to D
		getTotalUnitPriceIfskuIdCEqualToD(skuIdCEqualToD);

		// Buy SKU 1 & SKU 2 for a fixed price ( C + D = 30 ) if D count greater then C
		// and C count is ZERO
		getTotalUnitPriceIfskuIdCGreaterThenDAndskuIdDcount(skuIdCGreaterThenD, skuIdDcount);

		// Buy SKU 1 & SKU 2 for a fixed price ( C + D = 30 ) if C count greater then D
		// and D count is ZERO
		getTotalUnitPriceIfskuIdDGreaterThenCAndskuIdCcount(skuIdDGreaterThenC, skuIdCcount);

		return totalPriceskuIdCandD + totalPriceskuIdA + totalPriceskuIdB;
	}

	/**
	 * To calculate Unit A total prince including Active Promotions
	 * 
	 * @param skuIdAcount
	 */
	private void getTotalPriceForUnitA(int skuIdAcount) {

		int activePromotionsACount = skuIdAcount / 3;
		int totalActivePromotionsAPrice = activePromotionsACount * 130;
		totaUnitSkuIdA = skuIdAcount - (activePromotionsACount * 3);
		int totaUnitPriceSkuIdA = totaUnitSkuIdA * 50;
		totalPriceskuIdA = totaUnitPriceSkuIdA + totalActivePromotionsAPrice;

	}

	/**
	 * To calculate Unit B total prince including Active Promotions
	 * 
	 * @param skuIdBcount
	 */
	private void getTotalPriceForUnitB(int skuIdBcount) {

		int activePromotionsBCount = skuIdBcount / 2;
		int totalActivePromotionsBPrice = activePromotionsBCount * 45;
		totaUnitSkuIdB = skuIdBcount - (activePromotionsBCount * 2);
		int totaUnitPriceSkuIdB = totaUnitSkuIdB * 30;
		totalPriceskuIdB = totaUnitPriceSkuIdB + totalActivePromotionsBPrice;
	}

	/**
	 * To calculate total prince of Unit C and D if Unit C is greater then Unit D
	 * 
	 * @param skuIdCGreaterThenD
	 * @param skuIdDcount
	 */
	private void getTotalUnitPriceIfskuIdCGreaterThenD(boolean skuIdCGreaterThenD, int skuIdDcount) {

		if (skuIdCGreaterThenD && skuIdDcount > 0) {
			for (int i = 0; i < skuIdCcount; i++) {
				if (skuIdDcount > 0) {
					skuIdCAndDactivePromotionsPrice = +30;
					skuIdCcount--;
					skuIdDcount--;
				}
			}
			totaUnitSkuIdCPrice = skuIdCcount * 20;
			totalPriceskuIdCandD = totaUnitSkuIdCPrice + skuIdCAndDactivePromotionsPrice;
		}

	}

	/**
	 * To calculate total prince of Unit C and D if Unit D is greater then Unit C
	 * 
	 * @param skuIdDGreaterThenC
	 * @param skuIdCcount
	 */
	private void getTotalUnitPriceIfskuIdDGreaterThenC(boolean skuIdDGreaterThenC, int skuIdCcount) {

		if (skuIdDGreaterThenC && skuIdCcount > 0) {
			for (int i = 0; i < skuIdDcount; i++) {
				if (skuIdCcount > 0) {
					skuIdCAndDactivePromotionsPrice = +30;
					skuIdCcount--;
					skuIdDcount--;
				}
			}
			totaUnitSkuIdDPrice = skuIdDcount * 20;
			totalPriceskuIdCandD = totaUnitSkuIdDPrice + skuIdCAndDactivePromotionsPrice;
		}

	}

	/**
	 * To calculate total prince of Unit C and D if Unit C is equal to Unit D
	 * 
	 * @param skuIdCEqualToD
	 */
	private void getTotalUnitPriceIfskuIdCEqualToD(boolean skuIdCEqualToD) {
		if (skuIdCEqualToD) {
			for (int i = 0; i < skuIdCcount; i++) {
				skuIdCAndDactivePromotionsPrice = +30;
				skuIdCcount--;
				skuIdDcount--;
			}
			totalPriceskuIdCandD = skuIdCAndDactivePromotionsPrice;
		}
	}

	/**
	 * To calculate total prince of Unit C and D if Unit C is greater then Unit D
	 * and D Unit Count is ZERO
	 * 
	 * @param skuIdCGreaterThenD
	 * @param skuIdDcount
	 */
	private void getTotalUnitPriceIfskuIdCGreaterThenDAndskuIdDcount(boolean skuIdCGreaterThenD, int skuIdDcount) {

		if (skuIdCGreaterThenD && skuIdDcount == 0) {
			totaUnitSkuIdCPrice = skuIdCcount * 20;
			totaUnitSkuIdDPrice = skuIdDcount * 20;
			totalPriceskuIdCandD = totaUnitSkuIdCPrice + totaUnitSkuIdDPrice;
		}
	}

	/**
	 * To calculate total prince of Unit C and D if Unit D is greater then Unit C
	 * and C Unit Count is ZERO
	 * 
	 * @param skuIdDGreaterThenC
	 * @param skuIdCcount
	 */
	private void getTotalUnitPriceIfskuIdDGreaterThenCAndskuIdCcount(boolean skuIdDGreaterThenC, int skuIdCcount) {

		if (skuIdDGreaterThenC && skuIdCcount == 0) {
			totaUnitSkuIdCPrice = skuIdCcount * 20;
			totaUnitSkuIdDPrice = skuIdDcount * 20;
			totalPriceskuIdCandD = totaUnitSkuIdCPrice + totaUnitSkuIdDPrice;
		}
	}

}
