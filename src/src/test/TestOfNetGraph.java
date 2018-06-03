package src.test;
import org.junit.Assert;
import org.junit.Test;
import src.edge.*;
import src.graph.*;
import src.vertex.*;
import org.junit.Before;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class TestOfNetGraph {
    Computer computer=new Computer("计算机0");
    Computer computer1=new Computer("计算机1");
    Computer computer2=new Computer("计算机2");
    Computer computer3=new Computer("计算机3");
    Router router=new Router("路由器0");
    Router router1=new Router("路由器1");
    Router router2=new Router("路由器2");
    Router router3=new Router("路由器3");
    Server server=new Server("服务器0");
    Server server1=new Server("服务器1");
    Server server2=new Server("服务器2");
    Server server3=new Server("服务器3");
    NetworkConnection networkConnection=new NetworkConnection("label0",3.0);
    NetworkConnection networkConnection1=new NetworkConnection("label1",2.0);
    NetworkConnection networkConnection2=new NetworkConnection("label2",3.0);
    NetworkConnection networkConnection3=new NetworkConnection("label3",1.5);
    NetworkConnection networkConnection4=new NetworkConnection("label4",2.5);
    NetworkConnection networkConnection5=new NetworkConnection("label5",3.5);
    NetworkConnection networkConnection6=new NetworkConnection("label6",4.0);
    NetworkTopology graph=new NetworkTopology();
    @Before
    public void initVertex(){
        computer.fillVertexInfo(new String[]{"156.142.4.1"});
        computer1.fillVertexInfo(new String[]{"156.142.4.2"});
        computer2.fillVertexInfo(new String[]{"156.142.4.3"});
        computer3.fillVertexInfo(new String[]{"156.142.4.4"});
        router.fillVertexInfo(new String[]{"156.142.5.1"});
        router1.fillVertexInfo(new String[]{"156.142.5.2"});
        router2.fillVertexInfo(new String[]{"156.142.5.3"});
        router3.fillVertexInfo(new String[]{"156.142.5.4"});
        server.fillVertexInfo(new String[]{"156.142.6.1"});
        server1.fillVertexInfo(new String[]{"156.142.6.2"});
        server2.fillVertexInfo(new String[]{"156.142.6.3"});
        server3.fillVertexInfo(new String[]{"156.142.6.4"});
        networkConnection.addVertices(Arrays.asList(computer1,router));
        networkConnection1.addVertices(Arrays.asList(server,computer1));
        networkConnection2.addVertices(Arrays.asList(router,server));
        networkConnection3.addVertices(Arrays.asList(computer2,server));
        networkConnection4.addVertices(Arrays.asList(computer2,router1));
        networkConnection5.addVertices(Arrays.asList(router1,computer1));
        networkConnection6.addVertices(Arrays.asList(server,router1));
    }
    @Test
    public void testVertex(){
        System.out.println(computer);
        System.out.println(router);
        System.out.println(server);
        Assert.assertEquals(computer.equals(computer1),false);
        Assert.assertEquals(computer.equals(computer),true);
        Assert.assertEquals(computer.equals(router),false);
    }
    @Test
    public void testEdge(){
        Assert.assertEquals(networkConnection.equals(networkConnection1),false);
        Assert.assertEquals(networkConnection.equals(networkConnection),true);
        Assert.assertEquals(networkConnection.containVertex(computer1),true);
        Assert.assertEquals(networkConnection.containVertex(server),false);
        System.out.println(networkConnection);
        System.out.println(networkConnection1);
    }
    @Test
    public void testGraph(){
        graph.addVertex(computer1);
        graph.addVertex(server);
        graph.addVertex(router);
        graph.addVertex(router1);
        Assert.assertEquals(graph.addEdge(networkConnection3),false);//边中包含还没有加入的顶点
        Assert.assertEquals(graph.addVertex(computer2),true);
        graph.addEdge(networkConnection);
        Assert.assertEquals(graph.addEdge(networkConnection),false);
        graph.addEdge(networkConnection1);
        graph.addEdge(networkConnection2);
        graph.addEdge(networkConnection3);
        graph.addEdge(networkConnection4);
        graph.addEdge(networkConnection5);
        System.out.println(graph);
        Assert.assertEquals(graph.removeEdge(networkConnection4),true);
        System.out.println(graph);
        graph.addEdge(networkConnection4);
        Assert.assertEquals(graph.removeEdge(networkConnection6),false);
        Assert.assertEquals(graph.removeVertex(computer3),false);
        graph.removeVertex(computer1);
        System.out.println(graph);
    }
}
