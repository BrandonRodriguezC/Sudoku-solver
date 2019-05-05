import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

public class Main {
	static int matrix[][] = new int[9][9];
	static int solved[][] = { { 5, 7, 9, 6, 8, 4, 1, 2, 3 }, { 4, 3, 6, 1, 2, 9, 7, 8, 5 },
			{ 1, 8, 2, 7, 5, 3, 4, 9, 6 }, { 9, 4, 1, 5, 3, 8, 6, 7, 2 }, { 7, 6, 3, 2, 9, 1, 8, 5, 4 },
			{ 2, 5, 8, 4, 7, 6, 9, 3, 1 }, { 6, 9, 5, 8, 4, 2, 3, 1, 7 }, { 8, 1, 7, 3, 6, 5, 2, 4, 9 },
			{ 3, 2, 4, 9, 1, 7, 5, 6, 8 }, };
	static int count;
	static ArrayList<Node> solutionList;

	public static void main(String[] args) throws IOException {
		pc2Read();
	}

	public static void process(int matrix[][]) {
		count = 0;
		solutionList = new ArrayList<>();

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				if (matrix[i][j] == 0) {
					Node cell = new Node(sugestions(i, j), i, j);
					solutionList.add(cell);
				}
			}
		}

		Collections.sort(solutionList);
//
//		for (Node n : solutionList) {
//			System.out.println("F: " + n.getF() + " C: " + n.getC() + " S: " + Arrays.toString(n.getSuggestions()));
//		}

		System.out.println();
		printMatrix();
		solve(solutionList.get(count));
		printMatrix();
	}

	public static boolean solved() {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				int value = matrix[i][j];
				if (value == 0) {
					return false;
				}
				if(checkInCol(i, j, value) && checkInRow(i, j, value) && checkInQuadrant(i, j, value)) {
					return false;
				}
			}
		}
		//System.out.println("solved true");
		return true;
	}

	public static boolean check() {
		for (int f = 0; f < matrix.length; f++) {
			for (int c = 0; c < matrix.length; c++) {
				if (matrix[f][c] != solved[f][c]) {
					return false;
				}
			}
		}
		System.out.println("checked true");
		return true;
	}

	public static boolean solve(Node a) {
		
		String sug[] = a.getSuggestions();
		int f = a.getF();
		int c = a.getC();

		for (int i = 0; i < sug.length; i++) {
			int value = Integer.parseInt(sug[i]);
			if (!isInRow(f, value) && !isInCol(c, value) && !isInQuadrant(f, c, value)) {
				matrix[f][c] = value;
				if (solved()) {
					return true;
				}
				count++;
				if (solve(solutionList.get(count))) {
					return true;
				} else {
					matrix[f][c] = 0;
					count--;
				}
			}
		}
		return false;
	}

	public static String[] sugestions(int f, int c) {
		String sugestion = "";
		for (int s = 1; s < 10; s++) {
			if (!isInRow(f, s) && !isInCol(c, s) && !isInQuadrant(f, c, s)) {
				sugestion += s;
			}
		}
		return sugestion.split("");
	}

	public static boolean isInQuadrant(int f, int c, int value) {
		int qr = 0, qc = 0;
		if (f >= 0 && f < 3) {
			qr = 0;
		} else if (f >= 3 && f < 6) {
			qr = 3;
		} else if (f >= 6 && f < 9) {
			qr = 6;
		}

		if (c >= 0 && c < 3) {
			qc = 0;
		} else if (c >= 3 && c < 6) {
			qc = 3;
		} else if (c >= 6 && c < 9) {
			qc = 6;
		}

		for (int f1 = qr; f1 < qr + 3; f1++) {
			for (int c1 = qc; c1 < qc + 3; c1++) {
				if (matrix[f1][c1] == value) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean checkInQuadrant(int f, int c, int value) {
		int qr = 0, qc = 0;
		if (f >= 0 && f < 3) {
			qr = 0;
		} else if (f >= 3 && f < 6) {
			qr = 3;
		} else if (f >= 6 && f < 9) {
			qr = 6;
		}

		if (c >= 0 && c < 3) {
			qc = 0;
		} else if (c >= 3 && c < 6) {
			qc = 3;
		} else if (c >= 6 && c < 9) {
			qc = 6;
		}

		for (int f1 = qr; f1 < qr + 3; f1++) {
			for (int c1 = qc; c1 < qc + 3; c1++) {
				if (matrix[f1][c1] == value && f1!=f && c1!=c) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isInCol(int c, int value) {
		for (int f = 0; f < matrix.length; f++) {
			if (matrix[f][c] == value) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean checkInCol(int f1,int c, int value) {
		
		for (int f = 0; f < matrix.length; f++) {
			if (matrix[f][c] == value && f!=f1) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isInRow(int f, int value) {
		for (int c = 0; c < matrix.length; c++) {
			if (matrix[f][c] == value) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean checkInRow(int f, int c1, int value) {
		for (int c = 0; c < matrix.length; c++) {
			if (matrix[f][c] == value && c!=c1) {
				return true;
			}
		}
		return false;
	}

	public static void printMatrix() {
		System.out.println();
		System.out.println("---------------------------MATRIX---------------------------");
		System.out.println();
		for (int i = 0; i < matrix.length; i++) {
			if (i % 3 == 0 && i != 0) {
				System.out.println("----------------------");
			}
			for (int j = 0; j < matrix.length; j++) {
				if (j % 3 == 0 && j != 0) {
					System.out.print("| ");
				}
				System.out.print(matrix[i][j] + " ");
			}

			System.out.println();
		}
	}
	
	public static void printSolved() {
		System.out.println();
		System.out.println("---------------------------ORIGINAL AND SOLVED---------------------------");
		System.out.println();
		for (int i = 0; i < solved.length; i++) {
			if (i % 3 == 0 && i != 0) {
				System.out.println("----------------------" + "\t" + "----------------------");
			}
			for (int j = 0; j < solved.length; j++) {
				if (j % 3 == 0 && j != 0) {
					System.out.print("| ");
				}
				System.out.print(matrix[i][j] + " ");

			}
			System.out.print("\t");

			for (int j = 0; j < solved.length; j++) {
				if (j % 3 == 0 && j != 0) {
					System.out.print("| ");
				}
				System.out.print(solved[i][j] + " ");

			}

			System.out.println();
		}
	}

	public static void pc2Read() throws IOException {
		String line, vec[] = null;

		File archivo = new File("test2.txt");
		FileReader fr = new FileReader(archivo);
		BufferedReader buffer = new BufferedReader(fr);

		try {

			for (int i = 0; i < matrix.length; i++) {
				line = buffer.readLine();
				vec = line.split("-");
				for (int j = 0; j < matrix.length; j++) {
					matrix[i][j] = Integer.parseInt(vec[j]);
				}
			}
			process(matrix);
		} catch (NullPointerException e) {
		}
		buffer.close();
	}
}