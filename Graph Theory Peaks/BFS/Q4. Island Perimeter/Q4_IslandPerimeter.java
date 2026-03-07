public class Q4_IslandPerimeter {

    public int islandPerimeter(int[][] grid) {

        // If grid is null, no island exists
        if (grid == null) return 0;

        // Traverse the grid to find the first land cell
        for (int i = 0 ; i < grid.length ; i++){
            for (int j = 0 ; j < grid[0].length ; j++){

                // When we find the first land cell, start DFS to calculate perimeter
                if (grid[i][j] == 1) {
                    return getPerimeter(grid,i,j);
                }
            }
        }

        // If no land found
        return 0;
    }
    
    public int getPerimeter(int[][] grid, int i, int j){

        // If we go outside the grid, it contributes 1 to perimeter
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return 1;
        }

        // If we reach water, it means island edge -> contributes 1
        if (grid[i][j] == 0) {
            return 1;
        }

        // If the cell is already visited, do not count again
        if (grid[i][j] == -1) return 0;
        
        int count = 0;

        // Mark current land cell as visited
        grid[i][j] = -1;
        
        // Explore top neighbor
        count += getPerimeter(grid, i-1, j);

        // Explore left neighbor
        count += getPerimeter(grid, i, j-1);

        // Explore right neighbor
        count += getPerimeter(grid, i, j+1);

        // Explore bottom neighbor
        count += getPerimeter(grid, i+1, j);
        
        // Return total perimeter from all directions
        return count;
    }
}