package org.hbrs.se1.ws26.exercises.uebung3;

import java.util.List;

public class MemberView {


    public void dump(List<Member> liste){
        for (Member member : liste) {
            System.out.println(member.toString());
        }
    }
}
