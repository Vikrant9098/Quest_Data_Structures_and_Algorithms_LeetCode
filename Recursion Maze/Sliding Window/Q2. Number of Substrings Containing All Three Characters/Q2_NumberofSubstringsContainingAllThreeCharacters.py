def numberOfSubstrings(s):                 # Function to count valid substrings

    map = {}                               # Dictionary to store character counts

    count = 0                             # Store total number of valid substrings

    start = end = 0                       # Two pointers for sliding window

    n = len(s)                            # Length of the string

    while end < n:                        # Move end pointer through the string

        map[s[end]] = map.get(s[end], 0) + 1  
                                           # Add current character to map

        while map.get('a', 0) > 0 and map.get('b', 0) > 0 and map.get('c', 0) > 0:
                                           # Check if window has at least one a, b, and c

            count += n - end               # Count all substrings starting here

            map[s[start]] -= 1             # Remove left character from window

            start += 1                     # Move start pointer forward

        end += 1                           # Move end pointer forward

    return count                           # Return total count
