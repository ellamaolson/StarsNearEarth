
import java.util.*;

public class main {
	 public static void main(String[] args) throws Exception
	   {
	      String outString;
	      int k, arraySize, row, col;
	      double maxX, minX, maxY, minY, maxZ, minZ,
	      xRange, yRange, zRange; 
	      final int NUM_COLS = 70;
	      final int NUM_ROWS = 35;
	   
	      SNE_Reader  starInput 
	         = new SNE_Reader("nearest_100_stars.txt");

	      if (starInput.readError())
	      {
	         System.out.println("couldn't open " + starInput.getFileName()
	            + " for input.");
	         return;
	      }

	      System.out.println(starInput.getFileName());
	      System.out.println(starInput.getNumStars());

	      arraySize = starInput.getNumStars();
	      SNE_Interpreter[] starArray = new SNE_Interpreter[arraySize];
	      for (k = 0; k < arraySize; k++)
	         starArray[k] =  new SNE_Interpreter( starInput.getStar(k) );

	      for ( k = 0; k < arraySize; k++ )
	         System.out.println( starArray[k].getNameCommon() + " " 
	         + starArray[k].coordToString());
	      
	      System.out.println("\nDisplaying 2D views of Stars Near Earth\n");
	      
	      maxX = minX = maxY = minY = maxZ = minZ = 0;
	      for (k = 0; k < arraySize; k++)
	      {
	         if( maxX < starArray[k].getX() )
	            maxX = starArray[k].getX();
	         if( minX > starArray[k].getX() )
	            minX = starArray[k].getX();
	         
	         if( maxY < starArray[k].getY() )
	            maxY = starArray[k].getY();
	         if( minY > starArray[k].getY() )
	            minY = starArray[k].getY();
	         
	         if( maxZ < starArray[k].getZ() )
	            maxZ = starArray[k].getZ();
	         if( minZ > starArray[k].getZ() )
	            minZ = starArray[k].getZ();
	      }
	      
	      xRange = maxX - minX;
	      yRange = maxY - minY;
	      zRange = maxZ - minZ;

	      SparseMat<Character> starMap 
	         = new SparseMat<Character>(NUM_ROWS, NUM_COLS, ' ');

	      //XY Plane -----------------------
	      System.out.println("XY plane of the 100 nearest stars--------------\n");
	      for (k = 9; k < arraySize; k++)
	      {
	         col = (int) (((starArray[k].getX() - minX)/xRange) * NUM_COLS);
	         row = (int) (((starArray[k].getY() - minY)/yRange) * NUM_ROWS);
	         starMap.set(row, col, '*');
	      }
	      for (k = 0; k < 9; k++)
	      {
	         col = (int) (((starArray[k].getX() - minX)/xRange) * NUM_COLS);
	         row = (int) (((starArray[k].getY() - minY)/yRange) * NUM_ROWS);
	         starMap.set(row, col, (char) (starArray[k].getRank() + '0') );
	      }
	     
	      row = NUM_ROWS/2;
	      col = NUM_COLS/2;
	      starMap.set(row, col, 'S');

	      for (row = NUM_ROWS - 1; row >= 0; row--)
	      {
	         outString = "";
	         for(col = NUM_COLS - 1; col >= 0; col--)
	         {
	            outString += starMap.get(row, col);
	         }
	         System.out.println( outString );
	      }
	      
	      starMap.clear();
	      
	    //XZ Plane -------------------
	      System.out.println("XZ plane of the 100 nearest stars--------------\n");
	      for (k = 9; k < arraySize; k++)
	      {
	         col = (int) (((starArray[k].getX() - minX)/xRange) * NUM_COLS);
	         row = (int) (((starArray[k].getZ() - minZ)/zRange) * NUM_ROWS);
	         starMap.set(row, col, '*');
	      }
	      for (k = 0; k < 9; k++)
	      {
	         col = (int) (((starArray[k].getX() - minX)/xRange) * NUM_COLS);
	         row = (int) (((starArray[k].getZ() - minZ)/zRange) * NUM_ROWS);
	         starMap.set(row, col, (char) (starArray[k].getRank() + '0') );
	      }
	     
	      row = NUM_ROWS/2;
	      col = NUM_COLS/2;
	      starMap.set(row, col, 'S');

	      for (row = NUM_ROWS - 1; row >= 0; row--)
	      {
	         outString = "";
	         for(col = NUM_COLS - 1; col >= 0; col--)
	         {
	            outString += starMap.get(row, col);
	         }
	         System.out.println( outString );
	      }
	      
	      starMap.clear();
	      
	    //YZ Plane ---------------------
	      System.out.println("YZ plane of the 100 nearest stars--------------\n");
	      for (k = 9; k < arraySize; k++)
	      {
	         col = (int) (((starArray[k].getY() - minY)/yRange) * NUM_COLS);
	         row = (int) (((starArray[k].getZ() - minZ)/zRange) * NUM_ROWS);
	         starMap.set(row, col, '*');
	      }
	      for (k = 0; k < 9; k++)
	      {
	         col = (int) (((starArray[k].getY() - minY)/yRange) * NUM_COLS);
	         row = (int) (((starArray[k].getZ() - minZ)/zRange) * NUM_ROWS);
	         starMap.set(row, col, (char) (starArray[k].getRank() + '0') );
	      }
	     
	      row = NUM_ROWS/2;
	      col = NUM_COLS/2;
	      starMap.set(row, col, 'S');

	      for (row = NUM_ROWS - 1; row >= 0; row--)
	      {
	         outString = "";
	         for(col = NUM_COLS - 1; col >= 0; col--)
	         {
	            outString += starMap.get(row, col);
	         }
	         System.out.println( outString );
	      }
	   }
	}

	class SparseMat<E> implements Cloneable
	{
	   protected class MatNode implements Cloneable
	   {
	      public int col;
	      public E data;

	      MatNode()
	      {
	         col = 0;
	         data = null;
	      }

	      MatNode(int cl, E dt)
	      {
	         col = cl;
	         data = dt;
	      }

	      public Object clone() throws CloneNotSupportedException
	      {
	         MatNode newObject = (MatNode)super.clone();
	         return (Object) newObject;
	      }
	   }

	   static public final int MIN_SIZE = 1;
	   protected int rowSize, colSize;
	   protected E defaultVal;
	   protected SNE_Array_List < SNE_Linked_List< MatNode > > rows;
	   
	   //-------------------------------------------------------------------------- Need FHSNE_Array_List FHSNE_Linked_List 
	   //-------------------------------------------------------------------------- SNE_Reader StarNearEarth

	   public int getRowSize() { return rowSize; }
	   public int getColSize() { return colSize; }

	   public SparseMat( int numRows, int numCols, E defaultVal) 
	   {
	      if ( numRows < MIN_SIZE || numCols < MIN_SIZE || defaultVal == null)
	         throw new IllegalArgumentException();

	      rowSize = numRows;
	      colSize = numCols;
	      allocateEmptyMatrix();
	      this.defaultVal = defaultVal;
	   }

	   protected void allocateEmptyMatrix()
	   {
	      int row;
	      rows = new SNE_Array_List < SNE_Linked_List< MatNode > >();
	      for (row = 0; row < rowSize; row++)
	         rows.add( new SNE_Linked_List< MatNode >());   // add a blank row
	   }

	   public void clear()
	   { 
	      int row;

	      for (row = 0; row < rowSize; row++)
	         rows.get(row).clear();
	   }
	  
	   public Object clone() throws CloneNotSupportedException
	   {
	      int row;
	      ListIterator<MatNode> iter;
	      SNE_Linked_List < MatNode > newRow;

	      SparseMat<E> newObject = (SparseMat<E>)super.clone();

	      newObject.allocateEmptyMatrix();

	      for (row = 0; row < rowSize; row++)
	      {
	         newRow = newObject.rows.get(row);
	         for (
	             iter =  (ListIterator<MatNode>)rows.get(row).listIterator() ; 
	             iter.hasNext() ; 
	             )
	         {
	            newRow.add( (MatNode) iter.next().clone() );
	         }
	      }

	      return newObject;
	   }

	   protected boolean valid(int row, int col)
	   {
	      if (row >= 0 && row < rowSize && col >= 0 && col < colSize)
	         return true;
	      return false;
	   }

	   public boolean set(int row, int col, E x)
	   {
	      if (!valid(row, col))
	         return false;

	      ListIterator<MatNode> iter;

	      for (
	          iter =  (ListIterator<MatNode>)rows.get(row).listIterator() ; 
	          iter.hasNext() ; 
	          )
	      {
	         if ( iter.next().col == col )
	         {
	            if ( x.equals(defaultVal) )
	               iter.remove();
	            else
	               iter.previous().data = x;
	            return true;
	         }
	      }

	      if ( !x.equals(defaultVal) )
	         rows.get(row).add( new MatNode(col, x) );
	      return true;
	   }

	   public E get(int row, int col)
	   {
	      if (!valid(row, col))
	         throw new IndexOutOfBoundsException();

	      ListIterator<MatNode> iter;

	      for (
	          iter =  (ListIterator<MatNode>)rows.get(row).listIterator() ; 
	          iter.hasNext() ; 
	          )
	      {
	         if ( iter.next().col == col )
	            return iter.previous().data;
	      }
	      return defaultVal;
	   }

	   public void showSubSquare(int start, int size)
	   {
	      int row, col;

	      if (start < 0 || size < 0 
	            || start + size > rowSize
	            || start + size > colSize )
	         return;

	      for (row = start; row < start + size; row++)
	      {
	         for (col = start; col < start + size; col++)
	            System.out.print( String.format("%5.1f", 
	                  (Double)get(row, col)) + " " );
	         System.out.println();
	      }
	      System.out.println();
	   }
}
