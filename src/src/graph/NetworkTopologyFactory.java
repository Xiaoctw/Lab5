package src.graph;
import src.vertex.*;
import src.edge.*;
public class NetworkTopologyFactory implements GraphFactory {
    @Override
    public Graph createGraph(String filePath) {
        return new NetworkTopology();
    }
    public Graph createGraph(){return new NetworkTopology();}
    public Computer createComputer(String label){
        return new Computer(label);
    }
    public Router createRouter(String label){
        return new Router(label);
    }
    public Server createServer(String label){
        return new Server(label);
    }
    public NetworkConnection createNetworkConnection(String label,double weight){
        return new NetworkConnection(label, weight);
    }
}
