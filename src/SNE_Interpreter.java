public class SNE_Interpreter extends SNE
{
	private double x, y, z;
	   
	   // constructors
	   public SNE_Interpreter() 
	   {
	      super();
	   }
	   // construct an SNE_Interpreter from a StartNearEarth object
	   public SNE_Interpreter( SNE sne )
	   {
	      setRank(sne.getRank()); 
	      setNameCns(sne.getNameCns());
	      setNumComponents(sne.getNumComponents());
	      setNameLhs(sne.getNameLhs());
	      setRAsc(sne.getRAsc());
	      setDec(sne.getDec());
	      setPropMotionMag(sne.getPropMotionMag());
	      setPropMotionDir(sne.getPropMotionDir());
	      setParallaxMean(sne.getParallaxMean());
	      setParallaxVariance(sne.getParallaxVariance());
	      SetBWhiteDwarfFlag(sne.getWhiteDwarfFlag());
	      setSpectralType(sne.getSpectralType());
	      setMagApparent(sne.getMagApparent());
	      setMagAbsolute(sne.getMagAbsolute());
	      setMass(sne.getMass());
	      setNotes(sne.getNotes());
	      setNameCommon(sne.getNameCommon()); 
	      calcCartCoords();
	   }
	   
	   // accessors
	   double getX() { return x; }
	   double getY() { return y; }
	   double getZ() { return z; }   
	   
	   public void calcCartCoords() 
	   { 
	      double lightYears, rAsc, decl;
	      lightYears = 3.262/getParallaxMean();
	      rAsc = getRAsc() * (Math.PI/180);
	      decl = getDec() * (Math.PI/180);

	      x = lightYears * decl * Math.cos(rAsc);
	      y = lightYears * decl * Math.sin(rAsc);
	      z = lightYears * decl;  
	   }

	   public String coordToString()
	   { 
	      String coordinates = ("(" + String.format( "%.3f", x ) + ", " 
	            + String.format( "%.3f", y ) + ", "
	            + String.format( "%.3f", z ) + ")\n"); 
	      return coordinates;
	   }
}
