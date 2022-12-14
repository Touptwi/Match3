package grid;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class GridModel {

    private final int rows, columns;

    private Grid controller;

    private ArrayList<ArrayList<Tile>> gridTable;
	private Dimension jewelSize;

	private Tile selectedTile;
	private List<BufferedImage> jewelImages = new ArrayList<>();

	private boolean needCheckMatch3;
	private boolean needCheckFlyingTiles;
    
    public enum Direction {NORTH, EAST, SOUTH, WEST}
    public enum Type {RED_JEWEL, GREEN_JEWEL, BLUE_JEWEL, PURPLE_JEWEL, YELLOW_JEWEL}
    
    public GridModel(int rows, int columns, Grid controller) {
        this.rows = rows;
        this.columns = columns;
        this.controller = controller;

        this.gridTable = new ArrayList<>();
		this.jewelSize = new Dimension(75,75);
		loadImages();
        createGridTable();
    }

    /** fonction loadImage
     *  Charge les images dans jewelImages.
     */
	private void loadImages() {
		for(Type type : Type.values()) {
			try {
//				String parentPath = new File(System.getProperty("user.dir")).getParent();
//				URL url = new File(parentPath + "\\Images\\Jewels\\" + type.name() + ".png").toURI().toURL();

				this.jewelImages.add(ImageIO.read(ClassLoader.getSystemResource("Images/Jewels/" + type.name() + ".png")));
			} catch (IOException e) {System.out.print("Can't load image of "+type.name());}

		}
	}

    // Initialise une grille realisable, c'est a dire ou au moins un coup est jouable
    private void createGridTable() {
    	
    	// On initialise la grille a null
        for (int i=0; i<this.columns; i++) {
            this.gridTable.add(new ArrayList<>(this.rows));
            for (int j=0; j<this.rows; j++)
            	this.gridTable.get(i).add(new Jewel(this));
        }
    	
    	//D'abord, on initialise 3 tiles aligables en 1 coup
    	create3FirstTiles();
    	
    	//Puis on genere le reste en faisant gaffe de pas generer 3 tuiles alignees
    	for(int x=0; x<this.columns; x++) {
    		for(int y=0; y<this.rows; y++) {
    			if(this.getTile(new Point(x, y)).getType() == null) {
    				
    				Type possibleColor = getPossibleColor(this.getTile(new Point(x,y)));
    				this.gridTable.get(x).set(y, new Jewel(this, possibleColor));
    			}
    		}
    	}
    	
    	System.out.println("ai fini de crreer la grille !");
    	

    }

    /** fonction crate3FisrtTiles
     *  Initialise 3 tiles, dans une configuration ou elles sont alignables en 1 coup.
     *  Sert pour l'initialisation de la grille.
     */
	private void create3FirstTiles() {
		
		boolean isFirstMatchVertical = new Random().nextBoolean();
    	Point firstTile;
    	
    	//Type firstColor = Type.values()[new Random().nextInt(5)];
    	Type firstColor = Type.BLUE_JEWEL;
    	
    	if (isFirstMatchVertical) {
    		firstTile = new Point(new Random().nextInt(columns), 
    							  new Random().nextInt(rows-3));
    		this.gridTable.get(firstTile.x).set(firstTile.y, new Jewel(this, firstColor));
    		this.gridTable.get(firstTile.x).set(firstTile.y+2, new Jewel(this, firstColor));
    		
    		if (firstTile.x == 0)
    			this.gridTable.get(firstTile.x+1).set(firstTile.y+1, new Jewel(this, firstColor));
    		else
    			this.gridTable.get(firstTile.x-1).set(firstTile.y+1, new Jewel(this, firstColor));
    		
    		
    	} else { 
    		firstTile = new Point(new Random().nextInt(columns-3), 
					              new Random().nextInt(rows));
    		
    		this.gridTable.get(firstTile.x).set(firstTile.y, new Jewel(this, firstColor));
    		this.gridTable.get(firstTile.x+2).set(firstTile.y, new Jewel(this, firstColor));
    		
    		if (firstTile.y == 0)
    			this.gridTable.get(firstTile.x+1).set(firstTile.y+1, new Jewel(this, firstColor));
    		else
    			this.gridTable.get(firstTile.x+1).set(firstTile.y-1, new Jewel(this, firstColor));
    	
    	}
	}
	
	/** fonction getPossibleColor
	 *  Donne les couleurs qu'une tuile peut prendre sans qu'elle ne genere 3 tuiles alignees.
	 *  A noter qu'une tile peut toujours prendre au moins une couleur, cette fonction ne renvoie donc jamais null.
	 *  Sert pour l'initialisation de la grille.
	 *
	 * @param tile : la tile a tester
	 * @return une couleur que la tile peut prendre.
	 */
	private Type getPossibleColor(Tile tile) {
		
		ArrayList<Type> possibleColors = new ArrayList<Type>();
		possibleColors.add(Type.BLUE_JEWEL);
		possibleColors.add(Type.RED_JEWEL);
		possibleColors.add(Type.GREEN_JEWEL);
		possibleColors.add(Type.YELLOW_JEWEL);
		possibleColors.add(Type.PURPLE_JEWEL);
		
		for(Direction d : Direction.values()) {
			
//			System.out.print("Direction " + d.name() + " : ");
			
			Tile neighbor = this.getNeighbor(tile, d);
			if (neighbor != null && neighbor.getType() != null) {
				Type neighborColor = neighbor.getType();
				
//				System.out.println("voisin de couleur " + neighborColor.name());
				
				Tile neighbor2 = this.getNeighbor(neighbor, d);
				if(neighbor2 != null && neighbor2.getType() != null) {
					
//					System.out.println("2e voisin de couleur " + neighbor2.getType().name());
					if(neighbor2.getType() == neighborColor)
						possibleColors.remove(neighborColor);
//					System.out.println("les 2 couleurs sont les memes!");
				}
			}
			
		}
		
		Type returnColor = possibleColors.get(new Random().nextInt(possibleColors.size()));
		return returnColor;
	}

	//Get the Tile's coord or (-1, -1) if not found
    public Point getCoords(Tile tile) {
        for (ArrayList<Tile> column : this.gridTable) {
            for (Tile Tile : column) {
                if (Tile == tile) {
                    return new Point(this.gridTable.indexOf(column), column.indexOf(Tile));
                }
            }
        }
        System.out.print("GridModel.getCoords() error: Tile not find");
        return new Point(-1, -1);
    }

	//Get the Tile at given coords or null if not found
    public Tile getTile(Point Coords) {
        if(Coords.x>=0 && Coords.y>=0 && Coords.x<this.columns && Coords.y<this.rows) {
            return this.gridTable.get(Coords.x).get(Coords.y);
        }
        System.out.println("GridModel.getTile() error: Out of grid "+Coords);
        return null;
    }

	//Return the List of Tile's Neighbors
    public ArrayList<Tile> getNeighbors(Tile tile) {
        ArrayList<Tile> neighbors = new ArrayList<>();
        Point tileCoords = this.getCoords(tile);
        neighbors.add(this.getTile(new Point(tileCoords.x, tileCoords.y-1)));
        neighbors.add(this.getTile(new Point(tileCoords.x+1, tileCoords.y)));
        neighbors.add(this.getTile(new Point(tileCoords.x, tileCoords.y+1)));
        neighbors.add(this.getTile(new Point(tileCoords.x-1, tileCoords.y)));
        return neighbors;
    }

	//Return the Tile's neighbor in the given direction
    public Tile getNeighbor(Tile tile, Direction d) {

		if(tile==null)
			return null;

    	Point coord = tile.getCoords();
    	switch(d) {
    	case NORTH :
    		if (coord.y != 0)
    			return this.getTile(new Point(coord.x, coord.y-1)); 
    		else
    			return null;
    		
		case EAST :
    		if (coord.x != this.columns-1)
    			return this.getTile(new Point(coord.x+1, coord.y)); 
    		else
    			return null;
    		
    		
		case SOUTH:
			if (coord.y != this.rows-1)
    			return this.getTile(new Point(coord.x, coord.y+1)); 
    		else
    			return null;
    		
    		
    	case WEST:
    		if (coord.x != 0)
    			return this.getTile(new Point(coord.x-1, coord.y)); 
    		else
    			return null;
    	
		default:
			return null;
    	}
    	
    }
    
    public Tile getNeighbor(Point coord, Direction d) {
    	return getNeighbor(this.getTile(coord), d);
    }
    
   
    public ArrayList<ArrayList<Tile>> getGridTable() {return this.gridTable;}
    public int getColumns() {return this.columns;}
    public int getRows() {return this.rows;}
	public Dimension getJewelSize() {return this.jewelSize;}

    public Tile getSelectedTile() {return this.selectedTile;}
    public void setSelectedTile(Tile Tile) {this.selectedTile = Tile;}
	public List<BufferedImage> getJewelImages() {return this.jewelImages;}

	//Switch Tile's type if they are neighbors
    public void switchTiles(Tile Tile1, Tile Tile2) {
        if(Tile1!=null && Tile2!=null && getNeighbors(Tile2).contains(Tile1)) {
            Type temp = Tile2.getType();
            Tile2.setType(Tile1.getType());
			Tile1.setType(temp);
        }
    }

	//Call switchTiles and create an animation in the given direction
    public void moveTileTo(Tile Tile, Direction direction) {
		Tile neighborTile = getNeighbor(Tile, direction);
		switchTiles(Tile, neighborTile);
		//Create animation
		this.controller.getView().movingTileAnimation(neighborTile, getCoords(Tile), getCoords(neighborTile));
    }

	//Make a checkMatch3on the whole grid
	private void checkMatch3ToAll() {
		for(ArrayList<Tile> column : this.gridTable) {
			for(Tile tile : column)
				if(tile!=null && tile.getType()!=null)
					checkMatch3To(tile);
		}
	}

	/*
	Check if there is a Match3 at the Tile given
	Set type of Tiles finds to null
	Increment the score
	Tell that model need to do a checkFlyingTiles
	*/
	public void checkMatch3To(Tile Tile) {
		Type matchColor = Tile.getType();
		boolean match3 = false;
		for (ArrayList<Tile> Match : getLongMatchOf(Tile)) {
			if(!Match.isEmpty()) {
				for (Tile tile : Match) {
					getTile(tile.getCoords()).setType(null);
				}
				
				getTile(Tile.getCoords()).setType(null);

				this.controller.getGame().incrementScore(Match.size());
				
				this.controller.getGame().notifyMatch(Match.size(), matchColor);

				match3 = true;
			}
		}

		if(match3) {
			this.controller.getView().playBreakingTilesSound();
			this.controller.getGame().incrementScore(1);
			this.needCheckFlyingTiles = true;
			//If there is already no more animations to call the update, it does it
			if (this.controller.getView().isTempTilesEmpty())
				goBackToModel();
		}
	}

	//Return a list of Neighbors in line of 2
	private ArrayList<ArrayList<Tile>> get2NeighborsInAllDirections(Tile Tile) {
		ArrayList<ArrayList<Tile>> possibleMatchs = new ArrayList<>();
		ArrayList<Tile> NorthMatch = new ArrayList<>();
		ArrayList<Tile> EastMatch = new ArrayList<>();
		ArrayList<Tile> SouthMatch = new ArrayList<>();
		ArrayList<Tile> WestMatch = new ArrayList<>();
		NorthMatch.add(getNeighbor(Tile, Direction.NORTH));
		EastMatch.add(getNeighbor(Tile, Direction.EAST));
		SouthMatch.add(getNeighbor(Tile, Direction.SOUTH));
		WestMatch.add(getNeighbor(Tile, Direction.WEST));
		for (int i = 1; i < 2; i++) {
			NorthMatch.add(getNeighbor(NorthMatch.get(NorthMatch.size() - 1), Direction.NORTH));
			EastMatch.add(getNeighbor(EastMatch.get(EastMatch.size() - 1), Direction.EAST));
			SouthMatch.add(getNeighbor(SouthMatch.get(SouthMatch.size() - 1), Direction.SOUTH));
			WestMatch.add(getNeighbor(WestMatch.get(WestMatch.size() - 1), Direction.WEST));
		}
		possibleMatchs.add(NorthMatch);
		possibleMatchs.add(EastMatch);
		possibleMatchs.add(SouthMatch);
		possibleMatchs.add(WestMatch);
		return possibleMatchs;
	}

	//Return a list of Neighbors in line where there are match3
	private ArrayList<ArrayList<Tile>> getMatchsOf(Tile Tile) {
		ArrayList<ArrayList<Tile>> possibleMatchs = get2NeighborsInAllDirections(Tile);
		for(int i=0 ; i< possibleMatchs.size(); i++) {
			//Check if the first tile is the same type (else it removes the line from the list)
			if(possibleMatchs.get(i).get(0)==null || possibleMatchs.get(i).get(0).getType()!=Tile.getType())
				possibleMatchs.set(i, new ArrayList<>());
			//Check deeper if there is a real match3 (else it removes the line from the list)
			else if(possibleMatchs.get(i).get(1)==null || possibleMatchs.get(i).get(1).getType()!=Tile.getType())
				if(!possibleMatchs.get((i+2)%4).isEmpty() && possibleMatchs.get((i+2)%4).get(0)!=null && possibleMatchs.get((i+2)%4).get(0).getType()==Tile.getType())
					possibleMatchs.get(i).remove(1);
				else
					possibleMatchs.set(i, new ArrayList<>());
		}
		return possibleMatchs;
	}

	//Return the list of Match3 but extended to match4, match5, etc
	private ArrayList<ArrayList<Tile>> getLongMatchOf(Tile Tile) {
		ArrayList<ArrayList<Tile>> Matchs = getMatchsOf(Tile);
		for(int i=0 ; i< Matchs.size(); i++) {
			Direction direction = Arrays.stream(Direction.values()).collect(Collectors.toList()).get(i);
			if(!Matchs.get(i).isEmpty()) {
				Tile nextNeighbor = getNeighbor(Matchs.get(i).get(Matchs.get(i).size()-1), direction);
				//Check deeper
				while (nextNeighbor!=null && nextNeighbor.getType() == Tile.getType()) {
					Matchs.get(i).add(getNeighbor(Matchs.get(i).get(Matchs.get(i).size()-1), direction));
					nextNeighbor = getNeighbor(Matchs.get(i).get(Matchs.get(i).size()-1), direction);
				}
			}
		}
		return Matchs;
	}

	//Check if there is any Tiles flying that need to fall (and make it)
	public void checkFlyingTiles() {
		boolean isFlyingTile = false;

		//Generate new Tiles at the Top
		for (ArrayList<Tile> column : this.gridTable) {
			if (column.get(0).getType() == null) {
				column.get(0).setType(Arrays.stream(Type.values()).collect(Collectors.toList()).get(new Random().nextInt(Type.values().length)));
			}
		}

		//Make Tiles falling
		for (int i = this.rows - 2; i >= 0; i--) {
			for (ArrayList<Tile> column : this.gridTable) {
				if (column.get(i).getType() != null && getNeighbor(column.get(i), Direction.SOUTH) != null && getNeighbor(column.get(i), Direction.SOUTH).getType() == null) {
					moveTileTo(column.get(i), Direction.SOUTH);
					isFlyingTile = true;
				}
			}
		}

		//Tell to model that Tiles still need to fall
		if (isFlyingTile)
			this.needCheckFlyingTiles = true;
		//Tell to model that he can/have to check for Match3
		else
			this.needCheckMatch3 = true;

		//If there is already no more animations to call the update, it does it
		if (this.controller.getView().isTempTilesEmpty())
			goBackToModel();
	}

	//Called at the end of the last animation
	//Usefull to avoid stackOverFlows in checkMatch3 and checkFlyingTiles
	public void goBackToModel() {
		if(needCheckFlyingTiles) {
			this.needCheckFlyingTiles = false;
			checkFlyingTiles();
		}
		else if(this.needCheckMatch3) {
			this.needCheckMatch3 = false;
			checkMatch3ToAll();
		}
	}

	public void destroyRow(int i) {
		for (ArrayList<Tile> column : gridTable)
			column.get(i).setType(null);
		
	}
	
	public void destroyColumn(int j) {
		ArrayList<Tile> column = gridTable.get(j);
		for (Tile tile : column)
			tile.setType(null);
	}
}
