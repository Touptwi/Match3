package Grid;

import Grid.Jewel.Jewel;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GridModel {

    private final int rows, columns;
    private ArrayList<ArrayList<Tile>> gridTable;

	private Tile selectedTile;
    
    public enum Direction {NORTH, EAST, SOUTH, WEST}
    public enum Type {RED_JEWEL, GREEN_JEWEL, BLUE_JEWEL, PURPLE_JEWEL, YELLOW_JEWEL}
    
    public GridModel(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.gridTable = new ArrayList<>();
        createGridTable();
    }

    // Cr�e une grille r�alisable
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
	
	private Type getPossibleColor(Tile tile) {
		
		ArrayList<Type> possibleColors = new ArrayList<Type>();
		possibleColors.add(Type.BLUE_JEWEL);
		possibleColors.add(Type.RED_JEWEL);
		possibleColors.add(Type.GREEN_JEWEL);
		possibleColors.add(Type.YELLOW_JEWEL);
		possibleColors.add(Type.PURPLE_JEWEL);
		
		for(Direction d : Direction.values()) {
			
			System.out.print("Direction " + d.name() + " : ");
			
			Tile neighbor = this.getNeighbor(tile, d);
			if (neighbor != null && neighbor.getType() != null) {
				Type neighborColor = neighbor.getType();
				
				System.out.println("voisin de couleur " + neighborColor.name());
				
				Tile neighbor2 = this.getNeighbor(neighbor, d);
				if(neighbor2 != null && neighbor2.getType() != null) {
					
					System.out.println("2e voisin de couleur " + neighbor2.getType().name());
					if(neighbor2.getType() == neighborColor)
						possibleColors.remove(neighborColor);
						System.out.println("les 2 couleurs sont les memes!");
				}
			}
			
		}
		
		Type returnColor = possibleColors.get(new Random().nextInt(possibleColors.size()));
		return returnColor;
	}

    public Point getCoords(Tile tile) {
        for (ArrayList<Tile> column : this.gridTable) {
            for (Tile Tile : column) {
                if (Tile == tile) {
                    return new Point(this.gridTable.indexOf(column), column.indexOf(Tile));
                }
            }
        }
        System.out.print("Grid.getCoords() error: Tile not find");
        return new Point(-1, -1);
    }

    public Tile getTile(Point Coords) {
        if(Coords.x>=0 && Coords.y>=0 && Coords.x<this.columns && Coords.y<this.rows) {
            return this.gridTable.get(Coords.x).get(Coords.y);
        }
        System.out.print("Grid.getTile() error: Out of grid");
        return null;
    }

    public ArrayList<Tile> getNeighbors(Tile tile) {
        ArrayList<Tile> neighbors = new ArrayList<>();
        Point tileCoords = this.getCoords(tile);
        neighbors.add(this.getTile(new Point(tileCoords.x, tileCoords.y+1)));
        neighbors.add(this.getTile(new Point(tileCoords.x+1, tileCoords.y)));
        neighbors.add(this.getTile(new Point(tileCoords.x, tileCoords.y-1)));
        neighbors.add(this.getTile(new Point(tileCoords.x-1, tileCoords.y)));
        return neighbors;
    }
    
    public Tile getNeighbor(Tile tile, Direction d) {
    	
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

    public Tile getSelectedTile() {return this.selectedTile;}
    public void setSelectedTile(Tile Tile) {this.selectedTile = Tile;}

    public void switchTiles(Tile Tile1, Tile Tile2) {
        if(Tile1!=null && Tile2!=null) {
            Type temp = Tile1.getType();
            Tile1.setType(Tile2.getType());
            Tile2.setType(temp);
        }
    }

    public void moveTileTo(Tile Tile, Direction direction) {
        if(direction.equals(Direction.NORTH)){
            switchTiles(Tile, getNeighbors(Tile).get(0));
        } else if (direction.equals(Direction.EAST)) {
            switchTiles(Tile, getNeighbors(Tile).get(1));
        } else if (direction.equals(Direction.SOUTH)) {
            switchTiles(Tile, getNeighbors(Tile).get(2));
        } else if (direction.equals(Direction.WEST)) {
            switchTiles(Tile, getNeighbors(Tile).get(3));
        }
    }
}
