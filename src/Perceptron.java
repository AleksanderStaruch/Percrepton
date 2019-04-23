import java.io.File;
import java.util.*;

public class Perceptron {
    private List<Point> list;
    private int vectorSize;
    private List<String> set;

    public Perceptron(String path) {
        list=new ArrayList<>();
        set= new ArrayList<>();
        try{
            File file = new File(path);
            Scanner od = new Scanner(file);
            String linia;
            String[] tmp;
            while(od.hasNext()){
                linia=od.nextLine();
                tmp=linia.split(",");
                double[] tab=new double[tmp.length-1];
                vectorSize=tmp.length-1;

                for(int i=0;i<tmp.length-1;i++){
                    tab[i]=Double.parseDouble(tmp[i]);
                }
                list.add(new Point(tab,tmp[tmp.length-1]));
                if(!set.contains(tmp[tmp.length-1])){
                    set.add(tmp[tmp.length-1]);
                }
            }
            od.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private double[] wagi;
    private double teta;

    public void learn(double teta,double n){
        double[] w= new double[vectorSize];
        for(int i=0;i<vectorSize;i++){
            Random random = new Random();
            double tmp=random.nextDouble()*90;
            w[i]=(double) Math.round(tmp)/10;
        }
//        double[] w={2,0,2,4};
        int count=0;
        for(Point point:list){
            double[] tmp=point.getTab();
            int d=set.indexOf(point.getName());
            double net=0;
            for(int i=0;i<vectorSize;i++){
                net+=w[i]*tmp[i];
            }
            net-=teta;
            int y;
            if(net >=0){
                y=1;
            }else{
                y=0;
            }

            if((d-y)!=0){
                count++;
            }

            for(int i=0;i<vectorSize;i++) {
                w[i]=w[i]+n*(d-y)*tmp[i];
                teta=teta-n*(d-y);
            }
        }
        for(int i=0;i<vectorSize;i++){
            double tmp=w[i]*10;
            w[i]=(double) Math.round(tmp)/10;
        }
        this.wagi=w;
        this.teta=teta;
        System.out.println(count);
    }

    public String checkDataTestAccuracy(String path){
        String r="";
        List<Point> testList=new ArrayList<>();
        try{
            File file = new File(path);
            Scanner od = new Scanner(file);
            String linia;
            String[] tmp;
            while(od.hasNext()){
                linia=od.nextLine();
                tmp=linia.split(",");
                double[] tab=new double[tmp.length-1];

                for(int i=0;i<tmp.length-1;i++){
                    tab[i]=Double.parseDouble(tmp[i]);
                }
                testList.add(new Point(tab,tmp[tmp.length-1]));
            }
            od.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }

        for(Point p:testList){
            double[] tab=p.getTab();
            double net=0;
            for(int i=0;i<tab.length;i++){
                net+=tab[i]*wagi[i];
            }
            net-=teta;
            int y;
            if(net >=0){
                y=1;
            }else{
                y=0;
            }
            if(y==set.indexOf(p.getName())){
                r+=(p+" +");
                r+="\n\r";
            }else{
                r+=(p+" -");
                r+="\n\r";
            }
        }

        r+=(Arrays.toString(wagi)+" "+teta);
        r+="\n\r";
        return r;
    }

    public String whichFlower(List <String> list){
        try {
            double[] tab = new double[list.size()];
            for(int i=0;i<list.size();i++){
                tab[i]=Double.parseDouble(list.get(i));
            }

            double net=0;
            for(int i=0;i<tab.length;i++){
                net+=tab[i]*wagi[i];
            }
            net-=teta;
            int y;
            if(net >=0){
                y=1;
            }else{
                y=0;
            }

            return set.get(y);

        } catch (Exception e) {
             return "Error";
        }
    }
}
