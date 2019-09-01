package com.example.mizansen.Helper;


public class StringXORerHelper {


    static String ApiKey = "23jel` uQ%sBNzimt?eXu%+3eB<|e*^H:LDxzszU><Q/`$^/<<3d#,!Z@l_:[Og2";
    static String SecretKey = "+a1M0%bg,))|+QP7=pT{rW.WLh8)2GQb;|p|+^K%3r3o6I26tm?TF@ciP-;:--*9";

    public static void main() { // test
        int[] encrypted = encrypt(ApiKey,SecretKey);
        for(int i = 0; i < encrypted.length; i++)
            System.out.printf("%d,", encrypted[i]);
        System.out.println("");
        System.out.println(decrypt(encrypted,SecretKey));
    }

    public int[] getXor(){
        return encrypt(ApiKey,SecretKey);
    }

    private static int[] encrypt(String str, String key) {
        int[] output = new int[str.length()];
        for(int i = 0; i < str.length(); i++) {
            int o = (Integer.valueOf(str.charAt(i)) ^ Integer.valueOf(key.charAt(i % (key.length() - 1)))) + '0';
            output[i] = o;
        }
        return output;
    }

    private static int[] string2Arr(String str) {
        String[] sarr = str.split(",");
        int[] out = new int[sarr.length];
        for (int i = 0; i < out.length; i++) {
            out[i] = Integer.valueOf(sarr[i]);
        }
        return out;
    }

    private static String decrypt(int[] input, String key) {
        String output = "";
        for(int i = 0; i < input.length; i++) {
            output += (char) ((input[i] - 48) ^ (int) key.charAt(i % (key.length() - 1)));
        }
        return output;
    }
    
}
