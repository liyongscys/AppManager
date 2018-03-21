package com.cabletech.common.util;


public class GeoUtils {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double x=122.85457166699996;
		double y=45.614058333000003;
		double x1=122.85293999999999;
		double y1=45.614734999999996;
		double len=GeoUtils.getDistanceOfTwoPoints(x, y, x1, y1, GaussSphere.WGS84);
		System.out.println(len);
		

	}
	public enum GaussSphere{
        Beijing54,
        Xian80,
        WGS84,
    } 
    private static double Rad(double d){
        return d * Math.PI / 180.0;
    }
    public static float getDistanceOfTwoPoints(double lng1,double lat1,double lng2,double lat2,
    		   GaussSphere gs){
    		        double radLat1 = Rad(lat1);
    		        double radLat2 = Rad(lat2);
    		        double a = radLat1 - radLat2;
    		        double b = Rad(lng1) - Rad(lng2);
    		        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
    		         Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b/2),2)));
    		        s = s * (gs == GaussSphere.WGS84 ? 6378137.0 : (gs == GaussSphere.Xian80 ? 6378140.0 : 6378245.0));
    		        s = Math.round(s * 10000) / 10000;
    		        return (float)s;
    		 }
    public static float getDistanceOfTwoPoints(Double lng1,Double lat1,Double lng2,Double lat2,
 		   GaussSphere gs){
    	return getDistanceOfTwoPoints(lng1.doubleValue(), lat1.doubleValue(), lng2.doubleValue(), lat2.doubleValue(), gs);
    }
    public static float getDistanceOfTwoPoints(Double lng1,Double lat1,Double lng2,Double lat2){
     	return getDistanceOfTwoPoints(lng1, lat1, lng2, lat2, GaussSphere.WGS84);
     }
    public static float getDistanceOfTwoPoints(double lng1,double lat1,double lng2,double lat2){
     	return getDistanceOfTwoPoints(lng1, lat1, lng2, lat2, GaussSphere.WGS84);
     }
    

}
