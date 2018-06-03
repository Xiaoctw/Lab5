package src.application;

import org.junit.Assert;
import org.junit.Test;
import src.graph.*;
import src.edge.*;
import src.vertex.*;

import java.util.Arrays;

public class SocialNetworkApp {
    @Test
    public void createSocialNetwork(){
        SocialNetworkFactory factory=new SocialNetworkFactory();
        SocialNetwork graph= (SocialNetwork) factory.createGraph("filepath");
        Person xiao=new Person("xiao");
        Person li=new Person("li");
        Person han=new Person("han");
        Person tom=new Person("tom");
        Person tim=new Person("tim");
        Person maliya=new Person("maliya");
        Word word=new Word("word");
        CommutTie commutTie=new CommutTie("commuttie0",1);
        CommutTie commutTie1=new CommutTie("committue1",0.17);
        ForwardTie forwardTie=new ForwardTie("forwardTie0",0.24);
        ForwardTie forwardTie1=new ForwardTie("forwardTie1",0.16);
        ForwardTie forwardTie2=new ForwardTie("forwardTie2",0.05);
        FriendTie friendTie=new FriendTie("friendTie0",0.4);
        FriendTie friendTie1=new FriendTie("friendTie1",0.21);
        xiao.fillVertexInfo(new String[]{"male","19"});
        li.fillVertexInfo(new String[]{"female","18"});
        han.fillVertexInfo(new String[]{"male","20"});
        tom.fillVertexInfo(new String[]{"male","19"});
        tim.fillVertexInfo(new String[]{"female","19"});
        maliya.fillVertexInfo(new String[]{"female","19"});
        commutTie.addVertices(Arrays.asList(xiao,li));
        commutTie1.addVertices(Arrays.asList(li,han));
        forwardTie.addVertices(Arrays.asList(tom,maliya));
        forwardTie1.addVertices(Arrays.asList(maliya,han));
        forwardTie2.addVertices(Arrays.asList(tim,li));
        friendTie.addVertices(Arrays.asList(xiao,han));
        friendTie1.addVertices(Arrays.asList(xiao,li));
        SocialNetwork socialNetwork=new SocialNetwork();
        Assert.assertFalse(socialNetwork.addEdge(commutTie));//尚未添加的顶点
        socialNetwork.addVertex(xiao);
        socialNetwork.addVertex(li);
        socialNetwork.addVertex(tim);
        socialNetwork.addVertex(han);
        socialNetwork.addVertex(tom);
        Assert.assertFalse(socialNetwork.addVertex(xiao));
        socialNetwork.addVertex(maliya);
        socialNetwork.addEdge(commutTie);
        socialNetwork.addEdge(commutTie1);
        socialNetwork.addEdge(friendTie);
        socialNetwork.addEdge(friendTie1);
        socialNetwork.addEdge(forwardTie);
        System.out.println(socialNetwork);
        socialNetwork.addEdge(forwardTie1);
        System.out.println(socialNetwork);
        socialNetwork.changeWeight(commutTie,0.6);
        socialNetwork.removeEdge(commutTie);
        System.out.println(socialNetwork.getCommutTieSet());
        System.out.println(socialNetwork.getForwardTieSet());
        System.out.println(socialNetwork.getFriendTieSet());
        socialNetwork.addEdge(commutTie);
        socialNetwork.removeVertex(li);
    }
}
