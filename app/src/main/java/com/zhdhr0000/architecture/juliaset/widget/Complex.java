package com.zhdhr0000.architecture.juliaset.widget;

/**
 * Created by win7 on 2017/3/20.
 */

public class Complex {
    double real;
    double imaginary;

    Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    //内积
    public double magnitude() {
        return Math.sqrt(this.real * this.real + this.imaginary * this.imaginary);
    }

    //加
    public Complex add(Complex another) {
        return new Complex(this.real + another.real, this.imaginary + another.imaginary);
    }

    //乘
    public Complex multiply(Complex another) {
        double newReal = (this.real * another.real) + (this.imaginary * another.imaginary);
        double newImaginary = (this.real * another.imaginary) + (this.imaginary * another.real);
        return new Complex(newReal, newImaginary);
    }

    //减
    public Complex sub(Complex another) {
        return new Complex(this.real - another.real, this.imaginary - another.imaginary);
    }

}
