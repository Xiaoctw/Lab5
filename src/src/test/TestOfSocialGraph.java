package src.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import src.vertex.*;
import src.edge.*;
import src.graph.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestOfSocialGraph {
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
    @Before
    public void init(){
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

    }
    @Test
    public void testPerson(){
        System.out.println(xiao.toString());
        Assert.assertEquals(xiao.equals(han),false);
        Assert.assertEquals(xiao.equals(word),false);
        Assert.assertEquals(xiao.equals(xiao),true);
    }
    @Test
    public void testEdge(){

        System.out.println(commutTie.toString());
        System.out.println(commutTie1.toString());
        Assert.assertEquals(commutTie.containVertex(xiao),true);
        Assert.assertEquals(commutTie.equals(commutTie1),false);
        Assert.assertEquals(commutTie.equals(commutTie),true);
    }
    @Test
    public void testGraph(){
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
           double sum=0;
        for (Edge edge:socialNetwork.edges()) { sum+=edge.getWeight(); }
        Assert.assertEquals(sum,1,0.001);
        socialNetwork.changeWeight(commutTie,0.6);
        double sum1=0,sum2=0,sum3=0;
        Assert.assertEquals(commutTie.getWeight(),0.6,0.001);
        for (Edge edge:socialNetwork.edges()) { sum1+=edge.getWeight(); }
        Assert.assertEquals(sum1,1.0,0.01);
        socialNetwork.removeEdge(commutTie);
        for (Edge edge:socialNetwork.edges()) { sum2+=edge.getWeight(); }
        Assert.assertEquals(sum2,1.0,0.01);
        System.out.println(socialNetwork.getCommutTieSet());
        System.out.println(socialNetwork.getForwardTieSet());
        System.out.println(socialNetwork.getFriendTieSet());
        socialNetwork.addEdge(commutTie);
        socialNetwork.removeVertex(li);
        for (Edge edge:socialNetwork.edges()) { sum3+=edge.getWeight(); }
        Assert.assertEquals(sum3,1.0,0.01);
    }
}

