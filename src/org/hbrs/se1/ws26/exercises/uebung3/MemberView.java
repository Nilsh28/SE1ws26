package org.hbrs.se1.ws26.exercises.uebung3;

import java.util.List;

public class MemberView {


    public void dump(List<Member> liste){
        for (int i = 0; i < liste.size(); i++) {
            System.out.println(liste.get(i).toString());
        }
    }
}
