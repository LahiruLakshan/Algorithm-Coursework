/*
* 	IIT ID : 20191277
* 	UOW : 1790796
* 	Name : Lahiru Lakshan Tissera
*	Reference : https://www.geeksforgeeks.org/ford-fulkerson-algorithm-for-maximum-flow-problem/
* */
import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.LinkedList;
public class MaxFlow {

	private static int numVertices;


	public static void main(String[] args){
		long start = System.currentTimeMillis();
		MaxFlow m = new MaxFlow();

		int[][] graph = readingFromFile(); //Get the file and assign to 2D array

		if (graph != null){
			numVertices = graph.length;
			displayGraph(graph); 	// Display graph(Matrix)

			// Displaying the Generated Maximum Flow
			System.out.println("\nThe maximum possible flow is " + m.fordFulkerson(graph, 0, (graph.length - 1)));

			long now = System.currentTimeMillis();	// Getting the times taken
			double elapsed = (now - start) / 1000.0;
			System.out.println("Elapsed time = " + elapsed + " seconds");
		}
	}

	private static int[][] readingFromFile() {
		ArrayList<String> dataInput = new ArrayList<>();
		try {
			File myObj = new File("benchmarks/cw.txt"); // getting input file
			System.out.println("Collecting data from the file . . .");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				dataInput.add(data);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Something went wrong, File not present!");
		}
		if (dataInput.size() != 0) {
			return generateGraph(dataInput);
		} else {
			System.out.println("No data in file");
			return null;
		}
	}

	public static int[][] generateGraph(ArrayList<String> inputData){
		// Generating the graph matrix
		int graphSize = Integer.parseInt(inputData.get(0).trim());
		int[][] graph = new int[graphSize][graphSize];
		System.out.println("Creating Adjacent Matrix . . .");


		for (int item = 1; item < inputData.size(); item++) {
			String[] splitItem = inputData.get(item).split(" ");
			int x = Integer.parseInt(splitItem[0].trim());
			int y = Integer.parseInt(splitItem[1].trim());
			int capacity = Integer.parseInt(splitItem[2].trim());

			addEdge(x, y, capacity, graph);
		}

		return graph;
	}

	private static void addEdge(int x, int y, int capacity, int[][] graph) {
		graph[x][y] = capacity; // adding the edge
	}

	public static void displayGraph(int[][] graph){
		// Displaying the graph
		int numberOfEdges = 0;
		for (int[] dataRow : graph) {
			for (int index = 0; index< graph.length; index++) {
				if(dataRow[index] > 0){
					numberOfEdges++;
				}
				System.out.print(dataRow[index] + " ");		// Display Matrix
			}
			System.out.println();
		}
		System.out.println("\nTotal of " + graph.length + " vertices present");
		System.out.println("Total of " + numberOfEdges + " edges present");
		System.out.println("The Source Node = 0");
		System.out.println("The Target Node = " + (graph.length - 1));
		System.out.println("\n");
	}

	private boolean bfs(int[][] rGraph, int source, int target, int[] parent) {

		// Create a visited array and mark all vertices as not visited
		boolean[] visited = new boolean[numVertices];
		for (int i = 0; i < numVertices; ++i)
			visited[i] = false;

		// Create a queue, enqueue source vertex and mark
		// source vertex as visited
		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.add(source);
		visited[source] = true;
		parent[source] = -1;

		while (queue.size() != 0) {
			// Dequeue a vertex from queue
			int u = queue.poll();
			for (int v = 0; v < numVertices; v++) {
				if (!visited[v] && rGraph[u][v] > 0) {
					// If we find a connection to the target node, then there is no point in BFS anymore We just have to set its parent and can return true

					if (v == target) {
						parent[v] = u;

						return true;
					}
					queue.add(v);
					parent[v] = u;
					visited[v] = true;
				}
			}
		}

		return false;// We didn't reach sink in BFS starting from source, so return false
	}

	// Returns tne maximum flow from s to t in the given graph
	private int fordFulkerson(int[][] graph, int source, int target) {

		int u, v;

		int[][] rGraph = new int[numVertices][numVertices];
		for (u = 0; u < numVertices; u++)
			for (v = 0; v < numVertices; v++) {
				rGraph[u][v] = graph[u][v];
			}

		// This array is filled by BFS and to store path
		int[] parent = new int[numVertices];
		int maxFlow = 0;	// There is no flow initially

		// Augment the flow while there is path from source to target
		while (bfs(rGraph, source, target, parent)) {
			// Find minimum residual capacity of the edges along the path filled by BFS. Or we can say find the maximum flow through the path found.
			int pathFlow = Integer.MAX_VALUE;

			ArrayList<Integer> augmentPath = new ArrayList();
			for (v = target; v != source; v = parent[v]) {
//				System.out.print(v + " ");
				augmentPath.add(v);
				u = parent[v];
				pathFlow = Math.min(pathFlow, rGraph[u][v]);
			}

			Collections.reverse(augmentPath);
			System.out.print("Augment Path = 0");
			for (Integer path:augmentPath ){
				System.out.print(" --> "+path);
			}
			System.out.println();
			// update residual capacities of the edges and reverse edges along the path
			for (v = target; v != source; v = parent[v]) {
				u = parent[v];
				rGraph[u][v] -= pathFlow;
				rGraph[v][u] += pathFlow;
			}


			System.out.println("Augmenting path of capacity : " + pathFlow); //Displaying Bottleneck capacity
			System.out.println("Therefore, maximum flow is now : "+maxFlow + " + " + pathFlow + " = " + (maxFlow+pathFlow));
			System.out.println("-------- Residual Graph --------");
			for (int[] dataRow : rGraph) {
				for (int index = 0; index< graph.length; index++) {
					System.out.print(dataRow[index] + " ");		// Display Matrix
				}
				System.out.println();
			}
			System.out.println();
			maxFlow += pathFlow;// Add path flow to overall flow
		}

		// Return the overall flow
		return maxFlow;
	}

}
