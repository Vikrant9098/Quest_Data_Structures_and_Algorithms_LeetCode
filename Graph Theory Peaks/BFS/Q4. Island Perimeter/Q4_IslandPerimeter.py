class Solution(object):
    def islandPerimeter(self, grid):

        # r = number of rows, c = number of columns
        # s = total perimeter
        r, c, s = len(grid), len(grid[0]), 0

        # Traverse each cell in the grid
        for i in range(r):
            for j in range(c):

                # If the current cell is land (1)
                if grid[i][j]:

                    # Each land cell contributes 4 sides initially
                    s += 4

                    # If the upper cell is also land, shared edge exists
                    # So subtract 2 from perimeter
                    if i and grid[i-1][j]:
                        s -= 2

                    # If the left cell is also land, shared edge exists
                    # So subtract 2 from perimeter
                    if j and grid[i][j-1]:
                        s -= 2

        # Return the total island perimeter
        return s