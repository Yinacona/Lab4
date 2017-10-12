package com.company;

/**
 * Created by uuuup on 2017/9/5.
 */
public class Side {
    private String head = new String();
    private String tail = new String();
    private int key;

    public Side(String str1, String str2){
        head = str1;
        tail = str2;
        this.key = 0;
        increase();
    }

    public void increase(){
        this.key++;
    }

    public int getKey(){
        return this.key;
    }

    public String getHead(){
        return head;
    }

    public String getTail(){
        return tail;
    }

    @Override
    public boolean equals(Object obj) {
        boolean bres = false;
        if (obj instanceof Side) {
            Side o = (Side) obj;
            bres = o.getHead().equals(this.getHead()) && o.getTail().equals(this.getTail());
        }
        return bres;
    }
  //  public boolean equals(Side S){
//        return (head.equals(S.getHead()) && tail.equals(S.getTail()) );
//    }

    public static void main(String[] args){
        Side S = new Side("1", "2");
        Side S1 = new Side("1", "2");
        System.out.print(S.equals(S1));
        System.out.print(S.getKey());
    }

}
