/*
 * 
 *  * (c) kiboy5
 *  * 3rd Year BS in Computer Science @ Holy Angel University
 */
package graphical.ui;

/**
 *
 * @author User
 */
public class Data{
    private int iter;
    private String xl;
    private String xr;
    private String xu;
    private String xi;
    private String xi1;
    private String ximin1;
    private String ea;

    public int getIter() {
        return iter;
    }

    public String getXl() {
        return xl;
    }

    public String getXr() {
        return xr;
    }

    public String getXu() {
        return xu;
    }

    public String getEa() {
        return ea;
    }

    public String getXi() {
        return xi;
    }

    public String getXimin1() {
        return ximin1;
    }

    public String getXi1() {
        return xi1;
    }
    public Data(int iter, String xl, String xr, String xu, String ea){
        this.iter = iter;
        this.xl = xl;
        this.xr = xr;
        this.xu = xu;
        this.ea = ea;
    }

    public Data(int iter, String xi1, String xi, String ea){
        this.iter = iter;
        this.xi1 = xi1;
        this.xi = xi;
        this.ea = ea;
    }
    public Data(int iter, String xi1, String xi, String ximin1, String ea, int x){
        this.iter = iter;
        this.xi1 = xi1;
        this.xi = xi;
        this.ximin1 = ximin1;
        this.ea = ea;
    }
}
