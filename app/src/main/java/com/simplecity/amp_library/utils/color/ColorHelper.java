package com.simplecity.amp_library.utils.color;


import android.app.Notification;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.util.Pair;
import android.util.Log;

 private static final String TAG = "ColorHelper";
Add comment
More actions




    // Eager Singleton instance


    private static final ColorHelper INSTANCE = new ColorHelper();





    /** @return the singleton instance */

    public static ColorHelper getInstance() {


        return INSTANCE;






    }




    /** Prevent instantiation */


    private ColorHelper() {}





    // contrast‐finding using LAB







    private static int findContrastColor(int color, int other, boolean findFg, double minRatio) {

        int fg = findFg ? color : other;

        int bg = findFg ? other : color;

@@ -68,32 +41,26 @@ private static int findContrastColor(int color, int other, boolean findFg, doubl

        double[] lab = new double[3];

        ColorUtilsFromCompat.colorToLAB(findFg ? fg : bg, lab);




        double low = 0;


        double high = lab[0];


        final double a = lab[1];


        final double bb = lab[2];

        for (int i = 0; i < 15 && high - low > 0.00001; i++) {

            final double l = (low + high) / 2;

            if (findFg) {


                fg = ColorUtilsFromCompat.LABToColor(l, a, bb);

            } else {


                bg = ColorUtilsFromCompat.LABToColor(l, a, bb);

            }

            if (ColorUtilsFromCompat.calculateContrast(fg, bg) > minRatio) {

                low = l;

            } else {

                high = l;

            }

        }


        return ColorUtilsFromCompat.LABToColor(low, a, bb);

    }











    private static int findAlphaToMeetContrast(int color, int backgroundColor, double minRatio) {

        int fg = color;

        int bg = backgroundColor;

@@ -105,7 +72,8 @@ private static int findAlphaToMeetContrast(int color, int backgroundColor, doubl

        int g = Color.green(color);

        int b = Color.blue(color);




        int low = startAlpha;


        int high = 255;

        for (int i = 0; i < 15 && high - low > 0; i++) {

            final int alpha = (low + high) / 2;

            fg = Color.argb(alpha, r, g, b);

@@ -118,18 +86,7 @@ private static int findAlphaToMeetContrast(int color, int backgroundColor, doubl

        return Color.argb(high, r, g, b);

    }




    private static int findContrastColorAgainstDark(int color, int other, boolean findFg, double minRatio) {












        int fg = findFg ? color : other;

        int bg = findFg ? other : color;

        if (ColorUtilsFromCompat.calculateContrast(fg, bg) >= minRatio) {

@@ -139,7 +96,8 @@ private static int findContrastColorAgainstDark(int color, int other, boolean fi

        float[] hsl = new float[3];

        ColorUtilsFromCompat.colorToHSL(findFg ? fg : bg, hsl);




        float low = hsl[2];


        float high = 1f;

        for (int i = 0; i < 15 && high - low > 0.00001; i++) {

            final float l = (low + high) / 2;

            hsl[2] = l;

@@ -157,41 +115,25 @@ private static int findContrastColorAgainstDark(int color, int other, boolean fi

        return findFg ? fg : bg;

    }











    private static int changeColorLightness(int baseColor, int amount) {


        final double[] lab = ColorUtilsFromCompat.getTempDouble3Array();


        ColorUtilsFromCompat.colorToLAB(baseColor, lab);


        lab[0] = Math.max(Math.min(100, lab[0] + amount), 0);


        return ColorUtilsFromCompat.LABToColor(lab[0], lab[1], lab[2]);

    }



    public static int resolvePrimaryColor(Context context, int backgroundColor) {

        boolean useDark = shouldUseDark(backgroundColor);


        return context.getResources().getColor(


            useDark ? android.R.color.primary_text_light : android.R.color.primary_text_dark


        );





    }



    public static int resolveSecondaryColor(Context context, int backgroundColor) {

        boolean useDark = shouldUseDark(backgroundColor);


        return context.getResources().getColor(


            useDark ? android.R.color.secondary_text_light : android.R.color.secondary_text_dark


        );





    }



    private static boolean shouldUseDark(int backgroundColor) {

@@ -202,16 +144,16 @@ private static boolean shouldUseDark(int backgroundColor) {

        return useDark;

    }




    private static double calculateLuminance(int color) {


        return ColorUtilsFromCompat.calculateLuminance(color);

    }




    private static double calculateContrast(int fg, int bg) {


        return ColorUtilsFromCompat.calculateContrast(fg, bg);

    }




    private static boolean satisfiesTextContrast(int bg, int fg) {


        return calculateContrast(fg, bg) >= 4.5;

    }



    static boolean isColorLight(int backgroundColor) {

@@ -659,87 +601,64 @@ static void RGBToHSL(@IntRange(from = 0x0, to = 0xFF) int r,

    }









    private static final int LIGHTNESS_TEXT_DIFFERENCE_LIGHT = 20;


    private static final int LIGHTNESS_TEXT_DIFFERENCE_DARK  = -10;





    public Pair<Integer, Integer> ensureColors(


        Context context,


        boolean hasFg,


        int bgColor,


        int fgColor


    ) {


        int primary;


        int secondary;


        if (!hasFg) {


            primary   = resolvePrimaryColor(context, bgColor);


            secondary = resolveSecondaryColor(context, bgColor);


            if (bgColor != 0) {


                primary   = findAlphaToMeetContrast(primary, bgColor, 4.5);


                secondary = findAlphaToMeetContrast(secondary, bgColor, 4.5);



            }

        } else {


            double backLum = calculateLuminance(bgColor);


            double textLum = calculateLuminance(fgColor);


            double contrast= calculateContrast(fgColor, bgColor);


            boolean bgLight = (backLum > textLum && satisfiesTextContrast(bgColor, Color.BLACK))


                           || (backLum <= textLum && !satisfiesTextContrast(bgColor, Color.WHITE));




            if (contrast < 4.5f) {


                if (bgLight) {


                    secondary = findContrastColor(fgColor, bgColor, true, 4.5f);


                    primary   = changeColorLightness(secondary, -LIGHTNESS_TEXT_DIFFERENCE_LIGHT);






                } else {


                    secondary = findContrastColorAgainstDark(fgColor, bgColor, true, 4.5f);


                    primary   = changeColorLightness(secondary, -LIGHTNESS_TEXT_DIFFERENCE_DARK);







                }

            } else {


                primary   = fgColor;


                secondary = changeColorLightness(


                    primary,


                    bgLight ? LIGHTNESS_TEXT_DIFFERENCE_LIGHT : LIGHTNESS_TEXT_DIFFERENCE_DARK


                );


                if (calculateContrast(secondary, bgColor) < 4.5f) {


                    if (bgLight) {


                        secondary = findContrastColor(secondary, bgColor, true, 4.5f);






                    } else {


                        secondary = findContrastColorAgainstDark(


                            secondary,


                            bgColor,


                            true,


                            4.5f


                        );

                    }


                    primary = changeColorLightness(


                        secondary,


                        bgLight


                            ? -LIGHTNESS_TEXT_DIFFERENCE_LIGHT


                            : -LIGHTNESS_TEXT_DIFFERENCE_DARK


                    );

                }

            }

        }


        return new Pair<>(primary, secondary);

    }

}