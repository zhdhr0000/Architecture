package com.zhdhr0000.architecture.juliaset.widget;

import android.graphics.Color;
import android.renderscript.Double3;

/**
 * Created by win7 on 2017/3/20.
 */

public class JuliaSetColor extends Color {

    public double hue = 0.0f;
    public double brightness = 0.0f;

    JuliaSetColor() {
    }

    JuliaSetColor(double hue, double brightness) {
        this.hue = hue;
        this.brightness = brightness;
    }

    void init(double hue, double brightness) {
        this.hue = hue;
        this.brightness = brightness;
    }


    class RGBRange {
        Double3 colorIn = new Double3(0, 0, 0);
        Double3 colorOut = new Double3(0, 0, 0);
    }

}