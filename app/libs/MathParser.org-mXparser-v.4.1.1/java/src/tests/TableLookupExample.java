package tests;

/*
 * Imports
 */
import java.util.ArrayList;
import java.util.List;
import org.mariuszgromada.math.mxparser.*;

/*
 * Primitive table implementation
 * just for tests
 */
class Column {
	public String columnName;
	public List<Double> columnValues;
	public Column(String columnName) {
		this.columnName = columnName;
		columnValues = new ArrayList<Double>();
	}
	public void addValue(double value) {
		columnValues.add(value);
	}
}
class Table {
	public List<Column> columns;
	public Table() {
		columns = new ArrayList<Column>();
	}
	public void addColumn(String columnName) {
		columns.add(new Column(columnName));
	}
	public int getColumnIndex(String columnName) {
		for (int i = 0; i < columns.size(); i++)
			if ( columns.get(i).columnName.equals(columnName) )
				return i;
		return -1;
	}
	public Column getColumn(String columnName) {
		for (int i = 0; i < columns.size(); i++) {
			Column col = columns.get(i);
			if ( col.columnName.equals(columnName) )
				return col;
		}
		return null;
	}
	public void addValue(String columnName, double value) {
		Column col = getColumn(columnName);
		col.addValue(value);
	}
	public double getValue(String columnName, int valueIndex) {
		if (valueIndex < 0) return Double.NaN;
		Column col = getColumn(columnName);
		if (col != null)
			if ( valueIndex < col.columnValues.size() )
				return col.columnValues.get(valueIndex);
		return Double.NaN;
	}
	public double getValue(int columnIndex, int valueIndex) {
		if (valueIndex < 0) return Double.NaN;
		Column col = columns.get(columnIndex);
		if (col != null)
			if ( valueIndex < col.columnValues.size() )
				return col.columnValues.get(valueIndex);
		return Double.NaN;
	}
}

/*
 * Table lookup function extension
 * mXparser.FunctionExtension interface implementation
 */
class TableLookupFunction implements FunctionExtension {

	String columnName;
	Table lookupTable;
	int periodIndex;

	public TableLookupFunction(Table lookupTable, String columnName) {
		this.lookupTable = lookupTable;
		this.columnName = columnName;
	}

	public int getParametersNumber() {
		return 1;
	}

	public String getParameterName(int parameterIndex) {
		if (parameterIndex == 0) return "periodIndex";
		return "";
	}

	public void setParameterValue(int parameterIndex, double parameterValue) {
		if (parameterIndex == 0)
			periodIndex = (int)parameterValue;
	}

	public double calculate() {
		return lookupTable.getValue(columnName, periodIndex);
	}

	public FunctionExtension clone() {
		return null;
	}

	/*
	 * Creating list of user defined Functions
	 * representing columns names
	 *
	 * Functions with one parameter - index in the lookup table
	 */
	public static final Function[] getLookupFunctions(Table lookupTable) {
		int colsNum = lookupTable.columns.size();
		Function[] lookupFunctions = new Function[colsNum];
		for (int colIndex = 0; colIndex < colsNum; colIndex++) {
			String colName = lookupTable.columns.get(colIndex).columnName;
			lookupFunctions[colIndex] = new Function(colName, new TableLookupFunction(lookupTable, colName) );
		}
		return lookupFunctions;
	}
}

/*
 * Our example of
 * user defined function
 * based on table lookup
 */
public class TableLookupExample {

	public static void main(String[] args) {

		/*
		 * Creating instance of table
		 */
		Table finances = new Table();
		/*
		 * Adding rows
		 */
		finances.addColumn("CurrentAssets");
		finances.addColumn("CurrentLiabilities");
		/* 1st row in table (index = 0) */
		finances.addValue("CurrentAssets", 100);
		finances.addValue("CurrentLiabilities", 200);

		/* 2st row in table (index = 1) */
		finances.addValue("CurrentAssets", 200);
		finances.addValue("CurrentLiabilities", 300);

		/* 3rd row in table (index = 2) */
		finances.addValue("CurrentAssets", 300);
		finances.addValue("CurrentLiabilities", 400);

		/*
		 * Expression that is using this function
		 */
		Expression e = new Expression("CurrentAssets(1) / CurrentLiabilities(1)");
		e.addDefinitions( TableLookupFunction.getLookupFunctions(finances) );

		/*
		 * Value calculation
		 */
		mXparser.consolePrintln(e.getExpressionString() + " = " + e.calculate());
	}
}
