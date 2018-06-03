package src.application;

import org.junit.Assert;
import org.junit.Test;
import src.vertex.*;
import src.edge.*;
import src.graph.*;

import java.util.Arrays;
import java.util.logging.Logger;

public class NetworkTopologyApp {
    @Test
    public void createNetworkTopology(){
        Logger logger=Logger.getLogger("log");
        NetworkTopologyFactory factory=new NetworkTopologyFactory();
        NetworkTopology graph= (NetworkTopology) factory.createGraph("filepath");
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
        graph.addVertex(computer1);
        logger.info("添加顶点"+computer.getLabel());
        graph.addVertex(server);
        logger.info("添加顶点"+server.getLabel());
        graph.addVertex(router);
        logger.info("添加顶点"+router.getLabel());
        graph.addVertex(router1);
        graph.addEdge(networkConnection);
        logger.info("添加边"+networkConnection.getLabel());
        graph.addEdge(networkConnection1);
        logger.info("添加边"+networkConnection1.getLabel());
        graph.addEdge(networkConnection2);
        logger.info("添加边"+networkConnection2.getLabel());
        graph.addEdge(networkConnection3);
        graph.addEdge(networkConnection4);
        graph.addEdge(networkConnection5);
        System.out.println(graph);
        System.out.println(graph);
        graph.addEdge(networkConnection4);
        graph.removeVertex(computer1);
        System.out.println(graph);
    }
}
