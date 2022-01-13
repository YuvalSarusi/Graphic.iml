package Primitives;

public class Material {

    private double kd; //the light's scattering
    private double ks; //the light's  returning
    private int nShininess; //light's shining
    private double kr;

    public Material(double kd, double ks, int nShininess, double kr) {
        this.kd = kd;
        this.ks = ks;
        this.nShininess = nShininess;
        this.kr = kr;
    }

    public int getnShininess() {
        return nShininess;
    }

    public void setnShininess(int nShininess) {
        this.nShininess = nShininess;
    }

    public double getKr() {
        return kr;
    }

    public void setKr(double kr) {
        this.kr = kr;
    }

    public double getKd() {
        return kd;
    }

    public void setKd(double kd) {
        this.kd = kd;
    }

    public double getKs() {
        return ks;
    }

    public void setKs(double ks) {
        this.ks = ks;
    }

    public int getNShininess() {
        return nShininess;
    }

    public void setNShininess(int nShininess) {
        this.nShininess = nShininess;
    }

    public Material(double kd, double ks, int nShininess) {
        this.kd = kd;
        this.ks = ks;
        this.nShininess = nShininess;
    }

    public Material(Material material){
        this.kd = material.kd;
        this.ks = material.ks;
        this.nShininess = material.nShininess;
    }
}
