class Solution(object):
    def sumScores(self, s):
        """
        :type s: str
        :rtype: int
        """
        # Initialize the Z array with zeros
        n = len(s)
        z = [0] * n

        # l = left boundary, r = right boundary of current Z block
        l, r = 0, 0

        # First Z value is always the full length of the string
        z[0] = n

        # Traverse the string from index 1
        for i in range(1, n):

            # Case 1: i is outside the current Z block
            if i > r:
                l, r = i, i                 # Reset Z block

                # Expand the Z block while characters match
                while r < n and s[r - l] == s[r]:
                    r += 1

                z[i] = r - l               # Store Z value
                r -= 1                     # Move r back

            # Case 2: i is inside the current Z block
            else:
                k = i - l                  # Corresponding index inside Z block

                # If we can reuse previously computed Z value
                if z[k] < r - i + 1:
                    z[i] = z[k]

                # Otherwise, recompute Z value
                else:
                    l = i                  # Reset left boundary

                    # Expand the Z block
                    while r < n and s[r - l] == s[r]:
                        r += 1

                    z[i] = r - l           # Store Z value
                    r -= 1                 # Move r back

        # Return sum of all Z values (final score)
        return sum(z)
