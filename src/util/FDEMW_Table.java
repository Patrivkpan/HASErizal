package util;

import java.util.Vector;

public class FDEMW_Table{
	private static FDEMW_Table instance;
	private Vector<Vector<String>> table = new Vector<Vector<String>>();

	private FDEMW_Table(){}

	public static FDEMW_Table getInstance(){
		if(FDEMW_Table.instance==null) {
			FDEMW_Table.instance = new FDEMW_Table();
		}

		return instance;
	}

	public Vector<Vector<String>> getTable(){
		return table;
	}

	public void printTable(){
		int cycles = Clock.getInstance().getCycle();
		this.putStalls();
		System.out.print("INSTRUCTIONS   | ");
		for (int i=0; i<cycles-1 ; i++) {
			if(i<9) System.out.print(i+1+" | ");
			else System.out.print(i+1+"| ");
		}
		System.out.println("");
		for (int i=0; i<table.size() ; i++) {
			Vector<String> tableRow = table.get(i);
			for (int j=0; j<tableRow.size()-1; j++ ) {
				System.out.print(tableRow.get(j));
				if( (tableRow.get(j).length() < 4 && j == 0) || (tableRow.get(j).length()==1 && j==2 )  ) System.out.print("  | ");
				else System.out.print(" | ");

			}
			System.out.println("");
		}

		System.out.println("");
	}

	private void putStalls(){
		for(int i=0;i<table.size(); i++){
			Vector<String> tableRow = table.get(i);
			for (int j= tableRow.indexOf("F"); j<tableRow.lastIndexOf("W") ; j++ ) {
				if(tableRow.get(j)==" ") tableRow.set(j,"S");
			}
		}
	}

	public void addEmptyColumn(){
		for (int i=0; i<table.size() ; i++) {
			table.get(i).add(" ");
		}
	}

}